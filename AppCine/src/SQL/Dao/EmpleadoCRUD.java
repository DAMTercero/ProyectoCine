/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Clases.Empleado;
import java.sql.Connection;
import static java.sql.DriverManager.getConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ikitess
 */
public class EmpleadoCRUD {

    private static final String mysqlConector = "mysql";
    private static final String sqliteConector = "sqlite";

    public static boolean insertEmpleado(Empleado obj, String conector) throws SQLException {
        Connection conn = null;
        boolean result = false;

        try {
            //Establecemos conexion
            conn = getConnection(conector);

            //Query
            String query = "INSERT INTO EMPLEADO values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos los datos
            ps.setInt(1, obj.getID_EMPLEADO());
            ps.setString(2, obj.getNOMBRE());
            ps.setString(3, obj.getAPELLIDO1());
            ps.setString(4, obj.getAPELLIDO2());
            ps.setString(5, obj.getFECHA_NAC());
            ps.setString(6, obj.getFECHA_CONTRATO());
            ps.setString(7, obj.getFECHA_FIN());
            ps.setString(8, obj.getNACIONALIDAD());
            ps.setString(9, obj.getCARGO());
            ps.setBoolean(10, obj.isDISPONIBLE());

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
            Logger.getLogger(SqlCrude.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos el resultado
        return result;
    }

    public static boolean updateEmpleado(Empleado obj, String conector) throws SQLException {
        boolean result = false;
        Connection conn = null;

        try {
            //Establecemos la conexion
            conn = getConnection(conector);

            //Query
            String query = "UPDATE EMPLEADO SET ID_EMPLEADO = ?, NOMBRE = ?, APELLIDO1 = ?, APELLIDO2 = ?, FECHA_NAC = ?, FECHA_CONTRATO = ?, FECHA_FIN = ?, NACIONALIDAD = ?, CARGO = ?, DISPONIBLE = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Indicamos los datos
            ps.setInt(1, obj.getID_EMPLEADO());
            ps.setString(2, obj.getNOMBRE());
            ps.setString(3, obj.getAPELLIDO1());
            ps.setString(4, obj.getAPELLIDO2());
            ps.setString(5, obj.getFECHA_NAC());
            ps.setString(6, obj.getFECHA_CONTRATO());
            ps.setString(7, obj.getFECHA_FIN());
            ps.setString(8, obj.getNACIONALIDAD());
            ps.setString(9, obj.getCARGO());
            ps.setBoolean(10, obj.isDISPONIBLE());

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
            Logger.getLogger(SqlCrude.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos el resultado
        return result;
    }
}
