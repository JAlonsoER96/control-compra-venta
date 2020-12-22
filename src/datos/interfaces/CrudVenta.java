/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos.interfaces;

import java.util.List;

/**
 *
 * @author Alonso Romero
 * @param <T> venta
 * @param <D> detalle_venta
 */
public interface CrudVenta <T,D>{
    //Listar datos
    public List<T> list(String texto, int totalPerPagina, int numPagina);
    //listar Detalles
    public List<D> listDetail(int id);
    //guardar
    public boolean insert(T obj);
    //Anular ingreso
    public boolean desactivate(int id);
    //Total registros
    public int count();
    //validar registro
    public boolean exist(String texto1, String texto2);
}
