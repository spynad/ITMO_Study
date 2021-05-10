package controller;

import client.Client;
import client.Context;
import exception.RouteBuildException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import route.*;

import javax.validation.ValidatorFactory;

public class RouteController {
    public Label idLabel;
    public TextField cordYField;
    public TextField idField;
    public TextField fromNameField;
    public TextField nameField;
    public TextField distanceField;
    public TextField fromXField;
    public TextField toZField;
    public TextField fromYField;
    public TextField toYField;
    public TextField cordXField;
    public TextField toXField;
    public Label nameLabel;
    public Label distanceLabel;
    public Label fromXLabel;
    public Label fromYLabel;
    public Label fromNameLabel;
    public Label cordXLabel;
    public Label toXLabel;
    public Label cordYLabel;
    public Label toYLabel;
    public Label toZLabel;
    public CheckBox isNullCheckBox;
    public Button cancelButton;
    public Button okButton;

    private Client client;
    private Context context;
    private Stage stage;

    public void initialize(Client client, Context context, Stage stage, boolean disallowIdField) {
        Platform.runLater(() -> {
            this.client = client;
            this.context = context;
            this.stage = stage;
            idField.setDisable(disallowIdField);
        });
    }


    public void ok(ActionEvent actionEvent) {
        RouteBuilder routeBuilder = new RouteBuilder(context.getValidationFactory());
        Route route;
        try {
            routeBuilder = routeBuilder.setName(nameField.getText());
            routeBuilder = routeBuilder.setCoordinates(new Coordinates(Long.parseLong(cordXField.getText()),
                    Double.parseDouble(cordYField.getText())));
            if (!isNullCheckBox.isSelected()) {
                routeBuilder = routeBuilder.setFirstLocation(new FirstLocation(Integer.parseInt(fromXField.getText()),
                        Long.parseLong(fromYField.getText()), fromNameField.getText()));
            } else {
                routeBuilder = routeBuilder.setFirstLocation(null);
            }
            routeBuilder = routeBuilder.setSecondLocation(new SecondLocation(Integer.parseInt(toXField.getText()),
                    Long.parseLong(toYField.getText()), Double.parseDouble(toZField.getText())));
            routeBuilder = routeBuilder.setDistance(Double.parseDouble(distanceField.getText()));
            routeBuilder = routeBuilder.setUsername(context.getAuthModule().getUser().getUsername());
            if (idField.isDisabled()) {
                route = routeBuilder.buildWithoutId();
            } else {
                routeBuilder = routeBuilder.setId(Integer.parseInt(idField.getText()));
                route = routeBuilder.buildWithId();
            }
            stage.setUserData(route);
            stage.close();
        } catch (RouteBuildException | NumberFormatException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }

    }

    public void cancel(ActionEvent actionEvent) {
        stage.setUserData(null);
        stage.close();
    }

    public void setFromNull(ActionEvent actionEvent) {
        if (isNullCheckBox.isSelected()) {
            fromXField.setText(null);
            fromYField.setText(null);
            fromNameField.setText(null);
            fromXField.setDisable(true);
            fromYField.setDisable(true);
            fromNameField.setDisable(true);
        } else {
            fromXField.setDisable(false);
            fromYField.setDisable(false);
            fromNameField.setDisable(false);
        }
    }
}
