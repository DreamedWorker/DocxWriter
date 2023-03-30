package com.dream.docxwriter;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.PoiChecker;
import cn.hutool.poi.word.Word07Writer;
import com.dream.docxwriter.controller.AboutController;
import com.dream.docxwriter.helper.DocxHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Optional;

public class HelloController extends Control {
    @FXML
    private TextField mainTime;
    @FXML
    private TextField mainSender;
    @FXML
    private TextField mainHeader;
    @FXML
    private TextField mainIndex;
    //-------------Part of Items-------------------//
    @FXML
    private ListView<String> paraList;
    @FXML
    private TextField mainRedHead;
    @FXML
    private TextField mainTitle;
    private final ObservableList<String> item = FXCollections.observableArrayList();
    private String securityInfo = "n;n;n";

    //-----------------Part of Menu Events---------------------//
    @FXML
    private void onMainNewSelected() throws IOException { //create a new window with the same view
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800.0, 600.0);
        Stage stage = new Stage();
        stage.setTitle("WordWriter - Official File Generator");
        stage.setScene(scene);
        stage.show(); //copy the code from Application.kt
    }

    @FXML
    private void onAboutSelected() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("activity_about.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800.0, 600.0);
        Stage stage = new Stage();
        stage.setTitle("WordWriter - Official File Generator");
        stage.setScene(scene);
        stage.show(); //copy the code from Application.kt
    }

    @FXML
    private void onHelpDocSelected() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(URI.create("https://github.com/DreamedWorker/DocxWriter/wiki"));
    }

    @FXML
    private void onClearAllSelected() {
        securityInfo = "n;n;n";
        item.clear();
        paraList.setItems(item);
        mainRedHead.setText("");
        mainHeader.setText("");
        mainIndex.setText("");
        mainTitle.setText("");
        mainSender.setText("");
        mainTime.setText("");
    }

    @FXML
    private void onPrimarySelected() throws IOException {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("元数据编辑器");
        ButtonType saveBtn = new ButtonType("保存", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveBtn ,ButtonType.CANCEL);
        Scene newParaView = new Scene(new FXMLLoader(HelloController.class.getResource("window_primary_info.fxml")).load(), 600.0, 300.0);
        dialog.getDialogPane().setContent(newParaView.getRoot());
        TextField num = (TextField) newParaView.lookup("#primaryFileIndex");
        TextField sec = (TextField) newParaView.lookup("#primaryFileSecurity");
        TextField nec = (TextField) newParaView.lookup("#primaryFileNecessary");
        String[] values = securityInfo.split(";");
        num.setText((values[0].equals("n") ? "" : values[0]));
        sec.setText((values[1].equals("n") ? "" : values[1]));
        nec.setText((values[2].equals("n") ? "" : values[2]));
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveBtn) {
                securityInfo = DocxHelper.primaryData(
                        num.getText(), sec.getText(), nec.getText()
                );
            }
            return null;
        });
        dialog.show();
    }

    @FXML
    private void closeWindow() { //With the same function of "finish()" on Android SDK
        System.exit(0);
    }

    //-------------------Part of Events used by main logic---------------------------------//
    @FXML
    private void addPara() throws IOException{
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("新建自然段");
        ButtonType saveBtn = new ButtonType("保存", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveBtn ,ButtonType.CANCEL);
        Scene newParaView = new Scene(new FXMLLoader(HelloController.class.getResource("window_para.fxml")).load(), 600.0, 300.0);
        dialog.getDialogPane().setContent(newParaView.getRoot());
        TextArea container = (TextArea) newParaView.lookup("#window_para_container");
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveBtn) {
                String data = "自然段:" + container.getText();
                item.add(data);
                paraList.setItems(item);
                System.out.println(data);
            }
            return null;
        });
        dialog.show();
    }

    @FXML
    private void addSubtitle() throws IOException {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("新建子标题");
        ButtonType saveBtn = new ButtonType("保存", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveBtn ,ButtonType.CANCEL);
        Scene newParaView = new Scene(new FXMLLoader(HelloController.class.getResource("window_subtitle.fxml")).load(), 600.0, 300.0);
        dialog.getDialogPane().setContent(newParaView.getRoot());
        TextArea container = (TextArea) newParaView.lookup("#window_subtitle_container");
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveBtn) {
                String data = "子标题:" + container.getText();
                item.add(data);
                paraList.setItems(item);
                System.out.println(data);
            }
            return null;
        });
        dialog.show();
    }

    @FXML
    private void addSubContext() throws IOException {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("新建子内容");
        ButtonType saveBtn = new ButtonType("保存", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveBtn ,ButtonType.CANCEL);
        Scene newParaView = new Scene(new FXMLLoader(HelloController.class.getResource("window_context.fxml")).load(), 600.0, 300.0);
        dialog.getDialogPane().setContent(newParaView.getRoot());
        TextArea container = (TextArea) newParaView.lookup("#window_context_container");
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveBtn) {
                String data = "子内容:" + container.getText();
                item.add(data);
                paraList.setItems(item);
                System.out.println(data);
            }
            return null;
        });
        dialog.show();
    }

    @FXML
    private void deleteSelected(){
        Alert dialog = new Alert(Alert.AlertType.WARNING);
        dialog.setTitle("删除操作警告");
        dialog.setHeaderText("你正在尝试删除当前选中的条目，确定要这样做吗？");
        dialog.setContentText("此操作不可撤销，请谨慎选择！");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK){
                int index = paraList.getSelectionModel().getSelectedIndex();
                item.remove(index);
                paraList.setItems(item);
            }
            return null;
        });
        dialog.showAndWait();
    }

    @FXML
    private void modifySelectedP() throws IOException{
        int b = paraList.getSelectionModel().getSelectedIndex();
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("条目修改助手");
        ButtonType saveBtn = new ButtonType("保存改动", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveBtn ,ButtonType.CANCEL);
        Scene newParaView = new Scene(new FXMLLoader(HelloController.class.getResource("window_change.fxml")).load(), 600.0, 300.0);
        dialog.getDialogPane().setContent(newParaView.getRoot());
        TextArea container = (TextArea) newParaView.lookup("#window_change_container");
        String oriData = item.get(b);
        container.setText(oriData.split(":")[1]);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveBtn) {
                item.set(b, oriData.split(":")[0] + ":" + container.getText());
                paraList.setItems(item);
            }
            return null;
        });
        dialog.show();
    }

    @FXML
    private void saveFile() throws Exception {
        PoiChecker.checkPoiImport();
        String filePath;
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("os.name").toLowerCase());
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("文件保存工具");
        dialog.setContentText("不需要拓展名（.docx）");
        dialog.setHeaderText("输入此文件的名称");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            filePath = result.get() + ".docx";
            System.out.println(System.getProperty("user.dir") + "/" + result.get() + ".docx");
            FileUtil.writeString("", filePath, Charset.defaultCharset());
            Word07Writer writer = new Word07Writer();
            XWPFDocument docx = writer.getDoc();
            DocxHelper.dealPrimaryData(docx, securityInfo); //处理文件头元素
            if (!mainRedHead.getText().equals("")){ //具有红头的文件处理
                DocxHelper.dealRedHead(docx, mainRedHead.getText(), mainIndex.getText()); //红头与发文号
            } else {
                DocxHelper.dealIndex(docx, mainIndex.getText());
            }
            DocxHelper.dealActualTitle(docx, mainTitle.getText());
            DocxHelper.dealFileHeader(docx, mainHeader.getText());
            DocxHelper.dealContext(docx, item);
            DocxHelper.dealBottom(docx, mainSender.getText(), mainTime.getText());
            FileOutputStream output = new FileOutputStream(filePath);
            docx.write(output);
            output.flush();
            output.close();
        }
    }

    @FXML
    private void aboutWindow(){
    }
}