/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.UsuarioDAO;
import datos.RolDAO;
import entidades.Rol;
import entidades.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alonso Romero
 */
public class ControlUsuario {

    private final UsuarioDAO DATOS;
    private final RolDAO DATOSROL;
    private final Usuario user;
    private DefaultTableModel model;
    private int totalMostrados;

    //Constructor
    public ControlUsuario() {
        this.DATOS = new UsuarioDAO();
        this.DATOSROL = new RolDAO();
        this.user = new Usuario();
        this.totalMostrados = 0;
    }

    //Control usuario
    public DefaultTableModel listar(String texto, int totalPerPagina, int numPagina) {
        List<Usuario> lista = new ArrayList();
        lista.addAll(DATOS.list(texto, totalPerPagina, numPagina));
        //Encabezados de tabla
        String[] titulos = {"ID", "Rol Id", "Rol", "Nombre", "Tipo documento", "# Documento", "Dirección", "Teléfono", "Email", "Clave", "Estado"};
        //Instanción modelo tabla
        this.model = new DefaultTableModel(null, titulos);
        String estado;
        String[] registro = new String[11];
        this.totalMostrados = 0;
        for (Usuario item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }
            registro[0] = Integer.toString(item.getIdusuario());
            registro[1] = Integer.toString(item.getRol_id());
            registro[2] = item.getNombreRol();
            registro[3] = item.getNombre();
            registro[4] = item.getTipo_documento();
            registro[5] = item.getNum_documento();
            registro[6] = item.getDireccion();
            registro[7] = item.getTelefono();
            registro[8] = item.getEmail();
            registro[9] = item.getClave();
            registro[10] = estado;
            //Agrega el registro al modelo de tabla
            this.model.addRow(registro);
            this.totalMostrados += 1;
        }
        return this.model;
    }
    /*
    *@a
    */
    public String login(String email, String clave){
        String respuesta = "0";
        Usuario user = this.DATOS.login(email, this.encriptar(clave));
        System.out.println(user);
        if (user != null) {
            if (user.isActivo()) {
                respuesta = "1";
                VariablesLogin.userID = user.getIdusuario();
                VariablesLogin.rolId = user.getRol_id();
                VariablesLogin.nombreRol = user.getNombreRol();
                VariablesLogin.nombreUser = user.getNombre();
                VariablesLogin.emailUser = user.getEmail();
            }else{
                respuesta = "2";
            }
        }
        return respuesta;
    }

    public DefaultComboBoxModel select() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Rol> lista = new ArrayList();
        lista = DATOSROL.select();
        for (Rol item : lista) {
            items.addElement(new Rol(item.getIdrol(), item.getNombre()));
        }
        return items;
    }

    private String encriptar(String texto) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        byte[] hash = md.digest(texto.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%d", b));
        }
        return sb.toString();
    }

    public String insert(int rol_id, String nombre, String tipo_documento, String num_documento, String direccion, String telefono, String email, String clave) {
        if (DATOS.exist(email)) {
            return "El registro ya existe";
        } else {
            user.setRol_id(rol_id);
            user.setNombre(nombre);
            user.setTipo_documento(tipo_documento);
            user.setNum_documento(num_documento);
            user.setDireccion(direccion);
            user.setTelefono(telefono);
            user.setEmail(email);
            user.setClave(this.encriptar(clave));
            if (DATOS.insert(user)) {
                return "OK";
            } else {
                return "Error al insertar registro";
            }
        }
    }

    public String update(int id, int rol_id, String nombre, String emalAnt, String tipo_documento, String num_documento, String direccion, String telefono, String email, String clave) {
        String encriptado;
        if (email.equals(emalAnt)) {
            user.setIdusuario(id);
            user.setRol_id(rol_id);
            user.setNombre(nombre);
            user.setTipo_documento(tipo_documento);
            user.setNum_documento(num_documento);
            user.setDireccion(direccion);
            user.setTelefono(telefono);
            user.setEmail(email);
            if (clave.length() == 64) {
                encriptado = clave;
            } else {
                encriptado = this.encriptar(clave);
            }
            user.setClave(this.encriptar(encriptado));

            if (DATOS.update(user)) {
                return "OK";
            } else {
                return "Error al actualizar";
            }
        } else {
            if (DATOS.exist(email)) {
                return "El registro ya  existe";

            } else {
                user.setIdusuario(id);
                user.setRol_id(rol_id);
                user.setNombre(nombre);
                user.setTipo_documento(tipo_documento);
                user.setNum_documento(num_documento);
                user.setDireccion(direccion);
                user.setTelefono(telefono);
                user.setEmail(email);
                if (clave.length() == 64) {
                    encriptado = clave;
                } else {
                    encriptado = this.encriptar(clave);
                }
                user.setClave(this.encriptar(encriptado));
                if (DATOS.update(user)) {
                    return "OK";
                } else {
                    return "Error al actualizar";
                }
            }
        }
    }
    public String desactivate(int id) {
        if (DATOS.desactivate(id)) {
            return "OK";
        } else {
            return "Error al actualizar";
        }

    }

    public String activate(int id) {
        if (DATOS.activate(id)) {
            return "OK";
        } else {
            return "Error al actualizar";
        }

    }

    public int count() {
        return DATOS.count();
    }

    public int showAll() {
        return this.totalMostrados;
    }

}
