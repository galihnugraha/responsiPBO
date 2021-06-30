package com;

import java.awt.event.*;
import javax.swing.*;

public class ViewTotalHarga extends JFrame implements ActionListener {
    ModelTotalHarga modelTotalHarga;

    String idBarang;
    int banyakItem, totalHarga;

    JLabel labelJudul = new JLabel("Total Harga");
    JLabel labelNama = new JLabel("Nama : ");
    JLabel labelMassa = new JLabel("Massa (gr) : ");
    JLabel labelHarga = new JLabel("Harga Satuan : ");
    JLabel labelTotalHarga = new JLabel("Total Harga : ");

    JLabel textNama, textMassa, textHarga, textTotalHarga;
    
    JButton buttonKembali = new JButton("Kembali");

    public ViewTotalHarga(String idBarang, String nama, String massa, int harga, int totalHarga){    
        this.idBarang = idBarang;
        
        setTitle(nama);
        setSize(370, 350);

        textNama = new JLabel(nama);
        textMassa = new JLabel(massa);
        textHarga = new JLabel("Rp."+harga);
        textTotalHarga = new JLabel("Rp."+totalHarga);

        setLayout(null);
        add(labelJudul);
        add(labelNama);
        add(labelMassa);
        add(labelHarga);
        add(labelTotalHarga);
        add(textNama);
        add(textMassa);
        add(textHarga);
        add(textTotalHarga);
        add(buttonKembali);

        labelJudul.setBounds(150, 10, 120, 20);
        labelNama.setBounds(15, 40, 120, 20);
        textNama.setBounds(115, 40, 200, 20);
        labelMassa.setBounds(15, 70, 120, 20);
        textMassa.setBounds(115, 70, 200, 20);
        labelHarga.setBounds(15, 100, 120, 20);
        textHarga.setBounds(115, 100, 200, 20);
        labelTotalHarga.setBounds(15, 130, 120, 20);
        textTotalHarga.setBounds(115, 130, 200, 20);
        buttonKembali.setBounds(100, 270, 170, 20);

        setVisible(true);
        setLocationRelativeTo(null);

        buttonKembali.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonKembali) {
            this.dispose();
            new DetailItem(this.idBarang);
        }
    }
}
