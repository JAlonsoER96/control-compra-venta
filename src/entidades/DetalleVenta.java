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
public class DetalleVenta {
    private int idDetalleVenta;
    private int ingreso_id;
    private int articulo_id;
    private String codigoArt;
    private String nombreArt;
    private int articuloStock;
    private int cantidad;
    private double precio;
    private double descuento;
    private double subTotal;

    public DetalleVenta() {
    }

    public DetalleVenta(int idDetalleVenta, int ingreso_id, int articulo_id, String codigoArt, String nombreArt, int articuloStock, int cantidad, double precio, double descuento, double subTotal) {
        this.idDetalleVenta = idDetalleVenta;
        this.ingreso_id = ingreso_id;
        this.articulo_id = articulo_id;
        this.codigoArt = codigoArt;
        this.nombreArt = nombreArt;
        this.articuloStock = articuloStock;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.subTotal = subTotal;
    }

    public DetalleVenta(int articulo_id, String codigoArt, String nombreArt, int articuloStock, int cantidad, double precio, double descuento, double subTotal) {
        this.articulo_id = articulo_id;
        this.codigoArt = codigoArt;
        this.nombreArt = nombreArt;
        this.articuloStock = articuloStock;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
        this.subTotal = subTotal;
    }

    public DetalleVenta(int articulo_id, int cantidad, double precio,double descuento) {
        this.articulo_id = articulo_id;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
    }
    
    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
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

    public int getArticuloStock() {
        return articuloStock;
    }

    public void setArticuloStock(int articuloStock) {
        this.articuloStock = articuloStock;
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

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
}
