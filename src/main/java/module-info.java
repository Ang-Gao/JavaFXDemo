module com.example.classweek3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens com.example.classweek3 to javafx.fxml;
    exports com.example.classweek3;
}