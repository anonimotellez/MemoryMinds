package com.example.view;

import com.example.app.Main;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameView extends StackPane {

    private GridPane board;
    private Button returnButton;

    public GameView(Main app) {

        getStyleClass().add("game-view");

        board = new GridPane();
        board.setHgap(10);
        board.setVgap(10);
        board.setAlignment(Pos.CENTER);
        board.getStyleClass().add("game-board");

        returnButton = new Button("Regresar");
        returnButton.setOnAction(e -> {
            app.showStartView();
        });
        returnButton.getStyleClass().add("return-button");

        createBoard();

        VBox column = new VBox(16);
        column.setAlignment(Pos.CENTER);
        column.getChildren().addAll(board, returnButton);
        getChildren().add(column);
    }

    private void createBoard() {
        int rows = 4;
        int cols = 4;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                CardView card = new CardView("A");
                board.add(card, col, row);
            }
        }
    }
}
