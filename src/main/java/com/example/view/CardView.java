package com.example.view;

import com.example.model.Card;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.function.Consumer;

public class CardView extends StackPane {

    private final Card card;
    private final Consumer<CardView> onCardClicked;
    private final Label label;
    private final DropShadow shadow;
    private static final double WIDTH = 90;
    private static final double HEIGHT = 110;

    public CardView(Card card, Consumer<CardView> onCardClicked) {
        this.card = card;
        this.onCardClicked = onCardClicked;

        setPrefSize(WIDTH, HEIGHT);
        setAlignment(Pos.CENTER);
        setPickOnBounds(true);
        getStyleClass().add("card-view");

        shadow = new DropShadow();
        shadow.setRadius(18);
        shadow.setOffsetY(6);
        shadow.setColor(Color.rgb(0, 0, 0, 0.45));
        setEffect(shadow);

        label = new Label("?");
        label.getStyleClass().add("card-label");

        getChildren().add(label);

        updateVisualState();

        setOnMouseClicked(event -> {
            if (this.card.isMatched() || this.card.isFlipped()) {
                return;
            }

            this.card.setFlipped(true);
            updateVisualState();

            if (this.onCardClicked != null) {
                this.onCardClicked.accept(this);
            }
        });
    }

    public Card getCard() {
        return card;
    }

    public void hideCard() {
        card.setFlipped(false);
        updateVisualState();
    }

    public void markMatched() {
        card.setMatched(true);
        card.setFlipped(true);
        updateVisualState();
    }

    private void updateVisualState() {
        boolean revealed = card.isFlipped() || card.isMatched();
        label.setText(revealed ? card.getTextDisplay() : "?");

        getStyleClass().removeAll("hidden", "revealed");
        label.getStyleClass().removeAll("hidden", "revealed");

        if (revealed) {
            getStyleClass().add("revealed");
            label.getStyleClass().add("revealed");
        } else {
            getStyleClass().add("hidden");
            label.getStyleClass().add("hidden");
        }
    }
}