package controller;

import client.Context;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import locale.ClientLocale;
import route.Route;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class FilterController {

    public ChoiceBox<String> columnChoiceBox;
    public Button cancelButton;
    public Button okButton;
    public TextField filterField;

    private final ObservableList<String> routeFields = FXCollections.observableArrayList("id", "name", "coordinates_x",
            "coordinates_y", "creationdate", "location1_x", "location1_y", "location1_name", "location2_x",
            "location2_y", "location2_z", "username", "distance");
    private ObservableList<Route> routeTable;
    private Stage stage;
    private Context context;


    public void initialize(Stage stage, Context context) {
        Platform.runLater(() -> {
            this.stage = stage;
            this.context = context;
            columnChoiceBox.setItems(routeFields);
            routeTable = (ObservableList<Route>) stage.getUserData();
            localize();
        });

    }

    private void localize() {
        okButton.setText(ClientLocale.getString("UI_OK"));
        cancelButton.setText(ClientLocale.getString("UI_CANCEL"));
    }

    public void cancel(ActionEvent actionEvent) {
        stage.setUserData(null);
        stage.close();
    }

    public void ok(ActionEvent actionEvent) {
        try {
            switch (columnChoiceBox.getValue()) {
                case "name":
                    stage.setUserData(routeTable.stream()
                            .filter(x -> x.getName().equals(filterField.getText()))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                    break;
                case "id":
                    stage.setUserData(routeTable.stream()
                            .filter(x -> x.getId().equals(Integer.parseInt(filterField.getText())))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                    break;
                case "coordinates_x":
                    stage.setUserData(routeTable.stream()
                            .filter(x -> x.getCoordinates().getX() == Long.parseLong(filterField.getText()))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                    break;
                case "coordinates_y":
                    stage.setUserData(routeTable.stream()
                            .filter(x -> x.getCoordinates().getY() == Double.parseDouble(filterField.getText()))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                    break;
                case "creationdate":
                    stage.setUserData(routeTable.stream()
                            .filter(x -> x.getCreationDate().equals(LocalDate.parse(filterField.getText())))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                    break;
                case "location1_x":
                    stage.setUserData(routeTable.stream()
                            .filter(x -> x.getFrom().getX().equals(Integer.parseInt(filterField.getText())))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                    break;
                case "location1_y":
                    stage.setUserData(routeTable.stream()
                            .filter(x -> x.getFrom().getY() == Long.parseLong(filterField.getText()))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                    break;
                case "location1_name":
                    stage.setUserData(routeTable.stream()
                            .filter(x -> x.getFrom().getName().equals(filterField.getText()))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                    break;
                case "location2_x":
                    stage.setUserData(routeTable.stream()
                            .filter(x -> x.getTo().getX().equals(Integer.parseInt(filterField.getText())))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                    break;
                case "location2_y":
                    stage.setUserData(routeTable.stream()
                            .filter(x -> x.getTo().getY().equals(Long.parseLong(filterField.getText())))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                    break;
                case "location2_z":
                    stage.setUserData(routeTable.stream()
                            .filter(x -> x.getTo().getZ().equals(Double.parseDouble(filterField.getText())))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                    break;
                case "username":
                    stage.setUserData(routeTable.stream()
                            .filter(x -> x.getUsername().equals(filterField.getText()))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                    break;
                case "distance":
                    stage.setUserData(routeTable.stream()
                            .filter(x -> x.getDistance() == (Double.parseDouble(filterField.getText())))
                            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                    break;
                default:
                    stage.setUserData(null);
            }
        } catch (NumberFormatException e) {
            context.getUserIO().printLine(e.getMessage());
        }
        stage.close();
    }
}
