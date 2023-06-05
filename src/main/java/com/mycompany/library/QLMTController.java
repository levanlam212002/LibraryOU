/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.library;

import com.mycompany.pojo.Book;
import com.mycompany.pojo.Muon;
import com.mycompany.pojo.Reader_card;
import com.mycompany.pojo.Readers;
import com.mycompany.service.BookService;
import com.mycompany.service.MuonService;
import com.mycompany.service.ReaderCardService;
import com.mycompany.service.ReaderService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.events.EventException;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class QLMTController implements Initializable {

    @FXML
    private TableView<Reader_card> tbCard;
    @FXML
    private ListView<Book> rlvBook;
    @FXML
    private ListView<Book> llvBook;
    @FXML
    private TextField txtReader;
    @FXML
    private DatePicker dpPay;
    @FXML
    private DatePicker dpApp;
    @FXML
    private RadioButton radDaNhan;
    @FXML
    private RadioButton radDaTra;
    @FXML
    private RadioButton radChuaNhan;
    @FXML
    private RadioButton radChuaTra;
    private String id;
    private Date borrowedDate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BookService s = new BookService();
        MuonService m = new MuonService();
        ReaderCardService r = new ReaderCardService();
        loadTableView();
        try {
            loadTableData(0);
            List<Book> cates = s.getBooksNotBorrowed();
            this.llvBook.setItems(FXCollections.observableList(cates));
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnRight(ActionEvent event) {
        if (this.llvBook.getSelectionModel().getSelectedItem() != null) {
            Book b = this.llvBook.getSelectionModel().getSelectedItem();
            this.rlvBook.getItems().add(b);
            this.llvBook.getItems().remove(b);
        } else {
            MessageBox.getBox("Book", "Vui lòng chọn sách!", Alert.AlertType.ERROR).show();
        }

    }

    public void btnLeft(ActionEvent event) {
        if (this.rlvBook.getSelectionModel().getSelectedItem() != null) {
            Book b = this.rlvBook.getSelectionModel().getSelectedItem();
            this.rlvBook.getItems().remove(b);
            this.llvBook.getItems().add(b);
        } else {
            MessageBox.getBox("Book", "Vui lòng chọn sách!", Alert.AlertType.ERROR).show();
        }
    }

    private void loadTableView() {

        TableColumn colId = new TableColumn("Mã");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(50);

        TableColumn colBorrowed = new TableColumn("Ngày mượn");
        colBorrowed.setCellValueFactory(new PropertyValueFactory("borrowed_date"));
        colBorrowed.setPrefWidth(100);

        TableColumn colPayDate = new TableColumn("Ngày trả ");
        colPayDate.setCellValueFactory(new PropertyValueFactory("pay_date"));
        colPayDate.setPrefWidth(100);

        TableColumn colAppointment = new TableColumn("Ngày hẹn trả");
        colAppointment.setCellValueFactory(new PropertyValueFactory("appointment_date"));
        colAppointment.setPrefWidth(100);

        TableColumn colReaderId = new TableColumn("Mã độc giả");
        colReaderId.setCellValueFactory(new PropertyValueFactory("reader_id"));
        colReaderId.setPrefWidth(100);

        TableColumn colGetBook = new TableColumn("Đã lấy sách");
        colGetBook.setCellValueFactory(new PropertyValueFactory("getBook"));
        colGetBook.setPrefWidth(100);

        TableColumn colPayBook = new TableColumn("Đã trả sách");
        colPayBook.setCellValueFactory(new PropertyValueFactory("payBook"));
        colPayBook.setPrefWidth(100);

        this.tbCard.getColumns().addAll(colId, colBorrowed, colAppointment, colPayDate, colReaderId, colGetBook, colPayBook);

    }

    private void loadTableData(int kw) throws SQLException {
        ReaderCardService s = new ReaderCardService();
        this.tbCard.setItems(FXCollections.observableList(s.getCard(kw)));
    }

    public void radNhan(ActionEvent event) {
        this.dpApp.setVisible(true);
    }

    public void radChuaNhan(ActionEvent event) {
        this.dpApp.setVisible(false);
        this.dpApp.setValue(null);
    }

    public void radTra(ActionEvent event) {
        this.dpPay.setVisible(true);
    }

    public void radChuaTra(ActionEvent event) {
        this.dpPay.setVisible(false);
        this.dpPay.setValue(null);
    }

    public void btnBorrowed(ActionEvent event) {
        ReaderCardService s = new ReaderCardService();
        Reader_card b = new Reader_card();
        BookService book = new BookService();
        int nhan;
        int tra;
        if (this.radDaNhan.isSelected()) {
            nhan = 1;
        } else {
            nhan = 0;
        }
        if (this.radDaTra.isSelected()) {
            tra = 1;
        } else {
            tra = 0;
        }
        if (this.radChuaTra.isSelected()) {
            b = new Reader_card(Date.valueOf(java.time.LocalDate.now()), Date.valueOf(this.dpApp.getValue()), Integer.parseInt(this.txtReader.getText().trim()), nhan, tra);
        } else {
            try {
                b = new Reader_card(Date.valueOf(java.time.LocalDate.now()), Date.valueOf(this.dpPay.getValue()), Date.valueOf(this.dpApp.getValue()), Integer.parseInt(this.txtReader.getText().trim()), nhan, tra);
            } catch (EventException | NullPointerException ex) {
                MessageBox.getBox("Book", "Vui lòng nhập đầy đủ thông tin!", Alert.AlertType.ERROR).show();
            }

        }
        List<Book> sach = this.rlvBook.getItems();
        if (sach.isEmpty()) {
            MessageBox.getBox("Card", "Chọn sách muốn mượn!", Alert.AlertType.INFORMATION).show();
        } else {
            List<Muon> m = new ArrayList<>();
            MuonService muon = new MuonService();
            ReaderService r = new ReaderService();
            boolean flag = true;
            try {
                List<Readers> re = r.getReaders(Integer.parseInt(this.txtReader.getText().trim()));
                List<Reader_card> rc = s.getCard(Integer.parseInt(this.txtReader.getText().trim()));
                if (re.size() == 0) {
                    flag = false;
                    MessageBox.getBox("Card", "Bạn đã nhập sai mã độc giả!", Alert.AlertType.INFORMATION).show();
                }
                for (Readers i : re) {
                    if (r.kiemTraHanThe(i.getEnd_date())) {
                        flag = false;
                        MessageBox.getBox("Card", "Thẻ của bạn đã hết hạn", Alert.AlertType.INFORMATION).show();
                    }
                }
                for (Reader_card j : rc) {
                    if (s.kiemTraChuaTraHetSach(j)) {
                        flag = false;
                        MessageBox.getBox("Card", "Bạn vui lòng trả hết sách trước khi muốn mượn tiếp tục", Alert.AlertType.INFORMATION).show();
                    }
                }
                if (muon.kiemTraSoSachMuon(sach)) {
                    flag = false;
                    MessageBox.getBox("Card", "Bạn chỉ được mượn tối đa 5 cuốn trong 1 lần", Alert.AlertType.INFORMATION).show();
                }
                if (s.kiemTraNgayMuon(Date.valueOf(this.dpApp.getValue()), Date.valueOf(java.time.LocalDate.now()))) {
                    flag = false;
                    MessageBox.getBox("Card", "Bạn chỉ được mượn tối đa 30 ngày", Alert.AlertType.INFORMATION).show();
                }
                BookService bs = new BookService();
                if (bs.KiemTraNgayQuaKhu(Date.valueOf(this.dpApp.getValue()))) {
                    flag = false;
                    MessageBox.getBox("Card", "Ngày hẹn trả không hợp lệ!", Alert.AlertType.INFORMATION).show();
                }
                if (flag) {
                    for (Book k : sach) {
                        m.add(new Muon(b.getId(), k.getId()));
                        book.BorrowedBook(k.getId());
                    }
                    if (s.addCard(b, m)) {
                        MessageBox.getBox("Book", "Mượn thành công", Alert.AlertType.INFORMATION).show();
                        this.reset();
                    } else {
                        MessageBox.getBox("Book", "Vui lòng tải lại. Đã có sự cố", Alert.AlertType.INFORMATION).show();
                    }
                }
            } catch (SQLException | NullPointerException ex) {
                MessageBox.getBox("Book", "Bạn vui lòng nhập đầy đủ thông tin", Alert.AlertType.ERROR).show();
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void mouseClicked(MouseEvent event) throws SQLException {
        this.rlvBook.getItems().clear();
        this.llvBook.getItems().clear();
        Reader_card b = tbCard.getSelectionModel().getSelectedItem();
        MuonService muon = new MuonService();
        BookService s = new BookService();
        this.txtReader.setText(String.valueOf(b.getReader_id()));
        if (b.getGet_books() == 1) {
            this.radDaNhan.setSelected(true);
            borrowedDate = b.getBorrowed_date();
            this.dpApp.setVisible(true);
            this.dpApp.setValue(LocalDate.parse(String.valueOf(b.getAppointment_date())));
        } else {
            this.radChuaNhan.setSelected(true);
            this.dpApp.setVisible(false);
            this.dpApp.setValue(java.time.LocalDate.now());
        }
        if (b.getPay_book() == 1) {
            this.radDaTra.setSelected(true);
            this.dpPay.setVisible(true);
            this.dpPay.setValue(LocalDate.parse(String.valueOf(b.getPay_date())));
        } else {
            this.radChuaTra.setSelected(true);
            this.dpPay.setVisible(false);
            this.dpPay.setValue(java.time.LocalDate.now());
        }
        id = b.getId();
        List<Muon> m = muon.getMuonDelete(id);
        List<Book> cates = s.getBooks(null);
        for (Muon i : m) {
            for (Book j : cates) {
                if (j.getId() == i.getBook_id()) {
                    this.rlvBook.getItems().add(j);
                }
            }
        }
    }

    public void btnTraSach(ActionEvent event) throws SQLException {
        BookService s = new BookService();
        ReaderCardService r = new ReaderCardService();
        MuonService muon = new MuonService();
        boolean flag = true;
        try {
            
            if (radDaTra.isSelected() && this.dpPay.getValue().isBefore(java.time.LocalDate.now())) {
                flag = false;
                MessageBox.getBox("Thông báo", "Sách đã được trả!", Alert.AlertType.INFORMATION).show();
            }else
            if (s.KiemTraNgayQuaKhu(Date.valueOf(this.dpPay.getValue()))) {
                flag = false;
                MessageBox.getBox("Lỗi", "Ngày trả sách không thể là ngày trong quá khứ!", Alert.AlertType.ERROR).show();
            }
            if (flag) {
                long ngay = r.kiemTraTraTre(Date.valueOf(this.dpPay.getValue()), borrowedDate) - 30;
                r.PayBook(Date.valueOf(this.dpPay.getValue()), id);
                List<Muon> m = muon.getMuonDelete(id);
                BookService b = new BookService();
                for (Muon i : m) {
                    b.PayBook(i.getBook_id());
                }
                loadTableData(0);
                List<Book> cates = s.getBooksNotBorrowed();
                this.llvBook.setItems(FXCollections.observableList(cates));
                if (ngay > 0) {
                    MessageBox.getBox("Book", "Bạn trả trễ " + ngay + " ngày bạn bị phạt " + ngay * 5000 + " VND", Alert.AlertType.INFORMATION).show();
                } else {
                    MessageBox.getBox("Book", "Trả sách thành công", Alert.AlertType.INFORMATION).show();
                }
                this.rlvBook.getItems().clear();
            }
        } catch (NullPointerException ex) {
            MessageBox.getBox("Book", "Vui lòng nhập đầy đủ thông tin!", Alert.AlertType.ERROR).show();
        }
    }

    public void btnNhanSach(ActionEvent event) throws SQLException {
        boolean flag = true;
        ReaderCardService r = new ReaderCardService();
        ReaderService rd = new ReaderService();
        BookService bs = new BookService();
        try {
            List<Readers> re = rd.getReaders(Integer.parseInt(this.txtReader.getText()));

            if (radDaNhan.isSelected()) {
                flag = false;
                MessageBox.getBox("Card", "Sách đã được nhận!", Alert.AlertType.INFORMATION).show();
            }
            if (re.size() == 0) {
                flag = false;
                MessageBox.getBox("Card", "Bạn đã nhập sai mã độc giả!", Alert.AlertType.INFORMATION).show();
            }
            if (r.kiemTraNgayMuon(Date.valueOf(this.dpApp.getValue()), Date.valueOf(java.time.LocalDate.now()))) {
                flag = false;
                MessageBox.getBox("Card", "Bạn chỉ được mượn tối đa 30 ngày", Alert.AlertType.INFORMATION).show();
            }
            if (bs.KiemTraNgayQuaKhu(Date.valueOf(this.dpApp.getValue()))) {
                flag = false;
                MessageBox.getBox("Card", "Ngày hẹn trả không hợp lệ!", Alert.AlertType.INFORMATION).show();
            }
            if (flag) {
                try {
                    r.GetBook(Date.valueOf(java.time.LocalDate.now()), Date.valueOf(this.dpApp.getValue()), id);
                    loadTableData(0);
                    MessageBox.getBox("Book", "Nhận sách đã đặt thành công", Alert.AlertType.INFORMATION).show();
                    this.rlvBook.getItems().clear();
                } catch (NullPointerException ex) {
                    MessageBox.getBox("Book", "Vui lòng nhập đầy đủ thông tin!", Alert.AlertType.ERROR).show();
                }
            }
        } catch (NumberFormatException ex) {
            MessageBox.getBox("Book", "Kiểm tra thông tin độc giả!", Alert.AlertType.ERROR).show();
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

    public void btnReload(ActionEvent event) throws SQLException {
        BookService s = new BookService();
        MuonService m = new MuonService();
        ReaderCardService r = new ReaderCardService();
        List<Reader_card> c = r.getCardBooking();
        for (Reader_card i : c) {
            if (r.kiemTraHanLaySachDaDat(Date.valueOf(java.time.LocalDate.now()), i.getBooking_date()) && i.getGet_books() == 0) {
                List<Muon> b = m.getMuonDelete(i.getId());
                for (Muon j : b) {
                    s.PayBook(j.getBook_id());
                    m.deleteMuon(j.getId());
                }
                r.deleteCard(i.getId());
            }
        }
        try {
            loadTableData(0);
            List<Book> cates = s.getBooksNotBorrowed();
            this.llvBook.setItems(FXCollections.observableList(cates));
            this.reset();
        } catch (SQLException ex) {
            Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void reset() throws SQLException {
        this.dpApp.getEditor().clear();
        this.dpPay.getEditor().clear();
        this.radChuaNhan.setSelected(false);
        this.radChuaTra.setSelected(false);
        this.radDaNhan.setSelected(false);
        this.radDaTra.setSelected(false);
        this.txtReader.clear();
        this.rlvBook.getItems().clear();
        loadTableData(0);
    }
}
