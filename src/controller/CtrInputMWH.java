/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import model.MWHModel;
import model.MySQLConn;
import model.MyTableModel;
import model.Parameter;

/**
 *
 * @author AG
 */
public class CtrInputMWH extends MWHModel {

    /*SELECT m.no_MWH AS NoMWH
    , m.no_form AS NoForm
    , m.id_pemasok
    , p.nama_pemasok AS NamaPemasok
    , m.no_surat_jalan AS NoSuratJalan
    , m.tgl_MWH AS Tanggal
    , m.warna AS Warna
    , m.jmlh_pengiriman AS Qty
    , m.jmlh_di_test AS QtyTest
    , m.jenis_barang AS Jenis
    , m.ukuran AS Ukuran
    FROM mwh AS m
    INNER JOIN pemasok AS p
    ON p.id_pemasok = m.id_pemasok */
    Parameter param;

    MySQLConn conn;
    PreparedStatement pstm;
    ResultSet rs;
    MyTableModel mdlInputMWH;

    public CtrInputMWH() {
        param = new Parameter();
    }

    public Parameter getParam() {
        return param;
    }

    public void setParam(Parameter param) {
        this.param = param;
    }

    public TableModel getModelInputMWH() {
        try {
            conn = new MySQLConn();
            mdlInputMWH = new MyTableModel();
            pstm = conn.connect("SELECT\n"
                    + "  m.no_MWH AS NoMWH,\n"
                    + "  m.no_form AS NoForm,\n"
                    + "  p.nama_pemasok AS NamaPemasok,\n"
                    + "  m.no_surat_jalan AS NoSuratJalan,\n"
                    + "  m.tgl_MWH AS Tanggal,\n"
                    + "  m.warna AS Warna,\n"
                    + "  m.jmlh_pengiriman AS Qty,\n"
                    + "  m.jmlh_di_test AS QtyTest,\n"
                    + "  m.jenis_barang AS Jenis,\n"
                    + "  m.ukuran AS Ukuran\n"
                    + "FROM mwh AS m\n"
                    + "INNER JOIN pemasok AS p\n"
                    + "ON p.id_pemasok = m.id_pemasok");
            rs = pstm.executeQuery();

            mdlInputMWH.addColumn("No MWH");
            mdlInputMWH.addColumn("No Form");
            mdlInputMWH.addColumn("Pemasok");
            mdlInputMWH.addColumn("No Surat Jalan");
            mdlInputMWH.addColumn("Tanggal MWH");
            mdlInputMWH.addColumn("Warna");
            mdlInputMWH.addColumn("Jumlah Pengiriman");
            mdlInputMWH.addColumn("Jumlah di Test");
            mdlInputMWH.addColumn("Jenis Barang");
            mdlInputMWH.addColumn("Ukuran");

            Object[] o;
            while (rs.next()) {
                o = new Object[10];
                o[0] = rs.getString("NoMWH");
                o[1] = rs.getString("NoForm");
                o[2] = rs.getString("NamaPemasok");
                o[3] = rs.getString("NoSuratJalan");
                o[4] = rs.getString("Tanggal");
                o[5] = rs.getString("Warna");
                o[6] = rs.getString("Qty");
                o[7] = rs.getString("QtyTest");
                o[8] = rs.getString("Jenis");
                o[9] = rs.getString("Ukuran");
                mdlInputMWH.addRow(o);
            }

            return mdlInputMWH;
        } catch (SQLException ex) {
            Logger.getLogger(CtrInputMWH.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int getIdPemasok(String namaPemasok) {
        Integer idPemasok = null;
        try {
            MySQLConn con = new MySQLConn();
            PreparedStatement stm = con.connect("SELECT\n"
                    + "  id_pemasok,\n"
                    + "  nama_pemasok\n"
                    + "FROM pemasok\n"
                    + "WHERE nama_pemasok = ?;");
            stm.setString(1, namaPemasok);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                idPemasok = rs.getInt("id_pemasok");
                //System.out.println("Id: "+idPemasok);
            }
            return idPemasok;
        } catch (SQLException ex) {
            Logger.getLogger(CtrInputMWH.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public String getNamaPemasok(int idPemasok) {
        String sNamaPemasok = null;
        try {
            MySQLConn con = new MySQLConn();
            PreparedStatement stm = con.connect("SELECT\n"
                    + "  id_pemasok,\n"
                    + "  nama_pemasok\n"
                    + "FROM pemasok\n"
                    + "WHERE id_pemasok = ?;");
            stm.setInt(1, idPemasok);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                sNamaPemasok = rs.getString("nama_pemasok");
                //System.out.println("Id: "+idPemasok);
            }
            return sNamaPemasok;
        } catch (SQLException ex) {
            Logger.getLogger(CtrInputMWH.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sNamaPemasok;
    }

    public boolean SimpanMWH() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect("INSERT INTO mwh\n"
                    + "            (no_MWH,\n"
                    + "             no_form,\n"
                    + "             id_pemasok,\n"
                    + "             no_surat_jalan,\n"
                    + "             tgl_MWH,\n"
                    + "             warna,\n"
                    + "             jmlh_pengiriman,\n"
                    + "             jmlh_di_test,\n"
                    + "             jenis_barang,\n"
                    + "             ukuran,\n"
                    + "             STATUS)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,UPPER(?),0);");
            pstm.setString(1, getNo_MWH());
            pstm.setString(2, getNo_form());
            pstm.setInt(3, getParam().getIdPemasok());
            pstm.setString(4, getNo_surat_jalan());
            pstm.setDate(5, getTgl_MWH());
            pstm.setString(6, getWarna());
            pstm.setString(7, getJmlh_pengiriman());
            pstm.setString(8, getJmlh_di_test());
            pstm.setString(9, getJenis_barang());
            pstm.setString(10, getUkuran());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrInputMWH.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ubahMWH() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect("UPDATE mwh\n"
                    + "SET no_form = ?,\n"
                    + "  id_pemasok = ?,\n"
                    + "  no_surat_jalan = ?,\n"
                    + "  tgl_MWH = ?,\n"
                    + "  warna = ?,\n"
                    + "  jmlh_pengiriman = ?,\n"
                    + "  jmlh_di_test = ?,\n"
                    + "  jenis_barang = ?,\n"
                    + "  ukuran = UPPER(?)\n"
                    + "WHERE no_MWH = ?");
            pstm.setString(1, getNo_form());
            pstm.setInt(2, getParam().getIdPemasok());
            pstm.setString(3, getNo_surat_jalan());
            pstm.setDate(4, getTgl_MWH());
            pstm.setString(5, getWarna());
            pstm.setString(6, getJmlh_pengiriman());
            pstm.setString(7, getJmlh_di_test());
            pstm.setString(8, getJenis_barang());
            pstm.setString(9, getUkuran());
            pstm.setString(10, getNo_MWH());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrInputMWH.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
