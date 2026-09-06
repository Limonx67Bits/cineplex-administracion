package main.java.com.pbcorporations.cineplex.administracion.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnection {
    private static Connection conn;
    
    private DBConnection(){}
    
    public static Connection getConnection() throws SQLException{
        if (conn == null || conn.isClosed()){
            conn = DriverManager.getConnection(Credentials.URL_DB, Credentials.USER_DB, Credentials.PW_DB);
        }
        return conn;
    }
}
