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
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import model.ComboBoxItem;
import model.User;
import model.MySQLConn;
import model.MyTableModel;

/**
 *
 * @author AG
 */
public class CtrUser extends User {

    MySQLConn conn;
    PreparedStatement pstm;
    ResultSet rs;
    MyTableModel mdlUser;

    public CtrUser() {
    }

    public MyTableModel getModelListUser() {
        try {
            conn = new MySQLConn();
            mdlUser = new MyTableModel();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  username,\n"
                    + "  telepon,\n"
                    + "  namalengkap,\n"
                    + "  level\n"
                    + "FROM User;");
            rs = pstm.executeQuery();

            mdlUser.addColumn("Username");
            mdlUser.addColumn("Telepon");
            mdlUser.addColumn("Nama Lengkap");
            mdlUser.addColumn("Level");

            Object[] o;
            while (rs.next()) {
                o = new Object[4];
                o[0] = rs.getString("Username");
                o[1] = rs.getString("telepon");
                o[2] = rs.getString("namalengkap");
                o[3] = rs.getString("Level");
                mdlUser.addRow(o);
            }

            return mdlUser;
        } catch (SQLException ex) {
            Logger.getLogger(CtrUser.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public MyTableModel getModelCariUser() {
        try {
            conn = new MySQLConn();
            mdlUser = new MyTableModel();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  username,\n"
                    + "  telepon,\n"
                    + "  namalengkap,\n"
                    + "  level\n"
                    + "FROM user\n"
                    + "WHERE username like ?;");
            pstm.setString(1, "%" + getUsername() + "%");
            rs = pstm.executeQuery();

            mdlUser.addColumn("Username");
            mdlUser.addColumn("Telepon");
            mdlUser.addColumn("Nama Lengkap");
            mdlUser.addColumn("Level");

            Object[] o;
            while (rs.next()) {
                o = new Object[4];
                o[0] = rs.getString("username");
                o[1] = rs.getString("telepon");
                o[2] = rs.getString("namalengkap");
                o[3] = rs.getString("level");
                mdlUser.addRow(o);
            }

            return mdlUser;
        } catch (SQLException ex) {
            Logger.getLogger(CtrUser.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean tambahUser() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "INSERT INTO User\n"
                    + "            (username,\n"
                    + "             telepon,\n"
                    + "             password,\n"
                    + "             namalengkap,\n"
                    + "             level)\n"
                    + "VALUES (?,?,?,?,?);");
            pstm.setString(1, getUsername());
            pstm.setString(2, getTelepon());
            pstm.setString(3, getPassword());
            pstm.setString(4, getNamalengkap());
            pstm.setString(5, getLevel());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrUser.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ubahUser() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "UPDATE User\n"
                    + "SET \n"
                    + "  telepon = ?,\n"
                    + "  namalengkap = ?,\n"
                    + "  level = ?\n"
                    + "WHERE username = ?");
            pstm.setString(1, getTelepon());
            pstm.setString(2, getNamalengkap());
            pstm.setString(3, getLevel());
            pstm.setString(4, getUsername());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrUser.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean hapusUser() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "DELETE\n"
                    + "FROM User\n"
                    + "WHERE username = ?");
            pstm.setString(1, getUsername());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrUser.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public User checkLogin() {
        try {
            User User = null;
            conn = new MySQLConn();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  id_user,\n"
                    + "  username,\n"
                    + "  namalengkap,\n"
                    + "  level\n"
                    + "FROM User\n"
                    + "WHERE username = ?\n"
                    + "   and password = ?");
            pstm.setString(1, getUsername());
            pstm.setString(2, getPassword());
            rs = pstm.executeQuery();

            if (rs.next()) {
                User = new User();
                User.setId_user(rs.getInt("id_user"));
                User.setUsername(rs.getString("username"));
                User.setNamalengkap(rs.getString("namalengkap"));
                User.setLevel(rs.getString("level"));
                pstm = conn.connect("INSERT INTO log (id_user, create_date) VALUES(?, NOW())");
                pstm.setInt(1, User.getId_user());
                pstm.executeUpdate();
            }

            return User;
        } catch (SQLException ex) {
            Logger.getLogger(CtrUser.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ComboBoxModel getModelComboBoxUser() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  username,\n"
                    + "  namalengkap\n"
                    + "FROM User;");
            rs = pstm.executeQuery();

            DefaultComboBoxModel<ComboBoxItem> isiCmbUser = new DefaultComboBoxModel<>();

            while (rs.next()) {
                isiCmbUser.addElement(new ComboBoxItem(rs.getString(2), rs.getString(1)));
            }

            return isiCmbUser;
        } catch (SQLException ex) {
            Logger.getLogger(CtrUser.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean changePassword() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "UPDATE User\n"
                    + "SET \n"
                    + "  password = ?\n"
                    + "WHERE id_user = ?");
            pstm.setString(1, getPassword());
            pstm.setInt(2, getId_user());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrUser.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
