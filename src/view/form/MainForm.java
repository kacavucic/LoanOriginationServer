package view.form;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MainForm extends javax.swing.JFrame {

    public MainForm() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        tblOnline = new JTable();
        lblServerStatus = new JLabel();
        btnStart = new JButton();
        btnStop = new JButton();
        lblStatus = new JLabel();
        lblNumberOnline = new JLabel();
        txtNumberOnline = new JTextField();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuConfiguration = new javax.swing.JMenu();
        jmiServer = new JMenuItem();
        jmiDatabase = new JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server program");

        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Online users"));

        tblOnline.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        jScrollPane.setViewportView(tblOnline);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblServerStatus.setText("Server status:");

        btnStart.setText("Start server");

        btnStop.setText("Stop server");

        lblStatus.setText("");

        lblNumberOnline.setText("Number of online users:");

        jMenuConfiguration.setText("Configuration");

        jmiServer.setText("Server settings");
        jMenuConfiguration.add(jmiServer);

        jmiDatabase.setText("Database settings");
        jMenuConfiguration.add(jmiDatabase);

        jMenuBar.add(jMenuConfiguration);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblServerStatus)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnStart)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnStop)
                                                .addGap(18, 18, Short.MAX_VALUE)
                                                .addComponent(lblNumberOnline)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtNumberOnline, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblNumberOnline)
                                                .addComponent(txtNumberOnline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(lblServerStatus)
                                                .addComponent(btnStart)
                                                .addComponent(btnStop)
                                                .addComponent(lblStatus)))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }


    // Variables declaration - do not modify
    private JButton btnStart;
    private JButton btnStop;
    private JLabel lblServerStatus;
    private JLabel lblNumberOnline;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JMenu jMenuConfiguration;
    private JMenuItem jmiDatabase;
    private JMenuItem jmiServer;
    private JLabel lblStatus;
    private JTable tblOnline;
    private JTextField txtNumberOnline;
    // End of variables declaration

    public JButton getBtnStart() {
        return btnStart;
    }

    public void setBtnStart(JButton btnStart) {
        this.btnStart = btnStart;
    }

    public JButton getBtnStop() {
        return btnStop;
    }

    public void setBtnStop(JButton btnStop) {
        this.btnStop = btnStop;
    }

    public JMenuItem getJmiDatabase() {
        return jmiDatabase;
    }

    public void setJmiDatabase(JMenuItem jmiDatabase) {
        this.jmiDatabase = jmiDatabase;
    }

    public JMenuItem getJmiServer() {
        return jmiServer;
    }

    public void setJmiServer(JMenuItem jmiServer) {
        this.jmiServer = jmiServer;
    }

    public JTable getTblOnline() {
        return tblOnline;
    }

    public void setTblOnline(JTable tblOnline) {
        this.tblOnline = tblOnline;
    }

    public JLabel getLblStatus() {
        return lblStatus;
    }

    public void setLblStatus(JLabel lblStatus) {
        this.lblStatus = lblStatus;
    }

    public JTextField getTxtNumberOnline() {
        return txtNumberOnline;
    }

    public void setTxtNumberOnline(JTextField txtNumberOnline) {
        this.txtNumberOnline = txtNumberOnline;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void btnStartActionListener(ActionListener actionListener) {
        btnStart.addActionListener(actionListener);
    }

    public void btnStopActionListener(ActionListener actionListener) {
        btnStop.addActionListener(actionListener);
    }

    public void jmiServerActionListener(ActionListener actionListener) {
        jmiServer.addActionListener(actionListener);
    }

    public void jmiDatabaseActionListener(ActionListener actionListener) {
        jmiDatabase.addActionListener(actionListener);
    }
}
