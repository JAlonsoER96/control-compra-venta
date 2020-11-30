/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

public class Categoria {

    private int id;
    private String nombre;
    private String descripcion;
    private boolean activo;

//Constructor vacio
    public Categoria() {
    }
//Constructor para select 
    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

//Constructor con parámetros
    public Categoria(int id, String nombre, String descripcion, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

//Getters and setters
    public int getIdcategoria() {
        return id;
    }

    public void setIdcategoria(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
//Método toString

    @Override
    public String toString() {
        return nombre;
    }
}
