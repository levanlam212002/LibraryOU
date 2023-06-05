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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ThongKeThemController implements Initializable {
    @FXML private TableView<Reader_card> tbDatOnline;
    @FXML private TableView<Reader_card> tbTraTre;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableView();
        try {
            loadTableDataPayLate();
            loadTableDataBooking();
        } catch (SQLException ex) {
            Logger.getLogger(ThongKeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void btnQuayLai(ActionEvent event) throws IOException{
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("ThongKe.fxml"));
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Thống Kê");
        stage.show();
        Button btn = (Button) event.getSource();
        Stage stagelogin = (Stage) btn.getScene().getWindow();
        stagelogin.close();
    }
    private void loadTableView(){
        
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
        
        this.tbTraTre.getColumns().addAll(colId,colBorrowed,colPayDate,colReaderId);
        this.tbDatOnline.getColumns().addAll(colId,colBookingDate,colBorrowed,colReaderId);
    }
   
    private void loadTableDataPayLate() throws SQLException{
        ReaderCardService s = new ReaderCardService();
        this.tbTraTre.setItems(FXCollections.observableList(s.getCardByPayLate()));
    }
    private void loadTableDataBooking() throws SQLException{
        ReaderCardService s = new ReaderCardService();
        this.tbDatOnline.setItems(FXCollections.observableList(s.getCardBooking()));
    }

}
