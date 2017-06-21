package model.dao;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import model.pojos.Detalles;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Yuliamz
 */
public class CarroDAO {
    BigDecimal idCompra;
    
    public boolean addAsignacion(BigDecimal clienteID,BigDecimal carroID){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        try {
            BigDecimal id = (BigDecimal) s.createQuery("select c.idCompra from Compra c order by c.idCompra desc").setMaxResults(1).uniqueResult();
            id =id.add(BigDecimal.ONE);
            idCompra=id;
            System.out.println("id compra: "+id);
            Query ins = s.createSQLQuery("insert into compra values (?,null,?)")
                    .setParameter(0, id)
                    .setParameter(1, clienteID);
            ins.executeUpdate();
            
            Query query = s.createSQLQuery("insert into asignacion values (?,?)")
                .setParameter(0, carroID)
                .setParameter(1, id); 
        query.executeUpdate();
        s.getTransaction().commit();
        return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            s.close();
        }
    }
    
    public boolean addProduct(BigDecimal ProductID,BigDecimal carroID){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        try {
            Detalles detail = (Detalles) s.createQuery("from Detalles d where d.compra=? and d.productos=?")
                    .setParameter(0, idCompra)
                    .setParameter(1, ProductID)
                    .uniqueResult();
            if (detail!=null) {
                BigDecimal cant = detail.getCantidadProducto().add(BigDecimal.ONE);
                Query query = s.createSQLQuery("update Detalles set CANTIDAD_PRODUCTO=? where id_detalle=?")
                .setParameter(0, cant)
                .setParameter(1, detail.getIdDetalle());
                query.executeUpdate();
                s.getTransaction().commit();
                return true;
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
