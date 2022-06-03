
package view.controller;

import controller.Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import session.Session;
import view.coordinator.MainCoordinator;
import view.form.MainForm;
import view.form.component.table.UserTableModel;

public class MainController {

    private final MainForm mainForm;

    public MainController(MainForm frmServer) {
        this.mainForm = frmServer;
        mainForm.getLblStatus().setForeground(Color.red);
        mainForm.getLblStatus().setText("INACTIVE");
        mainForm.getTxtNumberOnline().setText("0");
        mainForm.getTxtNumberOnline().setEnabled(false);
        mainForm.getBtnStop().setEnabled(false);
        fillTblUsers();
        addActionListeners();
    }

    public void openForm() {
        mainForm.setLocationRelativeTo(null);
        mainForm.setVisible(true);
        // mainForm.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void addActionListeners() {
        mainForm.btnStartActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Controller.getInstance().startServer();
                    mainForm.getBtnStart().setEnabled(false);
                    mainForm.getBtnStop().setEnabled(true);
                    mainForm.getLblStatus().setText("ACTIVE");
                    mainForm.getLblStatus().setForeground(Color.green.darker());
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        mainForm.btnStopActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Controller.getInstance().stopServer();
                    refreshTbl();
                    mainForm.getBtnStart().setEnabled(true);
                    mainForm.getBtnStop().setEnabled(false);
                    mainForm.getLblStatus().setText("INACTIVE");
                    mainForm.getLblStatus().setForeground(Color.red);
                } catch (IOException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        mainForm.jmiDatabaseActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCoordinator.getInstance().openDatabaseSettingsForm();
            }
        });

        mainForm.jmiServerActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainCoordinator.getInstance().openServerSettingsForm();
            }
        });
    }

    private void fillTblUsers() {
        UserTableModel model = new UserTableModel(Session.getInstance().getAllUsers());
        mainForm.getTblOnline().setModel(model);
    }

    public void refreshTbl() {
        UserTableModel model = (UserTableModel) mainForm.getTblOnline().getModel();
        model.setUsers(Session.getInstance().getAllUsers());
        mainForm.getTxtNumberOnline().setText(String.valueOf(Session.getInstance().getNumberOfOnlineUsers()));
    }

    public MainForm getFrmMain() {
        return mainForm;
    }

}
