/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Dao;

import SQL.Clases.Empleado;
import SQL.Clases.Pelicula;
import SQL.Clases.Sala;
import SQL.Conexion.sql;
import java.io.IOException;
import java.sql.Connection;
import static java.sql.DriverManager.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author josep
 */
public class SqlCrude extends SQL.Conexion.sql {

    private static final String mysqlConector = "mysql";
    private static final String sqlServerConector = "sqlServer";

    
    public static Connection getConnection(String conector) throws ClassNotFoundException, SQLException, IOException {
        if (conector.equals(mysqlConector)) {
            return getCon_mysql_jdbc();
        } else {
            return getCon_sql();
        }
    }

    public static Sala getSala(String conector) throws SQLException {
        Sala obj = null;
        Connection conn = null;

        try {
            conn = getConnection(conector);
            String query = "SELECT * FROM SALA";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = (Sala) parseObject(rs, Sala.class.getSimpleName());
                if (obj != null) {
                    obj.setConector(conector);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SqlCrude.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return obj;

    }

    public static Pelicula getPelicula(String conector) throws SQLException {
        Pelicula obj = null;
        Connection conn = null;

        try {
            conn = getConnection(conector);
            String query = "SELECT * FROM PELICULA";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = (Pelicula) parseObject(rs, Pelicula.class.getSimpleName());
                if (obj != null) {
                    obj.setConector(conector);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SqlCrude.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return obj;

    }

    public static Empleado getEmpleado(String conector) throws SQLException {
        Empleado obj = null;
        Connection conn = null;

        try {
            conn = getConnection(conector);
            String query = "SELECT * FROM EMPLEADO";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                obj = (Empleado) parseObject(rs, Empleado.class.getSimpleName());
                if (obj != null) {
                    obj.setConector(conector);
                }
            }
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SqlCrude.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return obj;

    }

    private static Object parseObject(ResultSet rs, String tipo) throws SQLException {
        if (tipo.equals(Sala.class.getSimpleName())) {
            //Creamos el objeto sala 
            return new Sala(rs.getInt("ID_SALA"), rs.getInt("CAPACIDAD"), rs.getString("PANTALLA"), rs.getString("APERTURA"), rs.getBoolean("DISPONIBLE"));
        } else if (tipo.equals(Pelicula.class.getSimpleName())) {
            //Creamos el objeto Pelicula
            return new Pelicula(rs.getInt("ID_PELICULA"), rs.getString("TITULO"), rs.getString("ANYO_STRENO"), rs.getString("DIRECTOR"), rs.getString("ACTOR_PRINCI"), rs.getString("ACTOR_SECUN"), rs.getString("DURACION"), rs.getString("TRAILER"), rs.getBoolean("DISPONIBLE"));
        } else if (tipo.equals(Empleado.class.getSimpleName())) {
            //Creamos el objeto Empleado

            return new Empleado(rs.getInt("ID_EMPLEADO"), rs.getString("NOMBRE"), rs.getString("APELLIDO1"), rs.getString("APELLIDO2"), rs.getString("FECHA_NAC"), rs.getString("FECHA_CONTRATO"), rs.getString("FECHA_FIN"), rs.getString("NACIONALIDAD"), rs.getString("CARGO"), rs.getBoolean("DISPONIBLE"));
        } else {
            //Devolvemos un null 
            return null;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public static Sala getSalaById(int Id_Sala, String conector) throws SQLException, SQLException, SQLException, SQLException {
        //Objeto jugador
        Sala obj = null;
        Connection conn = null;

        try {
            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM SALA WHERE ID_SALA = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos el id a la condicion
            ps.setInt(1, Id_Sala);

            //Ejecutamos la query
            ResultSet rs = ps.executeQuery();

            //Recorremos el resultado
            while (rs.next()) {
                //Obtenemos la sala
                obj = (Sala) parseObject(rs, Sala.class.getSimpleName());

                //Comprobamos que no sea nulo
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);

                }
            }

            //Cerramos conexion
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos la sala
        return obj;
    }

    /////////////////////////////////////////////////////////////////////
    public static Pelicula getPeliculaById(int Id_Pelicula, String conector) throws SQLException, SQLException, SQLException, SQLException {
        //Objeto jugador
        Pelicula obj = null;
        Connection conn = null;

        try {
            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM PELICULA WHERE ID_PELICULA = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos el id a la condicion
            ps.setInt(1, Id_Pelicula);

            //Ejecutamos la query
            ResultSet rs = ps.executeQuery();

            //Recorremos el resultado
            while (rs.next()) {
                //Obtenemos la sala
                obj = (Pelicula) parseObject(rs, Pelicula.class.getSimpleName());

                //Comprobamos que no sea nulo
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);

                }
            }

            //Cerramos conexion
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos la sala
        return obj;
    }

    ///////////////////////////////////////////////////////////////////////
    public static Pelicula getPeliculaByTitle(int TITULO, String conector) throws SQLException, SQLException, SQLException, SQLException {
        //Objeto jugador
        Pelicula obj = null;
        Connection conn = null;

        try {
            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM PELICULA WHERE TITULO = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos el id a la condicion
            ps.setInt(1, TITULO);

            //Ejecutamos la query
            ResultSet rs = ps.executeQuery();

            //Recorremos el resultado
            while (rs.next()) {
                //Obtenemos la sala
                obj = (Pelicula) parseObject(rs, Pelicula.class.getSimpleName());

                //Comprobamos que no sea nulo
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);

                }
            }

            //Cerramos conexion
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos la sala
        return obj;
    }

    //////////////////////////////////////////////////////////7
    public static Pelicula getPeliculaByANYO_STRENO(int ANYO_STRENO, String conector) throws SQLException, SQLException, SQLException, SQLException {
        //Objeto jugador
        Pelicula obj = null;
        Connection conn = null;

        try {
            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM PELICULA WHERE ANYO_STRENO = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos el id a la condicion
            ps.setInt(1, ANYO_STRENO);

            //Ejecutamos la query
            ResultSet rs = ps.executeQuery();

            //Recorremos el resultado
            while (rs.next()) {
                //Obtenemos la sala
                obj = (Pelicula) parseObject(rs, Pelicula.class.getSimpleName());

                //Comprobamos que no sea nulo
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);

                }
            }

            //Cerramos conexion
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos la sala
        return obj;
    }
    /////////////////////////////////////////////

    public static Pelicula getPeliculaByDIRECTOR(int DIRECTOR, String conector) throws SQLException, SQLException, SQLException, SQLException {
        //Objeto jugador
        Pelicula obj = null;
        Connection conn = null;

        try {
            //Obtenemos el conector
            conn = getConnection(conector);

            //Query
            String query = "SELECT * FROM PELICULA WHERE DIRECTOR = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Añadimos el id a la condicion puede que sea el error
            ps.setInt(1, DIRECTOR);

            //Ejecutamos la query
            ResultSet rs = ps.executeQuery();

            //Recorremos el resultado
            while (rs.next()) {
                //Obtenemos la sala
                obj = (Pelicula) parseObject(rs, Pelicula.class.getSimpleName());

                //Comprobamos que no sea nulo
                if (obj != null) {
                    //Añadimos el conector
                    obj.setConector(conector);

                }
            }

            //Cerramos conexion
            conn.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Imposible establecer conexion con las bases de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        //Devolvemos la sala
        return obj;
    }

}
