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
import model.dao.GondolaDAO;

/**
 *
 * @author Yuliamz
 */
@WebService(serviceName = "GondolaWS")
public class GondolaWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addProducto")
    public Boolean addProducto(@WebParam(name = "CeldaID") int CeldaID) {
        GondolaDAO gondolaDAO = new GondolaDAO();
        return gondolaDAO.addProducto(new BigDecimal(CeldaID));
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "removeProducto")
    public Boolean removeProducto(@WebParam(name = "CeldaID") int CeldaID) {
        GondolaDAO gondolaDAO = new GondolaDAO();
        return gondolaDAO.removeProducto(new BigDecimal(CeldaID));
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCantidadProductos")
    public Integer getCantidadProductos(@WebParam(name = "CeldaID") int CeldaID) {
        GondolaDAO dAO = new GondolaDAO();
        return dAO.getCantidadProductos(new BigDecimal(CeldaID));
    }
}
