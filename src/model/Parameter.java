/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author AG
 */
public class Parameter {

    public static String formatDate = "yyyy-MM-dd";

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return false;
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return false;
    }
    private int id_pemasok;
    private String nama_pemasok;

    public int getIdPemasok() {
        return id_pemasok;
    }

    public void setIdPemasok(int id_pemasok) {
        this.id_pemasok = id_pemasok;
    }

    public String getNamaPemasok() {
        return nama_pemasok;
    }

    public void setNamaPemasok(String nama_pemasok) {
        this.nama_pemasok = nama_pemasok;
    }

}
