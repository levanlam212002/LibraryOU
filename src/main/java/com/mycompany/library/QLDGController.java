/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.library;

import com.mycompany.pojo.Muon;
import com.mycompany.pojo.Object;
import com.mycompany.pojo.Reader_card;
import com.mycompany.pojo.Readers;
import com.mycompany.service.BookService;
import com.mycompany.service.MuonService;
import com.mycompany.service.ObjectService;
import com.mycompany.service.ReaderCardService;
import com.mycompany.service.ReaderService;
import com.mycompany.service.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.RadioButton;
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
public class QLDGController implements Initializable {

    @FXML
    private ComboBox<Object> cbbObject;
    @FXML
    private TableView<Readers> tbDG;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtPart;
    @FXML
    private TextField txtPhone;
    @FXML
    private RadioButton radNam;
    @FXML
    private RadioButton radNu;
    @FXML
    private DatePicker dpBirthday;
    @FXML
    private DatePicker dpStart;
    @FXML
    private DatePicker dpEnd;
    @FXML
    private TextField txtObject;
    private int id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObjectService o = new ObjectService();
        loadTableView();
        try {
            List<Object> obj = o.getObjects();
            this.cbbObject.setItems(FXCollections.observableList(obj));
            this.loadTableData(0);
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadTableView() {

        TableColumn colId = new TableColumn("Mã");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(50);

        TableColumn colName = new TableColumn("Họ và tên");
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colName.setPrefWidth(150);

        TableColumn colSex = new TableColumn("Giới tính");
        colSex.setCellValueFactory(new PropertyValueFactory("sex"));
        colSex.setPrefWidth(100);

        TableColumn colBirthday = new TableColumn("Ngày sinh");
        colBirthday.setCellValueFactory(new PropertyValueFactory("birthday"));
        colBirthday.setPrefWidth(100);

        TableColumn colStart_date = new TableColumn("Ngày mở thẻ");
        colStart_date.setCellValueFactory(new PropertyValueFactory("start_date"));
        colStart_date.setPrefWidth(100);

        TableColumn colEnd_date = new TableColumn("Ngày hết hạn");
        colEnd_date.setCellValueFactory(new PropertyValueFactory("end_date"));
        colEnd_date.setPrefWidth(100);

        TableColumn colPart = new TableColumn("Đơn vị");
        colPart.setCellValueFactory(new PropertyValueFactory("part"));
        colPart.setPrefWidth(100);

        TableColumn colEmail = new TableColumn("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colEmail.setPrefWidth(100);

        TableColumn colAddress = new TableColumn("Địa chỉ");
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colAddress.setPrefWidth(100);

        TableColumn colPhone = new TableColumn("SĐT");
        colPhone.setCellValueFactory(new PropertyValueFactory("phone"));
        colPhone.setPrefWidth(100);

        TableColumn colObjectId = new TableColumn("Mã đối tượng");
        colObjectId.setCellValueFactory(new PropertyValueFactory("object_id"));
        colObjectId.setPrefWidth(50);

        this.tbDG.getColumns().addAll(colId, colName, colSex, colBirthday, colStart_date, colEnd_date, colPart, colEmail, colAddress, colPhone, colObjectId);

    }

    private void loadTableData(int kw) throws SQLException {
        ReaderService s = new ReaderService();
        this.tbDG.setItems(FXCollections.observableList(s.getReaders(kw)));
    }

    public void btnThem(ActionEvent event) throws SQLException {
        ReaderService s = new ReaderService();
        BookService b = new BookService();
        String sex = "";
        try {
            if (this.radNam.isSelected()) {
                sex = "Nam";
            } else if (this.radNu.isSelected()) {
                sex = "Nữ";
            } else {
                throw new NullPointerException();
            }
            if (this.txtName.getText().isEmpty() || this.txtAddress.getText().isEmpty() || this.txtPart.getText().isEmpty()) {
                throw new NullPointerException();
            } else if (b.KiemTraNgayTuongLai(Date.valueOf(this.dpBirthday.getValue()))) {
                MessageBox.getBox("Reader", "Bạn đã nhập sai ngày sinh, hãy kiểm tra lại!", Alert.AlertType.ERROR).show();
            } else if (b.KiemTraNgayQuaKhu(Date.valueOf(this.dpStart.getValue()))) {
                MessageBox.getBox("Reader", "Ngày mở thẻ không thể là quá khứ!", Alert.AlertType.ERROR).show();
            } else if (b.KiemTraNgayQuaKhu(Date.valueOf(this.dpEnd.getValue()))) {
                MessageBox.getBox("Reader", "Ngày hết hạn không thể là quá khứ!", Alert.AlertType.ERROR).show();
            } else {
                Readers r = new Readers(this.txtName.getText(), sex, Date.valueOf(this.dpBirthday.getValue()), Date.valueOf(this.dpStart.getValue()), Date.valueOf(this.dpEnd.getValue()), this.txtPart.getText(), this.txtEmail.getText(), this.txtAddress.getText(), this.txtPhone.getText(), this.cbbObject.getSelectionModel().getSelectedItem().getId());
                if (s.kiemTraSDT(txtPhone.getText()) == false) {
                    MessageBox.getBox("Reader", "Bạn nhập sai số điện thoại. Hãy kiểm tra lại.", Alert.AlertType.ERROR).show();
                } else if (s.KiemTraDocGiaTrungSDT(s.getReaders(0), txtPhone.getText(), this.txtName.getText(), sex)) {
                    if (s.kiemTraEmail(txtEmail.getText())) {
                        if(s.KiemTraDocGiaTrungEmail(s.getReaders(0), txtEmail.getText(), this.txtName.getText(), sex)){
                            s.addReader(r);
                            this.reset();
                            MessageBox.getBox("Reader", "Thêm thành công", Alert.AlertType.INFORMATION).show();
                        }else
                            MessageBox.getBox("Reader", "Email đã được dùng nếu muốn tạo thẻ mới thì thẻ cũ phải hết hạn và bạn cần nhập chính xác thông tin!", Alert.AlertType.ERROR).show();
                    } else {
                        MessageBox.getBox("Reader", "Vui lòng sử dụng mail trường cấp.", Alert.AlertType.ERROR).show();
                    }
                }else {
                    MessageBox.getBox("Reader", "Số điện thoại đã được dùng nếu muốn tạo thẻ mới thì thẻ cũ phải hết hạn và bạn cần nhập chính xác thông tin!", Alert.AlertType.ERROR).show();
                }
            }

        } catch (NullPointerException ex) {
            MessageBox.getBox("Reader", "Bạn vui lòng nhập đầy đủ thông tin", Alert.AlertType.INFORMATION).show();
        }
    }

    public void btnXoa(ActionEvent event) throws SQLException {
        ReaderService s = new ReaderService();
        ReaderCardService rd = new ReaderCardService();
        UserService u = new UserService();
        MuonService muon = new MuonService();
        try{
        int id = tbDG.getSelectionModel().getSelectedItem().getId();

        Alert tb = MessageBox.getBox("Thông báo", "Ban chac chan muon xoa khong?", Alert.AlertType.CONFIRMATION);

        tb.showAndWait().ifPresent(res -> {
            if (res == ButtonType.OK) {
                try {
                    List<Reader_card> listRC = rd.getByReaderID(id);
                    for (Reader_card i : listRC) {
                        List<Muon> listM = muon.getByCardId(i.getId());
                        for (Muon y : listM) {
                            muon.deleteMuon(y.getId());
                        }
                    }
                    for (Reader_card x : listRC) {
                        rd.deleteCard(x.getId());
                    }
                    u.deleteUser(id);
                    s.deleteReader(id);
                    MessageBox.getBox("Thông báo", "Xóa thành công", Alert.AlertType.CONFIRMATION).show();
                    reset();
                } catch (SQLException | NullPointerException ex) {
                    MessageBox.getBox("Thông báo", "Lôi!!", Alert.AlertType.ERROR).show();
                    Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        }catch(NullPointerException ex){
             MessageBox.getBox("Thông báo", "Vui lòng chọn độc giả muốn xóa!", Alert.AlertType.ERROR).show();
        }
    }

    public void mouseClicked(MouseEvent event) {
        Readers b = tbDG.getSelectionModel().getSelectedItem();
        this.txtName.setText(b.getName());
        if ("Nam".equals(b.getSex())) {
            this.radNam.setSelected(true);
        } else {
            this.radNu.setSelected(true);
        }
        this.dpBirthday.setValue(LocalDate.parse(String.valueOf(b.getBirthday())));
        this.dpStart.setValue(LocalDate.parse(String.valueOf(b.getStart_date())));
        this.dpEnd.setValue(LocalDate.parse(String.valueOf(b.getEnd_date())));
        this.txtPart.setText(b.getPart());
        this.txtEmail.setText(b.getEmail());
        this.txtAddress.setText(b.getAddress());
        this.txtPhone.setText(b.getPhone());
        this.cbbObject.getSelectionModel().select(b.getObject_id() - 1);
        id = b.getId();
    }

    public void btnSua(ActionEvent event) throws SQLException {
        ReaderService s = new ReaderService();
        String sex = "";
        if (this.radNam.isSelected()) {
            sex = "Nam";
        } else if (this.radNu.isSelected()) {
            sex = "Nữ";
        }
        try {
            Readers r = new Readers(id, this.txtName.getText(), sex, Date.valueOf(this.dpBirthday.getValue()), Date.valueOf(this.dpStart.getValue()), Date.valueOf(this.dpEnd.getValue()), this.txtPart.getText(), this.txtEmail.getText(), this.txtAddress.getText(), this.txtPhone.getText(), this.cbbObject.getSelectionModel().getSelectedItem().getId());
            if (s.kiemTraSDT(txtPhone.getText())) {
                if (s.kiemTraEmail(txtEmail.getText())) {
                    s.updateReader(r);
                    this.reset();
                    MessageBox.getBox("Reader", "Sửa thành công", Alert.AlertType.INFORMATION).show();
                } else {
                    MessageBox.getBox("Reader", "Vui lòng sử dụng mail trường cấp.", Alert.AlertType.ERROR).show();
                }
            } else {
                MessageBox.getBox("Reader", "Bạn nhập sai số điện thoại. Hãy kiểm tra lại.", Alert.AlertType.ERROR).show();
            }
        } catch (SQLException | NullPointerException ex) {
            MessageBox.getBox("Reader", "Vui lòng chọn đối tượng cần sửa.", Alert.AlertType.ERROR).show();
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

    public void btnThemDoiTuong(ActionEvent event) throws SQLException {
        ObjectService object = new ObjectService();
        Object ob = new Object(this.txtObject.getText().trim().replaceAll("\\s+", " "));
        if (this.txtObject.getText().trim().isEmpty()) {
            MessageBox.getBox("Object", "Vui lòng nhập đối tượng cần thêm!", Alert.AlertType.ERROR).show();
        } else {
            if (object.addObject(ob)) {
                this.txtObject.clear();
                MessageBox.getBox("Object", "Thêm thành công", Alert.AlertType.INFORMATION).show();
            } else {
                MessageBox.getBox("Object", "Đối tượng đã tồn tại!", Alert.AlertType.ERROR).show();
            }
            ObjectService o = new ObjectService();
            try {
                List<Object> obj = o.getObjects();
                this.cbbObject.setItems(FXCollections.observableList(obj));
            } catch (SQLException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void btnThemTaiKhoan(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("TaiKhoan.fxml"));
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        TaiKhoanController s = fxmloader.getController();
        s.setId(this.id);
        stage.setTitle("Thêm tài khoản cho độc giả");
        stage.show();
        Button btn = (Button) event.getSource();
        Stage stagelogin = (Stage) btn.getScene().getWindow();
        stagelogin.close();
    }

    public void reset() throws SQLException {
        this.cbbObject.setValue(null);
        this.dpBirthday.getEditor().clear();
        this.dpEnd.getEditor().clear();
        this.dpStart.getEditor().clear();
        this.radNam.setSelected(false);
        this.radNu.setSelected(false);
        this.txtAddress.clear();
        this.txtEmail.clear();
        this.txtName.clear();
        this.txtPart.clear();
        this.txtPhone.clear();
        this.loadTableData(0);
    }
}
