/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.mysql.jdbc.Statement;
import database.Conexion;
import datos.interfaces.CrudIngreso;
import entidades.DetalleIngreso;
import entidades.Ingreso;
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
public class IngresoDAO implements CrudIngreso<Ingreso, DetalleIngreso> {

    //Variable de conexión
    private final Conexion CON;
    //Prepared Statement
    private PreparedStatement ps;
    private ResultSet rs;
    //Variable para metodos
    private boolean resp;

    public IngresoDAO() {
        this.CON = Conexion.getInstancia();
    }

    @Override
    public List<Ingreso> list(String texto, int totalPerPagina, int numPagina) {
        List<Ingreso> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("SELECT i.*,p.nombre persona,u.nombre usuario FROM ingreso i "
                    + "INNER JOIN persona p ON i.persona_id=p.idpersona INNER JOIN usuario u ON i.usuario_id=u.idusuario WHERE i.num_comprobante LIKE ? "
                    + "ORDER BY i.fecha DESC LIMIT ?,? ");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, (numPagina - 1) * totalPerPagina);
            ps.setInt(3, totalPerPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Ingreso(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(11), rs.getString(12), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10)));
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
    public List<DetalleIngreso> listDetail(int id) {
        List<DetalleIngreso> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement("select a.idarticulo,a.codigo,a.nombre as articulo, di.cantidad,di.precio, (di.cantidad*di.precio) as subtotal from detalle_ingreso di inner join articulo a on di.articulo_id = a.idarticulo  where "+
                    "di.ingreso_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new DetalleIngreso(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDouble(5),rs.getDouble(6)));
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
    public boolean insert(Ingreso obj) {
        resp = false;
        Connection conn = null;
        try {
            conn = CON.conectar();
            conn.setAutoCommit(false);
            String insert = "INSERT INTO ingreso (persona_id,usuario_id,tipo_comprobante,serie_comprobante,num_comprobante,fecha,impuesto,total) values(?,?,?,?,?,now(),?,?)";
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
                String insertDet = "INSERT INTO detalle_ingreso (ingreso_id,articulo_id,cantidad,precio) values(?,?,?,?)";
                ps = conn.prepareStatement(insertDet);
                for (DetalleIngreso item : obj.getDetalles()) {
                    ps.setInt(1, idGen);
                    ps.setInt(2, item.getArticulo_id());
                    ps.setInt(3, item.getCantidad());
                    ps.setDouble(4, item.getPrecio());
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
                Logger.getLogger(IngresoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(IngresoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return resp;
    }

    @Override
    public boolean desactivate(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE ingreso SET estado = 'Anulado' where idingreso =?");
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
            ps = CON.conectar().prepareStatement("select count(idingreso) as total from ingreso");
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
            ps = CON.conectar().prepareStatement("select idingreso from ingreso where serie_comprobante = ? and num_comprobante = ?");
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
    public List<Ingreso> consultaFechas(Date fechaInicio, Date fechaFin) {
        List<Ingreso> registros=new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT i.idingreso,i.usuario_id,u.nombre AS usuario_nombre,i.persona_id,p.nombre AS persona_nombre,i.tipo_comprobante,i.serie_comprobante,i.num_comprobante,i.fecha,i.impuesto,i.total,i.estado FROM ingreso i INNER JOIN persona p ON i.persona_id=p.idpersona INNER JOIN usuario u ON i.usuario_id=u.idusuario WHERE i.fecha>=? AND i.fecha<=?");
            ps.setDate(1,fechaInicio);            
            ps.setDate(2,fechaFin);
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Ingreso(rs.getInt(1),rs.getInt(4),rs.getInt(2),rs.getString(5),rs.getString(3),rs.getString(6),rs.getString(7),rs.getString(8),rs.getDate(9),rs.getDouble(10),rs.getDouble(11),rs.getString(12)));
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
