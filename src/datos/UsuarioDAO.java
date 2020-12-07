package datos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.sql.*;

import database.Conexion;
import datos.interfaces.CrudPaginado;
import entidades.Articulo;
import entidades.Usuario;

public class UsuarioDAO implements CrudPaginado<Usuario> {
    //Variable de conexión

    private final Conexion CON;
    //Prepared Statement
    private PreparedStatement ps;
    private ResultSet rs;

    //Variable para metodos
    private boolean resp;

    //Constructor
    public UsuarioDAO() {
        CON = Conexion.getInstancia();
    }

    //Métodos CRUD
    @Override
    public List<Usuario> list(String texto, int totalPerPagina,int numPagina) {
        List<Usuario> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("select u.*, r.nombre as rol from usuario as u inner join rol as r on u.rol_id = r.idrol "+
                    "where u.nombre LIKE ? order by u.idusuario asc limit ? , ?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, (numPagina-1)*totalPerPagina);
            ps.setInt(3, totalPerPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Usuario(rs.getInt(1),rs.getInt(2),rs.getString(11),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getBoolean(10)));
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
    public boolean insert(Usuario obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO usuario (rol_id,nombre,tipo_documento,num_documento,direccion,telefono,email,clave,activo) values(?,?,?,?,?,?,?,?,1)");
            ps.setInt(1, obj.getRol_id());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getTipo_documento());
            ps.setString(4, obj.getNum_documento());
            ps.setString(5, obj.getDireccion());
            ps.setString(6, obj.getTelefono());
            ps.setString(7, obj.getEmail());
            ps.setString(8, obj.getClave());
            
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
    public boolean update(Usuario obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE usuario SET rol_id=?,nombre=?,tipo_documento=?,num_documento=?,direccion=?,telefono=?,email=?,clave=? where idusuario =?");
            ps.setInt(1, obj.getRol_id());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getTipo_documento());
            ps.setString(4, obj.getNum_documento());
            ps.setString(5, obj.getDireccion());
            ps.setString(6, obj.getTelefono());
            ps.setString(7, obj.getEmail());
            ps.setString(8, obj.getClave());
            ps.setInt(9, obj.getIdusuario());
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
            ps = CON.conectar().prepareStatement("UPDATE usuario SET activo = 0 where idusuario =?");
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
            ps = CON.conectar().prepareStatement("UPDATE usuario SET activo = 1 where idusuario =?");
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
            ps = CON.conectar().prepareStatement("select count(idusuario) as total from usuario");
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
            ps = CON.conectar().prepareStatement("select email from usuario where email = ?");
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
