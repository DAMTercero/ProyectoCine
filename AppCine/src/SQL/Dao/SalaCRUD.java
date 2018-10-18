/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Clases.Sala;
import static SQL.Conexion.sql.getCon_mysql_jdbc;
import static SQL.Conexion.sql.getCon_sql;
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
 * @author ikitess
 */
public class SalaCRUD extends SQL.Conexion.sql {

    private static final String mysqlConector = "mysql";
    private static final String sqliteConector = "sqlite";

    public static boolean insertSala(Sala obj, String conector) throws SQLException, ClassNotFoundException, IOException {
        Connection conn = null;
        boolean result = false;

        try {
            //Establecemos conexion
            conn = getConnection(conector);

            //Query
            String query = "INSERT INTO SALA(CAPACIDAD, PANTALLA, FECHA_APERTURA, DISPONIBLE) values(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos los datos   
            ps.setInt(1, obj.getCAPACIDAD());
            ps.setString(2, obj.getFECHA_APERTURA());
            ps.setString(3, obj.getPANTALLA());
            ps.setBoolean(4, obj.getDISPONIBLE());

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

    public static boolean updateSala(Sala obj, String conector) throws SQLException, ClassNotFoundException, IOException {
        boolean result = false;
        Connection conn = null;

        try {
            //Establecemos la conexion
            conn = getConnection(conector);

            //Query
            String query = "UPDATE SALA SET  CAPACIDAD = ?, PANTALLA = ?, FECHA_APERTURA = ?, DISPONIBLE = ? WHERE ID_SALA = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Indicamos los datos            
            ps.setInt(1, obj.getCAPACIDAD());
            ps.setString(2, obj.getFECHA_APERTURA());
            ps.setString(3, obj.getPANTALLA());
            ps.setBoolean(4, obj.getDISPONIBLE());
            ps.setInt(5, obj.getID_SALA());

            //Ejecutamos la update
            int action = ps.executeUpdate();

            //Comprobamos si funcionado correctamente
            if (action > 0) {
                result = true;
            }

            //Cerramos conexion
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

    public ArrayList<Sala> filtrarSalas(Sala sala, String conector) throws IOException, SQLException, ClassNotFoundException {
        ArrayList<Sala> salasList = new ArrayList<>();
        Connection conn = null;

        try {
            //Establecemos conexion, verificando si es a MYSql o Sqlite
            conn = getConnection(conector);

            //Iniciar la select por defecto, con el 1 = 1 es como un buscar todos por si el resto nunca se llenase
            StringBuilder select = new StringBuilder("SELECT * FROM SALA WHERE 1=1");

            if (sala.getID_SALA() != -1) {//le paso -1 si estaba empty en el filtro
                select.append(" AND SALA.ID_SALA LIKE ?");
            }
            select.append(" ORDER BY SALA.DISPONIBLE DESC");

            //query
            int vControl = 1;
            PreparedStatement ps = conn.prepareStatement(select.toString());
            //devolver lista de empleados devueltos en el resultset        

            if (sala.getID_SALA() != -1) {
                ps.setInt(vControl, sala.getID_SALA());// tbuscar forma de que deje LIKE para numeros
                vControl += 1;
            }

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
