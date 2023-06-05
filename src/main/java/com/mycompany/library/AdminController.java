/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.library;

import com.mycompany.pojo.Book;
import com.mycompany.pojo.Category;
import com.mycompany.pojo.Muon;
import com.mycompany.service.BookService;
import com.mycompany.service.CategoryService;
import com.mycompany.service.MuonService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AdminController implements Initializable {

    @FXML
    private TableView<Book> tbSach;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAuthor;
    @FXML
    private TextField txtDescribe;
    @FXML
    private DatePicker dpPublicationDate;
    @FXML
    private TextField txtPublicationPlace;
    @FXML
    private TextField txtLocation;
    @FXML
    private ComboBox<Category> cbbCategoryId;
    @FXML
    private TextField txtCategory;
    private int id;
    private Date add;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategoryService s = new CategoryService();
        this.loadTableView();
        try {
            List<Category> cates = s.getCategorys();
            this.cbbCategoryId.setItems(FXCollections.observableList(cates));
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTableView() {

        TableColumn colId = new TableColumn("Mã");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(50);

        TableColumn colName = new TableColumn("Tên sách");
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colName.setPrefWidth(150);

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

        TableColumn colAdded_date = new TableColumn("Ngày nhập vào");
        colAdded_date.setCellValueFactory(new PropertyValueFactory("added_date"));
        colAdded_date.setPrefWidth(100);

        TableColumn colLocation = new TableColumn("Vị trí");
        colLocation.setCellValueFactory(new PropertyValueFactory("location"));
        colLocation.setPrefWidth(100);

        TableColumn colCategory = new TableColumn("Danh mục");
        colCategory.setCellValueFactory(new PropertyValueFactory("cate"));
        colCategory.setPrefWidth(100);

        this.tbSach.getColumns().addAll(colId, colName, colAuthor, colDescribe, colPublication_date, colPublication_place, colAdded_date, colLocation, colCategory);

    }

    private void loadTableData(String kw) throws SQLException {
        BookService s = new BookService();
        this.tbSach.setItems(FXCollections.observableList(s.getBooks(kw)));
    }

    public void btnThem(ActionEvent event) throws SQLException {
        BookService s = new BookService();
        try {
            if (this.txtName.getText().isEmpty() || this.txtAuthor.getText().isEmpty() || this.txtDescribe.getText().isEmpty() || this.txtPublicationPlace.getText().isEmpty() || this.txtLocation.getText().isEmpty()) {
                MessageBox.getBox("Book", "Vui lòng nhập đầy đủ thông tin!", Alert.AlertType.ERROR).show();
            } else if (s.KiemTraNgayTuongLai(Date.valueOf(this.dpPublicationDate.getValue()))) {
                MessageBox.getBox("Book", "Không thể nhập ngày xuất bản là tương lai!", Alert.AlertType.ERROR).show();
            } else {
                Book b = new Book(this.txtName.getText(), this.txtAuthor.getText(), this.txtDescribe.getText(), Date.valueOf(this.dpPublicationDate.getValue()), this.txtPublicationPlace.getText(), Date.valueOf(java.time.LocalDate.now()), this.txtLocation.getText(), this.cbbCategoryId.getSelectionModel().getSelectedItem().getId(), 0);
                s.addBook(b);
                this.reset();
                MessageBox.getBox("Book", "Thêm sách thành công", Alert.AlertType.INFORMATION).show();
            }
        } catch (SQLException | NullPointerException ex) {
            MessageBox.getBox("Book", "Vui lòng nhập đầy đủ thông tin!", Alert.AlertType.ERROR).show();
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnXoa(ActionEvent event) throws SQLException {
        BookService b = new BookService();
        MuonService m = new MuonService();

        int id1 = tbSach.getSelectionModel().getSelectedItem().getId();
        List<Muon> muon = m.getByBookId(id1);

        Alert tb = MessageBox.getBox("Thông báo", "Ban chac chan muon xoa khong?", Alert.AlertType.CONFIRMATION);
        tb.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
                try {
                    for (Muon x : muon) {
                        m.deleteMuon(x.getId());
                    }
                    b.deleteBook(id1);
                    MessageBox.getBox("Thông báo", "Xóa thành công", Alert.AlertType.CONFIRMATION).show();
                    reset();
                } catch (SQLException | NullPointerException ex) {
                    MessageBox.getBox("Thông báo", "Lôi!!", Alert.AlertType.ERROR).show();
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void mouseClicked(MouseEvent event) {
        Book b = tbSach.getSelectionModel().getSelectedItem();
        this.txtName.setText(b.getName());
        this.txtAuthor.setText(b.getAuthor());
        this.txtDescribe.setText(b.getDescribe());
        this.dpPublicationDate.setValue(LocalDate.parse(String.valueOf(b.getPublication_date())));
        this.txtPublicationPlace.setText(b.getPublication_place());
        this.txtLocation.setText(b.getLocation());
        this.cbbCategoryId.getSelectionModel().select(b.getCategory_id() - 1);
        id = b.getId();
        add = b.getAdded_date();
    }

    public void btnSua(ActionEvent event) throws SQLException {
        BookService s = new BookService();
        try {
            if (this.txtName.getText().isEmpty() || this.txtAuthor.getText().isEmpty() || this.txtDescribe.getText().isEmpty() || this.txtPublicationPlace.getText().isEmpty() || this.txtLocation.getText().isEmpty()) {
                MessageBox.getBox("Book", "Vui lòng nhập đầy đủ thông tin!", Alert.AlertType.ERROR).show();
            } else if (s.KiemTraNgayTuongLai(Date.valueOf(this.dpPublicationDate.getValue()))) {
                MessageBox.getBox("Book", "Không thể nhập ngày xuất bản là tương lai!", Alert.AlertType.ERROR).show();
            } else {
                Book b = new Book(id, this.txtName.getText(), this.txtAuthor.getText(), this.txtDescribe.getText(), Date.valueOf(this.dpPublicationDate.getValue()), this.txtPublicationPlace.getText(), add, this.txtLocation.getText(), this.cbbCategoryId.getSelectionModel().getSelectedItem().getId(), 0);
                s.updateBook(b);
                this.reset();
                MessageBox.getBox("Book", "Sửa sách thành công", Alert.AlertType.INFORMATION).show();
            }
        } catch (SQLException | NullPointerException ex) {
            MessageBox.getBox("Book", "Vui lòng chọn sách cần sửa hoặc nhập ngày xuất bản!", Alert.AlertType.ERROR).show();
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnQuayLai(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("NhanVien.fxml"));
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Chức năng của nhân viên thư viện");
        stage.show();
        Button btn = (Button) event.getSource();
        Stage stagelogin = (Stage) btn.getScene().getWindow();
        stagelogin.close();
    }

    public void btnThemDanhMuc(ActionEvent event) throws SQLException {
        CategoryService c = new CategoryService();
        Category cate = new Category(this.txtCategory.getText().trim().replaceAll("\\s+", " "));
        if (cate.getName().isEmpty()) {
            MessageBox.getBox("Category", "Vui lòng nhập danh mục muốn thêm!", Alert.AlertType.ERROR).show();
        } else if (c.addCategory(cate)) {
            MessageBox.getBox("Category", "Thêm thành công", Alert.AlertType.INFORMATION).show();
        } else {
            MessageBox.getBox("Category", "Danh mục đã tồn tại!", Alert.AlertType.ERROR).show();
        }
        try {
            List<Category> cates = c.getCategorys();
            this.cbbCategoryId.setItems(FXCollections.observableList(cates));
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reset() throws SQLException {
        this.txtName.clear();
        this.txtAuthor.clear();
        this.txtDescribe.clear();
        this.txtLocation.clear();
        this.txtPublicationPlace.clear();
        this.dpPublicationDate.getEditor().clear();
        this.cbbCategoryId.setValue(null);
        this.loadTableData(null);
    }
}
