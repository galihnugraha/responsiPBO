package com;

public class ModelEditBarang {
    String nama;
    int massa, harga;

    public ModelEditBarang(String[][] data) {
        nama = data[0][0];
        massa = Integer.parseInt(data[0][1].substring(0, data[0][1].length() - 2));
        harga = Integer.parseInt(data[0][2].substring(0, data[0][2].length() - 2));
    }

    public String getNama() {
        return this.nama;
    }
    
    public int getMassa() {
        return this.massa;
    }
    
    public int getHarga() {
        return this.harga;
    }
}