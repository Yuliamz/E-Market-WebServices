package model.pojos;
// Generated 20/06/2017 05:41:39 PM by Hibernate Tools 4.3.1


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Facturas generated by hbm2java
 */
public class Facturas  implements java.io.Serializable {


     private BigDecimal idFactura;
     private BigDecimal compra;
     private BigDecimal caja;
     private BigDecimal cliente;
     private Date fechaFactura;
     private BigDecimal valorPagoFactura;
     private List<Detalles> compras;

    public Facturas() {
    }

	
    public Facturas(BigDecimal idFactura, BigDecimal compra, BigDecimal caja, Date fechaFactura, BigDecimal valorPagoFactura, BigDecimal cliente) {
        this.idFactura = idFactura;
        this.compra = compra;
        this.caja = caja;
        this.fechaFactura = fechaFactura;
        this.valorPagoFactura = valorPagoFactura;
        this.cliente = cliente;
    }
    public Facturas(BigDecimal idFactura, BigDecimal compra, BigDecimal caja, Date fechaFactura, BigDecimal valorPagoFactura, List<Detalles> compras) {
       this.idFactura = idFactura;
       this.compra = compra;
       this.caja = caja;
       this.fechaFactura = fechaFactura;
       this.valorPagoFactura = valorPagoFactura;
       this.compras = compras;
    }
   
    public BigDecimal getIdFactura() {
        return this.idFactura;
    }
    
    public void setIdFactura(BigDecimal idFactura) {
        this.idFactura = idFactura;
    }
    public BigDecimal getCompra() {
        return this.compra;
    }
    
    public void setCompra(BigDecimal compra) {
        this.compra = compra;
    }
    public BigDecimal getCaja() {
        return this.caja;
    }
    
    public void setCaja(BigDecimal caja) {
        this.caja = caja;
    }
    public Date getFechaFactura() {
        return this.fechaFactura;
    }
    
    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }
    public BigDecimal getValorPagoFactura() {
        return this.valorPagoFactura;
    }
    
    public void setValorPagoFactura(BigDecimal valorPagoFactura) {
        this.valorPagoFactura = valorPagoFactura;
    }
    public List<Detalles> getCompras() {
        return this.compras;
    }
    
    public void setCompras(List<Detalles> compras) {
        this.compras = compras;
    }

    public BigDecimal getCliente() {
        return cliente;
    }

    public void setCliente(BigDecimal cliente) {
        this.cliente = cliente;
    }




}


