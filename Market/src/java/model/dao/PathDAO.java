/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Yuliamz
 */
public class PathDAO {
    
    public List<BigDecimal> getListaDeseos(BigDecimal ClienteID){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        BigDecimal id = (BigDecimal) s.createSQLQuery("select l.id_listadeseo from listadeseos l where l.id_cliente=? order by l.id_listadeseo desc")
                .setParameter(0, ClienteID)
                .setMaxResults(1)
                .uniqueResult();
        List<BigDecimal> productos = (List<BigDecimal>)s.createSQLQuery("select d.ID_PRODUCTO from DESEOS_PRODUCTOS d where d.ID_LISTADESEO=?")
                .setParameter(0, id)
                .list();
        s.close();
        if (productos!=null) {
            return productos;
        }else{
            throw new RuntimeException("El cliente no tiene lista de deseos");
        }
        
    }
    
}
