module com.example.omerta {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.omerta to javafx.fxml;
    exports com.example.omerta;
}