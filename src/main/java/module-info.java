module com.mycompany.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;


    opens com.mycompany.library to javafx.fxml;
    exports com.mycompany.library;
    exports com.mycompany.pojo;
}
