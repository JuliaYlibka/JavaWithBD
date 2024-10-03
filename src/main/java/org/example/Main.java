package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер JDBC не найден:");
            e.printStackTrace();
            return; // Прерываем выполнение программы, если драйвер не найден
        }
         String user = "sa";
         String password = "001002999";

        String url = "jdbc:sqlserver://LAPTOP-GT0I79AH;databaseName=LybraryBDForJava;integratedSecurity=true;trustServerCertificate=true;encrypt=true";
        Scanner in = new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection(url,user,password);
            System.out.println("Подключение к базе данных успешно установлено!");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных:");
            e.printStackTrace();
        }

        in.close(); // Закрытие сканера
    }
}
