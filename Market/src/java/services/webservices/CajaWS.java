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
import model.dao.CajaDAO;
import model.pojos.Facturas;

/**
 *
 * @author Yuliamz
 */
@WebService(serviceName = "CajaWS")
public class CajaWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "genFactura")
    public Facturas genFactura(@WebParam(name = "CarroID") int CarroID, @WebParam(name = "CajaID") int CajaID) {
        CajaDAO cajaDAO = new CajaDAO();
        return cajaDAO.genFactura(new BigDecimal(CarroID), new BigDecimal(CajaID));
    }
}
