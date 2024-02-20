module org.example.hw_7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.hw_7 to javafx.fxml;
    exports org.example.hw_7;
}