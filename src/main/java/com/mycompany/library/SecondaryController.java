package com.mycompany.library;

import com.mycompany.pojo.Book;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class SecondaryController {
    @FXML private TableView<Book> tbSach;
    @FXML
    public void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    @FXML
    public void switchToSearch() throws IOException {
        App.setRoot("search");
    }

}