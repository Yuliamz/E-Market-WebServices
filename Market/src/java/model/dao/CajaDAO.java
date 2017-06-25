package model.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import model.pojos.Detalles;
import model.pojos.Facturas;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Yuliamz
 * @web https://github.com/Yuliamz/
 * 
 */
public class CajaDAO {
   
    public Facturas genFactura(BigDecimal CarroID,BigDecimal CajaID){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        //ID DE COMPRA
        BigDecimal compra = (BigDecimal) s.createSQLQuery("select a.ID_COMPRA from ASIGNACION a where a.ID_CARRO=? order by a.ID_COMPRA desc")
                .setMaxResults(1)
                .setParameter(0, CarroID)
                .uniqueResult();        
        //VALOR DE LA COMPRA
        BigDecimal Total = (BigDecimal) s.createSQLQuery("select sum(d.CANTIDAD_PRODUCTO*p.PRECIO_PRODUCTO) from DETALLES d join PRODUCTOS p on d.ID_PRODUCTO=p.ID_PRODUCTO where d.ID_COMPRA=? group by d.ID_DETALLE")
                .setParameter(0, compra)
                .uniqueResult();        
        BigDecimal idFactura = ((BigDecimal) s.createSQLQuery("select f.id_factura from facturas f order by f.id_factura desc").setMaxResults(1).uniqueResult()).add(BigDecimal.ONE);
        
        //INSERCION EN LA TABLA FACTURAS
        Query insFacturas = s.createSQLQuery("insert into facturas values (?,?,?,SYSTIMESTAMP,?)")
                    .setParameter(0, idFactura)
                    .setParameter(1, compra)
                    .setParameter(2, CajaID)
                    .setParameter(3, Total);
            insFacturas.executeUpdate();
            
        // ACTUALIZACIÓN DE LA COMPRA INDICANDO LA FACTURA    
            Query updCompra = s.createSQLQuery("update compra set id_factura=? where id_compra=?")
                .setParameter(0, idFactura)
                .setParameter(1, compra);
                updCompra.executeUpdate();
        //LISTA DE DETALLES DE LA COMPRA       
        List<Detalles> list = s.createQuery("from Detalles d where d.compra=?").setParameter(0, compra).list();
        
        //ACTUALIZACIÓN DE LA CANTIDAD DE PRODUCTOS
        for (Detalles detalles : list) {
            //CANTIDAD DE PRODUCTOS ACTUAL, MENOS LA CANTIDAD DE PRODUCTOS EN EL DETALLE
            BigDecimal cantidadProductos = ((BigDecimal) s.createSQLQuery("select p.CANTIDAD_PRODUCTO from productos p where p.ID_PRODUCTO=?")
                    .setParameter(0, detalles.getProductos()))
                    .subtract(detalles.getCantidadProducto());
            //ACTUALIZACIÓN DE LA CANTIDAD DE PRODUCTOS RESTANTES
            Query upd = s.createSQLQuery("update Productos set cantidad_producto=? where id_producto=?")
                .setParameter(0, cantidadProductos)
                .setParameter(1, detalles.getProductos());
                upd.executeUpdate();
        }
        
        s.getTransaction().commit();
        s.close();        
        return new Facturas(idFactura, compra, CajaID, new Date(), Total, list);
    }
}