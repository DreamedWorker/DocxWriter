package com.dream.docxwriter;

import cn.hutool.setting.Setting;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        Setting setting = new Setting("normal.config");
        findSettingsData(setting);
        System.out.println(setting.getByGroup("font.XiaoBiaoSong", "macosx"));
        System.out.println(setting.getByGroup("font.XiaoBiaoSong", "windows"));
        HelloApplication.main(args);
    }

    private static void findSettingsData(Setting setting) {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            JOptionPane.showMessageDialog(null,
                    setting.getByGroup("app.windows", "normal"),
                    setting.getByGroup("app.hint", "normal"), JOptionPane.WARNING_MESSAGE);
        }
    }
}
