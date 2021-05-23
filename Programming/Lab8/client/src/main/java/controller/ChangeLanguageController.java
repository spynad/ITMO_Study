package controller;

import client.Context;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import locale.ClientLocale;

import java.util.Locale;
import java.util.Set;

public class ChangeLanguageController {
    public ChoiceBox<String> languageChoiceBox;
    public Button cancelButton;
    public Button okButton;

    private Stage stage;
    private Context context;
    private Set<Locale> resourceBundles;
    public void initialize(Stage stage, Context context) {
        Platform.runLater(() -> {
            this.stage = stage;
            this.context = context;
            ObservableList<String> availableLanguages = FXCollections.observableArrayList();
            resourceBundles = ClientLocale.getResourceBundles();
            for (Locale rb : resourceBundles) {
                availableLanguages.add(rb.getLanguage());
            }
            languageChoiceBox.setItems(availableLanguages);
        });
    }

    public void ok(ActionEvent actionEvent) {
        for (Locale locale : resourceBundles) {
            if (locale.getLanguage().equals(languageChoiceBox.getValue())) {
                Locale.setDefault(locale);
                stage.close();
                return;
            }
        }
    }

    public void cancel(ActionEvent actionEvent) {
        stage.close();
    }
}
