/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vxt.dbconnect_sqlserver_crud;

import dbconnect.Model;
import dbconnect.ModelRepository;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Crud_edit2Controller implements Initializable {
private String selectedBook_id="";
 
    @FXML
    private TextField book_id1;
    @FXML
    private TextField book_name1;
    @FXML
    private TextArea description1;
    @FXML
    private TextField price1;
    @FXML
    private TextField pub_id1;
    @FXML
    private TextField cat_id1;
    @FXML
    private TextField img1;
    @FXML
//    private Button add;
//    private Connection connection;
//    private ObservableList<Model> ls;
    
    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//    ModelRepository mcr = new ModelRepository();
//    
//    ls = mcr.findAll();

    
    public void initialize(URL url, ResourceBundle rb) {
    System.out.println("Received selectedBook_id: " + selectedBook_id);
    // Assuming selectedbook_id contains the book_id you want to retrieve
    String selectedBookId = CrudController.selectedBook_id; // Replace this with your actual variable or method to get selected book_id

    // Check if a book_id is selected
    if (selectedBookId != null && !selectedBookId.isEmpty()) {
        ModelRepository mcr = new ModelRepository();
        
        // Use the selected book_id to retrieve the specific book
        Model selectedModel = mcr.findByBookId(selectedBookId);

        // Check if the book was found
        if (selectedModel != null) {
            // Populate the fields with the retrieved data
            book_id1.setText(selectedModel.getBook_id());
            book_name1.setText(selectedModel.getBook_name());
            description1.setText(selectedModel.getDescription());
            price1.setText(String.valueOf(selectedModel.getPrice())); // Convert int to String
            pub_id1.setText(selectedModel.getPub_id());
            cat_id1.setText(selectedModel.getCat_id());
            img1.setText(selectedModel.getImg());
        } else {
            System.out.println("Book not found for the selected book_id: " + selectedBookId);
        }
    } else {
        System.out.println("No book_id selected.");
    }
}

    
    public void updateButton() {
    String book_id = book_id1.getText();
    String book_name = book_name1.getText();
    String description = description1.getText();
    String price = price1.getText();
    String pub_id = pub_id1.getText();
    String cat_id = cat_id1.getText();
    String img = img1.getText();
    
    Model model = new Model();
    model.setBook_id(book_id);
    model.setBook_name(book_name);
    model.setDescription(description);
    model.setPrice(Integer.parseInt(price)); // Assuming price is an integer
    model.setImg(img);
    model.setPub_id(pub_id);
    model.setCat_id(cat_id);
    

    // Create an instance of ModelRepository
    ModelRepository modelRepository = new ModelRepository();
//    ModelRepository modelRepository = new ModelRepository();
     Connection conn = modelRepository.getConnection();
    

    try {
        // Call the addPerson method on the instance of ModelRepository
        ModelRepository.updateBook(conn, model);
        System.out.println("Data added to the database.");
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
       
}

    public void setSelectedBookId(String getID) {
//     selectedBook_id = selectedBook_id;
    }
    
    
    
}
