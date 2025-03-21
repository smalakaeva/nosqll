package com.okmich.mysql2mongodb;

import com.okmich.mysql2mongodb.ui.AppFrame;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String userInput = (String) JOptionPane.showInputDialog(
                null,
                "Введите данные через запятую:",
                "Movielens on Mongo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                "jdbc:mysql://localhost:3306/movielens,debian-sys-maint,Fpqjfwnl9KQKzrPM,mongodb://localhost:27017/movielens,movielens"
        );

        if (userInput == null || userInput.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ошибка: Введите корректные данные");
            return;
        }

        String[] parts = userInput.split(",");
        if (parts.length < 5) {
            JOptionPane.showMessageDialog(null, "Ошибка: Неверный формат данных");
            return;
        }

        Map<String, String> param = new HashMap<>();
        param.put("dbServerUrl", parts[0]);
        param.put("dbUser", parts[1]);
        param.put("dbPassword", parts[2]);
        param.put("mongoDbUrl", parts[3]);
        param.put("mongoDbName", parts[4]);

        SwingUtilities.invokeLater(() -> {
            AppFrame appForm = new AppFrame(
                    param.get("dbServerUrl"),
                    param.get("dbUser"),
                    param.get("dbPassword"),
                    param.get("mongoDbUrl"),
                    param.get("mongoDbName")
            );
            appForm.setVisible(true);
        });
    }
}
