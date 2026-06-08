package com.example.view;

import com.example.app.Main;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StartView extends VBox {

    private Button startButton;
    private Button optionsButton;

    public StartView(Main app) {

        setAlignment(Pos.CENTER);
        setSpacing(20);

        Label title = new Label("Game Title");

        startButton = new Button("Comenzar");
        startButton.setOnAction(e -> {
            app.showGameView();
        });
        optionsButton = new Button("Opciones");

        getChildren().addAll(
                title,
                startButton,
                optionsButton);
    }
}