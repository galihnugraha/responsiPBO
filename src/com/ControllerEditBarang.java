package com;

import java.sql.*;
import javax.swing.*;

public class ControllerEditBarang extends JFrame{
    ViewEditBarang viewEditBarang;
    ModelEditBarang modelEditBarang;

    String idBarang;
    int massa, harga, totalHarga;
    String data[][] = new String[500][3];

    ThenConnector connector = new ThenConnector();

    public ControllerEditBarang(String idBarang){
        this.idBarang = idBarang;
        try{
            int jmlData = 0;
            String query = "SELECT * FROM barang WHERE id = '"+idBarang+"'";
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

        massa = Integer.parseInt(data[0][1].substring(0, data[0][1].length() - 2));
        harga = Integer.parseInt(data[0][2].substring(0, data[0][2].length() - 2));

        viewEditBarang = new ViewEditBarang(idBarang, data[0][0], massa, harga);
    }
}
