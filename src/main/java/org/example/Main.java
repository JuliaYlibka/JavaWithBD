package org.example;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Executor;

public class Main {
    String url = "jdbc:mysql://127.0.0.1:3306/Lybrary";
    String user = "root";
    String password = "111000";
    public static void main(String[] args) {

        Main app = new Main();
        app.viewBooks();
        app.addBook(222,"Психология","Романцев И.А.",2001);
        app.addBook(333,"English for beginner","Lokov D.A.",2122);
        app.viewBooks();
    }

    public void addBook(int id, String title, String author, int year) {
        String query = "INSERT INTO books (id,title, author, year) VALUES (?,?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, author);
            preparedStatement.setInt(4, year);
            preparedStatement.executeUpdate();
            System.out.println("Книга добавлена: " + title);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewBooks() {
        String query = "SELECT * FROM books";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") + ", Title: " + resultSet.getString("title") +
                        ", Author: " + resultSet.getString("author") + ", Year: " + resultSet.getInt("year"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(int id, String title, String author, int year) {
        String query = "UPDATE books SET title = ?, author = ?, year = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setInt(3, year);
            preparedStatement.setInt(4, id);
            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Книга обновлена с ID: " + id);
            } else {
                System.out.println("Книга с указанным ID не найдена.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        String query = "DELETE FROM books WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Книга удалена с ID: " + id);
            } else {
                System.out.println("Книга с указанным ID не найдена.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}