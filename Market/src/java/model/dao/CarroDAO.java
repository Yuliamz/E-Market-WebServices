package model.dao;

import java.math.BigDecimal;
import model.pojos.Detalles;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Yuliamz
 * @web https://github.com/Yuliamz/
 * DAO for shopping Cart
 */

//TODO quitar idCompra y poner la consula para tomar el ID  correspondiente
public class CarroDAO {
    BigDecimal idCompra;
    
    /**
     * Inicia un proceso de compra, (hace una inserción en la tabla compras)
     * Asigna un carro de compras a un proceso de compra (Incersión en la tabla Asgnación)
     * @param clienteID ID del cliente que esta usando el carro
     * @param carroID ID del carro
     * @return TRUE si la asignacion se realiza correctamete FALSE en caso contrario
     */
    public boolean addAsignacion(BigDecimal clienteID,BigDecimal carroID){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        try {
            //inserción del proceso de compra
            BigDecimal id = (BigDecimal) s.createQuery("select c.idCompra from Compra c order by c.idCompra desc").setMaxResults(1).uniqueResult();
            id =id.add(BigDecimal.ONE);
            idCompra=id;
            Query insCompra = s.createSQLQuery("insert into compra values (?,null,?)")
                    .setParameter(0, id)
                    .setParameter(1, clienteID);
            insCompra.executeUpdate();
            //inserción de asignación
            Query insAsig = s.createSQLQuery("insert into asignacion values (?,?)")
                .setParameter(0, carroID)
                .setParameter(1, id); 
        insAsig.executeUpdate();
        s.getTransaction().commit();
        return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            s.close();
        }
    }
    /**
     * Se registra un producto al carro
     * Se verifica si el producto ya ha sido registrado en el carro
     * si ya a sido registrado, se busca el detalle correspondiente y se aumenta la cantidad
     * caso contrario, se crea un detalle con cantidad uno
     * @param ProductID ID del producto que es detectado por el carro
     * @param carroID ID del carro
     * @return TRUE si se registró el producto correctamente, FALSE en caso contrario
     */
    public boolean addProduct(BigDecimal ProductID,BigDecimal carroID){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        try {
            // Busca un detalle con el producto indicado
            Detalles detail = (Detalles) s.createQuery("from Detalles d where d.compra=? and d.productos=?")
                    .setParameter(0, idCompra)
                    .setParameter(1, ProductID)
                    .uniqueResult();
            //si no existe, lo crea
            if (detail!=null) {
                BigDecimal cant = detail.getCantidadProducto().add(BigDecimal.ONE);
                Query query = s.createSQLQuery("update Detalles set CANTIDAD_PRODUCTO=? where id_detalle=?")
                .setParameter(0, cant)
                .setParameter(1, detail.getIdDetalle());
                query.executeUpdate();
                s.getTransaction().commit();
                return true;
            //si existe aumenta la cantidad en uno
            }else{
                BigDecimal id = ((BigDecimal) s.createQuery("select d.idDetalle from Detalles d order by d.idDetalle desc").setMaxResults(1).uniqueResult()).add(BigDecimal.ONE);
                Query query = s.createSQLQuery("insert into Detalles values (?,?,?,?)")
                .setParameter(0, id)
                .setParameter(1, ProductID)
                .setParameter(2, idCompra) 
                .setParameter(3, new BigDecimal(1)); 
                query.executeUpdate();
                s.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            s.close();
        }
    }
    
    public boolean removeProduct(BigDecimal ProductID,BigDecimal carroID){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        try {
            Detalles detail = (Detalles) s.createQuery("from Detalles d where d.compra=? and d.productos=?")
                    .setParameter(0, idCompra)
                    .setParameter(1, ProductID)
                    .uniqueResult();
            if (detail!=null) {
                if (detail.getCantidadProducto().intValue()>1) {
                    BigDecimal cant = detail.getCantidadProducto().subtract(BigDecimal.ONE);
                    Query query = s.createSQLQuery("update Detalles set CANTIDAD_PRODUCTO=? where id_detalle=?")
                .setParameter(0, cant)
                .setParameter(1, detail.getIdDetalle());
                query.executeUpdate();
                s.getTransaction().commit();
                return true;
                }else if(detail.getCantidadProducto().intValue()==1){
                    Query query = s.createSQLQuery("delete from Detalles where ID_DETALLE=?")
                    .setParameter(0, detail.getIdDetalle());
                    query.executeUpdate();
                    s.getTransaction().commit();
                    return true;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            s.close();
        }
        return false;
    }
    
    
}
