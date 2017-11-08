/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.CtrNewForm;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.MySQLConn;
import model.Parameter;

/**
 *
 * @author AG
 */
public class TRPengujian extends javax.swing.JInternalFrame {

    Connection koneksi = null;
    PreparedStatement ps = null;
    Statement state = null;
    ResultSet rs = null;
    int idUser;

    /**
     * Creates new form NewForm
     */
    public TRPengujian() {
        initComponents();
        setTitle("Pertamina Lubricants | Testing");
        CtrNewForm cn = new CtrNewForm();
        jxdTanggal.setFormats(Parameter.formatDate);
        jxdTanggal.setDate(new java.util.Date());
        getUserLogin();
        isiComboBox();
        setEnableHeader(true);
        setEnableUji(false);
        getHeaderValue(mwhComboBox.getSelectedItem().toString());
        tblSample.setModel(cn.getModelNewForm(mwhComboBox.getSelectedItem().toString()));
        getRowCount();
    }

    private void getUserLogin() {
        try {
            koneksi = MySQLConn.getKoneksi();
            state = koneksi.createStatement();
            String sql = "SELECT us.id_user, us.namalengkap, lg.create_date FROM LOG AS lg\n"
                    + "INNER JOIN USER AS us\n"
                    + "ON us.id_user = lg.id_user\n"
                    + "ORDER BY lg.id_log DESC\n"
                    + "LIMIT 1";

            rs = state.executeQuery(sql);
            if (rs.next()) {

                String sNamaUser = rs.getString("namalengkap");
                Sesion.setText(sNamaUser);
                this.idUser = rs.getInt("id_user");
            }
            rs.close();
            state.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void isiComboBox() {
        mwhComboBox.removeAllItems();
        String sNoMWH = "";
        String sJumlahTest = "";
        try {
            koneksi = MySQLConn.getKoneksi();
            state = koneksi.createStatement();
            String query = "SELECT * FROM mwh WHERE status = 0";

            rs = state.executeQuery(query);
            while (rs.next()) {

                sNoMWH = rs.getString("no_MWh");
                sJumlahTest = rs.getString("jmlh_di_test");
                mwhComboBox.addItem(sNoMWH);

            }
            if (sNoMWH.isEmpty()) {
                mwhComboBox.addItem("Tidak ada data");
            }

            rs.close();
            state.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void getHeaderValue(String noMwh) {
        String sJumlahTest = "";
        String sPemeriksa = "";
        String sTanggalTest = "";
        try {
            koneksi = MySQLConn.getKoneksi();
            String query = "SELECT m.jmlh_di_test, ts.pemeriksa, ts.tgl_test \n"
                    + " FROM mwh AS m \n"
                    + " INNER JOIN tmpsample AS ts \n"
                    + "   ON ts.no_MWH = m.no_MWH\n"
                    + " WHERE m.no_MWh = ?";

            ps = koneksi.prepareStatement(query);
            ps.setString(1, noMwh);
            rs = ps.executeQuery();

            if (rs.next()) {
                sJumlahTest = rs.getString("m.jmlh_di_test");
                sPemeriksa = rs.getString("ts.pemeriksa");
                sTanggalTest = rs.getDate("ts.tgl_test").toString();
                try {
                    java.util.Date sDate = new SimpleDateFormat("yyy-MM-dd").parse(sTanggalTest);
                    jxdTanggal.setDate(sDate);
                } catch (ParseException ex) {
                    Logger.getLogger(MDMWH.class.getName()).log(Level.SEVERE, null, ex);
                }
                txtJumlahTest.setText(sJumlahTest);
                txtPemeriksa.setText(sPemeriksa);
            } else {
                String queryOnlyJumlahTest = "SELECT m.jmlh_di_test\n"
                        + " FROM mwh AS m \n"
                        + " WHERE m.no_MWh = ?";
                ps = koneksi.prepareStatement(queryOnlyJumlahTest);
                ps.setString(1, noMwh);
                rs = ps.executeQuery();
                if (rs.next()) {
                    sJumlahTest = rs.getString("m.jmlh_di_test");
                    txtJumlahTest.setText(sJumlahTest);
                    txtPemeriksa.setText("");
                    jxdTanggal.setDate(new java.util.Date());
                }
            }

            /*if (sPemeriksa.isEmpty()) {
            txtPemeriksa.setText("");
            jxdTanggal.setDate(new java.util.Date());
            }*/
            rs.close();
            state.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void updateMWH(String noMWH) {
        try {
            koneksi = MySQLConn.getKoneksi();
            String sql = "UPDATE mwh SET status = 1 WHERE no_MWH = ?";
            ps = koneksi.prepareStatement(sql);
            ps.setString(1, noMWH);
            ps.executeUpdate();

        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TRPengujian.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void setEnableUji(boolean status) {
        txtCavity.setEnabled(status);
        txtBeratBtl.setEnabled(status);
        txtTinggiBtl.setEnabled(status);
        txtTorsiCap.setEnabled(status);
        txtBeratCap.setEnabled(status);
        txtTinggiCap.setEnabled(status);
        cbVisual.setEnabled(status);
        txtDropTest.setEnabled(status);
        txtUjiVolume.setEnabled(status);
        btnTambahButton.setEnabled(status);
        btnUbahSample.setEnabled(status);
        btnSimpan.setEnabled(status);
        btnCetak.setEnabled(status);
    }

    private void setEnableHeader(boolean status) {
        mwhComboBox.setEnabled(status);
        jxdTanggal.setEnabled(status);
        txtPemeriksa.setEnabled(status);
    }

    private void tambahSample(int iRowCount, String nomwh, Date tgltest, String cavitybtl, String beratbtl, String tinggibtl, String torsicap, String beratcap,
            String tinggicap, String visual, String droptest, String ujivolume, int idUser, String pemeriksa) {
        CtrNewForm cn = new CtrNewForm();
        cn.setCount(iRowCount);
        cn.setNomwh(nomwh);
        cn.setTgl_test(tgltest);
        cn.setPemeriksa(pemeriksa);
        cn.setCavity_btl(cavitybtl);
        cn.setBerat_btl(beratbtl);
        cn.setTinggi_btl(tinggibtl);
        cn.setTorsi_cap(torsicap);
        cn.setBerat_cap(beratcap);
        cn.setTinggi_cap(tinggicap);
        cn.setVisual(visual);
        cn.setDrop_test(droptest);
        cn.setUji_volume(ujivolume);
        cn.setIduser(idUser);
        cn.tambahSample();
    }

    private void ubahSample(int iRowCount, String nomwh, Date tgltest, String cavitybtl, String beratbtl, String tinggibtl, String torsicap, String beratcap,
            String tinggicap, String visual, String droptest, String ujivolume, int idUser, String pemeriksa) {
        CtrNewForm cn = new CtrNewForm();
        cn.setCount(iRowCount);
        cn.setNomwh(nomwh);
        cn.setTgl_test(tgltest);
        cn.setPemeriksa(pemeriksa);
        cn.setCavity_btl(cavitybtl);
        cn.setBerat_btl(beratbtl);
        cn.setTinggi_btl(tinggibtl);
        cn.setTorsi_cap(torsicap);
        cn.setBerat_cap(beratcap);
        cn.setTinggi_cap(tinggicap);
        cn.setVisual(visual);
        cn.setDrop_test(droptest);
        cn.setUji_volume(ujivolume);
        cn.setIduser(idUser);
        cn.ubahSample();
    }

    private void clearField() {
        txtCavity.setText("");
        txtBeratBtl.setText("");
        txtTinggiBtl.setText("");
        txtTorsiCap.setText("");
        txtBeratCap.setText("");
        txtTinggiCap.setText("");
        cbVisual.setSelectedIndex(0);
        //mwhComboBox.setSelectedIndex(0);
        txtDropTest.setText("");
        txtUjiVolume.setText("");
        //txtPemeriksa.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnCetak = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblForm = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        lblPenguji = new javax.swing.JLabel();
        lblPemeriksa = new javax.swing.JLabel();
        txtPemeriksa = new javax.swing.JTextField();
        jxdTanggal = new org.jdesktop.swingx.JXDatePicker();
        mwhComboBox = new javax.swing.JComboBox();
        Sesion = new javax.swing.JLabel();
        confirmCheckBox = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        txtJumlahTest = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblTotalRecord = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSample = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lblCavity = new javax.swing.JLabel();
        lblBeratBtl = new javax.swing.JLabel();
        lbTinggiBtl = new javax.swing.JLabel();
        txtCavity = new javax.swing.JFormattedTextField();
        txtBeratBtl = new javax.swing.JFormattedTextField();
        txtTinggiBtl = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        lblTorsi = new javax.swing.JLabel();
        lblBeratCap = new javax.swing.JLabel();
        lblTinggiCap = new javax.swing.JLabel();
        txtTorsiCap = new javax.swing.JFormattedTextField();
        txtBeratCap = new javax.swing.JFormattedTextField();
        txtTinggiCap = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        lblVisual = new javax.swing.JLabel();
        lblDropTest = new javax.swing.JLabel();
        lblUjiVolume = new javax.swing.JLabel();
        cbVisual = new javax.swing.JComboBox<>();
        txtDropTest = new javax.swing.JFormattedTextField();
        txtUjiVolume = new javax.swing.JFormattedTextField();
        btnTambahButton = new javax.swing.JButton();
        btnUbahSample = new javax.swing.JButton();

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        btnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel-icon 24.png"))); // NOI18N
        btnCetak.setText("Cancel");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });
        jPanel6.add(btnCetak);

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Save-icon 24.png"))); // NOI18N
        btnSimpan.setText("Save");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel6.add(btnSimpan);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Request Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 0, 0))); // NOI18N

        lblForm.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblForm.setText("Ref Perm Test (MWH) :");

        lblTanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTanggal.setText("Test Date:");

        lblPenguji.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPenguji.setText("Testers:");

        lblPemeriksa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPemeriksa.setText("* Inspector:");

        mwhComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        mwhComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                mwhComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        Sesion.setText("-");

        confirmCheckBox.setText("Confirm MWH Number");
        confirmCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmCheckBoxActionPerformed(evt);
            }
        });

        jLabel4.setText("Number of Tests:");

        txtJumlahTest.setText("0");

        jLabel5.setText("Remaining");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTanggal)
                    .addComponent(lblForm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(confirmCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jxdTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                    .addComponent(mwhComboBox, 0, 178, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblPemeriksa))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtJumlahTest, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(lblPenguji)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPemeriksa, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jxdTanggal, mwhComboBox, txtPemeriksa});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblForm, lblTanggal});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblPemeriksa, lblPenguji});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblForm)
                    .addComponent(mwhComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(lblPenguji)
                    .addComponent(Sesion, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtJumlahTest))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTanggal)
                    .addComponent(jxdTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPemeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPemeriksa, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmCheckBox))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblPemeriksa, lblPenguji});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel4, txtJumlahTest});

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel3.setText("Sample to -");
        jPanel7.add(jLabel3);

        lblTotalRecord.setText("0");
        jPanel7.add(lblTotalRecord);

        tblSample.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSample.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSampleMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSample);

        jPanel8.setBackground(new java.awt.Color(255, 255, 102));

        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("All fields marked with an asterisk (*) must contain data.");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Testing Form");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Testing Information (Please Input Number Only)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 0, 0))); // NOI18N

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Botol"));

        lblCavity.setText("* Cavity");

        lblBeratBtl.setText("* Berat");

        lbTinggiBtl.setText("* Tinggi");

        txtCavity.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.0"))));
        txtCavity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCavityKeyTyped(evt);
            }
        });

        txtBeratBtl.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.0"))));

        txtTinggiBtl.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCavity)
                    .addComponent(lblBeratBtl)
                    .addComponent(lbTinggiBtl))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtBeratBtl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(txtCavity, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTinggiBtl))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lbTinggiBtl, lblBeratBtl, lblCavity});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCavity)
                    .addComponent(txtCavity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBeratBtl)
                    .addComponent(txtBeratBtl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTinggiBtl)
                    .addComponent(txtTinggiBtl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Capper"));

        lblTorsi.setText("* Torsi");

        lblBeratCap.setText("* Berat");

        lblTinggiCap.setText("* Tinggi");

        txtTorsiCap.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        txtBeratCap.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        txtTinggiCap.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTorsi)
                    .addComponent(lblTinggiCap)
                    .addComponent(lblBeratCap))
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTorsiCap, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(txtBeratCap)
                    .addComponent(txtTinggiCap))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblBeratCap, lblTinggiCap, lblTorsi});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTorsi)
                    .addComponent(txtTorsiCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBeratCap)
                    .addComponent(txtBeratCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTinggiCap)
                    .addComponent(txtTinggiCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblVisual.setText("* Visual");

        lblDropTest.setText("* Drop Test");

        lblUjiVolume.setText("* Uji Volume");

        cbVisual.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baik", "Tidak Baik" }));

        txtDropTest.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        txtUjiVolume.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVisual)
                    .addComponent(lblDropTest)
                    .addComponent(lblUjiVolume))
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbVisual, 0, 116, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDropTest, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(txtUjiVolume))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblDropTest, lblUjiVolume, lblVisual});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVisual)
                    .addComponent(cbVisual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDropTest)
                    .addComponent(txtDropTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUjiVolume)
                    .addComponent(txtUjiVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnTambahButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Folder-Add-icon 32.png"))); // NOI18N
        btnTambahButton.setText("Add");
        btnTambahButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahButtonActionPerformed(evt);
            }
        });

        btnUbahSample.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit-validated-icon 32.png"))); // NOI18N
        btnUbahSample.setText("Edit");
        btnUbahSample.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahSampleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTambahButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUbahSample))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnTambahButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUbahSample))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel9, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        String sNoMWH = mwhComboBox.getSelectedItem().toString();
        CtrNewForm cn = new CtrNewForm();
        int count = tblSample.getRowCount();
        if (sNoMWH.trim() != "") {
            try {
                koneksi = MySQLConn.getKoneksi();
                state = koneksi.createStatement();
                String sql = " SELECT ts.no_MWH, mw.jmlh_di_test FROM tmpsample AS ts"
                        + " INNER JOIN mwh AS mw"
                        + " ON mw.no_MWH = ts.no_MWH"
                        + " WHERE ts.no_MWH = ? OR mw.jmlh_di_test = ?";
                ps = koneksi.prepareStatement(sql);
                ps.setString(1, sNoMWH);
                ps.setString(2, String.valueOf(count));
                rs = ps.executeQuery();

                if (rs.next()) {
                    String jmlTest = rs.getString("jmlh_di_test");
                    int inJmlTest = Integer.valueOf(jmlTest);
                    if (count == inJmlTest) {
                        updateMWH(sNoMWH);
                        simpanKeSampleTable();
                        hapusTmpSampleTable();
                        clearField();
                        setEnableHeader(true);
                        setEnableUji(false);
                        isiComboBox();
                        getHeaderValue(sNoMWH);
                        tblSample.setModel(cn.getModelNewForm(mwhComboBox.getSelectedItem().toString()));
                        JOptionPane.showMessageDialog(null, "Transaksi selesai, data disimpan ke server");
                    } else {
                        JOptionPane.showMessageDialog(null, "Terjadi kesalahan, Jumlah unit yang diuji belum terpenuhi");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan, Belum ada data untuk disimpan");
                }

            } catch (SQLException ex) {
                Logger.getLogger(TRPengujian.class.getName())
                        .log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan, Tidak ada permintaan test");
        }

    }//GEN-LAST:event_btnSimpanActionPerformed

    private void confirmCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmCheckBoxActionPerformed
        // TODO add your handling code here:
        if (confirmCheckBox.isSelected()) {
            String sMwh = mwhComboBox.getSelectedItem().toString();
            String sPemeriksa = txtPemeriksa.getText();
            if (sMwh.equals("Tidak ada data")) {
                JOptionPane.showMessageDialog(null, "Tidak ada data permintaan test");
                confirmCheckBox.setSelected(false);
            } else if (sPemeriksa.equals("")) {
                JOptionPane.showMessageDialog(null, "Data pemeriksa belum diisi");
                confirmCheckBox.setSelected(false);
            } else {
                setEnableHeader(false);
                setEnableUji(true);
                btnUbahSample.setEnabled(false);
            }
        } else {
            setEnableHeader(true);
            setEnableUji(false);
            clearField();
        }
    }//GEN-LAST:event_confirmCheckBoxActionPerformed

    private void btnTambahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahButtonActionPerformed
        //String sNoMWH = mwhComboBox.getSelectedItem().toString();
        CtrNewForm cn = new CtrNewForm();
        String snomwh = mwhComboBox.getSelectedItem().toString();
        String scavitybtl = txtCavity.getText();
        String sberatbtl = txtBeratBtl.getText();
        String stinggibtl = txtTinggiBtl.getText();
        String storsicap = txtTorsiCap.getText();
        String sberatcap = txtBeratCap.getText();
        String stinggicap = txtTinggiCap.getText();
        String svisual = cbVisual.getSelectedItem().toString();
        String sdroptest = txtDropTest.getText();
        String sujivolume = txtUjiVolume.getText();
        String spemeriksa = txtPemeriksa.getText();
        Date stgltest = new java.sql.Date(jxdTanggal.getDate().getTime());
        //int count = tblSample.getRowCount();
        int count = Integer.valueOf(lblTotalRecord.getText());
        int iRowCount = tblSample.getRowCount();
        try {
            koneksi = MySQLConn.getKoneksi();
            state = koneksi.createStatement();
            String sql = " SELECT mw.no_MWH, mw.jmlh_di_test FROM mwh AS mw"
                    + " WHERE mw.no_MWH = ?";
            ps = koneksi.prepareStatement(sql);
            ps.setString(1, snomwh);
            rs = ps.executeQuery();

            if (rs.next()) {
                String jmlTest = rs.getString("jmlh_di_test");
                int inJmlTest = Integer.valueOf(jmlTest);
                if (count >= inJmlTest) {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan, Jumlah unit yang diuji sudah terpenuhi");
                } else if (snomwh.trim().equals("") || scavitybtl.trim().equals("") || sberatbtl.trim().equals("")
                        || stinggibtl.trim().equals("") || storsicap.trim().equals("") || sberatcap.trim().equals("")
                        || stinggicap.trim().equals("") || svisual.trim().equals("")
                        || sdroptest.trim().equals("") || sujivolume.trim().equals("") || spemeriksa.trim().equals("")) {
                    JOptionPane.showMessageDialog(this, "Data belum lengkap, Mohon periksa kembali!");
                } else {
                    tambahSample(iRowCount + 1, snomwh, stgltest, scavitybtl, sberatbtl, stinggibtl, storsicap, sberatcap,
                            stinggicap, svisual, sdroptest, sujivolume, idUser, spemeriksa);
                    tblSample.setModel(cn.getModelNewForm(snomwh));
                    JOptionPane.showMessageDialog(this, "Data berhasil ditambah");
                    clearField();
                    getRowCount();
                    int iJumlahTest = Integer.valueOf(txtJumlahTest.getText());
                    int iTotal = iJumlahTest - 1;
                    txtJumlahTest.setText("" + iTotal);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan, Belum ada data untuk disimpan");
            }

        } catch (SQLException ex) {
            Logger.getLogger(TRPengujian.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnTambahButtonActionPerformed

    private void btnUbahSampleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahSampleActionPerformed
        CtrNewForm cn = new CtrNewForm();
        String snomwh = mwhComboBox.getSelectedItem().toString();
        String scavitybtl = txtCavity.getText();
        String sberatbtl = txtBeratBtl.getText();
        String stinggibtl = txtTinggiBtl.getText();
        String storsicap = txtTorsiCap.getText();
        String sberatcap = txtBeratCap.getText();
        String stinggicap = txtTinggiCap.getText();
        String svisual = cbVisual.getSelectedItem().toString();
        String sdroptest = txtDropTest.getText();
        String sujivolume = txtUjiVolume.getText();
        String spemeriksa = txtPemeriksa.getText();
        Date stgltest = new java.sql.Date(jxdTanggal.getDate().getTime());
        int row = tblSample.getSelectedRow();
        int iRowCount = (int) tblSample.getModel().getValueAt(row, 0);
        if (iRowCount == 0 || snomwh.trim().equals("") || scavitybtl.trim().equals("") || sberatbtl.trim().equals("")
                || stinggibtl.trim().equals("") || storsicap.trim().equals("") || sberatcap.trim().equals("")
                || stinggicap.trim().equals("") || svisual.trim().equals("")
                || sdroptest.trim().equals("") || sujivolume.trim().equals("") || spemeriksa.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Data belum lengkap, Mohon periksa kembali!");
        } else {
            ubahSample(iRowCount, snomwh, stgltest, scavitybtl, sberatbtl, stinggibtl, storsicap, sberatcap,
                    stinggicap, svisual, sdroptest, sujivolume, idUser, spemeriksa);

            JOptionPane.showMessageDialog(this, "Data berhasil diubah");
            clearField();
            getRowCount();
            tblSample.setModel(cn.getModelNewForm(mwhComboBox.getSelectedItem().toString()));
            btnUbahSample.setEnabled(false);
            btnTambahButton.setEnabled(true);
        }
    }//GEN-LAST:event_btnUbahSampleActionPerformed

    private void tblSampleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSampleMouseClicked
        if (evt.getClickCount() == 1) {
            JTable target = (JTable) evt.getSource();
            int row = target.getSelectedRow();
            setEnableUji(true);
            setEnableHeader(false);
            btnTambahButton.setEnabled(false);
            btnUbahSample.setEnabled(true);
            confirmCheckBox.setSelected(true);
            cbVisual.setSelectedItem(target.getModel().getValueAt(row, 1).toString());
            txtCavity.setText(target.getModel().getValueAt(row, 2).toString());
            txtBeratBtl.setText(target.getModel().getValueAt(row, 3).toString());
            txtTinggiBtl.setText(target.getModel().getValueAt(row, 4).toString());
            txtTorsiCap.setText(target.getModel().getValueAt(row, 5).toString());
            txtBeratCap.setText(target.getModel().getValueAt(row, 6).toString());
            txtTinggiCap.setText(target.getModel().getValueAt(row, 7).toString());
            txtDropTest.setText(target.getModel().getValueAt(row, 8).toString());
            txtUjiVolume.setText(target.getModel().getValueAt(row, 9).toString());
        }
    }//GEN-LAST:event_tblSampleMouseClicked

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        // TODO add your handling code here:
        setEnableHeader(true);
        confirmCheckBox.setSelected(false);
        setEnableUji(false);
        clearField();
    }//GEN-LAST:event_btnCetakActionPerformed

    private void mwhComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_mwhComboBoxPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        CtrNewForm cn = new CtrNewForm();
        String snomwh = mwhComboBox.getSelectedItem().toString();
        tblSample.setModel(cn.getModelNewForm(snomwh));
        getHeaderValue(snomwh);
        getRowCount();
        int iJumlahTest = Integer.valueOf(txtJumlahTest.getText());
        int iRowCount = tblSample.getRowCount();
        int iTotal = iJumlahTest - iRowCount;
        txtJumlahTest.setText("" + iTotal);
    }//GEN-LAST:event_mwhComboBoxPopupMenuWillBecomeInvisible

    private void txtCavityKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCavityKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_txtCavityKeyTyped

    private void getRowCount() {
        int count = tblSample.getRowCount();
        lblTotalRecord.setText(String.valueOf(count));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Sesion;
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahButton;
    private javax.swing.JButton btnUbahSample;
    private javax.swing.JComboBox<String> cbVisual;
    private javax.swing.JCheckBox confirmCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXDatePicker jxdTanggal;
    private javax.swing.JLabel lbTinggiBtl;
    private javax.swing.JLabel lblBeratBtl;
    private javax.swing.JLabel lblBeratCap;
    private javax.swing.JLabel lblCavity;
    private javax.swing.JLabel lblDropTest;
    private javax.swing.JLabel lblForm;
    private javax.swing.JLabel lblPemeriksa;
    private javax.swing.JLabel lblPenguji;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblTinggiCap;
    private javax.swing.JLabel lblTorsi;
    private javax.swing.JLabel lblTotalRecord;
    private javax.swing.JLabel lblUjiVolume;
    private javax.swing.JLabel lblVisual;
    private javax.swing.JComboBox mwhComboBox;
    private javax.swing.JTable tblSample;
    private javax.swing.JFormattedTextField txtBeratBtl;
    private javax.swing.JFormattedTextField txtBeratCap;
    private javax.swing.JFormattedTextField txtCavity;
    private javax.swing.JFormattedTextField txtDropTest;
    private javax.swing.JLabel txtJumlahTest;
    private javax.swing.JTextField txtPemeriksa;
    private javax.swing.JFormattedTextField txtTinggiBtl;
    private javax.swing.JFormattedTextField txtTinggiCap;
    private javax.swing.JFormattedTextField txtTorsiCap;
    private javax.swing.JFormattedTextField txtUjiVolume;
    // End of variables declaration//GEN-END:variables

    private void simpanKeSampleTable() {
        try {
            koneksi = MySQLConn.getKoneksi();
            state = koneksi.createStatement();
            String sql = "INSERT INTO sample\n"
                    + " SELECT * FROM tmpsample;";

            state.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(TRPengujian.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private void hapusTmpSampleTable() {
        try {
            koneksi = MySQLConn.getKoneksi();
            state = koneksi.createStatement();
            String sql = "DELETE FROM tmpsample;";

            state.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(TRPengujian.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
