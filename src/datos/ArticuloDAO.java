package datos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.sql.*;

import database.Conexion;
import datos.interfaces.CrudPaginado;
import entidades.Articulo;

public class ArticuloDAO implements CrudPaginado<Articulo> {
    //Variable de conexión

    private final Conexion CON;
    //Prepared Statement
    private PreparedStatement ps;
    private ResultSet rs;

    //Variable para metodos
    private boolean resp;

    //Constructor
    public ArticuloDAO() {
        CON = Conexion.getInstancia();
    }

    //Métodos CRUD
    @Override
    public List<Articulo> list(String texto, int totalPerPagina,int numPagina) {
        List<Articulo> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("select a.*, c.nombre as categoria from articulo as a inner join categoria as c on a.categoria_id = c.idcategoria "+
                    "where a.nombre LIKE ? order by a.idarticulo asc limit ? , ?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, (numPagina-1)*totalPerPagina);
            ps.setInt(3, totalPerPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Articulo(rs.getInt(1),rs.getInt(2),rs.getString(10),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getBoolean(9)));
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
    public List<Articulo> listForSale(String texto, int totalPerPagina,int numPagina) {
        List<Articulo> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("select a.*, c.nombre as categoria from articulo as a inner join categoria as c on a.categoria_id = c.idcategoria "+
                    "where a.nombre LIKE ? and a.stock>0 and a.activo = true order by a.idarticulo asc limit ? , ?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, (numPagina-1)*totalPerPagina);
            ps.setInt(3, totalPerPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Articulo(rs.getInt(1),rs.getInt(2),rs.getString(10),rs.getString(3),rs.getString(4),rs.getDouble(5),rs.getInt(6),rs.getString(7),rs.getString(8),rs.getBoolean(9)));
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
    public Articulo obtenerPorCod(String codigo){
        Articulo art = null;
        try {
            ps = CON.conectar().prepareStatement("select idarticulo,codigo,nombre,precio_venta,stock from articulo where codigo= ?");
            ps.setString(1,  codigo);
            rs = ps.executeQuery();
            if(rs.first()){
                art = new Articulo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5));
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
        return art;
    }
    public Articulo obtenerPorCodVenta(String codigo){
        Articulo art = null;
        try {
            ps = CON.conectar().prepareStatement("select idarticulo,codigo,nombre,precio_venta,stock from articulo where codigo= ? and stock>0 and activo = true");
            ps.setString(1,  codigo);
            rs = ps.executeQuery();
            if(rs.first()){
                art = new Articulo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getInt(5));
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
        return art;
    }
    @Override
    public boolean insert(Articulo obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO articulo (categoria_id,codigo,nombre,precio_venta,stock,descripcion,imagen,activo) values(?,?,?,?,?,?,?,1)");
            ps.setInt(1, obj.getCategoria_id());
            ps.setString(2, obj.getCodigo());
            ps.setString(3, obj.getNombre());
            ps.setDouble(4, obj.getPrecio_venta());
            ps.setInt(5, obj.getStock());
            ps.setString(6, obj.getDescripcion());
            ps.setString(7, obj.getImagen());
            
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
    public boolean update(Articulo obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE articulo SET categoria_id=?, codigo=?, nombre = ?,precio_venta=?,stock=? , descripcion = ?, imagen=? where idarticulo =?");
            ps.setInt(1, obj.getCategoria_id());
            ps.setString(2, obj.getCodigo());
            ps.setString(3, obj.getNombre());
            ps.setDouble(4, obj.getPrecio_venta());
            ps.setInt(5, obj.getStock());
            ps.setString(6, obj.getDescripcion());
            ps.setString(7, obj.getImagen());
            ps.setInt(8, obj.getIdarticulo());
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
            ps = CON.conectar().prepareStatement("UPDATE articulo SET activo = 0 where idarticulo =?");
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
            ps = CON.conectar().prepareStatement("UPDATE articulo SET activo = 1 where idarticulo =?");
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
            ps = CON.conectar().prepareStatement("select count(idarticulo) as total from articulo");
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
            ps = CON.conectar().prepareStatement("select nombre from articulo where nombre = ?");
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
