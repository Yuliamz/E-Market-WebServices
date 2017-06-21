/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.webservices;

import java.math.BigDecimal;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.dao.CarroDAO;

/**
 *
 * @author Yuliamz
 */
@WebService(serviceName = "CarroWS")
public class CarroWS {
    CarroDAO carroDAO = new CarroDAO();


    /**
     * Web service operation
     */
    @WebMethod(operationName = "assingClient")
    public Boolean assingClient(@WebParam(name = "CarroID") int CarroID, @WebParam(name = "ClienteID") int ClienteID) {
        return carroDAO.addAsignacion(new BigDecimal(ClienteID), new BigDecimal(CarroID));
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addProduct")
    public Boolean addProduct(@WebParam(name = "ProductID") int ProductID,@WebParam(name = "CarroID") int CarroID) {
        return carroDAO.addProduct(new BigDecimal(ProductID), new BigDecimal(CarroID));
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "removeProduct")
    public Boolean removeProduct(@WebParam(name = "ProductID") int ProductID, @WebParam(name = "CarroID") int CarroID) {
        return carroDAO.removeProduct(new BigDecimal(ProductID), new BigDecimal(CarroID));
    }
    
    
}
