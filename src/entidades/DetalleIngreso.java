/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author Alonso Romero
 */
public class DetalleIngreso {
    private int idDetalleIngreso;
    private int ingreso_id;
    private int articulo_id;
    private String codigoArt;
    private String nombreArt;
    private int cantidad;
    private double precio;
    private double subTotal;
    
    //Constructor vacio

    public DetalleIngreso() {
    }
    //contructor parámetros

    public DetalleIngreso(int idDetalleIngreso, int ingreso_id, int articulo_id, String codigoArt, String nombreArt, int cantidad, double precio, double subTotal) {
        this.idDetalleIngreso = idDetalleIngreso;
        this.ingreso_id = ingreso_id;
        this.articulo_id = articulo_id;
        this.codigoArt = codigoArt;
        this.nombreArt = nombreArt;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subTotal = subTotal;
    }
    //
    public DetalleIngreso(int articulo_id, int cantidad, double precio) {
        this.articulo_id = articulo_id;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public DetalleIngreso(int articulo_id, String codigoArt, String nombreArt, int cantidad, double precio, double subTotal) {
        this.articulo_id = articulo_id;
        this.codigoArt = codigoArt;
        this.nombreArt = nombreArt;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subTotal = subTotal;
    }
    
    //getter and setter

    public int getIdDetalleIngreso() {
        return idDetalleIngreso;
    }

    public void setIdDetalleIngreso(int idDetalleIngreso) {
        this.idDetalleIngreso = idDetalleIngreso;
    }

    public int getIngreso_id() {
        return ingreso_id;
    }

    public void setIngreso_id(int ingreso_id) {
        this.ingreso_id = ingreso_id;
    }

    public int getArticulo_id() {
        return articulo_id;
    }

    public void setArticulo_id(int articulo_id) {
        this.articulo_id = articulo_id;
    }

    public String getCodigoArt() {
        return codigoArt;
    }

    public void setCodigoArt(String codigoArt) {
        this.codigoArt = codigoArt;
    }

    public String getNombreArt() {
        return nombreArt;
    }

    public void setNombreArt(String nombreArt) {
        this.nombreArt = nombreArt;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
}
