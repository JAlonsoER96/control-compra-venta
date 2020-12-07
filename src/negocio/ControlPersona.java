/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.PersonaDAO;
import entidades.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alonso Romero
 */
public class ControlPersona {

    private final PersonaDAO DATOS;
    private final Persona person;
    private DefaultTableModel model;
    private int totalMostrados;

    //Constructor
    public ControlPersona() {
        this.DATOS = new PersonaDAO();
        this.person = new Persona();
        this.totalMostrados = 0;
    }

    //Control usuario
    public DefaultTableModel listar(String texto, int totalPerPagina, int numPagina) {
        List<Persona> lista = new ArrayList();
        lista.addAll(DATOS.list(texto, totalPerPagina, numPagina));
        //Encabezados de tabla
        String[] titulos = {"ID", "Tipo persona", "Nombre", "Tipo documento", "# Documento", "Dirección", "Teléfono", "Email", "Estado"};
        //Instanción modelo tabla
        this.model = new DefaultTableModel(null, titulos);
        String estado;
        String[] registro = new String[9];
        this.totalMostrados = 0;
        for (Persona item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }
            registro[0] = Integer.toString(item.getIdPersona());
            registro[1] = item.getTipo_persona();
            registro[2] = item.getNombre();
            registro[3] = item.getTipo_documento();
            registro[4] = item.getNum_documento();
            registro[5] = item.getDireccion();
            registro[6] = item.getTelefono();
            registro[7] = item.getEmail();
            registro[8] = estado;
            //Agrega el registro al modelo de tabla
            this.model.addRow(registro);
            this.totalMostrados += 1;
        }
        return this.model;
    }
    
    public DefaultTableModel listarTipo(String texto, int totalPerPagina, int numPagina, String tipo) {
        List<Persona> lista = new ArrayList();
        lista.addAll(DATOS.listT(texto, totalPerPagina, numPagina,tipo));
        //Encabezados de tabla
        String[] titulos = {"ID", "Tipo persona", "Nombre", "Tipo documento", "# Documento", "Dirección", "Teléfono", "Email", "Estado"};
        //Instanción modelo tabla
        this.model = new DefaultTableModel(null, titulos);
        String estado;
        String[] registro = new String[9];
        this.totalMostrados = 0;
        for (Persona item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }
            registro[0] = Integer.toString(item.getIdPersona());
            registro[1] = item.getTipo_persona();
            registro[2] = item.getNombre();
            registro[3] = item.getTipo_documento();
            registro[4] = item.getNum_documento();
            registro[5] = item.getDireccion();
            registro[6] = item.getTelefono();
            registro[7] = item.getEmail();
            registro[8] = estado;
            //Agrega el registro al modelo de tabla
            this.model.addRow(registro);
            this.totalMostrados += 1;
        }
        return this.model;
    }

    public String insert(String tipo_persona, String nombre, String tipo_documento, String num_documento, String direccion, String telefono, String email) {
        if (DATOS.exist(nombre)) {
            return "El registro ya existe";
        } else {
            person.setTipo_persona(tipo_persona);
            person.setNombre(nombre);
            person.setTipo_documento(tipo_documento);
            person.setNum_documento(num_documento);
            person.setDireccion(direccion);
            person.setTelefono(telefono);
            person.setEmail(email);
            if (DATOS.insert(person)) {
                return "OK";
            } else {
                return "Error al insertar registro";
            }
        }
    }

    public String update(int id, String tipo_persona, String nombre, String nombreAnt, String tipo_documento, String num_documento, String direccion, String telefono, String email) {
        String encriptado;
        if (nombre.equals(nombreAnt)) {
            person.setIdPersona(id);
            person.setTipo_persona(tipo_persona);
            person.setNombre(nombre);
            person.setTipo_documento(tipo_documento);
            person.setNum_documento(num_documento);
            person.setDireccion(direccion);
            person.setTelefono(telefono);
            person.setEmail(email);

            if (DATOS.update(person)) {
                return "OK";
            } else {
                return "Error al actualizar";
            }
        } else {
            if (DATOS.exist(email)) {
                return "El registro ya  existe";

            } else {
                person.setIdPersona(id);
                person.setTipo_persona(tipo_persona);
                person.setNombre(nombre);
                person.setTipo_documento(tipo_documento);
                person.setNum_documento(num_documento);
                person.setDireccion(direccion);
                person.setTelefono(telefono);
                person.setEmail(email);
                if (DATOS.update(person)) {
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
