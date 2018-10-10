/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Clases.Empleado;
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
public class EmpleadoCRUD extends SQL.Conexion.sql {

    private static final String mysqlConector = "mysql";
    private static final String sqliteConector = "sqlite";

    public static boolean insertEmpleado(Empleado obj, String conector) throws SQLException, ClassNotFoundException, IOException {
        Connection conn = null;
        boolean result = false;

        try {
            //Establecemos conexion, verificando si es a MYSql o Sqlite            
            conn = getConnection(conector);

            //Query
            String query = "INSERT INTO EMPLEADO values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos los datos
            ps.setInt(1, java.sql.Types.INTEGER);//pasar un nulo
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

    public static boolean updateEmpleado(Empleado obj, String conector) throws SQLException, IOException, ClassNotFoundException {
        boolean result = false;
        Connection conn = null;

        try {
            //Establecemos conexion, verificando si es a MYSql o Sqlite
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

    public ArrayList<Empleado> filtrarEmpleados(Empleado empleado, String conector) throws IOException, SQLException, ClassNotFoundException {
        ArrayList<Empleado> empleadosList = new ArrayList<>();
        Connection conn = null;

        try {
            //Establecemos conexion, verificando si es a MYSql o Sqlite
            conn = getConnection(conector);

            //Iniciar la select por defecto, con el 1 = 1 es como un buscar todos por si el resto nunca se llenase
            StringBuilder select = new StringBuilder("SELECT * FROM EMPLEADO WHERE 1=1");

            if (empleado.getID_EMPLEADO() != -1) {//le paso -1 si estaba empty en el filtro
                select.append(" AND EMPLEADO.ID_EMPLEADO LIKE ?");
            }
            if (!empleado.getNOMBRE().equalsIgnoreCase("")) {
                select.append(" AND EMPLEADO.NOMBRE LIKE ?");
            }
            if (!empleado.getAPELLIDO1().equalsIgnoreCase("")) {
                select.append(" AND EMPLEADO.APELLIDO1 LIKE ?");
            }
            if (!empleado.getAPELLIDO2().equalsIgnoreCase("")) {
                select.append(" AND EMPLEADO.APELLIDO2 LIKE ?");
            }

            //query
            int vControl = 1;
            PreparedStatement ps = conn.prepareStatement(select.toString());
            //devolver lista de empleados devueltos en el resultset        

            if (empleado.getID_EMPLEADO() != -1) {
                ps.setInt(vControl, empleado.getID_EMPLEADO());// tbuscar forma de que deje LIKE para numeros
                vControl += 1;
            }
            if (!empleado.getNOMBRE().equalsIgnoreCase("")) {
                ps.setString(vControl, "%" + empleado.getNOMBRE() + "%");
                vControl += 1;
            }
            if (!empleado.getAPELLIDO1().equalsIgnoreCase("")) {
                ps.setString(vControl, "%" + empleado.getAPELLIDO1() + "%");
                vControl += 1;
            }
            if (!empleado.getAPELLIDO2().equalsIgnoreCase("")) {
                ps.setString(vControl, "%" + empleado.getAPELLIDO2() + "%");
                vControl += 1;
            }

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
            Logger.getLogger(SqlCrude.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return empleadosList;
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
