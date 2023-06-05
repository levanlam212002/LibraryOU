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
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TaiKhoanController implements Initializable {

    @FXML private TextField txtUserName;
    @FXML private TextField txtPass;
    @FXML private TextField txtPassAgain;
    @FXML private Label lbId;
    @FXML private RadioButton radDocGia;
    @FXML private RadioButton radThuThu;
    @FXML private Label lbHienThi;
    private int id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbId.setVisible(false);
        lbHienThi.setVisible(false);
    }    
    public void btnQuayLai(ActionEvent event) throws IOException{
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("QLDG.fxml"));
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quản lý độc giả");
        stage.show();
        Button btn = (Button) event.getSource();
        Stage stagelogin = (Stage) btn.getScene().getWindow();
        stagelogin.close();
    }
    public void setId(int i){
        id = i;
    }
    public void radDocGia(ActionEvent event){
        if(radDocGia.isSelected()){
            if(id != 0){
                lbId.setVisible(true);
                lbHienThi.setVisible(true);
                lbId.setText(String.valueOf(id));
            }else{
                MessageBox.getBox("User", "Bạn phải chọn độc giả trước khi tạo tài khoản độc giả!", Alert.AlertType.WARNING).show();
            }
        }
        else{
            lbId.setVisible(false);
            lbHienThi.setVisible(false);
        }
    }
    public void radThuThu(ActionEvent event){
        if(radThuThu.isSelected()){
            lbId.setVisible(false);
            lbHienThi.setVisible(false);
        }
        else{
            lbId.setVisible(false);
            lbHienThi.setVisible(false);
        }
    }
    public void btnTaoTaiKhoan(ActionEvent event) throws SQLException{
        UserService u = new UserService();
        List<User> us=  u.getUsers();
        if(this.txtUserName.getText().trim().isEmpty() || this.txtPass.getText().trim().isEmpty() || this.txtPassAgain.getText().trim().isEmpty()){
            MessageBox.getBox("User", "Vui lòng nhập đầy đủ thông tin!", Alert.AlertType.ERROR).show();
        }else
        if(radDocGia.isSelected()){
            if(u.KiemTraDGCoTaiKhoanChua(us, id)){
                if(u.KiemTraUser(u.getUsers(), txtUserName.getText()) != 0)
                    MessageBox.getBox("User", "Tên đăng nhập đã tồn tại", Alert.AlertType.ERROR).show();
                else 
                if(txtPassAgain.getText().equals(txtPass.getText())){
                    User user = new User(txtUserName.getText(),txtPass.getText(),"Độc giả",id);
                    u.addUser(user);
                    this.reset();
                    MessageBox.getBox("User", "Tạo tài khoản thành công", Alert.AlertType.INFORMATION).show();
                }
                else
                   MessageBox.getBox("User", "Xác nhận mật khẩu không khớp", Alert.AlertType.ERROR).show();
            }
            else{
               MessageBox.getBox("User", "Độc giả này đã có tài khoản", Alert.AlertType.INFORMATION).show();
               this.reset();
            }
        } else if(radThuThu.isSelected()){
            if(u.KiemTraUser(u.getUsers(), txtUserName.getText()) != 0)
                    MessageBox.getBox("User", "Tên đăng nhập đã tồn tại", Alert.AlertType.ERROR).show();
            else 
                if(txtPassAgain.getText().equals(txtPass.getText())){
                    User user = new User(txtUserName.getText(),txtPass.getText(),"Thủ thư");
                    u.addAdmin(user);
                    this.reset();
                    MessageBox.getBox("User", "Tạo tài khoản thành công", Alert.AlertType.INFORMATION).show();
                }
                else
                   MessageBox.getBox("User", "Xác nhận mật khẩu không khớp", Alert.AlertType.ERROR).show();
        }
        else{
            MessageBox.getBox("User", "Vui lòng chọn loại tài khoản!", Alert.AlertType.ERROR).show();
        }
        
    }
    public void reset(){
        this.radDocGia.setSelected(false);
        this.radThuThu.setSelected(false);
        this.txtPass.clear();
        this.txtPassAgain.clear();
        this.txtUserName.clear();
    }
}
