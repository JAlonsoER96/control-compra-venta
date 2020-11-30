package datos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.sql.*;

import database.Conexion;
import entidades.Categoria;
import datos.interfaces.Crud;

public class CategoriaDAO implements Crud<Categoria> {
    //Variable de conexión

    private final Conexion CON;
    //Prepared Statement
    private PreparedStatement ps;
    private ResultSet rs;

    //Variable para metodos
    private boolean resp;

    //Constructor
    public CategoriaDAO() {
        CON = Conexion.getInstancia();
    }

    //Métodos CRUD
    @Override
    public List<Categoria> list(String texto) {
        List<Categoria> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("select * from categoria where nombre like ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
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
    public boolean insert(Categoria obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO categoria (nombre,descripcion,activo) values(?,?,1)");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
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
    public boolean update(Categoria obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE categoria SET nombre = ?, descripcion = ? where id =?");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setInt(3, obj.getIdcategoria());
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
            ps = CON.conectar().prepareStatement("UPDATE categoria SET activo = 0 where id =?");
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
            ps = CON.conectar().prepareStatement("UPDATE categoria SET activo = 1 where id =?");
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
            ps = CON.conectar().prepareStatement("select count(id) as total from categoria");
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
            ps = CON.conectar().prepareStatement("select nombre from categoria where nombre = ?");
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
    public List<Categoria> select() {
        List<Categoria> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("select id,nombre from categoria order by nombre asc");
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Categoria(rs.getInt(1), rs.getString(2)));
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

}
