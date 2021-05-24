package client;

import controller.AuthController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import locale.ClientLocale;
import org.checkerframework.checker.units.qual.C;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main extends Application {
    public static void main(String[] args)  {
        /*if (args.length == 2) {
            Application application = new Application(args[0], Integer.parseInt(args[1]));
            application.start();
        } else {
            System.err.println("usage: program_name address port");
        }*/
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Context context = new Context();
        Locale.setDefault(new Locale("ru"));
        Parameters parameters = getParameters();
        List<String> args = parameters.getRaw();
        if (args.size() == 2) {
            Client client = new Client(args.get(0), Integer.parseInt(args.get(1)), context);
            try {
                client.openConnection();
            } catch (IOException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText(ClientLocale.getString("UI_ERROR_SERVER_UNREACHABLE"));
                alert.showAndWait();
                return;
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../auth.fxml"));
            Parent root = loader.load();
            AuthController controller = loader.getController();
            controller.initialize(client, context, primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(ClientLocale.getString("UI_AUTH_TITLE"));
            primaryStage.setResizable(false);
            primaryStage.show();
        } else {
            System.err.println("arguments err");
            stop();
        }

    }
}
