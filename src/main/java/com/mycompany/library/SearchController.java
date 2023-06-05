/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.library;

import com.mycompany.pojo.Book;
import com.mycompany.service.BookService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SearchController implements Initializable {
    @FXML private TableView<Book> tbBooks;
    @FXML private TextField txtSearch;
    @FXML private RadioButton radTen;
    @FXML private RadioButton radTacGia;
    @FXML private RadioButton radNamXB;
    @FXML private RadioButton radDanhMuc;
    private int id;
    @FXML
    private ToggleGroup search;

    public void setId(int id){
        this.id = id;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadTableView();
        try {
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtSearch.textProperty().addListener((Observable evt) -> {
            try {
                if(this.radTen.isSelected())
                    this.loadTableData(this.txtSearch.getText().trim().replaceAll("\\s+"," "));
                else if(this.radTacGia.isSelected())
                    this.loadTableDataAuthor(this.txtSearch.getText().trim().replaceAll("\\s+"," "));
                else if(this.radNamXB.isSelected())
                try{
                    this.loadTableDataDate(this.txtSearch.getText().trim().replaceAll("\\s+",""));
                }catch(NumberFormatException ex){
                    MessageBox.getBox("Search", "Bạn vui lòng nhập đúng định dạng năm để tìm kiếm!", Alert.AlertType.ERROR).show();
                }
                else if (this.radDanhMuc.isSelected())
                    this.loadTableDataCate(this.txtSearch.getText().trim().replaceAll("\\s+"," "));
                else
                    this.loadTableData(this.txtSearch.getText().trim().replaceAll("\\s+"," "));
            } catch (SQLException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    @FXML
    public void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    public void switchToSearch() throws IOException {
        App.setRoot("search");
    }
    @FXML
    public void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    private void loadTableView(){
        
        TableColumn colId = new TableColumn("Mã");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(30);
        
        TableColumn colName = new TableColumn("Tên sách");
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colName.setPrefWidth(100);
        
        TableColumn colAuthor = new TableColumn("Tác giả");
        colAuthor.setCellValueFactory(new PropertyValueFactory("author"));
        colAuthor.setPrefWidth(100);
        
        TableColumn colDescribe = new TableColumn("Mô tả");
        colDescribe.setCellValueFactory(new PropertyValueFactory("describe"));
        colDescribe.setPrefWidth(100);
        
        TableColumn colPublication_date = new TableColumn("Ngày xuất bản");
        colPublication_date.setCellValueFactory(new PropertyValueFactory("publication_date"));
        colPublication_date.setPrefWidth(100);
        
        TableColumn colPublication_place = new TableColumn("Nơi xuất bản");
        colPublication_place.setCellValueFactory(new PropertyValueFactory("publication_place"));
        colPublication_place.setPrefWidth(100);
        
         TableColumn colAdded_date = new TableColumn("Ngày nhập");
        colAdded_date.setCellValueFactory(new PropertyValueFactory("added_date"));
        colAdded_date.setPrefWidth(100);
        
         TableColumn colLocation = new TableColumn("Vị trí");
        colLocation.setCellValueFactory(new PropertyValueFactory("location"));
        colLocation.setPrefWidth(100);
        
        TableColumn colCategory = new TableColumn("Danh mục");
        colCategory.setCellValueFactory(new PropertyValueFactory("cate"));
        colCategory.setPrefWidth(100);
        
        this.tbBooks.getColumns().addAll(colId,colName,colAuthor,colDescribe,colPublication_date,colPublication_place,colAdded_date,colLocation,colCategory);
    }
    private void loadTableData(String kw) throws SQLException{
        BookService s = new BookService();
        this.tbBooks.setItems(FXCollections.observableList(s.getBooks(kw)));
    }
    private void loadTableDataAuthor(String kw) throws SQLException{
        BookService s = new BookService();
        this.tbBooks.setItems(FXCollections.observableList(s.getBooksByAuthor(kw)));
    }
    private void loadTableDataCate(String kw) throws SQLException{
        BookService s = new BookService();
        this.tbBooks.setItems(FXCollections.observableList(s.getBooksByCategory(kw)));
    }
    private void loadTableDataDate(String kw) throws SQLException{
        BookService s = new BookService();
        this.tbBooks.setItems(FXCollections.observableList(s.getBooksByDate(kw)));
    }
    public void btnDatSachOn(ActionEvent event) throws IOException{
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("DatSach.fxml"));
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        DatSachController s = fxmloader.getController();
        s.setID(this.id);
        stage.setTitle("Đặt sách online");
        stage.show();
        Button btn = (Button) event.getSource();
        Stage stagelogin = (Stage) btn.getScene().getWindow();
        stagelogin.close();
    }
    public void btnDangXuat(ActionEvent event) throws IOException{
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("primary.fxml"));
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Đăng nhập");
        stage.show();
        Button btn = (Button) event.getSource();
        Stage stagelogin = (Stage) btn.getScene().getWindow();
        stagelogin.close();
    }
    public void btnTaiLai(ActionEvent event) throws SQLException{
        loadTableData(null);
        this.txtSearch.clear();
        this.radTen.setSelected(false);
        this.radDanhMuc.setSelected(false);
        this.radNamXB.setSelected(false);
        this.radTacGia.setSelected(false);
    }
    public void btnSuaTaiKhoan(ActionEvent event) throws IOException{
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("SuaTaiKhoan.fxml"));
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        SuaTaiKhoanController s = fxmloader.getController();
        s.setId(id);
        stage.setTitle("Sửa tài khoản");
        stage.show();
        Button btn = (Button) event.getSource();
        Stage stagelogin = (Stage) btn.getScene().getWindow();
        stagelogin.close();
    }
}
