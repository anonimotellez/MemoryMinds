package com.example.view;

import com.example.app.Main;
import com.example.model.Card;
import com.example.model.GameBoard;

import javafx.geometry.Pos;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.List;

public class GameView extends StackPane {

    private final Main app;
    private GridPane board;
    private Button returnButton;
    private final GameBoard gameBoard;
    private CardView firstSelectedCard;
    private CardView secondSelectedCard;
    private boolean inputLocked;
    private boolean gameFinished;

    public GameView(Main app, List<Card> cards) {
        this.app = app;
        this.gameBoard = new GameBoard(4, 4, cards);

        getStyleClass().add("game-view");

        board = new GridPane();
        board.setHgap(10);
        board.setVgap(10);
        board.setAlignment(Pos.CENTER);
        board.getStyleClass().add("game-board");

        returnButton = new Button("Volver al menú");
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
        int rows = gameBoard.getRows();
        int cols = gameBoard.getCols();
        List<Card> cards = gameBoard.getCards();
        int index = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Card card = cards.get(index++);
                board.add(new CardView(card, this::handleCardSelection), col, row);
            }
        }
    }

    private void handleCardSelection(CardView selectedCard) {

        if (inputLocked) {
            return;
        }

        if (firstSelectedCard == null) {
            firstSelectedCard = selectedCard;
            return;
        }

        if (firstSelectedCard == selectedCard) {
            return;
        }

        secondSelectedCard = selectedCard;
        inputLocked = true;

        boolean isMatch = firstSelectedCard.getCard().getId().equals(secondSelectedCard.getCard().getId());

        if (isMatch) {
            firstSelectedCard.markMatched();
            secondSelectedCard.markMatched();
            clearSelection();
            checkGameCompletion();
            return;
        }

        PauseTransition pause = new PauseTransition(Duration.millis(300));
        pause.setOnFinished(event -> {
            firstSelectedCard.hideCard();
            secondSelectedCard.hideCard();
            clearSelection();
        });
        pause.play();
    }

    private void clearSelection() {
        firstSelectedCard = null;
        secondSelectedCard = null;
        inputLocked = false;
    }

    private void checkGameCompletion() {

        if (gameFinished) {
            return;
        }

        boolean allMatched = gameBoard.getCards().stream().allMatch(Card::isMatched);

        if (allMatched) {
            gameFinished = true;
            app.showScoreView();
        }
    }
}
