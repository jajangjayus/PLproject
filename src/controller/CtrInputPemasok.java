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
import model.MySQLConn;
import model.MyTableModel;
import model.PemasokModel;

/**
 *
 * @author AG
 */
public class CtrInputPemasok extends PemasokModel {

    MySQLConn conn;
    PreparedStatement pstm;
    ResultSet rs;
    MyTableModel mdlInputPemasok;

    public CtrInputPemasok() {
    }

    public MyTableModel getModelInputPemasok() {
        try {
            conn = new MySQLConn();
            mdlInputPemasok = new MyTableModel();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  id_pemasok,\n"
                    + "  nama_pemasok,\n"
                    + "  alamat,\n"
                    + "  Phone\n"
                    + "FROM pertaminalubricants.pemasok");
            rs = pstm.executeQuery();

            mdlInputPemasok.addColumn("ID #");
            mdlInputPemasok.addColumn("Nama Pemasok");
            mdlInputPemasok.addColumn("Alamat");
            mdlInputPemasok.addColumn("Telepon");

            Object[] o;
            while (rs.next()) {
                o = new Object[4];
                o[0] = rs.getInt("id_pemasok");
                o[1] = rs.getString("nama_pemasok");
                o[2] = rs.getString("alamat");
                o[3] = rs.getString("Phone");
                mdlInputPemasok.addRow(o);
            }

            return mdlInputPemasok;
        } catch (SQLException ex) {
            Logger.getLogger(CtrInputPemasok.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean SimpanPemasok() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "INSERT INTO pemasok\n"
                    + "            (nama_pemasok,\n"
                    + "             alamat,\n"
                    + "             Phone)\n"
                    + "VALUES (?,?,?);");
            pstm.setString(1, getNama_pemasok());
            pstm.setString(2, getAlamat());
            pstm.setString(3, getPhone());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrInputPemasok.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ubahPemasok() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "UPDATE pemasok\n"
                    + "SET nama_pemasok = ?,\n"
                    + "  alamat = ?,\n"
                    + "  Phone = ?\n"
                    + "WHERE id_pemasok  = ?");
            pstm.setString(1, getNama_pemasok());
            pstm.setString(2, getAlamat());
            pstm.setString(3, getPhone());
            pstm.setInt(4, getId_pemasok());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrInputPemasok.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
