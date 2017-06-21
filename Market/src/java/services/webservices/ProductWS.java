package services.webservices;

import java.math.BigDecimal;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.dao.ProductoDAO;
import model.pojos.Position;
import model.pojos.Productos;

/**
 *
 * @author Yuliamz
 */
@WebService(serviceName = "ProductWS")
public class ProductWS {


    /**
     * Obten la información del producto correspondiente al ID indicado
     */
    
    @WebMethod(operationName = "getProduct")
    public Productos getProduct(@WebParam(name = "ProductID") int ProductID) {
        System.out.println("Activa el servicio getProduct");
        ProductoDAO productoDAO = new ProductoDAO();
        return productoDAO.getProduct(new BigDecimal(ProductID));
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getProductList")
    public List<Productos> getProductList() {
        System.out.println("Activa el servicio getProductList");
        ProductoDAO productoDAO = new ProductoDAO();
        return productoDAO.getProductList();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getProductPosition")
    public Position getProductPosition(@WebParam(name = "ProductID") int ProductID) {
        ProductoDAO productoDAO = new ProductoDAO();
        return productoDAO.getProductPosition(new BigDecimal(ProductID));
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getProductPrice")
    public BigDecimal getProductPrice(@WebParam(name = "ProductID") int ProductID) {
        ProductoDAO productoDAO = new ProductoDAO();
        return productoDAO.getProductPrice(new BigDecimal(ProductID));
    }
}
