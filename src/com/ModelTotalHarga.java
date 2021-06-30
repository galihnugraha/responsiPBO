package com;

public class ModelTotalHarga {
    String nama, massa;
    int harga, totalHarga;

    public ModelTotalHarga(String[][] data, int totalHarga) {
        nama = data[0][0];
        massa = data[0][1];
        harga = Integer.parseInt(data[0][2].substring(0, data[0][2].length() - 2));
        this.totalHarga = totalHarga;
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

    public int getTotalHarga() {
        return this.totalHarga;
    }
}