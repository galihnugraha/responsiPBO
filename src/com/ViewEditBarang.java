package com;

import java.awt.event.*;
import javax.swing.*;

public class ViewEditBarang extends JFrame implements ActionListener {
    ViewEditBarang viewEditBarang;
    ModelEditBarang modelEditBarang;
    
    String idBarang;
    
    ThenConnector connector = new ThenConnector();

    JLabel labelJudul;
    JLabel labelNama = new JLabel("Nama : ");
    JLabel labelMassa = new JLabel("Massa (gr) : ");
    JLabel labelHarga = new JLabel("Harga Satuan : ");

    JTextField textNama = new JTextField(30);
    JTextField textMassa = new JTextField(10);
    JTextField textHarga = new JTextField(10);

    JButton buttonSubmit = new JButton("Edit");
    JButton buttonReset = new JButton("Reset");
    JButton buttonKembali = new JButton("Batal");

    public ViewEditBarang(String idBarang, String nama, int massa, int harga){
        this.idBarang = idBarang;

        labelJudul = new JLabel("Edit "+ nama);

        this.textNama.setText(nama);
        this.textMassa.setText(Integer.toString(massa));
        this.textHarga.setText(Integer.toString(harga));

        setTitle("Edit Item "+nama);
        setSize(370, 260);

        setLayout(null);
        add(labelJudul);
        add(labelNama);
        add(labelMassa);
        add(labelHarga);
        add(textNama);
        add(textMassa);
        add(textHarga);
        add(buttonSubmit);
        add(buttonReset);
        add(buttonKembali);

        labelJudul.setBounds(150, 10, 120, 20);
        labelNama.setBounds(15, 40, 120, 20);
        textNama.setBounds(115, 40, 200, 20);
        labelMassa.setBounds(15, 70, 120, 20);
        textMassa.setBounds(115, 70, 200, 20);
        labelHarga.setBounds(15, 100, 120, 20);
        textHarga.setBounds(115, 100, 200, 20);
        buttonSubmit.setBounds(100, 140, 80, 20);
        buttonReset.setBounds(190, 140, 80, 20);
        buttonKembali.setBounds(100, 170, 170, 20);

        setVisible(true);
        setLocationRelativeTo(null);

        buttonSubmit.addActionListener(this);
        buttonReset.addActionListener(this);
        buttonKembali.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSubmit) {
            
            boolean catchError = false, catchNull = false;
            int massa = 0, harga = 0;

            if ( textNama.getText().length() == 0 || textMassa.getText().length() == 0 || textHarga.getText().length() == 0 ) {
                catchError = true;
                catchNull = true;
                JOptionPane.showMessageDialog(null, "Terdapat kolom yang kosong!");
            }

            if (!catchNull) {
                try {
                    massa = Integer.parseInt(textMassa.getText());
                    harga = Integer.parseInt(textHarga.getText());
                } catch (NumberFormatException error) {
                    catchError = true;
                    JOptionPane.showMessageDialog(null, "Kolom Massa dan Harga harus merupakan bilangan!");
                } catch (Exception error) {
                    catchError = true;
                    JOptionPane.showMessageDialog(null, error.getMessage());
                }
            }

            if (!catchError) {
                if (massa <= 0 || harga <= 0) {
                    catchError = true;
                    JOptionPane.showMessageDialog(null, "Kolom Massa dan Harga harus merupakan bilangan positif!");
                } else {
                    try {
                        String query = "UPDATE barang SET nama = '"+textNama.getText()+"', massa = "+massa+", harga = "+harga+" WHERE id = "+idBarang;
                        
                        connector.statement = connector.connection.createStatement();
                        connector.statement.executeUpdate(query);
            
                        System.out.println("Update Berhasil");
                        JOptionPane.showMessageDialog(null,"Update Berhasil !!");
                    } catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                    new LihatBarang();
                    this.dispose();
                }
            }   
        }
        
        if (e.getSource() == buttonReset) {
            this.textNama.setText("");
            this.textMassa.setText("");
            this.textHarga.setText("");
        }
        
        if (e.getSource() == buttonKembali) {
            this.dispose();
            new DetailItem(idBarang);
        }
    }
}
