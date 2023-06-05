/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.conf;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;




/**
 *
 * @author HP
 */
public class jdbcUtils {
    private static Connection conn;
     static {
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
         } catch (ClassNotFoundException ex) {
             ex.printStackTrace();
         }
    }

    /**
     * @return the conn
     */
    public static java.sql.Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost/librarydb", "root", "Hoang123@");
    }
   
}
