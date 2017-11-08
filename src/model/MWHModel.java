/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author AG
 */
public class MWHModel {

    private String no_MWH;
    private String no_form;
    private int id_pemasok;
    private String no_surat_jalan;
    private Date tgl_MWH;
    private String warna;
    private String jmlh_pengiriman;
    private String jmlh_di_test;
    private String jenis_barang;
    private String ukuran;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNo_MWH() {
        return no_MWH;
    }

    public void setNo_MWH(String no_MWH) {
        this.no_MWH = no_MWH;
    }

    public String getNo_form() {
        return no_form;
    }

    public void setNo_form(String no_form) {
        this.no_form = no_form;
    }

    public int getId_pemasok() {
        return id_pemasok;
    }

    public void setId_pemasok(int id_pemasok) {
        this.id_pemasok = id_pemasok;
    }

    public String getNo_surat_jalan() {
        return no_surat_jalan;
    }

    public void setNo_surat_jalan(String no_surat_jalan) {
        this.no_surat_jalan = no_surat_jalan;
    }

    public Date getTgl_MWH() {
        return tgl_MWH;
    }

    public void setTgl_MWH(Date tgl_MWH) {
        this.tgl_MWH = tgl_MWH;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getJmlh_pengiriman() {
        return jmlh_pengiriman;
    }

    public void setJmlh_pengiriman(String jmlh_pengiriman) {
        this.jmlh_pengiriman = jmlh_pengiriman;
    }

    public String getJmlh_di_test() {
        return jmlh_di_test;
    }

    public void setJmlh_di_test(String jmlh_di_test) {
        this.jmlh_di_test = jmlh_di_test;
    }

    public String getJenis_barang() {
        return jenis_barang;
    }

    public void setJenis_barang(String jenis_barang) {
        this.jenis_barang = jenis_barang;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

}
