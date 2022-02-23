package com.example.classweek3;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TodoData {
    private static TodoData instance;
    private List<TodoItem> todoItems;
    private static final String filename = "ToDoListItems.txt";
    private final DateTimeFormatter formatter;
    private TodoData(){
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
    public static TodoData getInstance(){
        if (instance == null) {
            synchronized (TodoData.class){
                if (instance == null) {
                    instance = new TodoData();
                    return instance;
                }
                return instance;
            }
        }
        return instance;
    }


    public void loadTodoItems(){
        todoItems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader br = null;
        String input;
        try{
            br = Files.newBufferedReader(path);
            while((input= br.readLine()) != null){
                String[] itemPieces = input.split("\t");
                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dateString = itemPieces[2];
                LocalDate date  = LocalDate.parse(dateString, formatter);
                TodoItem todoItem = new TodoItem(shortDescription,details,date);
                todoItems.add(todoItem);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void setTodoItems(List<TodoItem> todoItems){
        this.todoItems = todoItems;
    }
}
