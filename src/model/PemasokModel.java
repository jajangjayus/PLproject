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
public class PemasokModel {

    private int id_pemasok;
    private String nama_pemasok;
    private String alamat;
    private String Phone;

    public int getId_pemasok() {
        return id_pemasok;
    }

    public void setId_pemasok(int id_pemasok) {
        this.id_pemasok = id_pemasok;
    }

    public String getNama_pemasok() {
        return nama_pemasok;
    }

    public void setNama_pemasok(String nama_pemasok) {
        this.nama_pemasok = nama_pemasok;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

}
