/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.webservices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.dao.PathDAO;

/**
 *
 * @author Yuliamz
 */
@WebService(serviceName = "PathWS")
public class PathWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getListaDesos")
    public List<Integer> getListaDesos(@WebParam(name = "ClienteID") int ClienteID) {
        PathDAO dAO = new PathDAO();
        List<BigDecimal> bds = dAO.getListaDeseos(new BigDecimal(ClienteID));
        List<Integer> list = new ArrayList<Integer>();
        bds.forEach((bd) -> {list.add(new Integer(bd.intValue()));});
        return list;
    }
}
