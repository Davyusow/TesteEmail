module com.example.testeemail {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;


    opens com.example.testeemail to javafx.fxml;
    exports com.example.testeemail;
}