package model.pojos;
// Generated 20/06/2017 05:41:39 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Compra generated by hbm2java
 */
public class Compra  implements java.io.Serializable {


     private BigDecimal idCompra;
     private BigDecimal facturas;
     private BigDecimal cliente;

    public Compra() {
    }

	
    public Compra(BigDecimal idCompra, BigDecimal cliente) {
        this.idCompra = idCompra;
        this.cliente = cliente;
    }
   
    public BigDecimal getIdCompra() {
        return this.idCompra;
    }
    
    public void setIdCompra(BigDecimal idCompra) {
        this.idCompra = idCompra;
    }
    public BigDecimal getFacturas() {
        return this.facturas;
    }
    
    public void setFacturas(BigDecimal facturas) {
        this.facturas = facturas;
    }
    public BigDecimal getCliente() {
        return this.cliente;
    }
    
    public void setCliente(BigDecimal cliente) {
        this.cliente = cliente;
    }
//    public Set getCarrosComprases() {
//        return this.carrosComprases;
//    }
//    
//    public void setCarrosComprases(Set carrosComprases) {
//        this.carrosComprases = carrosComprases;
//    }
//    public Set getFacturases() {
//        return this.facturases;
//    }
//    
//    public void setFacturases(Set facturases) {
//        this.facturases = facturases;
//    }
//    public Set getDetalleses() {
//        return this.detalleses;
//    }
//    
//    public void setDetalleses(Set detalleses) {
//        this.detalleses = detalleses;
//    }




}


