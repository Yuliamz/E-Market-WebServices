/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pojos;

import java.math.BigDecimal;

/**
 *
 * @author Yuliamz
 */
public class Position {
        private BigDecimal POSICION_X_GONDOLA;
        private BigDecimal POSICION_Y_GONDOLA;
        private BigDecimal FILA_CELDA;
        private BigDecimal COLUMNA_CELDA;

        public Position(BigDecimal POSICION_X_GONDOLA, BigDecimal POSICION_Y_GONDOLA, BigDecimal FILA_CELDA, BigDecimal COLUMNA_CELDA) {
            this.POSICION_X_GONDOLA = POSICION_X_GONDOLA;
            this.POSICION_Y_GONDOLA = POSICION_Y_GONDOLA;
            this.FILA_CELDA = FILA_CELDA;
            this.COLUMNA_CELDA = COLUMNA_CELDA;
        }

    public Position() {
    }
        
        

        public BigDecimal getPOSICION_X_GONDOLA() {
            return POSICION_X_GONDOLA;
        }

        public void setPOSICION_X_GONDOLA(BigDecimal POSICION_X_GONDOLA) {
            this.POSICION_X_GONDOLA = POSICION_X_GONDOLA;
        }

        public BigDecimal getPOSICION_Y_GONDOLA() {
            return POSICION_Y_GONDOLA;
        }

        public void setPOSICION_Y_GONDOLA(BigDecimal POSICION_Y_GONDOLA) {
            this.POSICION_Y_GONDOLA = POSICION_Y_GONDOLA;
        }

        public BigDecimal getFILA_CELDA() {
            return FILA_CELDA;
        }

        public void setFILA_CELDA(BigDecimal FILA_CELDA) {
            this.FILA_CELDA = FILA_CELDA;
        }

        public BigDecimal getCOLUMNA_CELDA() {
            return COLUMNA_CELDA;
        }

        public void setCOLUMNA_CELDA(BigDecimal COLUMNA_CELDA) {
            this.COLUMNA_CELDA = COLUMNA_CELDA;
        }
        
        
    
    
}
