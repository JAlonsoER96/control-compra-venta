/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;
//Imports

import datos.ArticuloDAO;
import datos.CategoriaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import entidades.Articulo;
import entidades.Categoria;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Alonso Romero
 */
public class ControlArticulo {

    private final ArticuloDAO DATOS;
    private final CategoriaDAO DATOSCAT;
    private final Articulo art;
    private DefaultTableModel model;
    private int totalMostrados;

    //Constructor
    public ControlArticulo() {
        this.DATOS = new ArticuloDAO();
        this.DATOSCAT = new CategoriaDAO();
        this.art = new Articulo();
        this.totalMostrados = 0;
    }
//Control categoria

    public DefaultTableModel listar(String texto, int totalPerPagina, int numPagina) {
        List<Articulo> lista = new ArrayList();
        lista.addAll(DATOS.list(texto, totalPerPagina, numPagina));
        //Encabezados de tabla
        String[] titulos = {"ID", "Categoría Id", "Categoría", "Codígo", "Nombre", "Precio venta", "Stock", "Descripción", "Imagen", "Estado"};
        //Instanción modelo tabla
        this.model = new DefaultTableModel(null, titulos);
        String estado;
        String[] registro = new String[10];
        this.totalMostrados = 0;
        for (Articulo item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }
            registro[0] = Integer.toString(item.getIdarticulo());
            registro[1] = Integer.toString(item.getCategoria_id());
            registro[2] = item.getNombreCAtegoria();
            registro[3] = item.getCodigo();
            registro[4] = item.getNombre();
            registro[5] = Double.toString(item.getPrecio_venta());
            registro[6] = Integer.toString(item.getStock());
            registro[7] = item.getDescripcion();
            registro[8] = item.getImagen();
            registro[9] = estado;
            //Agrega el registro al modelo de tabla
            this.model.addRow(registro);
            this.totalMostrados += 1;
        }
        return this.model;
    }
    public DefaultComboBoxModel select(){
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        List<Categoria> lista = new ArrayList();
        lista = DATOSCAT.select();
        for(Categoria item: lista){
            items.addElement(new Categoria(item.getIdcategoria(),item.getNombre()));
        }
        return items;
    }

    public String insert(int categoria_id, String codigo, String nombre, double precio_venta, int stock, String descripcion, String imagen) {
        if (DATOS.exist(nombre)) {
            return "El registro ya existe";
        } else {
            art.setCategoria_id(categoria_id);
            art.setCodigo(codigo);
            art.setNombre(nombre);
            art.setPrecio_venta(precio_venta);
            art.setStock(stock);
            art.setDescripcion(descripcion);
            art.setImagen(imagen);
            if (DATOS.insert(art)) {
                return "OK";
            } else {
                return "Error al insertar registro";
            }
        }

    }

    public String update(int id, int categoria_id, String codigo, String nombre, String nombreAnt, double precio_venta, int stock, String descripcion, String imagen) {
        if (nombre.equals(nombreAnt)) {
            art.setIdarticulo(id);
            art.setCategoria_id(categoria_id);
            art.setCodigo(codigo);
            art.setNombre(nombre);
            art.setPrecio_venta(precio_venta);
            art.setStock(stock);
            art.setDescripcion(descripcion);
            art.setImagen(imagen);

            if (DATOS.update(art)) {
                return "OK";
            } else {
                return "Error al actualizar";
            }
        } else {
            if (DATOS.exist(nombre)) {
                return "El registro ya  existe";

            } else {
                art.setIdarticulo(id);
                art.setCodigo(codigo);
                art.setNombre(nombre);
                art.setPrecio_venta(precio_venta);
                art.setStock(stock);
                art.setDescripcion(descripcion);
                art.setImagen(imagen);
                if (DATOS.update(art)) {
                    return "OK";
                } else {
                    return "Error al actualizar";
                }
            }
        }
    }

    public String desactivate(int id) {
        if (DATOS.desactivate(id)) {
            return "OK";
        } else {
            return "Error al actualizar";
        }

    }

    public String activate(int id) {
        if (DATOS.activate(id)) {
            return "OK";
        } else {
            return "Error al actualizar";
        }

    }

    public int count() {
        return DATOS.count();
    }

    public int showAll() {
        return this.totalMostrados;
    }
}
