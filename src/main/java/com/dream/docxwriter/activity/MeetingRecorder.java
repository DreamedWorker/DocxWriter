package com.dream.docxwriter.activity;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MeetingRecorder extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("DocxWriter - Official File Generator");
        FXMLLoader loader = new FXMLLoader(MeetingRecorder.class.getResource("activity_about"));
        Scene meeting = new Scene(loader.load(), 800, 600);
        stage.setScene(meeting);
        stage.show();
    }
}
