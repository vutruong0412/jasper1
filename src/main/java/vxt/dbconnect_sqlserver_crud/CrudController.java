/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package vxt.dbconnect_sqlserver_crud;

import dbconnect.Model;
import dbconnect.ModelAdd;
import dbconnect.ModelRepository;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.SelectionMode;


/**
 * FXML Controller class
 *
 * @author Admin
 */
public class CrudController implements Initializable {
    public static String selectedBook_id;
    @FXML
    private TableView<Model> table;
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
    private Button add;
    private Connection connection;
    private ObservableList<Model> ls;
    @FXML
    private Button add1;
    private Button delete;
          
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    ModelRepository mcr = new ModelRepository();
    ls = mcr.findAll();


ObservableList<TableColumn<Model, ?>> cols = table.getColumns();
cols.get(0).setCellValueFactory(new PropertyValueFactory("book_id"));
cols.get(1).setCellValueFactory(new PropertyValueFactory("book_name"));
cols.get(2).setCellValueFactory(new PropertyValueFactory("description"));
cols.get(3).setCellValueFactory(new PropertyValueFactory("price"));
cols.get(4).setCellValueFactory(new PropertyValueFactory("img"));
cols.get(5).setCellValueFactory(new PropertyValueFactory("pub_id"));
cols.get(6).setCellValueFactory(new PropertyValueFactory("cat_id"));
table.setItems(ls); 


//table.setOnMouseClicked(event -> {
//    if (event.getClickCount() == 1) {
//        Model selectedModel = table.getSelectionModel().getSelectedItem(); // Use 'table' here
//        if (selectedModel != null) {
//            String bookId = selectedModel.getBook_id();
//            System.out.println("Selected book_id: " + bookId);
//            // You can perform further actions based on the bookId here.
//        }
//    }
//});

table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
table.setOnMouseClicked(event -> {
    if (event.getClickCount() == 1) {
        Model selectedModel = table.getSelectionModel().getSelectedItem();
        if (selectedModel != null) {
            selectedBook_id = selectedModel.getBook_id();
            // Assuming you have text fields for displaying the data
            book_id1.setText(selectedModel.getBook_id());
            book_name1.setText(selectedModel.getBook_name());
            description1.setText(selectedModel.getDescription());
            price1.setText(String.valueOf(selectedModel.getPrice())); // Convert int to String
            pub_id1.setText(selectedModel.getPub_id());
            cat_id1.setText(selectedModel.getCat_id());
            img1.setText(selectedModel.getImg());
            
            System.out.println("Selected book_id: " + selectedModel.getBook_id());
            System.out.println("Biến selectedBook_id:"+selectedBook_id);
            // You can perform further actions based on the selected data here.
        }
    }
});
    }
    
   
    public void refreshDataAll() {
    ModelRepository mcr = new ModelRepository();
    ls.clear(); // Clear existing data
    ls.addAll(mcr.findAll()); // Add updated data
    }
    
//    public void refreshData() {
//    ModelRepository mcr = new ModelRepository();
//    String selectedBookId = selectedBook_id; 
//    boolean updated = false;
//
//    for (Model model : ls) {
//        if (model.getBook_id() == selectedBookId) {
//            // Tìm thấy dòng có selectedbook_id, cập nhật thông tin
//            Model updatedModel = mcr.findByBookId(selectedBookId);
//            if (updatedModel != null) {
//                model.setBook_id(updatedModel.getBook_id());
//                model.setBook_name(updatedModel.getBook_name());
//                model.setDescription(updatedModel.getDescription());
//                model.setPrice(updatedModel.getPrice());
//                model.setImg(updatedModel.getImg());
//                model.setPub_id(updatedModel.getPub_id());
//                model.setCat_id(updatedModel.getCat_id());
//                updated = true;
//                System.out.println("Data update single line: " + updatedModel.getBook_id());
//                break; // Đã cập nhật xong, thoát khỏi vòng lặp
//            }
//        }
//    }
//
//}
    
    public void refreshData() {
    ModelRepository mcr = new ModelRepository();
    String selectedBookId = selectedBook_id;
    boolean updated = false;

    for (Model model : ls) {
        if (model.getBook_id() == selectedBookId) {
            // Tìm thấy dòng có selectedbook_id, cập nhật thông tin
            Model updatedModel = mcr.findByBookId(selectedBookId);
            if (updatedModel != null) {
                model.setBook_name(updatedModel.getBook_name());
                model.setDescription(updatedModel.getDescription());
                model.setPrice(updatedModel.getPrice());
                model.setImg(updatedModel.getImg());
                model.setPub_id(updatedModel.getPub_id());
                model.setCat_id(updatedModel.getCat_id());
                updated = true;
                System.out.println("Data update single line: " + updatedModel.getBook_id());
                int index = ls.indexOf(model);
                table.getItems().set(index, model);
                break; // Đã cập nhật xong, thoát khỏi vòng lặp
            }
        }
    }
}

    
    public void clearForm() {
    book_id1.setText("");
    book_name1.setText("");
    description1.setText("");
    img1.setText("");
    price1.setText("");
    pub_id1.setText("");
    cat_id1.setText("");
        
    }

    
