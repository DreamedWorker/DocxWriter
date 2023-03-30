package com.dream.docxwriter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
//java --module-path /Users/bluedream/javafx-sdk-17.0.6/lib
// --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.web
// -jar /Users/bluedream/IdeaProjects/DocxWriter/out/artifacts/unnamed/unnamed.jar
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("DocxWriter - Official File Generator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}