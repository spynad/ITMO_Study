package io;

import javafx.scene.control.Alert;

import java.io.IOException;

public class JavaFXIO implements UserIO{
    @Override
    public String readLine() throws IOException {
        return null;
    }

    @Override
    public String readPasswordLine() throws IOException {
        return null;
    }

    @Override
    public boolean askYesOrNo(String message) throws IOException {
        return false;
    }

    @Override
    public void printLine(String line) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("info");
        alert.setHeaderText(line);
        alert.showAndWait();
    }

    @Override
    public void printErrorMessage(String line) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("error");
        alert.setHeaderText(line);
        alert.showAndWait();
    }

    @Override
    public void printUserPrompt() {

    }
}
