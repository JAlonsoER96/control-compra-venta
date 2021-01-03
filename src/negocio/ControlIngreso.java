/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;
//Imports
import database.Conexion;
import datos.ArticuloDAO;
import datos.IngresoDAO;
import entidades.Articulo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import entidades.Categoria;
import entidades.DetalleIngreso;
import entidades.Ingreso;
import entidades.Venta;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Alonso Romero
 */
public class ControlIngreso {

    private final IngresoDAO DATOS;
    private final ArticuloDAO DATOS_ART;
    private final Ingreso art;
    private DefaultTableModel model;
    private int totalMostrados;

    //Constructor
    public ControlIngreso() {
        this.DATOS = new IngresoDAO();
        DATOS_ART = new ArticuloDAO();
        this.art = new Ingreso();
        this.totalMostrados = 0;
    }
//Control categoria

    public DefaultTableModel listar(String texto, int totalPerPagina, int numPagina) {
        List<Ingreso> lista = new ArrayList();
        lista.addAll(DATOS.list(texto, totalPerPagina, numPagina));
        //Encabezados de tabla
        String[] titulos = {"ID", "Persona Id","Usuario Id", "Proveedor", "Usuario", "Tipo Comprobante", "Serie comprobante","Número Comprobante", "Fecha", "Impuesto", "Total","Estado"};
        //Instanción modelo tabla
        this.model = new DefaultTableModel(null, titulos);
        String[] registro = new String[12];
        SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyy");
        this.totalMostrados = 0;
        for (Ingreso item : lista) {
            registro[0] = Integer.toString(item.getIdIngreso());
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
        List<DetalleIngreso> lista = new ArrayList();
        lista.addAll(DATOS.listDetail(id));
        //Encabezados de tabla
        String[] titulos = {"ID", "Código","Artículo", "Cantidad", "Precio", "Subtotal"};
        //Instanción modelo tabla
        this.model = new DefaultTableModel(null, titulos);
        String[] registro = new String[6];
        this.totalMostrados = 0;
        for (DetalleIngreso item : lista) {
            registro[0] = Integer.toString(item.getArticulo_id());
            registro[1] = item.getCodigoArt();
            registro[2] = item.getNombreArt();
            registro[3] = Integer.toString(item.getCantidad());
            registro[4] = Double.toString(item.getPrecio());
            registro[5] = Double.toString(item.getSubTotal());
            //Agrega el registro al modelo de tabla
            this.model.addRow(registro);
            this.totalMostrados += 1;
        }
        return this.model;
    }
    public Articulo obtenerPorCod(String codigo){
        Articulo art =  DATOS_ART.obtenerPorCod(codigo);
        return art;
    }
    public DefaultComboBoxModel select(){
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Categoria> lista = new ArrayList();
        for(Categoria item: lista){
            items.addElement(new Categoria(item.getIdcategoria(),item.getNombre()));
        }
        return items;
    }

    public String insert(int persona_id,String tipo_comprobante, String serie_comprobante,String num_comprobante, double impuesto, double total, DefaultTableModel modelDetails) {
        if (DATOS.exist(serie_comprobante,num_comprobante)) {
            return "El registro ya existe";
        } else {
            art.setIdPersona(persona_id);
            art.setIdUsuario(VariablesLogin.userID);
            art.setTipoComprobante(tipo_comprobante);
            art.setSerieComprobante(serie_comprobante);
            art.setNumComprobante(num_comprobante);
            art.setImpuesto(impuesto);
            art.setTotal(total);
            List<DetalleIngreso> detalles = new ArrayList();
            int articulo_id;
            int cantidad;
            double precio;
            for (int i = 0; i < modelDetails.getRowCount(); i++) {
                articulo_id = Integer.parseInt(String.valueOf(modelDetails.getValueAt(i, 0)));
                cantidad = Integer.parseInt(String.valueOf(modelDetails.getValueAt(i, 3)));
                precio = Double.parseDouble(String.valueOf(modelDetails.getValueAt(i, 4)));
                detalles.add(new DetalleIngreso(articulo_id,cantidad,precio));
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
    public void reporte(String idingreso){
        Map p = new HashMap();
        p.put("idingreso", idingreso);
        JasperReport report;
        JasperPrint print;
        Conexion cnn = Conexion.getInstancia();
        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()+"/src/reportes/rptIngreso.jrxml");
            print = JasperFillManager.fillReport(report, p,cnn.conectar());
            JasperViewer view = new JasperViewer(print,false);
            view.setTitle("Reporte ingreso");
            view.setVisible(true);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public DefaultTableModel consultaFechas(Date fechaInicio, Date fechaFin){
        List<Ingreso> lista=new ArrayList();
        lista.addAll(DATOS.consultaFechas(fechaInicio,fechaFin));
        
        String[] titulos={"Id","Usuario ID","Usuario","Cliente ID","Cliente","Tipo Comprobante","Serie","Número","Fecha","Impuesto","Total","Estado"};
        this.model=new DefaultTableModel(null,titulos);        
        
        String[] registro = new String[12];
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
        
        this.totalMostrados=0;
        for (Ingreso item:lista){
            registro[0]=Integer.toString(item.getIdIngreso());
            registro[1]=Integer.toString(item.getIdUsuario());
            registro[2]=item.getNombreUsuario();
            registro[3]=Integer.toString(item.getIdPersona());
            registro[4]=item.getNombrePersona();
            registro[5]=item.getTipoComprobante();
            registro[6]=item.getSerieComprobante();
            registro[7]=item.getNumComprobante();
            registro[8]=sdf.format(item.getFecha());
            registro[9]=Double.toString(item.getImpuesto());
            registro[10]=Double.toString(item.getTotal());
            registro[11]=item.getEstado();
            
            this.model.addRow(registro);
            this.totalMostrados=this.totalMostrados+1;
        }
        return this.model;
    }
}
