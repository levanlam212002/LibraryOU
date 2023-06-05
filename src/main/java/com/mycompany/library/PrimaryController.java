package com.mycompany.library;

import com.mycompany.pojo.User;
import com.mycompany.service.UserService;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class PrimaryController {
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label lbprint;
    private int Reader_id;
    @FXML
    private void switchToNhanVien(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("NhanVien.fxml"));
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Tra cứu Sách");
        stage.show();
        Button btn = (Button) event.getSource();
        Stage stagelogin = (Stage) btn.getScene().getWindow();
        stagelogin.close();
    }
    @FXML
    private void switchToReader(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("search.fxml"));
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        SearchController s = fxmloader.getController();
        s.setId(Reader_id);
        stage.setTitle("Tra cứu Sách");
        stage.show();
        Button btn = (Button) event.getSource();
        Stage stagelogin = (Stage) btn.getScene().getWindow();
        stagelogin.close();
    }
    @FXML
    public void Login(ActionEvent event) throws IOException, NoSuchAlgorithmException, SQLException{
        UserService u = new UserService();
        List<User> us = u.getUsers();
        if(this.username.getText().trim().isEmpty()){
            lbprint.setTextFill(Color.RED);
            lbprint.setText("Vui lòng nhập tên đăng nhập");
        }else
        if(this.password.getText().trim().isEmpty()){
            lbprint.setTextFill(Color.RED);
            lbprint.setText("Vui lòng nhập mật khẩu");
        }else{
        int id = u.KiemTraUser(us, this.username.getText().trim());
        if (id == 0){
            lbprint.setTextFill(Color.RED);
            lbprint.setText("Tên đăng nhập không tồn tại");
            this.username.clear();
            this.password.clear();
        }else if(u.kiemTraMatKhau(u.getUser(id).get(0), this.password.getText().trim()))
            if(u.kiemTraQuyenAdmin(u.getUser(id).get(0)))
                switchToNhanVien(event);
            else{
                Reader_id = u.GetReaderId(us, id);
                switchToReader(event);
            }
        else{
            lbprint.setTextFill(Color.RED);
            lbprint.setText("Mật khẩu không đúng");
            this.password.clear();
        }
        }
        
    }
}