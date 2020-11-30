/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author alonso
 */
public class Articulo {
    private int idarticulo;
    private int categoria_id;
    private String nombreCAtegoria;
    private String codigo;
    private String nombre;
    private double precio_venta;
    private int stock;
    private String  descripcion;
    private String imagen;
    private boolean activo;
    
    //Constructor vacio
    public Articulo(){
        
    }
    //Constructor inicializado

    public Articulo(int idarticulo, int categoria_id, String nombreCAtegoria, String codigo, String nombre, double precio_venta, int stock, String descripcion, String imagen, boolean activo) {
        this.idarticulo = idarticulo;
        this.categoria_id = categoria_id;
        this.nombreCAtegoria = nombreCAtegoria;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio_venta = precio_venta;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.activo = activo;
    }
    //Métodos get an set

    public int getIdarticulo() {
        return idarticulo;
    }

    public void setIdarticulo(int idarticulo) {
        this.idarticulo = idarticulo;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getNombreCAtegoria() {
        return nombreCAtegoria;
    }

    public void setNombreCAtegoria(String nombreCAtegoria) {
        this.nombreCAtegoria = nombreCAtegoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    //toString

    @Override
    public String toString() {
        return "Articulo{" + "idarticulo=" + idarticulo + ", categoria_id=" + categoria_id + ", nombreCAtegoria=" + nombreCAtegoria + ", codigo=" + codigo + ", nombre=" + nombre + ", precio_venta=" + precio_venta + ", stock=" + stock + ", descripcion=" + descripcion + ", imagen=" + imagen + ", activo=" + activo + '}';
    }
    
}
