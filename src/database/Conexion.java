/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
//Imports

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Alonso Romero
 */
public class Conexion {
    private final String DRIVER="com.mysql.jdbc.Driver";
    //private final String URL="jdbc:mysql://localhost:3306/";
    private final String HOST="localhost";
    private final int PUERTO=3306;
    private final String DB="dbsistema";
    private final String USER="dbsistemaadmin";
    private final String PASSWORD="dbsistemap4$$";
    private final String URL=  String.format("jdbc:mysql://%s:%d/%s?useSSL=false", HOST,PUERTO,DB);  //"jdbc:mysql://localhost:3306/";
    
    public Connection cadena;
    public static Conexion instancia;
    
    private Conexion() {
        this.cadena = null;
    }
    
    //Conectar/Desconectar métodos
    public Connection conectar() {
        try {
            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection(URL,USER,PASSWORD);
            
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
        return this.cadena;
    }
    public void desconectar() {
        try {
            this.cadena.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
    public synchronized static Conexion getInstancia(){
        if (instancia==null) {
            instancia = new Conexion();
        }
        return instancia;
    }
}
