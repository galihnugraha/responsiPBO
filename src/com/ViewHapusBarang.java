package com;

import javax.swing.*;

public class ViewHapusBarang extends JFrame {
    ControllerHapusBarang controllerHapusBarang;
    ModelHapusBarang modelHapusBarang;

    ThenConnector connector = new ThenConnector();

    public ViewHapusBarang(String[][] data, String idBarang) {
        modelHapusBarang = new ModelHapusBarang(data);

        int opsi = JOptionPane.showConfirmDialog(null, "Benarkah anda ingin menghapus data "+modelHapusBarang.getNama()+" ?", "Penghapusan Data", JOptionPane.YES_NO_OPTION);
        
        if (opsi == JOptionPane.YES_OPTION) {
            try {
                String query = "DELETE FROM barang WHERE id = '"+idBarang+"'";
                
                connector.statement = connector.connection.createStatement();
                connector.statement.executeUpdate(query);
    
                System.out.println("Hapus Data Berhasil");
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }

            new LihatBarang();
            this.dispose();
        } else {
            this.dispose();
            new DetailItem(idBarang);
        }
    }
}
