module com.dream.docxwriter {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires cn.hutool;
    requires org.apache.poi.poi;
    requires org.apache.logging.log4j;
    requires org.apache.poi.ooxml;
    requires org.apache.poi.ooxml.schemas;
    requires org.apache.xmlbeans;
    requires org.apache.commons.codec;
    requires org.apache.commons.collections4;
    requires org.apache.commons.compress;
    requires org.apache.commons.io;
    requires java.desktop;
    //requires eu.hansolo.tilesfx;

    opens com.dream.docxwriter to javafx.fxml;
    opens com.dream.docxwriter.controller to javafx.fxml;
    exports com.dream.docxwriter;
    exports com.dream.docxwriter.controller;
}