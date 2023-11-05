/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class ModelAdd {
    ModelRepository conn = new ModelRepository();
    
    public static void addPerson(Connection connection, Model model) throws SQLException {
        String insertQuery = "INSERT INTO book (book_id, book_name, description, price, pub_id, cat_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, model.getBook_id());
            preparedStatement.setString(2, model.getBook_name());
            preparedStatement.setString(3, model.getDescription());
            preparedStatement.setInt(4, model.getPrice());
            preparedStatement.setString(5, model.getImg());
            preparedStatement.setString(6, model.getPub_id());
            preparedStatement.setString(7, model.getCat_id());
            preparedStatement.executeUpdate();
        }
    }
}