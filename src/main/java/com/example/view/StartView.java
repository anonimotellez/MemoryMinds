package com.example.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StartView extends VBox {

    private Button startButton;
    private Button optionsButton;

    public StartView() {

        setAlignment(Pos.CENTER);
        setSpacing(20);

        Label title = new Label("Game Title");

        startButton = new Button("Comenzar");
        optionsButton = new Button("Opciones");

        getChildren().addAll(
                title,
                startButton,
                optionsButton
        );
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getOptionsButton() {
        return optionsButton;
    }
}