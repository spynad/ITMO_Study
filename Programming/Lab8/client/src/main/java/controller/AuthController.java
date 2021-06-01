package controller;

import client.Client;
import client.Context;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import locale.ClientLocale;

import java.io.IOException;
import java.util.Locale;
import java.util.Set;

public class AuthController {
    @FXML
    private ChoiceBox<String> languageBox;
    @FXML
    private TextField usernameField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;

    private Client client;
    private Context context;
    private Stage currentStage;
    private Set<Locale> resourceBundles;

    public void initialize() {
        ObservableList<String> availableLanguages = FXCollections.observableArrayList();
        resourceBundles = ClientLocale.getResourceBundles();
        for (Locale rb : resourceBundles) {
            availableLanguages.add(rb.getLanguage());
        }
        languageBox.setItems(availableLanguages);
    }

    public void login() {
        try {
            if (context.getAuthModule().authorize(usernameField.getText(), passwordField.getText())) {
                for (Locale locale : resourceBundles) {
                    if (locale.getLanguage().equals(languageBox.getValue())) {
                        Locale.setDefault(locale);
                        break;
                    }
                }
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
                stage.setMaxHeight(800);
                stage.setMaxWidth(1500);
                stage.show();
            }
        } catch (IOException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
    }

    public void register() {
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
            localize();
        });
    }

    public void localize() {
        usernameLabel.setText(ClientLocale.getString("UI_AUTH_USERNAME"));
        passwordLabel.setText(ClientLocale.getString("UI_AUTH_PASSWORD"));
        loginButton.setText(ClientLocale.getString("UI_AUTH_AUTHORIZE"));
        registerButton.setText(ClientLocale.getString("UI_AUTH_REGISTER"));
    }
}
