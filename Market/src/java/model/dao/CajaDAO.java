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
        
        BigDecimal compra = (BigDecimal) s.createSQLQuery("select a.ID_COMPRA from ASIGNACION a where a.ID_CARRO=? order by a.ID_COMPRA desc")
                .setMaxResults(1)
                .setParameter(0, CarroID)
                .uniqueResult();        
        BigDecimal Total = (BigDecimal) s.createSQLQuery("select sum(d.CANTIDAD_PRODUCTO*p.PRECIO_PRODUCTO) from DETALLES d join PRODUCTOS p on d.ID_PRODUCTO=p.ID_PRODUCTO where d.ID_COMPRA=? group by d.ID_DETALLE")
                .setParameter(0, compra)
                .uniqueResult();        
        BigDecimal idFactura = ((BigDecimal) s.createSQLQuery("select f.id_factura from facturas f order by f.id_factura desc").setMaxResults(1).uniqueResult()).add(BigDecimal.ONE);
        
        Query ins = s.createSQLQuery("insert into facturas values (?,?,?,SYSTIMESTAMP,?)")
                    .setParameter(0, idFactura)
                    .setParameter(1, compra)
                    .setParameter(2, CajaID)
                    .setParameter(3, Total);
            ins.executeUpdate();
            
            Query query = s.createSQLQuery("update compra set id_factura=? where id_compra=?")
                .setParameter(0, idFactura)
                .setParameter(1, compra);
                query.executeUpdate();
               
        List<Detalles> list = s.createQuery("from Detalles d where d.compra=?").setParameter(0, compra).list();
        
        for (Detalles detalles : list) {
            BigDecimal cantidadProductos = ((BigDecimal) s.createSQLQuery("select p.CANTIDAD_PRODUCTO from productos p where p.ID_PRODUCTO=?")
                    .setParameter(0, detalles.getProductos()))
                    .subtract(detalles.getCantidadProducto());
            Query upd = s.createSQLQuery("update Productos set cantidad_producto=? where id_producto=?")
                .setParameter(0, cantidadProductos)
                .setParameter(1, detalles.getProductos());
                query.executeUpdate();
        }
        s.getTransaction().commit();
        Facturas facturas = new Facturas(idFactura, compra, CajaID, new Date(), Total, list);
        s.close();
        return facturas;
    }
}