package org.example.parallel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        // Ustawienie tytułu okna
        stage.setTitle("Aplikacja Równoległa - Politechnika Wrocławska");

        // Ustawienie minimalnych rozmiarów okna
        stage.setMinWidth(500);
        stage.setMinHeight(350);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}