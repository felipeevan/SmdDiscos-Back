/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smdecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Felipe
 */
public abstract class ServerConf {
    private final static String URL = "localhost";
    private final static String PORT = "5432";
    private final static String DATABASE = "SMD_Ecommerce";
    private final static String USER = "postgres";
    private final static String PASS = "felipe123";
    
    public static Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:postgresql://" + URL +":" +
                PORT + "/" + DATABASE, USER, PASS);
        return connection;
    }
}