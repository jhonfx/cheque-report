package chequereport;


import java.io.Serializable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jpurata
 */
public class ChequeReportDTO implements Serializable {
    private int folio;
    private String fecha;
    private double monto;
    private String razonSocial;
    private String textoMonto;
    private String paridad;
    private String concepto;    

    /**
     * @return the folio
     */
    public int getFolio() {
        return folio;
    }

    /**
     * @param folio the folio to set
     */
    public void setFolio(int folio) {
        this.folio = folio;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the monto
     */
    public double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the textoMonto
     */
    public String getTextoMonto() {
        return textoMonto;
    }

    /**
     * @param textoMonto the textoMonto to set
     */
    public void setTextoMonto(String textoMonto) {
        this.textoMonto = textoMonto;
    }

    /**
     * @return the concepto
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * @param concepto the concepto to set
     */
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    /**
     * @return the tipoMoneda
     */
    public String getParidad() {
        return paridad;
    }

    /**
     * @param tipoMoneda the tipoMoneda to set
     */
    public void setParidad(String paridad) {
        this.paridad = paridad;
    }
}
