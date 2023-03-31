package com.dream.docxwriter;

import com.dream.docxwriter.activity.MeetingRecorder;
import com.dream.docxwriter.global.Context;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
//java --module-path /Users/bluedream/javafx-sdk-17.0.6/lib
// --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.web
// -jar /Users/bluedream/IdeaProjects/DocxWriter/out/artifacts/unnamed/unnamed.jar
public class HelloApplication extends Application {
    public static Stage mStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("DocxWriter - Official File Generator");
        stage.setScene(scene);
        mStage = stage;
        Context.factory.createStage("DocxWriter - Official File Generator", 800, 600, "hello-view.fxml", HelloApplication.class);
        Context.factory.createStage("会议纪要实用工具", 800, 600, "window_subtitle.fxml", HelloApplication.class);
        stage.show();
    }

    public static void meetingRecorder(){
        MeetingRecorder.launch();
    }

    public static void main(String[] args) {
        launch();
    }
}