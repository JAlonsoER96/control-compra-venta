/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Alonso Romero
 */
public class Ingreso {
    private int idIngreso;
    private int idPersona;
    private int idUsuario;
    private String nombrePersona;
    private String nombreUsuario;
    private String tipoComprobante;
    private String serieComprobante;
    private String numComprobante;
    private Date fecha;
    private double impuesto;
    private double total;
    private String estado;
    private List<DetalleIngreso> detalles;
    //Constructor vacio
    public Ingreso() {
    }
    //Constructor parámetros

    public Ingreso(int idIngreso, int idPersona, int idUsuario, String nombrePersona, String nombreUsuario, String tipoComprobante, String serieComprobante, 
            String numComprobante, Date fecha, double impuesto, double total, String estado, List<DetalleIngreso> detalles) {
        this.idIngreso = idIngreso;
        this.idPersona = idPersona;
        this.idUsuario = idUsuario;
        this.nombrePersona = nombrePersona;
        this.nombreUsuario = nombreUsuario;
        this.tipoComprobante = tipoComprobante;
        this.serieComprobante = serieComprobante;
        this.numComprobante = numComprobante;
        this.fecha = fecha;
        this.impuesto = impuesto;
        this.total = total;
        this.estado = estado;
        this.detalles = detalles;
    }
    //Constructor sin lista detalles

    public Ingreso(int idIngreso, int idPersona, int idUsuario, String nombrePersona, String nombreUsuario, String tipoComprobante, String serieComprobante, String numComprobante, Date fecha, double impuesto, double tota, String estado) {
        this.idIngreso = idIngreso;
        this.idPersona = idPersona;
        this.idUsuario = idUsuario;
        this.nombrePersona = nombrePersona;
        this.nombreUsuario = nombreUsuario;
        this.tipoComprobante = tipoComprobante;
        this.serieComprobante = serieComprobante;
        this.numComprobante = numComprobante;
        this.fecha = fecha;
        this.impuesto = impuesto;
        this.total = tota;
        this.estado = estado;
    }
    
    //getter and setter 

    public int getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(int idIngreso) {
        this.idIngreso = idIngreso;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }
    public String getSerieComprobante(){
        return serieComprobante;
    }
    public void setSerieComprobante(String serieComprobante){
        this.serieComprobante = serieComprobante;
    }
    public String getNumComprobante() {
        return numComprobante;
    }

    public void setNumComprobante(String numComprobante) {
        this.numComprobante = numComprobante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double tota) {
        this.total = tota;
    }

    public String getEstado() {
        return estado;
    }

    public void setActivo(String estado) {
        this.estado = estado;
    }

    public List<DetalleIngreso> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleIngreso> detalles) {
        this.detalles = detalles;
    }
    
}
