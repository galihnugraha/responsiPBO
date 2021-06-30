package com;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener {
    JLabel labelJudul = new JLabel("Main Menu", SwingConstants.CENTER);
    JButton buttonTambahBarang = new JButton("Tambah Barang");
    JButton buttonLihatBarang = new JButton("Lihat Barang");

    public MainMenu(){
        setTitle("Aplikasi Responsi");
        setSize(180, 107);

        setLayout(new BorderLayout());
        add(labelJudul, "North");
        add(buttonTambahBarang, "Center");
        add(buttonLihatBarang, "South");

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buttonTambahBarang.addActionListener(this);
        buttonLihatBarang.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonTambahBarang) {
            new InputBarang();
        }

        if (e.getSource() == buttonLihatBarang) {
            new LihatBarang();
        }
    }
}
