/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;
//Imports

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import datos.CategoriaDAO;
import entidades.Categoria;

/**
 *
 * @author Alonso Romero
 */
public class ControlCategoria {

    private final CategoriaDAO DATOS;
    private final Categoria cat;
    private DefaultTableModel model;
    private int totalMostrados;

    //Constructor
    public ControlCategoria() {
        this.DATOS = new CategoriaDAO();
        this.cat = new Categoria();
        this.totalMostrados=0;
    }
//Control categoria
    public DefaultTableModel listar(String texto) {
        List<Categoria> lista = new ArrayList();
        lista.addAll(DATOS.list(texto));
        //Encabezados de tabla
        String[] titulos = {"Id", "Nombre", "Descripción", "Estado"};
        //Instanción modelo tabla
        this.model = new DefaultTableModel(null, titulos);
        String estado;
        String[] registro = new String[4];
        this.totalMostrados = 0;
        for (Categoria item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }
            registro[0] = Integer.toString(item.getIdcategoria());
            registro[1] = item.getNombre();
            registro[2] = item.getDescripcion();
            registro[3] = estado;
            //Agrega el registro al modelo de tabla
            this.model.addRow(registro);
            this.totalMostrados += 1;
        }
        return this.model;
    }

    public String insert(String nombre, String descripcion) {
        if (DATOS.exist(nombre)) {
            return "El registro ya existe";
        } else {
            cat.setNombre(nombre);
            cat.setDescripcion(descripcion);
            if (DATOS.insert(cat)) {
                return "OK";
            } else {
                return "Error al insertar registro";
            }
        }

    }

    public String update(int id, String nombre, String nombreAnt, String descripcion) {
        if (nombre.equals(nombreAnt)) {
            cat.setIdcategoria(id);
            cat.setNombre(nombre);
            cat.setDescripcion(descripcion);
            if (DATOS.update(cat)) {
                return "OK";
            } else {
                return "Error al actualizar";
            }
        } else {
            if (DATOS.exist(nombre)) {
                return "El registro ya  existe";

            } else {
                cat.setIdcategoria(id);
                cat.setNombre(nombre);
                cat.setDescripcion(descripcion);
                if (DATOS.update(cat)) {
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
