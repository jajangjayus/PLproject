/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.record.PageBreakRecord;

/**
 *
 * @author agung
 */
public class FormUtama extends javax.swing.JFrame {

    MDMWH mwh = new MDMWH();
    MDPemasok pemasok = new MDPemasok();
    TRPengujian newForm = new TRPengujian();
    MDUser listUser = new MDUser();
    RPLaporan cetakHasilTest = new RPLaporan();

    private String user = "";
    private String level = "";
    private boolean logIn = false;

    /**
     * Creates new form MainMenu
     */
    public FormUtama() {
        initComponents();
        //another confirm before exit
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                beforeExit();
            }
        });

        //show
        setVisible(true);
        //set maximize window
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        pb.setStringPainted(true);
        //set title

        //Hide menu
        MenuMasterData.setVisible(false);
        MenuTransaksi.setVisible(false);
        MenuReport.setVisible(false);
        menuWindow.setVisible(false);
        setupMenu.setVisible(false);

        //FormLogin login = new FormLogin();
        String title = "Pertamina Lubricants";
        //String status = "Logged In as " + login.getUser().getNamalengkap() + " (" + login.getUser().getLevel() + ")";
        setTitle(title);

        //set to center screen
        setLocationRelativeTo(null);

        //set loading/progress
        pb.setValue(10);
        pb.setValue(20);
        pb.setValue(30);
        pb.setValue(40);
        pb.setValue(50);
        pb.setValue(60);
        pb.setValue(70);
        pb.setValue(80);
        pb.setValue(90);
        pb.setValue(100);
        pb.setValue(0);
        pb.setVisible(false);
    }

    private String Login() {
        FormLogin lgn = new FormLogin();
        lgn.setLocationRelativeTo(this);
        lgn.setVisible(true);
        this.logIn = lgn.login;
        this.user = lgn.users;
        this.level = lgn.level;
        setLogin(logIn);
        return user;
    }

    private void setLogin(boolean Login) {
        if (Login) {

            switch (level) {
                case "Developer":
                    MenuMasterData.setVisible(true);
                    MenuTransaksi.setVisible(true);
                    MenuReport.setVisible(true);
                    menuWindow.setVisible(true);
                    setupMenu.setVisible(true);
                    groupAccessMenuItem.setVisible(true);
                    groupAccessSetupMenuItem.setVisible(true);
                    LoginMenuItem.setEnabled(false);
                    LogoutMenuItem.setEnabled(true);
                    miClear.setEnabled(true);
                    AboutMenuItem.setEnabled(true);
                    ExitMenuItem.setEnabled(true);
                    MenuMasterData.setEnabled(true);
                    MenuTransaksi.setEnabled(true);
                    MenuReport.setEnabled(true);
                    ReportUserMenuItem.setEnabled(true);
                    ReportPemasokMenuItem.setEnabled(true);
                    ReportMwhMenuItem.setEnabled(true);
                    ReportTestingMenuItem.setEnabled(true);
                    break;
                case "Administrator":
                    MenuMasterData.setVisible(true);
                    MenuTransaksi.setVisible(true);
                    MenuReport.setVisible(true);
                    menuWindow.setVisible(true);
                    setupMenu.setVisible(true);
                    groupAccessMenuItem.setVisible(true);
                    groupAccessSetupMenuItem.setVisible(true);
                    LoginMenuItem.setEnabled(false);
                    LogoutMenuItem.setEnabled(true);
                    miClear.setEnabled(true);
                    AboutMenuItem.setEnabled(true);
                    ExitMenuItem.setEnabled(true);
                    MenuMasterData.setEnabled(true);
                    MenuTransaksi.setEnabled(true);
                    MenuReport.setEnabled(true);
                    ReportUserMenuItem.setEnabled(true);
                    ReportPemasokMenuItem.setEnabled(true);
                    ReportMwhMenuItem.setEnabled(true);
                    ReportTestingMenuItem.setEnabled(true);
                    break;
                default:
                    MenuMasterData.setVisible(false);
                    MenuTransaksi.setVisible(true);
                    MenuReport.setVisible(true);
                    menuWindow.setVisible(true);
                    setupMenu.setVisible(true);
                    groupAccessMenuItem.setVisible(false);
                    groupAccessSetupMenuItem.setVisible(false);
                    LoginMenuItem.setEnabled(false);
                    LogoutMenuItem.setEnabled(true);
                    miClear.setEnabled(true);
                    AboutMenuItem.setEnabled(true);
                    ExitMenuItem.setEnabled(true);
                    MenuMasterData.setEnabled(false);
                    MenuTransaksi.setEnabled(true);
                    MenuReport.setEnabled(true);
                    ReportUserMenuItem.setEnabled(false);
                    ReportPemasokMenuItem.setEnabled(false);
                    ReportMwhMenuItem.setEnabled(false);
                    ReportTestingMenuItem.setEnabled(true);
                    break;
            }

        } else {
            MenuMasterData.setVisible(false);
            MenuTransaksi.setVisible(false);
            MenuReport.setVisible(false);
            menuWindow.setVisible(false);
            setupMenu.setVisible(false);
            LoginMenuItem.setEnabled(true);
            LogoutMenuItem.setEnabled(false);
            miClear.setEnabled(false);
            AboutMenuItem.setEnabled(true);
            ExitMenuItem.setEnabled(true);
            MenuMasterData.setEnabled(false);
            MenuTransaksi.setEnabled(false);
            MenuReport.setEnabled(false);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        desktopUtama = new javax.swing.JDesktopPane();
        pb = new javax.swing.JProgressBar();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        AboutMenuItem = new javax.swing.JMenuItem();
        setupMenu = new javax.swing.JMenu();
        changePasswordMenuItem = new javax.swing.JMenuItem();
        groupAccessMenuItem = new javax.swing.JMenuItem();
        groupAccessSetupMenuItem = new javax.swing.JMenuItem();
        LoginMenuItem = new javax.swing.JMenuItem();
        LogoutMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        ExitMenuItem = new javax.swing.JMenuItem();
        MenuMasterData = new javax.swing.JMenu();
        miInputMWH = new javax.swing.JMenuItem();
        miInputPemasok = new javax.swing.JMenuItem();
        miListUser = new javax.swing.JMenuItem();
        MenuTransaksi = new javax.swing.JMenu();
        miNewForm = new javax.swing.JMenuItem();
        MenuReport = new javax.swing.JMenu();
        ReportUserMenuItem = new javax.swing.JMenuItem();
        ReportPemasokMenuItem = new javax.swing.JMenuItem();
        ReportMwhMenuItem = new javax.swing.JMenuItem();
        ReportTestingMenuItem = new javax.swing.JMenuItem();
        menuWindow = new javax.swing.JMenu();
        miClear = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        desktopUtama.setBackground(new java.awt.Color(240, 240, 240));

        javax.swing.GroupLayout desktopUtamaLayout = new javax.swing.GroupLayout(desktopUtama);
        desktopUtama.setLayout(desktopUtamaLayout);
        desktopUtamaLayout.setHorizontalGroup(
            desktopUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 792, Short.MAX_VALUE)
        );
        desktopUtamaLayout.setVerticalGroup(
            desktopUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(desktopUtama);

        jMenu1.setText("File");

        AboutMenuItem.setMnemonic('A');
        AboutMenuItem.setText("About");
        AboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(AboutMenuItem);

        setupMenu.setText("Setup");

        changePasswordMenuItem.setText("Change Password");
        changePasswordMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordMenuItemActionPerformed(evt);
            }
        });
        setupMenu.add(changePasswordMenuItem);

        groupAccessMenuItem.setText("Group Access");
        setupMenu.add(groupAccessMenuItem);

        groupAccessSetupMenuItem.setText("Group Access Setup");
        setupMenu.add(groupAccessSetupMenuItem);

        jMenu1.add(setupMenu);

        LoginMenuItem.setText("Login");
        LoginMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(LoginMenuItem);

        LogoutMenuItem.setText("Logout");
        LogoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(LogoutMenuItem);
        jMenu1.add(jSeparator1);

        ExitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        ExitMenuItem.setMnemonic('E');
        ExitMenuItem.setText("Exit");
        ExitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(ExitMenuItem);

        jMenuBar1.add(jMenu1);

        MenuMasterData.setMnemonic('F');
        MenuMasterData.setText("Master Data");

        miInputMWH.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        miInputMWH.setText("Entry Data MWH");
        miInputMWH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miInputMWHActionPerformed(evt);
            }
        });
        MenuMasterData.add(miInputMWH);

        miInputPemasok.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        miInputPemasok.setText("Entry Data Pemasok");
        miInputPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miInputPemasokActionPerformed(evt);
            }
        });
        MenuMasterData.add(miInputPemasok);

        miListUser.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        miListUser.setMnemonic('J');
        miListUser.setText("Entry Data User");
        miListUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miListUserActionPerformed(evt);
            }
        });
        MenuMasterData.add(miListUser);

        jMenuBar1.add(MenuMasterData);

        MenuTransaksi.setMnemonic('T');
        MenuTransaksi.setText("Transaksi");

        miNewForm.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        miNewForm.setMnemonic('J');
        miNewForm.setText("Testing Units");
        miNewForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNewFormActionPerformed(evt);
            }
        });
        MenuTransaksi.add(miNewForm);

        jMenuBar1.add(MenuTransaksi);

        MenuReport.setText("Report");

        ReportUserMenuItem.setText("Report Data User ");
        MenuReport.add(ReportUserMenuItem);

        ReportPemasokMenuItem.setText("Report Data Pemasok");
        MenuReport.add(ReportPemasokMenuItem);

        ReportMwhMenuItem.setText("Report Data MWH");
        MenuReport.add(ReportMwhMenuItem);

        ReportTestingMenuItem.setText("Report Testing Unit");
        ReportTestingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportTestingMenuItemActionPerformed(evt);
            }
        });
        MenuReport.add(ReportTestingMenuItem);

        jMenuBar1.add(MenuReport);

        menuWindow.setMnemonic('W');
        menuWindow.setText("Window");

        miClear.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        miClear.setMnemonic('C');
        miClear.setText("Clear All Windows");
        miClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miClearActionPerformed(evt);
            }
        });
        menuWindow.add(miClear);

        jMenuBar1.add(menuWindow);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(pb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pb, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitMenuItemActionPerformed
        beforeExit();
    }//GEN-LAST:event_ExitMenuItemActionPerformed

    private void beforeExit() {
        if (JOptionPane.showConfirmDialog(this,
                "Apakah anda yakin akan keluar?", "Keluar Aplikasi",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void AboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutMenuItemActionPerformed
        About about = new About(this);
    }//GEN-LAST:event_AboutMenuItemActionPerformed

    private void miClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miClearActionPerformed
        setTampilanAktif("");
    }//GEN-LAST:event_miClearActionPerformed

    private void miNewFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miNewFormActionPerformed
        setTampilanAktif("NewForm");
    }//GEN-LAST:event_miNewFormActionPerformed

    private void miListUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miListUserActionPerformed
        setTampilanAktif("ListUser");
    }//GEN-LAST:event_miListUserActionPerformed

    private void miInputMWHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miInputMWHActionPerformed
        setTampilanAktif("MWH");
    }//GEN-LAST:event_miInputMWHActionPerformed

    private void miInputPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miInputPemasokActionPerformed
        setTampilanAktif("Pemasok");
    }//GEN-LAST:event_miInputPemasokActionPerformed

    private void ReportTestingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportTestingMenuItemActionPerformed
        // TODO add your handling code here:
        setTampilanAktif("ReportTesting");
    }//GEN-LAST:event_ReportTestingMenuItemActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        Login();
    }//GEN-LAST:event_formWindowOpened

    private void LoginMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginMenuItemActionPerformed
        // TODO add your handling code here:
        Login();
    }//GEN-LAST:event_LoginMenuItemActionPerformed

    private void LogoutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutMenuItemActionPerformed
        // TODO add your handling code here:
        int hasil = JOptionPane.showConfirmDialog(this, "Anda Yakin Akan Keluar", "Konfirmasi keluar Pengguna", JOptionPane.OK_CANCEL_OPTION);
        if (hasil == JOptionPane.OK_OPTION) {
            setLogin(false);
            setTampilanAktif("");
        }
    }//GEN-LAST:event_LogoutMenuItemActionPerformed

    private void changePasswordMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordMenuItemActionPerformed
        // TODO add your handling code here:
        ChangePassword changePassword = new ChangePassword(this);
    }//GEN-LAST:event_changePasswordMenuItemActionPerformed

    private void setTampilanAktif(String menu) {
        mwh.setVisible(false);
        pemasok.setVisible(false);
        newForm.setVisible(false);
        listUser.setVisible(false);
        cetakHasilTest.setVisible(false);
        switch (menu) {
            case "MWH":
                mwh = new MDMWH();
                mwh.setVisible(true);
                desktopUtama.add(mwh);
                pb.setValue(10);
                pb.setValue(25);
                pb.setValue(50);
                pb.setValue(75);
                pb.setValue(100);
                pb.setValue(0);
                pb.setVisible(false);
                break;
            case "Pemasok":
                pemasok = new MDPemasok();
                pemasok.setVisible(true);
                desktopUtama.add(pemasok);
                pb.setValue(10);
                pb.setValue(25);
                pb.setValue(50);
                pb.setValue(75);
                pb.setValue(100);
                pb.setValue(0);
                pb.setVisible(false);
                break;
            case "NewForm":
                newForm = new TRPengujian();
                newForm.setVisible(true);
                desktopUtama.add(newForm);
                pb.setValue(10);
                pb.setValue(25);
                pb.setValue(50);
                pb.setValue(75);
                pb.setValue(100);
                pb.setValue(0);
                pb.setVisible(false);
                break;
            case "ListUser":
                listUser = new MDUser();
                listUser.setVisible(true);
                desktopUtama.add(listUser);
                pb.setValue(10);
                pb.setValue(25);
                pb.setValue(50);
                pb.setValue(75);
                pb.setValue(100);
                pb.setValue(0);
                pb.setVisible(false);
                break;
            case "ReportTesting":
                cetakHasilTest = new RPLaporan();
                cetakHasilTest.setVisible(true);
                desktopUtama.add(cetakHasilTest);
                pb.setValue(10);
                pb.setValue(25);
                pb.setValue(50);
                pb.setValue(75);
                pb.setValue(100);
                pb.setValue(0);
                pb.setVisible(false);
                break;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AboutMenuItem;
    private javax.swing.JMenuItem ExitMenuItem;
    private javax.swing.JMenuItem LoginMenuItem;
    private javax.swing.JMenuItem LogoutMenuItem;
    private javax.swing.JMenu MenuMasterData;
    private javax.swing.JMenu MenuReport;
    private javax.swing.JMenu MenuTransaksi;
    private javax.swing.JMenuItem ReportMwhMenuItem;
    private javax.swing.JMenuItem ReportPemasokMenuItem;
    private javax.swing.JMenuItem ReportTestingMenuItem;
    private javax.swing.JMenuItem ReportUserMenuItem;
    private javax.swing.JMenuItem changePasswordMenuItem;
    private javax.swing.JDesktopPane desktopUtama;
    private javax.swing.JMenuItem groupAccessMenuItem;
    private javax.swing.JMenuItem groupAccessSetupMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu menuWindow;
    private javax.swing.JMenuItem miClear;
    private javax.swing.JMenuItem miInputMWH;
    private javax.swing.JMenuItem miInputPemasok;
    private javax.swing.JMenuItem miListUser;
    private javax.swing.JMenuItem miNewForm;
    private javax.swing.JProgressBar pb;
    private javax.swing.JMenu setupMenu;
    // End of variables declaration//GEN-END:variables
}
