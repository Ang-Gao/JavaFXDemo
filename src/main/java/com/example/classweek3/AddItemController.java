package com.example.classweek3;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddItemController {
    @FXML
    private TextField shortDescTF;
    @FXML
    private TextArea detailDescTF;
    @FXML
    private DatePicker datePicker;

    DateTimeFormatter formatter;
    public TodoItem collectionResults(){
        String shorts = shortDescTF.getText();
        String details = detailDescTF.getText();
        //date
        LocalDate ddl = datePicker.getValue();
        //return new item
        return new TodoItem(shorts,details,ddl);
    }
}
