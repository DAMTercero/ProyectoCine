/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Clases.Empleado;
import SQL.Clases.Historico;
import SQL.Clases.Pelicula;
import SQL.Clases.Sala;
import static SQL.Conexion.sql.getCon_mysql_jdbc;
import static SQL.Conexion.sql.getCon_sql;
import static SQL.Dao.EmpleadoCRUD.getConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 9fdam07
 */
public class HistoricoCRUD extends SQL.Conexion.sql {

    private static final String mysqlConector = "mysql";
    private static final String sqliteConector = "sqlite";

    public static boolean insertHistorico(Historico obj, String conector) throws SQLException, ClassNotFoundException, IOException {
        Connection conn = null;
        boolean result = false;

        try {
            //Establecemos conexion, verificando si es a MYSql o Sqlite            
            conn = getConnection(conector);

            //Query
            String query = "INSERT INTO HISTORICO(ID_SALA, ID_PELICULA, FECHA_EMISION, SESION, ID_EMPLEADO) values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos los datos
            ps.setInt(1, obj.getID_SALA());
            ps.setInt(2, obj.getID_PELICULA());
            ps.setString(3, obj.getFECHA_EMISION());
            ps.setString(4, obj.getSESION());
            ps.setInt(5, obj.getID_EMPLEADO());

            //Ejecutamos la insert
            int action = ps.executeUpdate();

            //Comprobamos si insert ha funcionado
            if (action > 0) {
                result = true;
            }

            //Cerrar conexion
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos el resultado
        return result;
    }

    public ArrayList<Historico> filtrarHistoricos(Historico historico, String conector) throws IOException, SQLException, ClassNotFoundException {
        ArrayList<Historico> historicosList = new ArrayList<>();
        Connection conn = null;

        try {
            //Establecemos conexion, verificando si es a MYSql o Sqlite
            conn = getConnection(conector);

            //Iniciar la select por defecto, con el 1 = 1 es como un buscar todos por si el resto nunca se llenase
            StringBuilder select = new StringBuilder("SELECT * FROM HISTORICO WHERE 1=1");

            if (historico.getID_SALA() != -1) {//le paso -1 si estaba empty en el filtro
                select.append(" AND HISTORICO.ID_SALA LIKE ?");
            }
            if (historico.getID_PELICULA() != -1) {
                select.append(" AND HISTORICO.ID_PELICULA LIKE ?");
            }
            if (!historico.getFECHA_EMISION().equalsIgnoreCase("")) {
                select.append(" AND HISTORICO.FECHA_EMISION LIKE ?");
            }
            if (!historico.getSESION().equalsIgnoreCase("")) {
                select.append(" AND HISTORICO.SESION LIKE ?");
            }
            if (historico.getID_EMPLEADO() != -1) {
                select.append(" AND HISTORICO.ID_EMPLEADO LIKE ?");
            }

            //query
            int vControl = 1;
            PreparedStatement ps = conn.prepareStatement(select.toString());
            //devolver lista de empleados devueltos en el resultset        

            if (historico.getID_SALA() != -1) {
                ps.setInt(vControl, historico.getID_SALA());// tbuscar forma de que deje LIKE para numeros
                vControl += 1;
            }
            if (historico.getID_PELICULA() != -1) {
                ps.setInt(vControl, historico.getID_PELICULA());
                vControl += 1;
            }
            if (!historico.getFECHA_EMISION().equalsIgnoreCase("")) {
                ps.setString(vControl, "%" + historico.getFECHA_EMISION() + "%");
                vControl += 1;
            }
            if (!historico.getSESION().equalsIgnoreCase("")) {
                ps.setString(vControl, "%" + historico.getSESION() + "%");
                vControl += 1;
            }
            if (historico.getID_EMPLEADO() != -1) {
                ps.setInt(vControl, historico.getID_EMPLEADO());
                vControl += 1;
            }

            ResultSet rs = ps.executeQuery();
            //procesar el resultset y crear una lista de empleados
            while (rs.next()) {
                Historico h = new Historico(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                historicosList.add(h);
            }
            //Cerrar conexion
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
           
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return historicosList;
    }

    public ArrayList<Empleado> selectEmpleados(String conector) throws IOException, SQLException, ClassNotFoundException {
        ArrayList<Empleado> empleadosList = new ArrayList<>();
        Connection conn = null;

        try {
            //Establecemos conexion, verificando si es a MYSql o Sqlite
            conn = getConnection(conector);

            //Iniciar la select por defecto, con el 1 = 1 es como un buscar todos por si el resto nunca se llenase
            StringBuilder select = new StringBuilder("SELECT * FROM EMPLEADO WHERE 1=1");
            //select.append(" AND WHERE DISPONIBLE = 1");//solo obtiene los disponibles

            PreparedStatement ps = conn.prepareStatement(select.toString());

            //devolver lista de empleados devueltos en el resultset
            ResultSet rs = ps.executeQuery();
            //procesar el resultset y crear una lista de empleados
            while (rs.next()) {
                Empleado em = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getBoolean(10));
                empleadosList.add(em);
            }
            //Cerrar conexion
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
          
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return empleadosList;
    }

    public ArrayList<Sala> selectSalas(String conector) throws IOException, SQLException, ClassNotFoundException {
        ArrayList<Sala> salasList = new ArrayList<>();
        Connection conn = null;

        try {
            //Establecemos conexion, verificando si es a MYSql o Sqlite
            conn = getConnection(conector);

            //Iniciar la select por defecto, con el 1 = 1 es como un buscar todos por si el resto nunca se llenase
            StringBuilder select = new StringBuilder("SELECT * FROM SALA WHERE 1=1");
            // select.append(" AND WHERE DISPONIBLE = 1");//solo obtiene los disponibles

            PreparedStatement ps = conn.prepareStatement(select.toString());
            //devolver lista de empleados devueltos en el resultset
            ResultSet rs = ps.executeQuery();
            //procesar el resultset y crear una lista de empleados
            while (rs.next()) {
                Sala pe = new Sala(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getBoolean(5));
                salasList.add(pe);
            }
            //Cerrar conexion
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
           
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return salasList;
    }

    public ArrayList<Pelicula> selectPeliculas(String conector) throws IOException, SQLException, ClassNotFoundException {
        ArrayList<Pelicula> peliculasList = new ArrayList<>();
        Connection conn = null;

        try {
            //Establecemos conexion, verificando si es a MYSql o Sqlite
            conn = getConnection(conector);

            //Iniciar la select por defecto, con el 1 = 1 es como un buscar todos por si el resto nunca se llenase
            StringBuilder select = new StringBuilder("SELECT * FROM PELICULA WHERE 1=1");
            //select.append(" AND WHERE DISPONIBLE = 1");//solo obtiene los disponibles

            PreparedStatement ps = conn.prepareStatement(select.toString());
            //devolver lista de empleados devueltos en el resultset 
            ResultSet rs = ps.executeQuery();
            //procesar el resultset y crear una lista de empleados
            while (rs.next()) {
                Pelicula pe = new Pelicula(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(9));
                peliculasList.add(pe);
            }
            //Cerrar conexion
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return peliculasList;
    }

    public ArrayList<Historico> selectFecha_Emision(String conector) throws IOException, SQLException, ClassNotFoundException {
        ArrayList<Historico> historicosList = new ArrayList<>();
        Connection conn = null;

        try {
            //Establecemos conexion, verificando si es a MYSql o Sqlite
            conn = getConnection(conector);

            //Iniciar la select por defecto, con el 1 = 1 es como un buscar todos por si el resto nunca se llenase
            StringBuilder select = new StringBuilder("SELECT DISTINCT * FROM HISTORICO GROUP BY FECHA_EMISION");

            PreparedStatement ps = conn.prepareStatement(select.toString());
            //devolver lista de empleados devueltos en el resultset 
            ResultSet rs = ps.executeQuery();
            //procesar el resultset y crear una lista de empleados
            while (rs.next()) {
                Historico h = new Historico(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                historicosList.add(h);
            }
            //Cerrar conexion
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return historicosList;
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
