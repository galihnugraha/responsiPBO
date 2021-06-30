package com;

import java.sql.*;

public class ControllerHapusBarang {
    ViewHapusBarang viewHapusBarang;
    ModelHapusBarang modelHapusBarang;

    String idBarang;
    String data[][] = new String[500][3];

    ThenConnector connector = new ThenConnector();
    
    public ControllerHapusBarang(String idBarang) {
        this.idBarang = idBarang;
        
        try{
            int jmlData = 0;
            String query = "SELECT * FROM barang WHERE id = '"+this.idBarang+"'";
            connector.statement = connector.connection.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query);
            while(resultSet.next()){
                data[jmlData][0] = resultSet.getString("nama"); 
                data[jmlData][1] = resultSet.getString("massa"); 
                data[jmlData][2] = resultSet.getString("harga");
                jmlData++; 
            }
            connector.statement.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
        }

        ModelHapusBarang modelHapusBarang = new ModelHapusBarang(data);
        ViewHapusBarang viewHapusBarang = new ViewHapusBarang(data, idBarang);
    }
}