/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Objects;

/**
 *
 * @author Alonso Romero
 */
public class Rol {
    private int idrol;
    private String nombre;
    private String descripcion;
    
    //Constructor vacio

    public Rol() {
    }
    //Constructor con parámetros

    public Rol(int idrol, String nombre, String descripcion) {
        this.idrol = idrol;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    //Constructor id - descripción

    public Rol(int idrol, String nombre) {
        this.idrol = idrol;
        this.nombre = nombre;
    }
    //Setter an getter 

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
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
    // to String 

    @Override
    public String toString() {
        return nombre;
    }
    
    //equal - hash

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.idrol;
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.descripcion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rol other = (Rol) obj;
        if (this.idrol != other.idrol) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        return true;
    }
    
    
}
