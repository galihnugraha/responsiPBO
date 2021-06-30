package com;

import java.sql.*;
import javax.swing.*;

public class ControllerTotalHarga extends JFrame{
    ViewTotalHarga viewTotalHarga;
    ModelTotalHarga modelTotalHarga;

    String idBarang;
    int harga, banyakItem, totalHarga;
    String data[][] = new String[500][3];

    ThenConnector connector = new ThenConnector();

    public ControllerTotalHarga(String idBarang, int banyakItem){
        this.idBarang = idBarang;
        this.banyakItem = banyakItem;

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
        
        harga = Integer.parseInt(data[0][2].substring(0, data[0][2].length() - 2));

        totalHarga = harga*banyakItem;
        if (banyakItem >= 12 && banyakItem < 20) {
            totalHarga = totalHarga*95/100;
        } else if (banyakItem >= 20 && banyakItem < 144) {
            totalHarga = totalHarga*90/100;
        } else if (banyakItem >= 144) {
            totalHarga = totalHarga*75/100;
        }

        viewTotalHarga = new ViewTotalHarga(idBarang, data[0][0], data[0][1], harga, totalHarga);
    }
}
