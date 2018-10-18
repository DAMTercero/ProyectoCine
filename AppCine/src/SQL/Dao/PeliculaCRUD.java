/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Clases.Pelicula;
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
public class PeliculaCRUD extends SQL.Conexion.sql {

    private static final String mysqlConector = "mysql";
    private static final String sqliteConector = "sqlite";

    public static boolean insertPelicula(Pelicula obj, String conector) throws SQLException, ClassNotFoundException, IOException {
        Connection conn = null;
        boolean result = false;

        try {
            //Establecemos conexion
            conn = getConnection(conector);

            //Query
            String query = "INSERT INTO PELICULA (TITULO, ANYO_ESTRENO, DIRECTOR, ACTOR_PRINCIPAL, ACTOR_SECUNDARIO, DURACION, TRAILER, DISPONIBLE) values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos los datos 
            ps.setString(1, obj.getTITULO());
            ps.setString(2, obj.getANYO_ESTRENO());
            ps.setString(3, obj.getDIRECTOR());
            ps.setString(4, obj.getACTOR_PRINCI());
            ps.setString(5, obj.getACTOR_SECUN());
            ps.setString(6, obj.getDURACION());
            ps.setString(7, obj.getTRAILER());
            ps.setBoolean(8, obj.isDISPONIBLE());

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

    public static boolean updatePelicula(Pelicula obj, String conector) throws SQLException, ClassNotFoundException, IOException {
        boolean result = false;
        Connection conn = null;

        try {
            //Establecemos la conexion
            conn = getConnection(conector);

            //Query
            String query = "UPDATE PELICULA SET TITULO = ?, ANYO_ESTRENO = ?, DIRECTOR = ?, ACTOR_PRINCIPAL = ?, ACTOR_SECUNDARIO = ?, DURACION = ?, TRAILER = ?, DISPONIBLE = ? WHERE ID_PELICULA = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Indicamos los datos            
            ps.setString(1, obj.getTITULO());
            ps.setString(2, obj.getANYO_ESTRENO());
            ps.setString(3, obj.getDIRECTOR());
            ps.setString(4, obj.getACTOR_PRINCI());
            ps.setString(5, obj.getACTOR_SECUN());
            ps.setString(6, obj.getDURACION());
            ps.setString(7, obj.getTRAILER());
            ps.setBoolean(8, obj.isDISPONIBLE());
            ps.setInt(9, obj.getID_PELICULA());

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

    public ArrayList<Pelicula> filtrarPeliculas(Pelicula pelicula, String conector) throws IOException, SQLException, ClassNotFoundException {
        ArrayList<Pelicula> peliculasList = new ArrayList<>();
        Connection conn = null;

        try {
            //Establecemos conexion, verificando si es a MYSql o Sqlite
            conn = getConnection(conector);

            //Iniciar la select por defecto, con el 1 = 1 es como un buscar todos por si el resto nunca se llenase
            StringBuilder select = new StringBuilder("SELECT * FROM PELICULA WHERE 1=1");

            if (pelicula.getID_PELICULA() != -1) {//le paso -1 si estaba empty en el filtro
                select.append(" AND PELICULA.ID_PELICULA LIKE ?");
            }
            if (!pelicula.getTITULO().equalsIgnoreCase("")) {
                select.append(" AND PELICULA.TITULO LIKE ?");
            }
            if (!pelicula.getANYO_ESTRENO().equalsIgnoreCase("")) {
                select.append(" AND PELICULA.ANYO_ESTRENO LIKE ?");
            }
            if (!pelicula.getDIRECTOR().equalsIgnoreCase("")) {
                select.append(" AND PELICULA.DIRECTOR LIKE ?");
            }
            select.append(" ORDER BY PELICULA.DISPONIBLE DESC");

            //query
            int vControl = 1;
            PreparedStatement ps = conn.prepareStatement(select.toString());
            //devolver lista de empleados devueltos en el resultset        

            if (pelicula.getID_PELICULA() != -1) {
                ps.setInt(vControl, pelicula.getID_PELICULA());// tbuscar forma de que deje LIKE para numeros
                vControl += 1;
            }
            if (!pelicula.getTITULO().equalsIgnoreCase("")) {
                ps.setString(vControl, "%" + pelicula.getTITULO() + "%");
                vControl += 1;
            }
            if (!pelicula.getANYO_ESTRENO().equalsIgnoreCase("")) {
                ps.setString(vControl, "%" + pelicula.getANYO_ESTRENO() + "%");
                vControl += 1;
            }
            if (!pelicula.getDIRECTOR().equalsIgnoreCase("")) {
                ps.setString(vControl, "%" + pelicula.getDIRECTOR() + "%");
                vControl += 1;
            }

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
