/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL.Conexion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josep
 */
public abstract class sql {

    protected static Connection con_mysql_jdbc;
    protected static Connection con_sql;
    private static Properties config;

    public static Connection getCon_mysql_jdbc() throws ClassNotFoundException, SQLException, IOException {
        //sql.setConfig(sql.getConfig());

        Class.forName("com.mysql.jdbc.Driver");

        sql.con_mysql_jdbc = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3312/cinesfinalv1","root","usbw");
        return con_mysql_jdbc;

    }

    public static void setCon_mysql_jdbc(Connection con_mysql_jdbc) {
        sql.con_mysql_jdbc = con_mysql_jdbc;
    }

    public static Connection getCon_sql() throws ClassNotFoundException, SQLException, IOException {
        //sql.setConfig(sql.getConfig());

        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //sql.con_sql = DriverManager.getConnection("jdbc:sqlserver://" + config.getProperty("sql.server") + ":" + config.getProperty("sql.pot") + ";databaseName=" + config.getProperty("sql.bd") + ";user=" + config.getProperty("sql.user") + ";password=");
        sql.con_sql = DriverManager.getConnection("jdbc:sqlite:DBEmbebidas/sqlitecine.sqlite");
        return con_sql;
    }

    public static void setCon_sql(Connection con_sql) {
        sql.con_sql = con_sql;
    }

    public static Properties getConfig() throws FileNotFoundException, IOException {
        FileInputStream file = null;
        file = new FileInputStream("src\\AppCine\\SQL\\Conexion\\config.properties");
        Properties cofig = new Properties();
        config.load(file);
        return config;
    }

    public static void setConfig(Properties config) {
        sql.config = config;
    }

}
