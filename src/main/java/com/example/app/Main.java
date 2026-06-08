package com.example.app;

import com.example.view.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        StartView startView = new StartView();

        startView.getStartButton().setOnAction(e -> {
            System.out.println("Comenzar");
        });

        Scene scene = new Scene(startView, 800, 600);

        stage.setTitle("Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}