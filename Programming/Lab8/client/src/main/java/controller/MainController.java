package controller;

import client.Client;
import client.Context;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import locale.ClientLocale;
import route.Route;
import route.RouteFormatter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

//FIXME REFACTOR! god controller, mvc is broken here
// - pls do not use this code in your own lab works it's soo bad
public class MainController {
    public Canvas visualizationCanvas;
    public AnchorPane visualizationAnchorPane;
    private Context context;
    private Stage currentStage;
    private Client client;

    public Menu labMenu;
    public MenuItem settingsMenuItem;
    public Menu commandsMenu;
    public Menu deleteMenu;
    public MenuItem filterItem;
    public Menu helpMenu;
    public MenuItem aboutMenuItem;
    public Tab tableTab;
    public Tab visualizationTab;
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

    private ObservableList<Route> routeList;
    private RouteFormatter routeFormatter = new RouteFormatter();
    private Map<String, Color> usersColorMap = new HashMap<>();
    private Set<String> users = new HashSet<>();
    private boolean filterEnabled = false;
    
    
    public void initialize(Client client, Context context, Stage stage) {
        Platform.runLater(() -> {
            this.context = context;
            this.currentStage = stage;
            this.client = client;

            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            coordinatesXColumn.setCellValueFactory(new PropertyValueFactory<>("coordinatesX"));
            coordinatesYColumn.setCellValueFactory(new PropertyValueFactory<>("localizedCoordinatesY"));
            creationdateColumn.setCellValueFactory(new PropertyValueFactory<>("localizedCreationDate"));
            location1XColumn.setCellValueFactory(new PropertyValueFactory<>("fromX"));
            location1YColumn.setCellValueFactory(new PropertyValueFactory<>("fromY"));
            location1NameColumn.setCellValueFactory(new PropertyValueFactory<>("fromName"));
            location2XColumn.setCellValueFactory(new PropertyValueFactory<>("toX"));
            location2YColumn.setCellValueFactory(new PropertyValueFactory<>("toY"));
            location2ZColumn.setCellValueFactory(new PropertyValueFactory<>("localizedToZ"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            distanceColumn.setCellValueFactory(new PropertyValueFactory<>("localizedDistance"));

            updateTable(null);

            usernameLabel.setText(context.getAuthModule().getUser().getUsername());
            visualizationTab.setOnSelectionChanged(event -> {
                if (visualizationTab.isSelected()) {
                    drawVisualization(true);
                }
            });
            Thread updateThread = new Thread(() -> {
                try {
                    while(true) {
                        try {
                            routeList = FXCollections.observableList((List<Route>) client.communicateWithServer("show", null, null).getObj());
                            updateTable(routeList);
                            if (visualizationTab.isSelected()) {
                                drawVisualization(false);
                            }
                            Thread.sleep(2000);
                        } catch (IOException e) {
                            System.err.println("no connection");
                        }
                    }
                } catch (ClassNotFoundException | InterruptedException e) {

                }
            });
            updateThread.start();
            stage.setOnHidden(event -> {
                updateThread.interrupt();
            });
        });
    }

    public void localize() {
        labMenu.setText(ClientLocale.getString("UI_MAIN_LAB_MENU"));
        settingsMenuItem.setText(ClientLocale.getString("UI_MAIN_LAB_MENU_ITEM_SETTINGS"));
        quitItem.setText(ClientLocale.getString("UI_MAIN_LAB_MENU_ITEM_QUIT"));
        commandsMenu.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU"));
        addItem.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_ADD"));
        updateItem.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_UPDATE"));
        deleteMenu.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_DELETE"));
        deleteByIdItem.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_DELETE_BY_ID"));
        deleteByIndexItem.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_DELETE_BY_INDEX"));
        deleteFirstItem.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_DELETE_FIRST"));
        deleteAllByDistanceItem.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_DELETE_ALL_BY_DISTANCE"));
        deleteAllItem.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_DELETE_ALL"));
        sumOfDistanceItem.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_GET_SUM_OF_DISTANCE"));
        //TODO: deprecated!!!
        filterByNameItem.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_FILTER_BY_NAME"));
        //TODO: end of deprecated
        filterItem.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_FILTER"));
        infoItem.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_COLLECTION_INFO"));
        refreshItem.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_REFRESH"));
        executeScriptItem.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_EXECUTE_SCRIPT"));
        historyItem.setText(ClientLocale.getString("UI_MAIN_COMMANDS_MENU_ITEM_HISTORY"));
        helpMenu.setText(ClientLocale.getString("UI_MAIN_HELP_MENU"));
        aboutMenuItem.setText(ClientLocale.getString("UI_MAIN_HELP_MENU_ITEM_ABOUT"));
        commandsHelpItem.setText(ClientLocale.getString("UI_MAIN_HELP_MENU_ITEM_COMMANDS"));
        tableTab.setText(ClientLocale.getString("UI_MAIN_TABLE"));
        visualizationTab.setText(ClientLocale.getString("UI_MAIN_VISUALIZATION"));
    }

    public void exit(ActionEvent actionEvent) {
        currentStage.close();
    }

    public void deleteFirst(ActionEvent actionEvent) {
        try {
            String msg = client.communicateWithServer("remove_first", null, null).getMessage();
            if (msg.length() > 0) {
                context.getUserIO().printLine(msg);
            }
            updateTable(null);
        } catch (IOException | ClassNotFoundException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
    }

    public void deleteAll(ActionEvent actionEvent) {
        try {
            String msg = client.communicateWithServer("clear", null, null).getMessage();
            if (msg.length() > 0) {
                context.getUserIO().printLine(msg);
            }
            updateTable(null);
        } catch (IOException | ClassNotFoundException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
    }

    public void sumOfDistance(ActionEvent actionEvent) {
        try {
            context.getUserIO().printLine(client.communicateWithServer("sum_of_distance", null, null).getMessage());
            updateTable(null);
        } catch (IOException | ClassNotFoundException e) {

        }
    }

    public void info(ActionEvent actionEvent) {
        try {
            context.getUserIO().printLine(client.communicateWithServer("info", null, null).getMessage());
            updateTable(null);
        } catch (IOException | ClassNotFoundException e) {

        }
    }

    public void refresh(ActionEvent actionEvent) {
        filterEnabled = false;
        updateTable(null);
    }

    private void updateTable(List<Route> filteredList) {
        try {
            if (filteredList == null) {
                List<Route> routes = (List<Route>) client.communicateWithServer("show", null, null).getObj();

                routeList = FXCollections.observableList(routes);
            } else {
                routeList = FXCollections.observableList(filteredList);
            }
        } catch (IOException | ClassNotFoundException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
        Platform.runLater(() -> {
            for (Route route : collectionTable.getItems()) {
                users.add(route.getUsername());
            }
            for (String username : users) {
                if (!usersColorMap.containsKey(username)) {
                    usersColorMap.put(username, Color.color(Math.random(), Math.random(), Math.random()));
                }
            }
            if (!filterEnabled) {
                collectionTable.setItems(routeList);
            }
            collectionTable.setRowFactory(rf -> {
                TableRow<Route> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getButton().equals(MouseButton.SECONDARY) && !row.isEmpty()) {
                        Route route = row.getItem();
                        if (route.getUsername().equals(context.getAuthModule().getUser().getUsername())) {
                            currentStage.setUserData(route);
                            update(route);
                        }
                    }
                });
                return row;
            });
            usernameLabel.setText(context.getAuthModule().getUser().getUsername());
            countLabel.setText(String.valueOf(routeList.size()));
        });
    }

    public void add(ActionEvent actionEvent) {
        Stage stage = new Stage(StageStyle.DECORATED);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../route.fxml"));
        stage.setResizable(false);
        stage.setTitle("Enter route");
        try {
            showAndWaitOnRouteStage(stage, loader, true, null);
            if (stage.getUserData() != null) {
                Route data = (Route) stage.getUserData();
                client.communicateWithServer("add", null, data);
                updateTable(null);
            }
        } catch (IOException | ClassNotFoundException | IllegalStateException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
    }

    public void updateEvent(ActionEvent actionEvent) {
        update(null);
    }

    private void update(Route route) {
        Stage stage = new Stage(StageStyle.DECORATED);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../route.fxml"));
        stage.setResizable(false);
        stage.setTitle("Enter route");
        try {
            showAndWaitOnRouteStage(stage, loader, false, route);
            if (stage.getUserData() != null) {
                Route data = (Route) stage.getUserData();
                context.getUserIO().printLine(String.valueOf(data.getId()));
                client.communicateWithServer("update " + data.getId(), null, data);
                updateTable(null);
            }
        } catch (IOException | IllegalStateException | ClassNotFoundException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
    }

    private void showAndWaitOnRouteStage(Stage stage, FXMLLoader loader, boolean disallowIdField, Route route) throws IOException {
        Parent root = loader.load();
        RouteController controller = loader.getController();
        controller.initialize(client, context, stage, disallowIdField, route);
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void deleteByID(ActionEvent actionEvent) {
        Stage stage = new Stage(StageStyle.DECORATED);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../argument.fxml"));
        stage.setResizable(false);
        stage.setTitle("Enter ID");
        try {
            showAndWaitOnArgStage(stage, loader);
            if (stage.getUserData() != null) {
                String data = (String) stage.getUserData();
                client.communicateWithServer("remove_by_id " + data, null, null);
                updateTable(null);
            }
        } catch (IOException | ClassNotFoundException | IllegalStateException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
    }

    public void deleteByIndex(ActionEvent actionEvent) {
        Stage stage = new Stage(StageStyle.DECORATED);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../argument.fxml"));
        stage.setResizable(false);
        stage.setTitle("Enter index");
        try {
            showAndWaitOnArgStage(stage, loader);
            if (stage.getUserData() != null) {
                String data = (String) stage.getUserData();
                client.communicateWithServer("remove_at " + data, null, null);
                updateTable(null);
            }
        } catch (IOException | ClassNotFoundException | IllegalStateException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
    }

    public void deleteAllByDistance(ActionEvent actionEvent) {
        Stage stage = new Stage(StageStyle.DECORATED);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../argument.fxml"));
        stage.setResizable(false);
        stage.setTitle("Enter distance");
        try {
            showAndWaitOnArgStage(stage, loader);
            if (stage.getUserData() != null) {
                String data = (String) stage.getUserData();
                client.communicateWithServer("remove_all_by_distance " + data, null, null);
                updateTable(null);
            }
        } catch (IOException | ClassNotFoundException | IllegalStateException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
    }

    private void showAndWaitOnArgStage(Stage stage, FXMLLoader loader) throws IOException {
        Parent root = loader.load();
        ArgumentController controller = loader.getController();
        controller.initialize(stage, context);
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    private void showAndWaitOnFilterStage(Stage stage, FXMLLoader loader) throws IOException {
        Parent root = loader.load();
        FilterController controller = loader.getController();
        controller.initialize(stage, context);
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void filterByName(ActionEvent actionEvent) {
        Stage stage = new Stage(StageStyle.DECORATED);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../argument.fxml"));
        stage.setResizable(false);
        stage.setTitle("Enter name");
        try {
            showAndWaitOnArgStage(stage, loader);
            if (stage.getUserData() != null) {
                String data = (String) stage.getUserData();
                List<Route> filteredRoutes = (List<Route>) client.communicateWithServer("filter_contains_name " + data, null, null).getObj();
                filterEnabled = true;
                updateTable(filteredRoutes);
            }
        } catch (IOException | ClassNotFoundException | IllegalStateException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
    }

    public void historyEventShow(ActionEvent actionEvent) {
        try {
            String msg = client.communicateWithServer("history", null, null).getMessage();
            context.getUserIO().printLine(msg);
        } catch (IOException | ClassNotFoundException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
    }

    public void filterElements(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage(StageStyle.DECORATED);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../filter.fxml"));
        stage.setResizable(false);
        stage.setTitle("Select desirable field and enter it:");
        stage.setUserData(collectionTable.getItems());
        showAndWaitOnFilterStage(stage, loader);
        if (stage.getUserData() != null) {
            collectionTable.setItems((ObservableList<Route>) stage.getUserData());
            filterEnabled = true;
        }
    }

    public void openSettings(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage(StageStyle.DECORATED);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../changeLanguage.fxml"));
        stage.setResizable(false);
        stage.setTitle("Language setting");
        Parent root = loader.load();
        ChangeLanguageController controller = loader.getController();
        controller.initialize(stage, context);
        Scene scene = new Scene(root);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
        localize();
    }

    public void drawVisualization(boolean animate) {
        Platform.runLater(() -> {
            GraphicsContext gc = visualizationCanvas.getGraphicsContext2D();
            gc.clearRect(0, 0, visualizationCanvas.getWidth(), visualizationCanvas.getHeight());
            for (Route route : routeList) {
                DoubleProperty opacity = new SimpleDoubleProperty();
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(0),
                                new KeyValue(opacity, 0)
                        ),
                        new KeyFrame(Duration.seconds(3),
                                new KeyValue(opacity, 1)
                        )
                );
                timeline.setCycleCount(1);
                Button button = new Button();
                button.setMaxSize(10, 10);
                button.setPrefHeight(10);
                button.setPrefWidth(10);
                button.setTranslateX(route.getCoordinatesX());
                button.setTranslateY(route.getCoordinatesY());
                button.setOpacity(0);
                button.setOnMouseClicked(event -> {
                    if (route.getUsername().equals(context.getAuthModule().getUser().getUsername())) {
                        update(route);
                        drawVisualization(false);
                    }
                });
                visualizationAnchorPane.getChildren().add(button);
                if (animate) {
                    AnimationTimer timer = new AnimationTimer() {
                        @Override
                        public void handle(long now) {
                            if (animate) {
                                gc.setGlobalAlpha(opacity.doubleValue());
                            } else {
                                gc.setGlobalAlpha(1);
                            }
                            gc.setFill(usersColorMap.get(route.getUsername()));
                            gc.fillRect(Math.max(0, Math.min(visualizationCanvas.getWidth(), route.getCoordinatesX())),
                                    Math.max(0, Math.min(visualizationCanvas.getHeight(), route.getCoordinatesY())), 10, 10);
                        }
                    };
                    timer.start();
                    timeline.play();
                    timeline.setOnFinished(event -> {
                        timer.stop();
                    });
                } else {
                    gc.setFill(usersColorMap.get(route.getUsername()));
                    gc.fillRect(Math.max(0, Math.min(visualizationCanvas.getWidth(), route.getCoordinatesX())),
                            Math.max(0, Math.min(visualizationCanvas.getHeight(), route.getCoordinatesY())), 10, 10);
                }
            }
        });
    }

    public void showHelp(ActionEvent actionEvent) {
        try {
            context.getUserIO().printLine(client.communicateWithServer("help", null, null).getMessage());
        } catch (IOException | ClassNotFoundException e) {
            context.getUserIO().printErrorMessage(e.getMessage());
        }
    }
}
