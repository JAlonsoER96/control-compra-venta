/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;
//Imports

import datos.ArticuloDAO;
import datos.VentaDAO;
import entidades.Articulo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import entidades.Categoria;
import entidades.DetalleVenta;
import entidades.Venta;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Alonso Romero
 */
public class ControlVenta {

    private final VentaDAO DATOS;
    private final ArticuloDAO DATOS_ART;
    private final Venta art;
    private DefaultTableModel model;
    private int totalMostrados;

    //Constructor
    public ControlVenta() {
        this.DATOS = new VentaDAO();
        DATOS_ART = new ArticuloDAO();
        this.art = new Venta();
        this.totalMostrados = 0;
    }
//Control categoria

    public DefaultTableModel listar(String texto, int totalPerPagina, int numPagina) {
        List<Venta> lista = new ArrayList();
        lista.addAll(DATOS.list(texto, totalPerPagina, numPagina));
        //Encabezados de tabla
        String[] titulos = {"ID", "Persona Id", "Usuario Id", "Cliente", "Usuario", "Tipo Comprobante", "Serie comprobante", "Número Comprobante", "Fecha", "Impuesto", "Total", "Estado"};
        //Instanción modelo tabla
        this.model = new DefaultTableModel(null, titulos);
        String[] registro = new String[12];
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        this.totalMostrados = 0;
        for (Venta item : lista) {
            registro[0] = Integer.toString(item.getIdVenta());
            registro[1] = Integer.toString(item.getIdPersona());
            registro[2] = Integer.toString(item.getIdUsuario());
            registro[3] = item.getNombrePersona();
            registro[4] = item.getNombreUsuario();
            registro[5] = item.getTipoComprobante();
            registro[6] = item.getSerieComprobante();
            registro[7] = item.getNumComprobante();
            registro[8] = sdf.format(item.getFecha());
            registro[9] = Double.toString(item.getImpuesto());
            registro[10] = Double.toString(item.getTotal());
            registro[11] = item.getEstado();
            //Agrega el registro al modelo de tabla
            this.model.addRow(registro);
            this.totalMostrados += 1;
        }
        return this.model;
    }

    public DefaultTableModel listarDetalle(int id) {
        List<DetalleVenta> lista = new ArrayList();
        lista.addAll(DATOS.listDetail(id));
        //Encabezados de tabla
        String[] titulos = {"ID", "Código", "Artículo", "Stock", "Cantidad", "Precio", "Descuento", "Subtotal"};
        //Instanción modelo tabla
        this.model = new DefaultTableModel(null, titulos);
        String[] registro = new String[8];
        this.totalMostrados = 0;
        for (DetalleVenta item : lista) {
            registro[0] = Integer.toString(item.getArticulo_id());
            registro[1] = item.getCodigoArt();
            registro[2] = item.getNombreArt();
            registro[3] = Integer.toString(item.getArticuloStock());
            registro[4] = Integer.toString(item.getCantidad());
            registro[5] = Double.toString(item.getPrecio());
            registro[6] = Double.toString(item.getDescuento());
            registro[7] = Double.toString(item.getSubTotal());
            //Agrega el registro al modelo de tabla
            this.model.addRow(registro);
            this.totalMostrados += 1;
        }
        return this.model;
    }

    public Articulo obtenerPorCod(String codigo) {
        Articulo art = DATOS_ART.obtenerPorCodVenta(codigo);
        return art;
    }
    public Articulo obtenerPorCodVenta(String codigo) {
        Articulo artV = DATOS_ART.obtenerPorCodVenta(codigo);
        return artV;
    }

    public DefaultComboBoxModel select() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Categoria> lista = new ArrayList();
        for (Categoria item : lista) {
            items.addElement(new Categoria(item.getIdcategoria(), item.getNombre()));
        }
        return items;
    }

    public String insert(int persona_id, String tipo_comprobante, String serie_comprobante, String num_comprobante, double impuesto, double total, DefaultTableModel modelDetails) {
        if (DATOS.exist(serie_comprobante, num_comprobante)) {
            return "El registro ya existe";
        } else {
            art.setIdPersona(persona_id);
            art.setIdUsuario(VariablesLogin.userID);
            art.setTipoComprobante(tipo_comprobante);
            art.setSerieComprobante(serie_comprobante);
            art.setNumComprobante(num_comprobante);
            art.setImpuesto(impuesto);
            art.setTotal(total);
            List<DetalleVenta> detalles = new ArrayList();
            int articulo_id;
            int cantidad;
            int stock;
            double precio;
            double descuento;
            for (int i = 0; i < modelDetails.getRowCount(); i++) {
                articulo_id = Integer.parseInt(String.valueOf(modelDetails.getValueAt(i, 0)));
                stock = Integer.parseInt(String.valueOf(modelDetails.getValueAt(i, 3)));
                cantidad = Integer.parseInt(String.valueOf(modelDetails.getValueAt(i, 4)));
                precio = Double.parseDouble(String.valueOf(modelDetails.getValueAt(i, 5)));
                descuento = Double.parseDouble(String.valueOf(modelDetails.getValueAt(i, 6)));
                detalles.add(new DetalleVenta(articulo_id, cantidad, precio, descuento));
            }
            art.setDetalles(detalles);
            if (DATOS.insert(art)) {
                return "OK";
            } else {
                return "Error al insertar registro";
            }
        }

    }

    public String desactivate(int id) {
        if (DATOS.desactivate(id)) {
            return "OK";
        } else {
            return "No se puede anular el ingreso";
        }

    }

    public int count() {
        return DATOS.count();
    }

    public int showAll() {
        return this.totalMostrados;
    }
    //Obtiene el ultimo número de serie emitido
    public String ultimoSerie(String tipoComprobante) {
        return this.DATOS.ultimoSerie(tipoComprobante);
    }
    //Obtiene el ultimo número de comprobnte emitido
    public String ultimoNumero(String tipoComprobante,String serieComprobante) {
        return this.DATOS.ultimoNumero(tipoComprobante, serieComprobante);
    }
}
