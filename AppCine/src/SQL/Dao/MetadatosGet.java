/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import static SQL.Conexion.sql.getCon_mysql_jdbc;
import static SQL.Conexion.sql.getCon_sql;
import SQL.Ventanas.Metadatos;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 9fdam07
 */
public class MetadatosGet extends SQL.Conexion.sql {

    public Metadatos ventanaMetadatos;

    private static final String mysqlConector = "mysql";
    private static final String sqliteConector = "sqlite";

    public MetadatosGet(Metadatos ventana) {
        this.ventanaMetadatos = ventana;
    }

    public void getMetadatos(String conector) {
        Connection conexion = null;
        try {

            if (conector.equalsIgnoreCase("mysql")) {
                conexion = getCon_mysql_jdbc();
            } else {
                conexion = getCon_sql();
            }
            DatabaseMetaData dbmd = conexion.getMetaData();//Creamos objeto DatabaseMetaData

            String nombre = dbmd.getDatabaseProductName();
            String url = dbmd.getURL();
            String driver = dbmd.getDriverName();
            String usuario = dbmd.getUserName();

            // ventanaMetadatos.areaDeTextoMetadata.setText("");
            ventanaMetadatos.areaDeTextoMetadata.setText("");//limpiarlo            
            ventanaMetadatos.areaDeTextoMetadata.append("INFORMACIÓN SOBRE LA BASE DE DATOS: " + nombre + "\n");
            ventanaMetadatos.areaDeTextoMetadata.append("Driver: " + driver + "\n");
            ventanaMetadatos.areaDeTextoMetadata.append("URL: " + url + "\n");
            ventanaMetadatos.areaDeTextoMetadata.append("Nombre: " + nombre + "\n");
            ventanaMetadatos.areaDeTextoMetadata.append("Usuario: " + usuario + "\n");

            ResultSet resul = null; //para la tabla y su informacion
            
            //CARLOS

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection(String conector) throws ClassNotFoundException, SQLException, IOException {
        switch (conector) {
            case mysqlConector:
                return getCon_mysql_jdbc();
            case sqliteConector:
                return getCon_sql();
            default:
                return null;
        }
    }
}
