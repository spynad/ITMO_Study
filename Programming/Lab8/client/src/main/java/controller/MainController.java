package controller;

import client.Client;
import client.Context;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import route.Route;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class MainController {
    public MenuItem quitItem;
    public MenuItem addItem;
    public MenuItem updateItem;
    public MenuItem deleteByIdItem;
    public MenuItem deleteByIndexItem;
    public MenuItem deleteFirstItem;
    public MenuItem deleteAllByDistanceItem;
    public MenuItem deleteAllItem;
    public Label usernameLabel;
    public Label countLabel;
    public MenuItem sumOfDistanceItem;
    public MenuItem filterByNameItem;
    public MenuItem infoItem;
    public MenuItem refreshItem;
    public MenuItem executeScriptItem;
    public MenuItem historyItem;
    public MenuItem commandsHelpItem;
    public TableView<Route> collectionTable;
    public TableColumn<Route, Integer> idColumn;
    public TableColumn<Route, String> nameColumn;
    public TableColumn<Route, Long> coordinatesXColumn;
    public TableColumn<Route, Double> coordinatesYColumn;
    public TableColumn<Route, LocalDate> creationdateColumn;
    public TableColumn<Route, String> location1XColumn;
    public TableColumn<Route, String> location1YColumn;
    public TableColumn<Route, String> location1NameColumn;
    public TableColumn<Route, Integer> location2XColumn;
    public TableColumn<Route, Long> location2YColumn;
    public TableColumn<Route, Double> location2ZColumn;
    public TableColumn<Route, Double> usernameColumn;
    public TableColumn<Route, Double> distanceColumn;

    private Context context;
    private Stage currentStage;
    private Client client;
    public void initialize(Client client, Context context, Stage stage) {
        Platform.runLater(() -> {
            this.context = context;
            this.currentStage = stage;
            this.client = client;

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            coordinatesXColumn.setCellValueFactory(new PropertyValueFactory<>("coordinatesX"));
            coordinatesYColumn.setCellValueFactory(new PropertyValueFactory<>("coordinatesY"));
            creationdateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
            location1XColumn.setCellValueFactory(new PropertyValueFactory<>("fromX"));
            location1YColumn.setCellValueFactory(new PropertyValueFactory<>("fromY"));
            location1NameColumn.setCellValueFactory(new PropertyValueFactory<>("fromName"));
            location2XColumn.setCellValueFactory(new PropertyValueFactory<>("toX"));
            location2YColumn.setCellValueFactory(new PropertyValueFactory<>("toY"));
            location2ZColumn.setCellValueFactory(new PropertyValueFactory<>("toZ"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));

            updateTable();
            usernameLabel.setText(context.getAuthModule().getUser().getUsername());
        });
    }

    public void exit(ActionEvent actionEvent) {
        currentStage.close();
    }

    public void deleteFirst(ActionEvent actionEvent) {
        try {
            String msg = client.communicateWithServer("remove_first", null).getMessage();
            if (msg.length() > 0) {
                context.getUserIO().printLine(msg);
            }
            updateTable();
        } catch (IOException | ClassNotFoundException e) {

        }
    }

    public void deleteAll(ActionEvent actionEvent) {
        try {
            String msg = client.communicateWithServer("clear", null).getMessage();
            if (msg.length() > 0) {
                context.getUserIO().printLine(msg);
            }
            updateTable();
        } catch (IOException | ClassNotFoundException e) {

        }
    }

    public void sumOfDistance(ActionEvent actionEvent) {
        try {
            context.getUserIO().printLine(client.communicateWithServer("sum_of_distance", null).getMessage());
            updateTable();
        } catch (IOException | ClassNotFoundException e) {

        }
    }

    public void info(ActionEvent actionEvent) {
        try {
            context.getUserIO().printLine(client.communicateWithServer("info", null).getMessage());
            updateTable();
        } catch (IOException | ClassNotFoundException e) {

        }
    }

    public void refresh(ActionEvent actionEvent) {
        updateTable();
    }

    private void updateTable() {
        ObservableList<Route> list;
        try {
            list = FXCollections.observableList((List<Route>) client.communicateWithServer("show", null).getObj());
            collectionTable.setItems(list);
            usernameLabel.setText(context.getAuthModule().getUser().getUsername());
            countLabel.setText(String.valueOf(list.size()));
        } catch (IOException | ClassNotFoundException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
    }
}
