package com;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class LihatBarang extends JFrame implements ListSelectionListener, ActionListener {

    String nama, massa;
    int harga, jumlahData;
    String data[][] = new String[500][3];

    ThenConnector connector = new ThenConnector();

    JTable tabel;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    Object namaKolom[] = {"Nama Barang", "Massa (gr)", "Harga"};
    
    JButton buttonKembali = new JButton("Kembali");

    public LihatBarang(){
        setTitle("Data Barang");
        setSize(600, 450);

        setLayout(null);
        add(buttonKembali);

        buttonKembali.setBounds(170, 370, 170, 20);

        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        try{
            int jmlData = 0; //menampung jumlah data sebanyak jumlah data yang ada, defaultnya 0
            String query = "SELECT * FROM `barang`"; //proses pengambilan data
            connector.statement = connector.connection.createStatement();
            ResultSet resultSet = connector.statement.executeQuery(query); //result isinya tabel belum berupa string
            while(resultSet.next()){ //konversi tabel ke string
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
        
        tabel = new JTable(data,namaKolom); //tabel merupakan variabel untuk tabelnya dengan isi tablemodel
        scrollPane = new JScrollPane(tabel);
        add(scrollPane);
        
        scrollPane.setBounds(20, 35, 500, 300);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        buttonKembali.addActionListener(this);

        // awal event listener tabel
        tabel.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = tabel.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonKembali) {
            this.dispose();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        String selectedData = null, columnName = "";
        String queryForId = null;
        int col = 0;

        String idForDetailItem[][] = new String[500][1];

        int[] selectedRow = tabel.getSelectedRows();
        int[] selectedColumns = tabel.getSelectedColumns();

        for (int i = 0; i < selectedRow.length; i++) {
          for (int j = 0; j < selectedColumns.length; j++) {
            col = selectedColumns[j];  
            selectedData = (String) tabel.getValueAt(selectedRow[i], selectedColumns[j]);
          }
        }

        if (selectedData == null) {
            JOptionPane.showMessageDialog(null,"Anda memilih kolom kosong");
        }
        else {
            columnName = (String) tabel.getColumnName(col);
            columnName = columnName.toLowerCase();
            
            if (columnName.equals("nama barang")) {
                queryForId = "SELECT id FROM barang WHERE nama = '"+selectedData+"'";
            } else if (columnName.equals("massa (gr)")) {
                queryForId = "SELECT id FROM barang WHERE massa = '"+selectedData+"'";
            } else if (columnName.equals("harga")) {
                queryForId = "SELECT id FROM barang WHERE harga = '"+selectedData+"'";
            }

            try {
                int jumlahId = 0;
                connector.statement = connector.connection.createStatement();
                ResultSet resultSet = connector.statement.executeQuery(queryForId); 
                while(resultSet.next()){ //konversi tabel ke string
                    idForDetailItem[jumlahId][0] = resultSet.getString("id");
                    jumlahId++; 
                }
                connector.statement.close();
            } catch(SQLException error){
                System.out.println(error.getMessage());
                System.out.println("SQL Error");
            }

            System.out.println("Selected ID : " + idForDetailItem[0][0]);
            new DetailItem(idForDetailItem[0][0]);
        }
        this.dispose();
    }
}
