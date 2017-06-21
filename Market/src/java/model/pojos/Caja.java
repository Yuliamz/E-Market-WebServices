package model.pojos;
// Generated 20/06/2017 05:41:39 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Caja generated by hbm2java
 */
public class Caja  implements java.io.Serializable {


     private BigDecimal idCaja;
     private BigDecimal posicionXCaja;
     private BigDecimal posicionYCaja;
     private Set facturases = new HashSet(0);

    public Caja() {
    }

	
    public Caja(BigDecimal idCaja, BigDecimal posicionXCaja, BigDecimal posicionYCaja) {
        this.idCaja = idCaja;
        this.posicionXCaja = posicionXCaja;
        this.posicionYCaja = posicionYCaja;
    }
    public Caja(BigDecimal idCaja, BigDecimal posicionXCaja, BigDecimal posicionYCaja, Set facturases) {
       this.idCaja = idCaja;
       this.posicionXCaja = posicionXCaja;
       this.posicionYCaja = posicionYCaja;
       this.facturases = facturases;
    }
   
    public BigDecimal getIdCaja() {
        return this.idCaja;
    }
    
    public void setIdCaja(BigDecimal idCaja) {
        this.idCaja = idCaja;
    }
    public BigDecimal getPosicionXCaja() {
        return this.posicionXCaja;
    }
    
    public void setPosicionXCaja(BigDecimal posicionXCaja) {
        this.posicionXCaja = posicionXCaja;
    }
    public BigDecimal getPosicionYCaja() {
        return this.posicionYCaja;
    }
    
    public void setPosicionYCaja(BigDecimal posicionYCaja) {
        this.posicionYCaja = posicionYCaja;
    }
    public Set getFacturases() {
        return this.facturases;
    }
    
    public void setFacturases(Set facturases) {
        this.facturases = facturases;
    }




}


