package com.dream.docxwriter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class AboutController {
    @FXML
    private Hyperlink githubInfo;
    @FXML
    private Hyperlink developProfile;

    @FXML
    private void goDeveloper() {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(URI.create("https://github.com/DreamedWorker"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void goLib() {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(URI.create("https://github.com/DreamedWorker/DocxWriter"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
