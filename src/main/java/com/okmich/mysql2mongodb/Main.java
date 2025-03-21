/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.okmich.mysql2mongodb;

import com.okmich.mysql2mongodb.ui.AppFrame;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author michael.enudi
 */
public class Main {



    public static void main(String[] args) {

        String userInput = (String) JOptionPane.showInputDialog(
                null,
                "Введите данные через запятую:",
                "Movielens on Mongo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                "jdbc:mysql://localhost:3306/movielens,debian-sys-maint,Fpqjfwnl9KQKzrPM,mongodb://localhost:27017/movielens,movielens");

        String[] parts = userInput.split(",");
        /**
         *
         * @param dbServerUrl
         * @param dbUser
         * @param dbPassword
         * @param mongoDbUrl
         * @param mongoDbName
         */

        Map<String, String> param = new HashMap<>();
        param.put("dbServerUrl", parts[0]);
        param.put("dbUser", parts[1]);
        param.put("dbPassword", parts[2]);
        param.put("mongoDbUrl", parts[3]);
        param.put("mongoDbName", parts[4]);

        String dbServerUrl = param.getOrDefault( "dbServerUrl", "jdbc:mysql://localhost:3306/movielens");
        String dbUser =  param.getOrDefault( "dbUser", "root");
        String dbPassword =  param.getOrDefault( "dbPassword", "password");
        String mongoDbUrl =  param.getOrDefault( "mongoDbUrl", "mongodb://localhost:27017/movielens");
        String mongoDbName =  param.getOrDefault( "mongoDbName", "movielens");

        SwingUtilities.invokeLater(() -> {
            // Создаем экземпляр формы
            AppFrame appForm = new AppFrame( dbServerUrl,  dbUser,  dbPassword,
                     mongoDbUrl,  mongoDbName);
            // Делаем форму видимой
            appForm.setVisible(true);
        });

    }

}