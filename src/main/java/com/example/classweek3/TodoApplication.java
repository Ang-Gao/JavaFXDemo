package com.example.classweek3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TodoApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TodoApplication.class.getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),700,800);
        primaryStage.setTitle("ToDo List -> Pick an item !");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void init() {
        TodoData.getInstance().loadTodoItems();
    }
    //问题：四个问题：listview的写入问题，textarea的同步问题，textarea底下显示的due的时间是什么格式,以及cell/改与删除
}
