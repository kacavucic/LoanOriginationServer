package view.controller;

import constant.Constant;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.PropertiesLoader;
import view.coordinator.MainCoordinator;
import view.form.ServerSettingsForm;

public class ServerSettingsController {

    ServerSettingsForm serverSettingsForm;

    public ServerSettingsController(ServerSettingsForm serverSettingsForm) {
        this.serverSettingsForm = serverSettingsForm;
        addActionListeners();
    }

    public void openForm() {
        prepareView();
        serverSettingsForm.setLocationRelativeTo(MainCoordinator.getInstance().getFrmMainController().getFrmMain());
        serverSettingsForm.setVisible(true);
    }

    private void addActionListeners() {
        serverSettingsForm.btnSaveActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPort = serverSettingsForm.getTxtPortNumber().getText().trim();
                PropertiesLoader.getInstance().setProperty(Constant.SERVER_PORT, newPort);
                try {
                    PropertiesLoader.getInstance().saveServerProperties();
                } catch (IOException ex) {
                    Logger.getLogger(ServerSettingsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                serverSettingsForm.dispose();
            }
        });
    }

    private void prepareView() {
        String port = PropertiesLoader.getInstance().getProperty(Constant.SERVER_PORT);
        serverSettingsForm.getTxtPortNumber().setText(port);
    }

}
