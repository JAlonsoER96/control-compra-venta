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
 * @param <T>
 */
public interface Crud<T> {

    //Listar datos
    public List<T> list(String texto);
    //guardar

    public boolean insert(T obj);
    //update

    public boolean update(T obj);
    //Eliminación lógica

    public boolean desactivate(int id);

    public boolean activate(int id);
    //Total registros

    public int count();
    //validar registro

    public boolean exist(String texto);
}
