/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.library;

import com.mycompany.pojo.Reader_card;
import com.mycompany.service.ReaderCardService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ThongKeController implements Initializable {

    @FXML
    private TableView<Reader_card> tbMuon;
    @FXML
    private TableView<Reader_card> tbTra;
    @FXML
    private ComboBox<String> cbbQuy;
    @FXML
    private TextField txtNam;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableView();
        this.cbbQuy.setItems(FXCollections.observableArrayList("Quý 1 (Tháng 1-2-3)", "Quý 2 (Tháng 4-5-6)", "Quý 3 (Tháng 7-8-9)", "Quý 4 (Tháng 10-11-12)"));
//            loadTableDataBorrowed();
//            loadTableDataPay();
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

    private void loadTableView() {

        TableColumn colId = new TableColumn("Mã");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(50);

        TableColumn colBorrowed = new TableColumn("Ngày mượn");
        colBorrowed.setCellValueFactory(new PropertyValueFactory("borrowed_date"));
        colBorrowed.setPrefWidth(150);

        TableColumn colPayDate = new TableColumn("Ngày trả");
        colPayDate.setCellValueFactory(new PropertyValueFactory("pay_date"));
        colPayDate.setPrefWidth(150);

        TableColumn colAppointment = new TableColumn("Ngày hẹn trả");
        colAppointment.setCellValueFactory(new PropertyValueFactory("appointment_date"));
        colAppointment.setPrefWidth(150);

        TableColumn colReaderId = new TableColumn("Mã độc giả");
        colReaderId.setCellValueFactory(new PropertyValueFactory("reader_id"));
        colReaderId.setPrefWidth(60);

        TableColumn colBookingDate = new TableColumn("Ngày đặt sách");
        colBookingDate.setCellValueFactory(new PropertyValueFactory("booking_date"));
        colBookingDate.setPrefWidth(150);

        this.tbMuon.getColumns().addAll(colId, colBorrowed, colAppointment, colReaderId);
        this.tbTra.getColumns().addAll(colId, colPayDate, colAppointment, colReaderId);
    }

    private void loadTableDataBorrowed(int year, int quy) throws SQLException {
        ReaderCardService s = new ReaderCardService();
        this.tbMuon.setItems(FXCollections.observableList(s.getCardByBorrowed(year, quy)));
    }

    private void loadTableDataPay(int year, int quy) throws SQLException {
        ReaderCardService s = new ReaderCardService();
        this.tbTra.setItems(FXCollections.observableList(s.getCardByPay(year, quy)));
    }

    public void btnXemTK(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("ThongKeThem.fxml"));
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Thống kê");
        stage.show();
        Button btn = (Button) event.getSource();
        Stage stagelogin = (Stage) btn.getScene().getWindow();
        stagelogin.close();
    }

    public void btnLapBaoCao(ActionEvent event) throws SQLException {
        try {
            loadTableDataBorrowed(Integer.parseInt(this.txtNam.getText()), this.cbbQuy.getSelectionModel().getSelectedIndex());
            loadTableDataPay(Integer.parseInt(this.txtNam.getText()), this.cbbQuy.getSelectionModel().getSelectedIndex());
        } catch (NumberFormatException ex) {
            MessageBox.getBox("Thống Kê", "Vui lòng nhập thông tin và nhập đúng định dạng năm!", Alert.AlertType.INFORMATION).show();
        }
    }
}
