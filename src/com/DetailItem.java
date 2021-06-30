package com;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DetailItem extends JFrame implements ActionListener {

    String nama, massa, idBarang;
    int harga, jumlahData;
    String data[][] = new String[500][3];

    ThenConnector connector = new ThenConnector();

    JLabel labelJudul = new JLabel("Detail Barang");
    JLabel labelNama = new JLabel("Nama : ");
    JLabel labelMassa = new JLabel("Massa (gr) : ");
    JLabel labelHarga = new JLabel("Harga Satuan : ");
    JLabel labelBanyak = new JLabel("Banyak : ");

    JLabel textNama, textMassa, textHarga;

    JTextField textBanyak = new JTextField(10);

    JButton buttonTotalHarga = new JButton("Total Harga");
    JButton buttonEdit = new JButton("Edit");
    JButton buttonHapus = new JButton("Hapus");
    JButton buttonKembali = new JButton("Kembali");

    public DetailItem(String idBarang){
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

        setTitle(data[0][0]);
        setSize(370, 350);

        textNama = new JLabel(data[0][0]);
        textMassa = new JLabel(data[0][1]);
        harga = Integer.parseInt(data[0][2].substring(0, data[0][2].length() - 2));
        textHarga = new JLabel("Rp."+harga);

        setLayout(null);
        add(labelJudul);
        add(labelNama);
        add(labelMassa);
        add(labelHarga);
        add(labelBanyak);
        add(textNama);
        add(textMassa);
        add(textHarga);
        add(textBanyak);
        add(buttonTotalHarga);
        add(buttonEdit);
        add(buttonHapus);
        add(buttonKembali);

        labelJudul.setBounds(150, 10, 120, 20);
        labelNama.setBounds(15, 40, 120, 20);
        textNama.setBounds(115, 40, 200, 20);
        labelMassa.setBounds(15, 70, 120, 20);
        textMassa.setBounds(115, 70, 200, 20);
        labelHarga.setBounds(15, 100, 120, 20);
        textHarga.setBounds(115, 100, 200, 20);
        labelBanyak.setBounds(15, 130, 120, 20);
        textBanyak.setBounds(115, 130, 200, 20);
        buttonTotalHarga.setBounds(100, 170, 170, 20);
        buttonEdit.setBounds(100, 240, 80, 20);
        buttonHapus.setBounds(190, 240, 80, 20);
        buttonKembali.setBounds(100, 270, 170, 20);

        setVisible(true);
        setLocationRelativeTo(null);

        buttonTotalHarga.addActionListener(this);
        buttonEdit.addActionListener(this);
        buttonHapus.addActionListener(this);
        buttonKembali.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonTotalHarga) {
            
            boolean catchError = false, catchNull = false;
            int banyak = 0;

            if (textBanyak.getText().length() == 0) {
                catchError = true;
                catchNull = true;
                JOptionPane.showMessageDialog(null, "Kolom Tidak Boleh Kosong!");
            }

            if (!catchNull) {
                try {
                    banyak = Integer.parseInt(textBanyak.getText());
                } catch (NumberFormatException error) {
                    catchError = true;
                    JOptionPane.showMessageDialog(null, "Harus merupakan bilangan!");
                } catch (Exception error) {
                    catchError = true;
                    JOptionPane.showMessageDialog(null, error.getMessage());
                }
            }

            if (!catchError) {
                if (banyak <= 0) {
                    catchError = true;
                    JOptionPane.showMessageDialog(null, "Harus merupakan bilangan positif!");
                } else {
                    new ControllerTotalHarga(this.idBarang, banyak);
                    this.dispose();
                }
            }   
        }

        if (e.getSource() == buttonEdit) {
            new ControllerEditBarang(idBarang);
            this.dispose();
        }

        if (e.getSource() == buttonHapus) {
            new ControllerHapusBarang(idBarang);
            this.dispose();
        }

        if (e.getSource() == buttonKembali) {
            this.dispose();
            new LihatBarang();
        }
    }
}
