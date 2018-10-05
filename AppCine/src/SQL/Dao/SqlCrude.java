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
import java.sql.Connection;
import static java.sql.DriverManager.getConnection;
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
public class SqlCrude extends SQL.Conexion.sql{
    
    private static final String mysqlConector="mysql";
    
    
    public static Sala getSala(String conector)throws SQLException{
        Sala obj=null;
        Connection conn=null;
      
        try{
            conn=getConnection(conector);
            String query="SELECT * FROM SALA";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                obj=(Sala)parseObject(rs,Sala.class.getSimpleName());
                if(obj!=null){
                    obj.setConector(conector);
                }
            }   
            conn.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Imposible establecer conexion con la base de datos.","Error",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SqlCrude.class.getName()).log(Level.SEVERE,null,ex);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn != null){
                conn.close();
            }
        }
        return obj;
            
        }
    public static Pelicula getPelicula(String conector)throws SQLException{
        Pelicula obj=null;
        Connection conn=null;
      
        try{
            conn=getConnection(conector);
            String query="SELECT * FROM PELICULA";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                obj=(Pelicula)parseObject(rs,Pelicula.class.getSimpleName());
                if(obj!=null){
                    obj.setConector(conector);
                }
            }   
            conn.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Imposible establecer conexion con la base de datos.","Error",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SqlCrude.class.getName()).log(Level.SEVERE,null,ex);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn != null){
                conn.close();
            }
        }
        return obj;
            
        }
     
    public static Empleado getEmpleado(String conector)throws SQLException{
        Empleado obj=null;
        Connection conn=null;
      
        try{
            conn=getConnection(conector);
            String query="SELECT * FROM EMPLEADO";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                obj=(Empleado)parseObject(rs,Empleado.class.getSimpleName());
                if(obj!=null){
                    obj.setConector(conector);
                }
            }   
            conn.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Imposible establecer conexion con la base de datos.","Error",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(SqlCrude.class.getName()).log(Level.SEVERE,null,ex);
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            if(conn != null){
                conn.close();
            }
        }
        return obj;
            
        } 
    
    private static Object parseObject(ResultSet rs, String tipo) throws SQLException {
        if (tipo.equals(Sala.class.getSimpleName())) {
            //Creamos el objeto sala 
            return new Sala(rs.getInt("ID_SALA"), rs.getInt("CAPACIDAD"), rs.getInt("PANTALLA"), rs.getString("APERTURA"), rs.getString("HORARIO"), rs.getBoolean("DISPONIBLE"));
        } else if (tipo.equals(Pelicula.class.getSimpleName())) {
            //Creamos el objeto Pelicula
            return new Pelicula(rs.getInt("ID_PELICULA"), rs.getString("TITULO"), rs.getString("ANYO_STRENO"), rs.getString("DIRECTOR"), rs.getString("ACTOR_PRINCI"), rs.getString("ACTOR_SECUN"), rs.getString("DURACION"), rs.getString("TRAILER"),rs.getBoolean("DISPONIBLE"));
        } else if (tipo.equals(Empleado.class.getSimpleName())) {
            //Creamos el objeto Empleado
            
            return new Empleado(rs.getInt("ID_EMPLEADO"), rs.getString("NOMBRE"),rs.getString("APELLIDO1"),rs.getString("APELLIDO2"),rs.getString("FECHA_NAC"),rs.getString("FECHA_CONTRATO"),rs.getString("FECHA_FIN"),rs.getString("NACIONALIDAD"),rs.getString("CARGO"),rs.getBoolean("DISPONIBLE"));
        } 
         else {
            //Devolvemos un null 
            return null;
        }
    }
    
    
    
        
        
    }
    
    
    
    

