/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MySQLConn;
import model.MyTableModel;
import model.NewForm;

/**
 *
 * @author AG
 */
public class CtrNewForm extends NewForm {

    MySQLConn conn;
    PreparedStatement pstm;
    ResultSet rs;
    MyTableModel mdlNewForm;

    public CtrNewForm() {
    }

    public MyTableModel getModelLaporanMwh(String noMwh) {
        try {
            conn = new MySQLConn();
            mdlNewForm = new MyTableModel();
            pstm = conn.connect("SELECT\n"
                    + "  s.sample,\n"
                    + "  s.no_MWH,\n"
                    + "  s.visual,\n"
                    + "  s.cavity_btl,\n"
                    + "  s.berat_btl,\n"
                    + "  s.tinggi_btl,\n"
                    + "  s.torsi_cap,\n"
                    + "  s.berat_cap,\n"
                    + "  s.tinggi_cap,\n"
                    + "  s.drop_test,\n"
                    + "  s.uji_volume,\n"
                    + "  s.tgl_test,\n"
                    + "  u.namalengkap,\n"
                    + "  s.pemeriksa\n"
                    + "FROM sample AS s\n"
                    + "INNER JOIN user AS u\n"
                    + "ON u.id_user = s.id_user\n"
                    + "WHERE s.no_MWH = ?");
            pstm.setString(1, noMwh);
            rs = pstm.executeQuery();

            mdlNewForm.addColumn("Sample Ke");
            mdlNewForm.addColumn("No MWH");
            mdlNewForm.addColumn("Visual");
            mdlNewForm.addColumn("Cavity Botol");
            mdlNewForm.addColumn("Berat Botol");
            mdlNewForm.addColumn("Tinggi Botol");
            mdlNewForm.addColumn("Torsi Capper");
            mdlNewForm.addColumn("Berat Capper");
            mdlNewForm.addColumn("Tinggi Capper");
            mdlNewForm.addColumn("Drop Test");
            mdlNewForm.addColumn("Uji Volume");
            mdlNewForm.addColumn("Tanggal Test");
            mdlNewForm.addColumn("Penguji");
            mdlNewForm.addColumn("Pemeriksa");

            Object[] o;
            while (rs.next()) {
                o = new Object[14];
                o[0] = rs.getString("s.sample");
                o[1] = rs.getString("s.no_MWH");
                o[2] = rs.getString("s.visual");
                o[3] = rs.getString("s.cavity_btl");
                o[4] = rs.getString("s.berat_btl");
                o[5] = rs.getString("s.tinggi_btl");
                o[6] = rs.getString("s.torsi_cap");
                o[7] = rs.getString("s.berat_cap");
                o[8] = rs.getString("s.tinggi_cap");
                o[9] = rs.getString("s.drop_test");
                o[10] = rs.getString("s.uji_volume");
                o[11] = rs.getString("s.uji_volume");
                o[12] = rs.getString("u.namalengkap");
                o[13] = rs.getString("s.pemeriksa");
                mdlNewForm.addRow(o);
            }

            return mdlNewForm;
        } catch (SQLException ex) {
            Logger.getLogger(CtrNewForm.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public MyTableModel getModelNewForm(String noMwh) {
        try {
            conn = new MySQLConn();
            mdlNewForm = new MyTableModel();
            pstm = conn.connect("SELECT\n"
                    + "  Count,\n"
                    + "  visual,\n"
                    + "  cavity_btl,\n"
                    + "  berat_btl,\n"
                    + "  tinggi_btl,\n"
                    + "  berat_cap,\n"
                    + "  tinggi_cap,\n"
                    + "  torsi_cap,\n"
                    + "  drop_test,\n"
                    + "  uji_volume\n"
                    + "FROM pertaminalubricants.tmpsample "
                    + "WHERE no_MWH = ?");
            pstm.setString(1, noMwh);
            rs = pstm.executeQuery();

            mdlNewForm.addColumn("Count");
            mdlNewForm.addColumn("Visual");
            mdlNewForm.addColumn("Cavity Botol");
            mdlNewForm.addColumn("Berat Botol");
            mdlNewForm.addColumn("Tinggi Botol");
            mdlNewForm.addColumn("Torsi Capper");
            mdlNewForm.addColumn("Berat Capper");
            mdlNewForm.addColumn("Tinggi Capper");
            mdlNewForm.addColumn("Drop Test");
            mdlNewForm.addColumn("Uji Volume");

            Object[] o;
            while (rs.next()) {
                o = new Object[10];
                o[0] = rs.getInt("Count");
                o[1] = rs.getString("visual");
                o[2] = rs.getString("cavity_btl");
                o[3] = rs.getString("berat_btl");
                o[4] = rs.getString("tinggi_btl");
                o[5] = rs.getString("torsi_cap");
                o[6] = rs.getString("berat_cap");
                o[7] = rs.getString("tinggi_cap");
                o[8] = rs.getString("drop_test");
                o[9] = rs.getString("uji_volume");
                mdlNewForm.addRow(o);
            }

            return mdlNewForm;
        } catch (SQLException ex) {
            Logger.getLogger(CtrNewForm.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean tambahSample() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect("INSERT INTO tmpsample\n"
                    + "            (Count, \n"
                    + "             no_MWH,\n"
                    + "             visual,\n"
                    + "             cavity_btl,\n"
                    + "             berat_btl,\n"
                    + "             tinggi_btl,\n"
                    + "             berat_cap,\n"
                    + "             tinggi_cap,\n"
                    + "             torsi_cap,\n"
                    + "             drop_test,\n"
                    + "             uji_volume,\n"
                    + "             tgl_test,\n"
                    + "             id_user,\n"
                    + "             pemeriksa)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

            pstm.setInt(1, getCount());
            pstm.setString(2, getNomwh());
            pstm.setString(3, getVisual());
            pstm.setString(4, getCavity_btl());
            pstm.setString(5, getBerat_btl());
            pstm.setString(6, getTinggi_btl());
            pstm.setString(7, getBerat_cap());
            pstm.setString(8, getTinggi_cap());
            pstm.setString(9, getTorsi_cap());
            pstm.setString(10, getDrop_test());
            pstm.setString(11, getUji_volume());
            pstm.setDate(12, (Date) getTgl_test());
            pstm.setInt(13, getIduser());
            pstm.setString(14, getPemeriksa());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrInputMWH.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ubahSample() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect("UPDATE tmpsample\n"
                    + "SET visual = ?,\n"
                    + "  cavity_btl = ?,\n"
                    + "  berat_btl = ?,\n"
                    + "  tinggi_btl = ?,\n"
                    + "  berat_cap = ?,\n"
                    + "  tinggi_cap = ?,\n"
                    + "  torsi_cap = ?,\n"
                    + "  drop_test = ?,\n"
                    + "  uji_volume = ?,\n"
                    + "  tgl_test = ?,\n"
                    + "  id_user = ?,\n"
                    + "  pemeriksa = ?\n"
                    + "WHERE Count = ?");

            pstm.setString(1, getVisual());
            pstm.setString(2, getCavity_btl());
            pstm.setString(3, getBerat_btl());
            pstm.setString(4, getTinggi_btl());
            pstm.setString(5, getBerat_cap());
            pstm.setString(6, getTinggi_cap());
            pstm.setString(7, getTorsi_cap());
            pstm.setString(8, getDrop_test());
            pstm.setString(9, getUji_volume());
            pstm.setDate(10, (Date) getTgl_test());
            pstm.setInt(11, getIduser());
            pstm.setString(12, getPemeriksa());
            pstm.setInt(13, getCount());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrInputMWH.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
