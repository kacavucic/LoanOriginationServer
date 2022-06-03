package view.coordinator;

import view.controller.MainController;
import view.controller.DatabaseSettingsController;
import view.controller.ServerSettingsController;
import view.form.DatabaseSettingsForm;
import view.form.MainForm;
import view.form.ServerSettingsForm;

public class MainCoordinator {

    private static MainCoordinator instance;
    private final MainController mainController;

    private MainCoordinator() {
        mainController = new MainController(new MainForm());
    }

    public static MainCoordinator getInstance() {
        if (instance == null) {
            instance = new MainCoordinator();
        }
        return instance;
    }

    public void openFrmMain() {
        mainController.openForm();
    }

    public MainController getFrmMainController() {
        return mainController;
    }

    public void openDatabaseSettingsForm() {
        DatabaseSettingsController databaseSettingsController
                = new DatabaseSettingsController(new DatabaseSettingsForm(mainController.getFrmMain(), true));
        databaseSettingsController.openForm();
    }

    public void openServerSettingsForm() {
        ServerSettingsController serverSettingsController
                = new ServerSettingsController(new ServerSettingsForm(mainController.getFrmMain(), true));
        serverSettingsController.openForm();
    }
}
