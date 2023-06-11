/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sgu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
   
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATA_BASE = "sgu";
    private static final String SSL = "?useSSL=false";
    private static final String URL = "jdbc:mysql://localhost/";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection conexion = null;

        try {
            Class.forName(JDBC_DRIVER);
            conexion = DriverManager.getConnection(URL + DATA_BASE + SSL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error De Conexión A Base De Datos!\n " + e);
            System.exit(0);

        }

        return conexion;

    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

