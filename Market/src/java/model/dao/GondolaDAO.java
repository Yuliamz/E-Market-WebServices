/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.math.BigDecimal;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Yuliamz
 */
public class GondolaDAO {
    
    public boolean removeProducto(BigDecimal celdaID){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        try {
            BigDecimal cant = (BigDecimal) s.createQuery("select c.cantidadProductoCelda from CeldaGondola c where c.idCelda=?")
                    .setParameter(0, celdaID)
                    .uniqueResult();
            BigDecimal newCant = cant.subtract(BigDecimal.ONE);
            Query query = s.createSQLQuery("update celda_gondola c set c.cantidad_producto_celda=? where c.id_celda=?")
                .setParameter(0, newCant)
                .setParameter(1, celdaID);
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
    public boolean addProducto(BigDecimal idCelda){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        s.beginTransaction();
        try {
            BigDecimal cant = (BigDecimal) s.createQuery("select c.cantidadProductoCelda from CeldaGondola c where c.idCelda=?").setParameter(0, idCelda).uniqueResult();
            BigDecimal newCant = cant.add(BigDecimal.ONE);
            Query query = s.createSQLQuery("update celda_gondola c set c.cantidad_producto_celda=? where c.id_celda=?")
                .setParameter(0, newCant)
                .setParameter(1, idCelda);
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
    
    public int getCantidadProductos(BigDecimal idCelda){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        BigDecimal cant = (BigDecimal) s.createQuery("select c.cantidadProductoCelda from CeldaGondola c where c.idCelda=?").setParameter(0, idCelda).uniqueResult();
        s.close();
        return cant.intValue();
    }
    
}
