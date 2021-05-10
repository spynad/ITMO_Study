package controller;

import client.Context;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoader {
    /*public static void getSceneFromFXML(Stage stage, String fxmlFile, Context context) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource("../" + fxmlFile));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.initialize(client, context, stage);
        Scene scene = new Scene(root);
    }

    public static void getSceneFromFXML(Stage stage, String fxmlFile, Context context) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource("../" + fxmlFile));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.initialize(context, stage);
        Scene scene = new Scene(root);
    }*/
}
