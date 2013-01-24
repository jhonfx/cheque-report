package chequereport;


import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jpurata
 */
public class Cheque {
    
    int folio;
    String fecha;
    double monto;
    String razonSocial;
    String textoMonto;
    String paridad;
    String concepto;
    
    public int getFolio() {
        return folio;
    }
    
    public void setFolio(int folio) {
        this.folio = folio;
    }
    
    public String getFecha() {
        return fecha;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    public double getMonto() {
        return monto;
    }
    
    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    public String getRazonSocial() {
        return razonSocial;
    }
    
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    
    public String getTextoMonto() {
        return textoMonto;
    }
    
    public void setTextoMonto(String textoMonto) {
        this.textoMonto = textoMonto;
    }
    
    public String getConcepto() {
        return concepto;
    }
    
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    /**
     * @return the paridad
     */
    public String getParidad() {
        return paridad;
    }

    /**
     * @param paridad the paridad to set
     */
    public void setParidad(String paridad) {
        this.paridad = paridad;
    }
    
}
