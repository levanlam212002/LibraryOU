/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.library;

import com.mycompany.pojo.User;
import com.mycompany.service.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SuaTaiKhoanController implements Initializable {
    @FXML private PasswordField txtpass;
    @FXML private PasswordField txtpassAgain;
    @FXML private Label lbId;
    private int id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
     public void setId(int i){
        lbId.setText(String.valueOf(i));
        id = i;
    }
    public void btnQuayLai(ActionEvent event) throws IOException{
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("search.fxml"));
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        SearchController s = fxmloader.getController();
        s.setId(id);
        stage.setTitle("Tra cứu sách");
        stage.show();
        Button btn = (Button) event.getSource();
        Stage stagelogin = (Stage) btn.getScene().getWindow();
        stagelogin.close();
    }
    public void btnThayDoiTaiKhoan(ActionEvent event) throws SQLException{
        UserService u = new UserService();
         if(txtpassAgain.getText().trim().isEmpty() || txtpass.getText().trim().isEmpty()){
             MessageBox.getBox("User", "Vui lòng nhập đầy đủ thông tin!", Alert.AlertType.ERROR).show();
        }else
        if(txtpassAgain.getText().equals(txtpass.getText())){
                User user = new User(txtpass.getText(),id);
                u.updateUser(user);
                this.reset();
                MessageBox.getBox("User", "Thay đổi tài khoản thành công", Alert.AlertType.INFORMATION).show();
            }
        else
           MessageBox.getBox("User", "Xác nhận mật khẩu không khớp", Alert.AlertType.ERROR).show();
    }
    public void reset(){
        this.txtpass.clear();
        this.txtpassAgain.clear();
    }
}
