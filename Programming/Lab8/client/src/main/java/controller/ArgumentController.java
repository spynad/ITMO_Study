package controller;

import client.Context;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import locale.ClientLocale;

public class ArgumentController {

    @FXML private TextField argumentField;
    @FXML private Button cancelButton;
    @FXML private Button okButton;

    private Stage stage;
    private Context context;

    public void initialize(Stage stage, Context context) {
        Platform.runLater(() -> {
            this.stage = stage;
            this.context = context;
            localize();
        });
    }

    private void localize() {
        okButton.setText(ClientLocale.getString("UI_OK"));
        cancelButton.setText(ClientLocale.getString("UI_CANCEL"));
    }

    public void ok() {
        if (argumentField.getText().length() > 0) {
            stage.setUserData(argumentField.getText());
            stage.close();
        } else {
            context.getUserIO().printErrorMessage(ClientLocale.getString("UI_ERROR_INVALID_ARGUMENT"));
        }
    }

    public void cancel() {
        stage.setUserData(null);
        stage.close();
    }
}
