/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import database.Conexion;
import entidades.Rol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Alonso Romero
 */
public class RolDAO {
     //Variable de conexión

    private final Conexion CON;
    //Prepared Statement
    private PreparedStatement ps;
    private ResultSet rs;

    //Variable para metodos
    private boolean resp;
      
    //Constructo vacio
    public RolDAO() {
        CON = Conexion.getInstancia();
    }
    
    public List<Rol> list() {
        List<Rol> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("select * from rol");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Rol(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }
    public List<Rol> select() {
        List<Rol> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("select idrol,nombre from rol order by nombre asc");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Rol(rs.getInt(1), rs.getString(2)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return registros;
    }
    //tptal registros
    public int count() {
        int totales = 0;
        try {
            ps = CON.conectar().prepareStatement("select count(idrol) as total from rol");
            rs = ps.executeQuery();
            while(rs.next()){
                totales=rs.getInt("total");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return totales;
    }
}
