/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.mysql.jdbc.Statement;
import database.Conexion;
import datos.interfaces.CrudVenta;
import entidades.DetalleVenta;
import entidades.Venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alonso Romero
 */
public class VentaDAO implements CrudVenta<Venta, DetalleVenta> {

    //Variable de conexión
    private final Conexion CON;
    //Prepared Statement
    private PreparedStatement ps;
    private ResultSet rs;
    //Variable para metodos
    private boolean resp;

    public VentaDAO() {
        this.CON = Conexion.getInstancia();
    }

    @Override
    public List<Venta> list(String texto, int totalPerPagina, int numPagina) {
        List<Venta> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("SELECT v.*, p.nombre persona, u.nombre usuario FROM venta v "
                    + "INNER JOIN persona p ON v.persona_id = p.idpersona INNER JOIN usuario u ON v.usuario_id = u.idusuario "
                    + "WHERE v.num_comprobante LIKE ? ORDER BY v.fecha DESC LIMIT ?,?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, (numPagina - 1) * totalPerPagina);
            ps.setInt(3, totalPerPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Venta(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(11), rs.getString(12), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10)));
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
    public List<DetalleVenta> listDetail(int id) {
        List<DetalleVenta> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("SELECT a.idarticulo, a.codigo,a.nombre AS articulo, stock,cantidad, dv.precio, descuento,((cantidad*dv.precio)-descuento) AS subtotal FROM detalle_venta dv "
                    + "INNER JOIN articulo a ON dv.articulo_id = a.idarticulo where dv.venta_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new DetalleVenta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8)));
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
    public boolean insert(Venta obj) {
        resp = false;
        Connection conn = null;
        try {
            conn = CON.conectar();
            conn.setAutoCommit(false);
            String insert = "INSERT INTO venta (persona_id,usuario_id,tipo_comprobante,serie_comprobante,num_comprobante,fecha,impuesto,total) values(?,?,?,?,?,now(),?,?)";
            ps = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, obj.getIdPersona());
            ps.setInt(2, obj.getIdUsuario());
            ps.setString(3, obj.getTipoComprobante());
            ps.setString(4, obj.getSerieComprobante());
            ps.setString(5, obj.getNumComprobante());
            ps.setDouble(6, obj.getImpuesto());
            ps.setDouble(7, obj.getTotal());

            int filasAfectadas = ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            int idGen = 0;

            if (rs.next()) {
                idGen = rs.getInt(1);
            }

            if (filasAfectadas == 1) {
                String insertDet = "INSERT INTO detalle_venta (venta_id,articulo_id,cantidad,precio, descuento) values(?,?,?,?,?)";
                ps = conn.prepareStatement(insertDet);
                for (DetalleVenta item : obj.getDetalles()) {
                    ps.setInt(1, idGen);
                    ps.setInt(2, item.getArticulo_id());
                    ps.setInt(3, item.getCantidad());
                    ps.setDouble(4, item.getPrecio());
                    ps.setDouble(5, item.getDescuento());
                    resp = ps.executeUpdate() > 0;
                }
                conn.commit();
            } else {
                conn.rollback();
            }
            ps.close();
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback();
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } catch (SQLException ex) {
                Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    rs.close();
                }
                if (conn != null);
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(VentaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resp;
    }

    @Override
    public boolean desactivate(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE venta SET estado = 'Anulado' where idventa =?");
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
            ps = CON.conectar().prepareStatement("select count(idventa) as total from venta");
            rs = ps.executeQuery();
            while (rs.next()) {
                totales = rs.getInt("total");
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
    public boolean exist(String texto1, String texto2) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("select idventa from venta where serie_comprobante = ? and num_comprobante = ?");
            ps.setString(1, texto1);
            ps.setString(2, texto2);
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

    //Obtener ultimo # de serie
    public String ultimoSerie(String tipoComprobante) {
        String serieComprobante = "";
        try {
            ps = CON.conectar().prepareStatement("SELECT serie_comprobante FROM venta where tipo_comprobante=? order by serie_comprobante desc limit 1");
            ps.setString(1, tipoComprobante);
            rs = ps.executeQuery();

            while (rs.next()) {
                serieComprobante = rs.getString("serie_comprobante");
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
        return serieComprobante;
    }

    //Ultimo número de comprobante
    public String ultimoNumero(String tipoComprobante, String serieComprobante) {
        String numComprobante = "";
        try {
            ps = CON.conectar().prepareStatement("SELECT num_comprobante FROM venta WHERE tipo_comprobante=? AND serie_comprobante=? order by num_comprobante desc limit 1");
            ps.setString(1, tipoComprobante);
            ps.setString(2, serieComprobante);
            rs = ps.executeQuery();

            while (rs.next()) {
                numComprobante = rs.getString("num_comprobante");
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
        return numComprobante;
    }
    public List<Venta> consultaFechas(Date fechaInicio, Date fechaFin) {
        List<Venta> registros=new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT v.idventa,v.usuario_id,u.nombre AS usuario_nombre,v.persona_id,p.nombre AS persona_nombre,v.tipo_comprobante,v.serie_comprobante,v.num_comprobante,v.fecha,v.impuesto,v.total,v.estado FROM venta v INNER JOIN persona p ON v.persona_id=p.idpersona INNER JOIN usuario u ON v.usuario_id=u.idusuario WHERE v.fecha>=? AND v.fecha<=?");
            ps.setDate(1,fechaInicio);            
            ps.setDate(2,fechaFin);
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Venta(rs.getInt(1),rs.getInt(4),rs.getInt(2),rs.getString(5),rs.getString(3),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9),rs.getDouble(10),rs.getDouble(11),rs.getString(12)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }

}
