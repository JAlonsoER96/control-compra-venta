/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.RolDAO;
import entidades.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alonso Romero
 */
public class ControlRol {
    private final RolDAO DATOS;
    private final Rol rol;
    private DefaultTableModel model;
    private int totalMostrados;

    public ControlRol() {
        this.DATOS = new RolDAO();
        this.rol = new Rol();
        this.totalMostrados =0;                
    }
    
    public DefaultTableModel listar() {
        List<Rol> lista = new ArrayList();
        lista.addAll(DATOS.list());
        //Encabezados de tabla
        String[] titulos = {"Id", "Nombre", "Descripción"};
        //Instanción modelo tabla
        this.model = new DefaultTableModel(null, titulos);
        String estado;
        String[] registro = new String[3];
        this.totalMostrados = 0;
        for (Rol item : lista) {
            registro[0] = Integer.toString(item.getIdrol());
            registro[1] = item.getNombre();
            registro[2] = item.getDescripcion();
            //Agrega el registro al modelo de tabla
            this.model.addRow(registro);
            this.totalMostrados += 1;
        }
        return this.model;
    }
        public int count() {
        return DATOS.count();
    }

    public int showAll() {
        return this.totalMostrados;
    }
    
}
