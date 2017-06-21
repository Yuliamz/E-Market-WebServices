/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import model.pojos.CeldaGondola;
import model.pojos.Gondolas;
import model.pojos.Position;
import model.pojos.Productos;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Yuliamz
 */
public class ProductoDAO {
    
    public Productos getProduct(BigDecimal IDProduct){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        Productos p = (Productos) s.get(Productos.class, IDProduct);
        s.close();
        if (p!=null) {
            System.out.println(p.getNombreProducto()+" "+p.getPrecioProducto()+" "+p.getDescripcionProducto());
            return p;
        }else{
            throw new RuntimeException("No Se encontro el producto: "+IDProduct);
        }
    }
    public List<Productos> getProductList(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        Query query = s.createQuery("from Productos");
        List<Productos> list = query.list();
        s.close();
        if (list!=null || !list.isEmpty()) {
            return list;
        }else{
            throw new RuntimeException("No existen productos");
        }
    }
    
    public Position getProductPosition(BigDecimal IDProduct){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        Position position = new Position();
        CeldaGondola celdaGondolas = (CeldaGondola) s.createQuery("from CeldaGondola c where c.productos="+IDProduct).uniqueResult();
        position.setCOLUMNA_CELDA(celdaGondolas.getColumnaCelda());
        position.setFILA_CELDA(celdaGondolas.getFilaCelda());
        Gondolas gondola = (Gondolas) s.createQuery("from Gondolas g where g.idGondola="+celdaGondolas.getGondolas()).uniqueResult();
        position.setPOSICION_X_GONDOLA(gondola.getPosicionXGondola());
        position.setPOSICION_Y_GONDOLA(gondola.getPosicionYGondola());
        s.close();
        if (position!=null) {
            return position;
        }else{
            throw new RuntimeException("El producto no existe o no esta asignado a una gondola");
        }
    }
    
    public BigDecimal getProductPrice(BigDecimal IDProduct){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session s = sf.openSession();
        BigDecimal price = (BigDecimal) s.createQuery("select p.precioProducto from Productos p where p.idProducto="+IDProduct).uniqueResult();
        return price;
    }
    
}
