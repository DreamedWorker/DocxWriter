package com.dream.docxwriter.helper;

import cn.hutool.setting.Setting;
import javafx.collections.ObservableList;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.math.BigInteger;

public class DocxHelper {

    public static String primaryData(String a, String b, String c){
        StringBuilder builder = new StringBuilder();
        if (!a.equals("")){
            builder.append(a).append(";");
        } else {
            builder.append("n;");
        }
        if (!b.equals("")){
            builder.append(b).append(";");
        } else {
            builder.append("n;");
        }
        if (!c.equals("")){
            builder.append(c);
        } else {
            builder.append("n");
        }
        return builder.toString();
    }

    public static void dealPrimaryData(XWPFDocument document, String dataA){
        String[] data = dataA.split(";");
        if (!dataA.equals("n;n;n")) {
            XWPFParagraph primary = document.createParagraph();
            if (!data[0].equals("n")) {
                XWPFRun run = primary.createRun();
                run.setText(data[0]);
                run.setFontSize(16.0);
                run.setFontFamily("STHeiti");
            }
            if (!data[1].equals("n")) {
                XWPFRun run = document.createParagraph().createRun();
                run.setText(data[1]);
                run.setFontSize(16.0);
                run.setFontFamily("STHeiti");
            }
            if (!data[2].equals("n")) {
                XWPFRun run = document.createParagraph().createRun();
                run.setText(data[2]);
                run.setFontSize(16.0);
                run.setFontFamily("STHeiti");
            }
        }
    }

    public static void dealRedHead(XWPFDocument document, String redHead, String index){
        Setting setting = new Setting("normal.config");
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText(redHead);
        run.setFontSize(22.0);
        run.setColor("ed2a1e");
        //run.setFontFamily("FZXBSJW--GB1-0");
        run.setFontFamily(setting.getByGroup("font.XiaoBiaoSong", "windows"));
        XWPFRun run1 = paragraph.createRun();
        run1.addCarriageReturn();
        run1.addCarriageReturn();
        run1.addCarriageReturn();
        run1.setText(index);
        run1.setFontSize(16.0);
        //run1.setFontFamily("STFangsong");
        run1.setFontFamily(setting.getByGroup("font.FangSong", "windows"));
        CTP ctp = paragraph.getCTP();
        CTPPr ctpPr = ctp.getPPr();
        if (ctpPr == null){
            ctpPr = ctp.addNewPPr();
        }
        CTPBdr ctBorders = ctpPr.isSetPBdr() ? ctpPr.getPBdr() : ctpPr.addNewPBdr();
        CTBorder border = ctBorders.addNewBottom();
        border.setVal(STBorder.SINGLE);
        border.setColor("ed2a1e");
    }

    public static void dealIndex(XWPFDocument document, String index){
        Setting setting = new Setting("normal.config");
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.addCarriageReturn();
        run.addCarriageReturn();
        run.setText(index);
        run.setFontSize(16.0);
        //run.setFontFamily("STFangsong");
        run.setFontFamily(setting.getByGroup("font.FangSong", "windows"));
    }

    public static void dealActualTitle(XWPFDocument document, String title){
        Setting setting = new Setting("normal.config");
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.addCarriageReturn();
        run.addCarriageReturn();
        run.setText(title);
        run.setFontSize(16.0);
        //run.setFontFamily("FZXBSJW--GB1-0");
        run.setFontFamily(setting.getByGroup("font.XiaoBiaoSong", "windows"));
    }

    public static void dealFileHeader(XWPFDocument document, String header){
        Setting setting = new Setting("normal.config");
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(header + "：");
        run.setFontSize(16.0);
        //run.setFontFamily("STFangsong")
        run.setFontFamily(setting.getByGroup("font.FangSong", "windows"));
        setStableLineStyle(paragraph);
    }

    public static void dealContext(XWPFDocument document, ObservableList<String> sentences){
        Setting setting = new Setting("normal.config");
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setIndentationFirstLine(652);
        XWPFRun run = paragraph.createRun();
        run.setFontSize(16.0);
        run.setFontFamily(setting.getByGroup("font.FangSong", "windows"));
        //run.setFontFamily("STFangsong");
        //run.setText("        " + sentences.get(0).split(":")[1]);
        run.setText(sentences.get(0).split(":")[1]);
        setStableLineStyle(paragraph);
        for (int i = 1; i < sentences.size(); i ++){
            XWPFParagraph xwpfParagraph = document.createParagraph();
            xwpfParagraph.setIndentationFirstLine(652);
            XWPFRun xwpfRun = xwpfParagraph.createRun();
            //xwpfRun.setText("        " + sentences.get(i).split(":")[1]);
            xwpfRun.setText(sentences.get(i).split(":")[1]);
            xwpfRun.setFontSize(16.0);
            xwpfRun.setFontFamily(setting.getByGroup("font.FangSong", "windows"));
            //xwpfRun.setFontFamily("STFangsong");
            setStableLineStyle(xwpfParagraph);
        }
    }

    private static void setStableLineStyle(XWPFParagraph paragraph){
        CTP ctp = paragraph.getCTP();
        CTPPr ppr = ctp.isSetPPr() ? ctp.getPPr() : ctp.addNewPPr();
        CTSpacing spacing = ppr.isSetSpacing()? ppr.getSpacing() : ppr.addNewSpacing();
        spacing.setAfter(BigInteger.valueOf(0));
        spacing.setBefore(BigInteger.valueOf(0));
        spacing.setLineRule(STLineSpacingRule.EXACT);
        spacing.setLine(BigInteger.valueOf(560));
    }

    public static void dealBottom(XWPFDocument document, String sender, String time){
        Setting setting = new Setting("normal.config");
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun run = paragraph.createRun();
        run.addCarriageReturn();
        run.addCarriageReturn();
        run.setText(sender);
        run.addCarriageReturn();
        run.setText(time);
        run.setFontSize(16.0);
        run.setFontFamily(setting.getByGroup("font.FangSong", "windows"));
        //run.setFontFamily("STFangsong");
    }
}
