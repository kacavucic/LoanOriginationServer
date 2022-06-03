package view.controller;

import constant.Constant;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.PropertiesLoader;
import view.coordinator.MainCoordinator;
import view.form.DatabaseSettingsForm;

public class DatabaseSettingsController {

    DatabaseSettingsForm databaseSettingsForm;

    public DatabaseSettingsController(DatabaseSettingsForm databaseSettingsForm) {
        this.databaseSettingsForm = databaseSettingsForm;
        addActionListeners();
    }

    public void openForm() {
        prepareView();
        databaseSettingsForm.setLocationRelativeTo(MainCoordinator.getInstance().getFrmMainController().getFrmMain());
        databaseSettingsForm.getTxtURL().setEnabled(false);
        databaseSettingsForm.setVisible(true);
    }

    private void addActionListeners() {
        databaseSettingsForm.btnSaveActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newDatabaseName = databaseSettingsForm.getTxtDatabaseName().getText().trim();
                String newUsername = databaseSettingsForm.getTxtUsername().getText().trim();
                String newPassword = String.valueOf(databaseSettingsForm.getTxtPassword().getPassword());
                String newPort = databaseSettingsForm.getTxtPortNumber().getText().trim();
                String newURL = "jdbc\\:mysql\\://localhost\\:" + newPort + "/" + newDatabaseName;

                PropertiesLoader.getInstance().setProperty(Constant.DATABASE, newDatabaseName);
                PropertiesLoader.getInstance().setProperty(Constant.USERNAME, newUsername);
                PropertiesLoader.getInstance().setProperty(Constant.PASSWORD, newPassword);
                PropertiesLoader.getInstance().setProperty(Constant.DB_PORT, newPort);
                PropertiesLoader.getInstance().setProperty(Constant.URL, newURL);

                try {
                    PropertiesLoader.getInstance().saveDBProperties();
                } catch (IOException ex) {
                    Logger.getLogger(DatabaseSettingsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                databaseSettingsForm.dispose();

            }
        });
    }

    private void prepareView() {
        String databaseName = PropertiesLoader.getInstance().getProperty(Constant.DATABASE);
        String username = PropertiesLoader.getInstance().getProperty(Constant.USERNAME);
        String password = PropertiesLoader.getInstance().getProperty(Constant.PASSWORD);
        String port = PropertiesLoader.getInstance().getProperty(Constant.DB_PORT);
        String url = PropertiesLoader.getInstance().getProperty(Constant.URL);

        databaseSettingsForm.getTxtDatabaseName().setText(databaseName);
        databaseSettingsForm.getTxtUsername().setText(username);
        databaseSettingsForm.getTxtPassword().setText(password);
        databaseSettingsForm.getTxtPortNumber().setText(port);
        databaseSettingsForm.getTxtURL().setText(url);

    }

}
