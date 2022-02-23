package com.example.classweek3;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainController {
    private static final String filename = "ToDoListItems.txt";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @FXML
    private ListView<TodoItem> todoList;
    @FXML
    private BorderPane mainWindow;
    @FXML
    private TextArea textArea;
    @FXML
    private Label dueDateLabel;

    List<TodoItem> arrayList = new ArrayList<>();
    // getTodoList###
    public ListView<TodoItem> getTodoList() {
        return todoList;
    }
    // setTodoList###
    public void addItemTo(TodoItem item) {
        todoList.getItems().add(item);
    }
    @FXML
    public void dialogShow(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        dialog.setTitle("New Item");
        dialog.setHeaderText("Todo");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(TodoApplication.class.getResource("add-item-window.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Cannot load fxml file!");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get().equals(ButtonType.OK)){
            System.out.println("Hello World");
            // TodoItem newItem = new TodoItem();
            //// TODO: 2022/2/23  ???
            AddItemController addItemController = fxmlLoader.getController();
            /*AddItemController addItemController = new AddItemController(); 错误，无法添加*/
            TodoItem newItem = addItemController.collectionResults();
            // TODO: 2022/2/23 changed
            arrayList.add(newItem);
            todoList.getItems().add(newItem);
            storeTodoItems();
        } else {
            System.out.println("Not Hello World");
        }
    }
    public void initialize(){
        TodoItem item1 = new TodoItem("item1","this is task1", LocalDate.of(2022, Month.DECEMBER,12));
        TodoItem item2 = new TodoItem("item2","this is task2", LocalDate.of(2022, Month.FEBRUARY,8));
        TodoItem item3 = new TodoItem("item3","this is task3", LocalDate.of(2021, Month.JULY,15));
        TodoItem item4 = new TodoItem("item4","this is task4", LocalDate.of(2022, Month.FEBRUARY,20));
        TodoItem item5 = new TodoItem("item5","this is task5", LocalDate.of(2022, Month.JUNE,11));
        arrayList.add(item1);
        arrayList.add(item2);
        arrayList.add(item3);
        arrayList.add(item4);
        arrayList.add(item5);
        TodoData.getInstance().setTodoItems(arrayList);
        /*todoList.getItems().addAll(arrayList); 区别是什么？*/
        todoList.getItems().setAll(arrayList);
        todoList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        // store data
        storeTodoItems();
        // add listener
        todoList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem todoItem, TodoItem t1) {
                int index = todoList.getSelectionModel().getSelectedIndex();
                textArea.setText(todoList.getItems().get(index).getDetails());
                dueDateLabel.setText(todoList.getItems().get(index).getDueDateTime().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")));
            }
        });
    }
    public void addTodo(){
        /*TodoItem todoItem = new TodoItem("short desc","details task1",new Date());
        todoList.getItems().add(todoItem);*/

    }
    public void storeTodoItems() {
        Path path = Paths.get(filename);
        BufferedWriter bw = null;
        try{
            bw = Files.newBufferedWriter(path);
            for (TodoItem item : arrayList) {
                bw.write(String.format("%s\t%s\t%s",
                        item.getShortDescription(),
                        item.getDetails(),
                        item.getDueDateTime().format(formatter)));
                bw.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}