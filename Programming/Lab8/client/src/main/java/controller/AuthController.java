package controller;

import client.Client;
import client.Context;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AuthController {
    public TextField usernameField;
    public Button loginButton;
    public Button registerButton;
    public PasswordField passwordField;

    private Client client;
    private Context context;
    private Stage currentStage;

    public void login(ActionEvent actionEvent) {
        try {
            if (context.getAuthModule().authorize(usernameField.getText(), passwordField.getText())) {
                currentStage.hide();
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainScene.fxml"));
                Parent root = loader.load();
                MainController controller = loader.getController();
                controller.initialize(client, context, stage);
                Scene scene = new Scene(root);
                stage.initStyle(StageStyle.DECORATED);
                stage.setScene(scene);
                stage.setTitle("Lab8 Application");
                stage.setResizable(true);
                stage.show();
            }
        } catch (IOException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
    }

    public void register(ActionEvent actionEvent) {
        try {
            context.getAuthModule().register(usernameField.getText(), passwordField.getText());
        } catch (IOException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public void initialize(Client client, Context context, Stage stage) {
        Platform.runLater(() -> {
            setContext(context);
            setClient(client);
            setCurrentStage(stage);
        });
    }
}
