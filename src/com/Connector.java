package com;

import java.sql.*;

public class Connector {
    String url = "jdbc:mysql://localhost:3306/barang?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    String username = "root";
    String password = "";
    Connection connection;
    Statement statement;
    public Connector() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url,username,password);
            statement = connection.createStatement();
            System.out.println("Koneksi Berhasil");
        }catch(Exception ex){
            System.err.println(ex);
            System.out.println("\nKoneksi gagal");
        }
    }

    
}

class ThenConnector {
    String url = "jdbc:mysql://localhost:3306/barang?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    String username = "root";
    String password = "";
    Connection connection;
    Statement statement;
    public ThenConnector() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url,username,password);
            statement = connection.createStatement();
        }catch(Exception ex){
            System.err.println(ex);
            System.out.println("\nKoneksi gagal");
        }
    }

    
}