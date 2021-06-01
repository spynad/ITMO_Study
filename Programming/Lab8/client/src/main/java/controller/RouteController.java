package controller;

import client.Client;
import client.Context;
import exception.RouteBuildException;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import locale.ClientLocale;
import route.*;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

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
    public Label routeLabel;
    public Label fromLabel;
    public Label coordinatesLabel;
    public Label secondLocationLabel;

    private Client client;
    private Context context;
    private Stage stage;

    public void initialize(Client client, Context context, Stage stage, boolean disallowIdField, Route route) {
        Platform.runLater(() -> {
            this.client = client;
            this.context = context;
            this.stage = stage;
            idField.setDisable(disallowIdField);
            if (route != null) {
                idField.setText(String.valueOf(route.getId()));
                idField.setDisable(true);
                nameField.setText(route.getName());
                distanceField.setText(String.valueOf(route.getDistance()));
                cordXField.setText(String.valueOf(route.getCoordinatesX()));
                cordYField.setText(String.valueOf(route.getCoordinatesY()));
                if (route.getFrom() != null) {
                    fromXField.setText(String.valueOf(route.getFromX()));
                    fromYField.setText(String.valueOf(route.getFromY()));
                    fromNameField.setText(route.getFromName());
                } else {
                    fromXField.setText(null);
                    fromYField.setText(null);
                    fromNameField.setText(null);
                    fromXField.setDisable(true);
                    fromYField.setDisable(true);
                    fromNameField.setDisable(true);
                    isNullCheckBox.setSelected(true);
                }
                toXField.setText(String.valueOf(route.getToX()));
                toYField.setText(String.valueOf(route.getToY()));
                toZField.setText(String.valueOf(route.getToZ()));
            }
            localize();
        });
    }

    private void localize() {
        nameLabel.setText(ClientLocale.getString("UI_ROUTE_NAME_LABEL"));
        nameField.setPromptText(ClientLocale.getString("UI_ROUTE_NAME_FIELD"));
        distanceLabel.setText(ClientLocale.getString("UI_ROUTE_DISTANCE_LABEL"));
        distanceField.setPromptText(ClientLocale.getString("UI_ROUTE_DISTANCE_FIELD"));
        coordinatesLabel.setText(ClientLocale.getString("UI_ROUTE_COORDINATES_LABEL"));
        fromLabel.setText(ClientLocale.getString("UI_ROUTE_FROM_LABEL"));
        secondLocationLabel.setText(ClientLocale.getString("UI_ROUTE_TO_LABEL"));
        isNullCheckBox.setText(ClientLocale.getString("UI_ROUTE_IS_NULL_CHECK_BOX"));

        toXField.setPromptText(ClientLocale.getString("UI_ROUTE_X_COORDINATE_FIELD"));
        toYField.setPromptText(ClientLocale.getString("UI_ROUTE_Y_COORDINATE_FIELD"));
        toZField.setPromptText(ClientLocale.getString("UI_ROUTE_Z_COORDINATE_FIELD"));

        fromXField.setPromptText(ClientLocale.getString("UI_ROUTE_X_COORDINATE_FIELD"));
        fromYField.setPromptText(ClientLocale.getString("UI_ROUTE_Y_COORDINATE_FIELD"));
        fromNameLabel.setText(ClientLocale.getString("UI_ROUTE_NAME_LABEL"));
        fromNameField.setPromptText(ClientLocale.getString("UI_ROUTE_NAME_FIELD"));

        cordXField.setPromptText(ClientLocale.getString("UI_ROUTE_X_COORDINATE_FIELD"));
        cordYField.setPromptText(ClientLocale.getString("UI_ROUTE_Y_COORDINATE_FIELD"));
    }


    public void ok() {
        RouteBuilder routeBuilder = new RouteBuilder(context.getValidationFactory());
        Route route;
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        try {
            routeBuilder = routeBuilder.setName(nameField.getText());
            routeBuilder = routeBuilder.setCoordinates(new Coordinates(Long.parseLong(cordXField.getText()),
                    format.parse(cordYField.getText()).doubleValue()));
            if (!isNullCheckBox.isSelected()) {
                routeBuilder = routeBuilder.setFirstLocation(new FirstLocation(Integer.parseInt(fromXField.getText()),
                        Long.parseLong(fromYField.getText()), fromNameField.getText()));
            } else {
                routeBuilder = routeBuilder.setFirstLocation(null);
            }
            routeBuilder = routeBuilder.setSecondLocation(new SecondLocation(Integer.parseInt(toXField.getText()),
                    Long.parseLong(toYField.getText()), format.parse(toZField.getText()).doubleValue()));
            routeBuilder = routeBuilder.setDistance(format.parse(distanceField.getText()).doubleValue());
            routeBuilder = routeBuilder.setUsername(context.getAuthModule().getUser().getUsername());
            if (idField.isDisabled() && idField.getText().equals("")) {
                route = routeBuilder.buildWithoutId();
            } else {
                routeBuilder = routeBuilder.setId(Integer.parseInt(idField.getText()));
                route = routeBuilder.buildWithId();
            }
            stage.setUserData(route);
            stage.close();
        } catch (RouteBuildException | NumberFormatException | ParseException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }

    }

    public void cancel() {
        stage.setUserData(null);
        stage.close();
    }

    public void setFromNull() {
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