public void addButton() {
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
        ModelRepository.addBook(conn, model);
        refreshData();
        clearForm();
        System.out.println("Data added to the database.");
    } catch (SQLException ex) {
        ex.printStackTrace();
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
        refreshData();
        clearForm();
        System.out.println("Data added to the database.");
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
       
}



public void editButtonAction() throws IOException {
//        App.setRoot("crud_edit2");
System.out.println("Edit button clicked in CrudController");
System.out.println("Sending Book ID "+ selectedBook_id);

FXMLLoader loader = new FXMLLoader(getClass().getResource("crud_edit2.fxml"));
    Parent root = loader.load();
    
    // Access the new controller instance
    Crud_edit2Controller editController = loader.getController();

    // Pass the selectedBook_id to the new controller
    editController.setSelectedBookId(selectedBook_id);

    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
    }

public void deleteButton(){
    
    
}

public void showDeleteConfirmation() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận xoá");
        alert.setHeaderText("Bạn có chắc chắn muốn xoá mục này?");
        alert.setContentText("Chọn 'OK' để xoá hoặc 'No' để hủy bỏ.");

        // Thêm nút Yes
        ButtonType buttonTypeYes = new ButtonType("Yes");
        // Thêm nút No
        ButtonType buttonTypeNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        // Hiển thị hộp thoại xác nhận và xử lý phản hồi
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            // Xử lý xoá item ở đây
            // Thêm mã xử lý để xoá mục hoặc thực hiện hành động "Yes" ở đây
            // Create an instance of ModelRepository
            ModelRepository modelRepository = new ModelRepository();
//    ModelRepository modelRepository = new ModelRepository();
            Connection conn = modelRepository.getConnection();

            // Call the addPerson method on the instance of ModelRepository
           if( ModelRepository.deleteBook(conn, selectedBook_id)){
                refreshDataAll();
//            clearForm();
           }
//           
        } else {
            // Người dùng đã chọn "No" hoặc đóng hộp thoại
            // Thêm mã xử lý cho trường hợp người dùng không muốn xoá ở đây
        }
    }

    public void openJRXML(ActionEvent event) {
    // Đường dẫn tới tệp JRXML
    String jrxmlPath = "src/main/java/vxt/dbconnect_sqlserver_crud/nhapkho.jrxml";

    // Gọi phương thức để mở tệp JRXML
     generateReport(jrxmlPath, ls);
}

public void generateReport(String jrxmlPath, ObservableList<Model> dataCollection) {
    try {
        // 1. Load the JRXML file
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlPath);

        // 2. Create a JRBeanCollectionDataSource with your data collection
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataCollection);

        // 3. Define parameters, if any
        Map<String, Object> parameters = new HashMap<>();

        // 4. Fill the report with data
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // 5. Export the report to your desired format (e.g., PDF)
//        JasperExportManager.exportReportToPdfFile(jasperPrint, "output.pdf");
        JasperViewer.viewReport(jasperPrint, false);
    } catch (JRException e) {
        e.printStackTrace();
    }
}

}
