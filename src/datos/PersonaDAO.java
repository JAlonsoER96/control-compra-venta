package datos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.sql.*;

import database.Conexion;
import datos.interfaces.CrudPaginado;
import entidades.Persona;

public class PersonaDAO implements CrudPaginado<Persona> {
    //Variable de conexión

    private final Conexion CON;
    //Prepared Statement
    private PreparedStatement ps;
    private ResultSet rs;

    //Variable para metodos
    private boolean resp;

    //Constructor
    public PersonaDAO() {
        CON = Conexion.getInstancia();
    }

    //Métodos CRUD
    @Override
    public List<Persona> list(String texto, int totalPerPagina,int numPagina) {
        List<Persona> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("select * from persona as p "+
                    "where p.nombre LIKE ? order by p.idpersona asc limit ? , ?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, (numPagina-1)*totalPerPagina);
            ps.setInt(3, totalPerPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Persona(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getBoolean(9)));
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
    public List<Persona> listT(String texto, int totalPerPagina,int numPagina, String tipo) {
        List<Persona> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("select * from persona as p "+
                    "where p.nombre LIKE ? and tipo_persona = ? order by p.idpersona asc limit ? , ?");
            ps.setString(1, "%" + texto + "%");
            ps.setString(2, tipo);
            ps.setInt(3, (numPagina-1)*totalPerPagina);
            ps.setInt(4, totalPerPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Persona(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getBoolean(9)));
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

    @Override
    public boolean insert(Persona obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO persona (tipo_persona,nombre,tipo_documento,num_documento,direccion,telefono,email,activo) values(?,?,?,?,?,?,?,1)");
            ps.setString(1, obj.getTipo_persona());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getTipo_documento());
            ps.setString(4, obj.getNum_documento());
            ps.setString(5, obj.getDireccion());
            ps.setString(6, obj.getTelefono());
            ps.setString(7, obj.getEmail());
            
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean update(Persona obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE persona SET tipo_persona=?,nombre=?,tipo_documento=?,num_documento=?,direccion=?,telefono=?,email=? where idpersona =?");
            ps.setString(1, obj.getTipo_persona());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getTipo_documento());
            ps.setString(4, obj.getNum_documento());
            ps.setString(5, obj.getDireccion());
            ps.setString(6, obj.getTelefono());
            ps.setString(7, obj.getEmail());
            ps.setInt(8, obj.getIdPersona());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean desactivate(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE persona SET activo = 0 where idpersona =?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean activate(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE persona SET activo = 1 where idpersona =?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public int count() {
        int totales = 0;
        try {
            ps = CON.conectar().prepareStatement("select count(idpersona) as total from persona");
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

    @Override
    public boolean exist(String texto) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("select nombre from persona where nombre = ?");
            ps.setString(1, texto);
            rs = ps.executeQuery();
            rs.last();
            if (rs.getRow() > 0) {
                resp = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return resp;
    }


}
