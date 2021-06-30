package com;

public class ModelHapusBarang {
    String nama, massa;
    int harga;

    public ModelHapusBarang(String[][] data) {
        nama = data[0][0];
        massa = data[0][1];
        harga = Integer.parseInt(data[0][2].substring(0, data[0][2].length() - 2));
    }

    public String getNama() {
        return this.nama;
    }
    
    public String getMassa() {
        return this.massa;
    }
    
    public int getHarga() {
        return this.harga;
    }
}