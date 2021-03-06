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
public class Usuario {
    private int idusuario;
    private int rol_id;
    private String nombreRol;
    private String nombre;
    private String tipo_documento;
    private String num_documento;
    private String direccion;
    private String telefono;
    private String email;
    private String clave;
    private boolean activo;
    
    //Constructor vacio

    public Usuario() {
    }
    //Constructor con parametros
    public Usuario(int idusuario, int rol_id, String nombreRol, String nombre, String tipo_documento, String num_documento, String direccion, String telefono, String email, String clave, boolean activo) {
        this.idusuario = idusuario;
        this.rol_id = rol_id;
        this.nombreRol = nombreRol;
        this.nombre = nombre;
        this.tipo_documento = tipo_documento;
        this.num_documento = num_documento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
        this.activo = activo;
    }
    //Constructor login

    public Usuario(int idusuario,int rol_id, String nombreRol, String nombre, String email, boolean activo) {
        this.idusuario = idusuario;
        this.rol_id = rol_id;
        this.nombreRol = nombreRol;
        this.nombre = nombre;
        this.email = email;
        this.activo = activo;
    }
    
    //getters and setters
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    /**
     *
     * @author Alonso Romero
     * @description Valida si el usuario esta activo o no
     */
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
