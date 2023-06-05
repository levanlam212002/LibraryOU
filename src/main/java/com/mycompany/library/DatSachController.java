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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DatSachController implements Initializable {

    @FXML
    private ListView<Book> llvBook;
    @FXML
    private ListView<Book> rlvBook;
    @FXML
    private Label lbId;
    private int idDG;

    public void setID(int id) {
        this.lbId.setText(String.valueOf(id));
        idDG = id;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BookService s = new BookService();
        MuonService m = new MuonService();
        ReaderCardService r = new ReaderCardService();
        try {
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
        } catch (SQLException ex) {
            Logger.getLogger(QLMTController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
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

    public void btnDatSach(ActionEvent event) {
        ReaderCardService s = new ReaderCardService();
        BookService book = new BookService();
        try {
            Reader_card b = new Reader_card(Integer.parseInt(this.lbId.getText()), 0, 0, Date.valueOf(java.time.LocalDate.now()), 1);
            List<Book> sach = this.rlvBook.getItems();
            if (sach.isEmpty()) {
                MessageBox.getBox("Book", "Chọn sách bạn muốn đặt!", Alert.AlertType.ERROR).show();
            } else {
                List<Muon> m = new ArrayList<>();
                MuonService muon = new MuonService();
                ReaderService r = new ReaderService();
                boolean flag = true;
                List<Readers> re = r.getReaders(Integer.parseInt(this.lbId.getText()));
                List<Reader_card> rc = s.getCard(Integer.parseInt(this.lbId.getText()));
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

                if (flag) {
                    for (Book k : sach) {
                        m.add(new Muon(b.getId(), k.getId()));
                        book.BorrowedBook(k.getId());
                    }
                    if (s.DatOnline(b, m)) {
                        this.rlvBook.getItems().clear();
                        MessageBox.getBox("Book", "Bạn phải đến lấy sách trước 48h sau khi đặt thành công", Alert.AlertType.INFORMATION).show();
                    } else {
                        MessageBox.getBox("Book", "Failed", Alert.AlertType.INFORMATION).show();
                    }
                }
            }
        } catch (SQLException | NullPointerException ex) {
            MessageBox.getBox("Book", "Chọn sách bạn muốn đặt!", Alert.AlertType.ERROR).show();
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void btnQuayLai(ActionEvent event) throws IOException {
        FXMLLoader fxmloader = new FXMLLoader(App.class.getResource("search.fxml"));
        Scene scene = new Scene(fxmloader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        SearchController s = fxmloader.getController();
        s.setId(idDG);
        stage.setTitle("Tra cứu Sách");
        stage.show();
        Button btn = (Button) event.getSource();
        Stage stagelogin = (Stage) btn.getScene().getWindow();
        stagelogin.close();
    }
}
