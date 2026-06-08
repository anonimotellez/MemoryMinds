package com.example.view;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class CardView extends StackPane {

    private boolean flipped = false;
    private final Label label;
    private final DropShadow shadow;
    private static final double WIDTH = 90;
    private static final double HEIGHT = 110;

    public CardView(String text) {

        setPrefSize(WIDTH, HEIGHT);
        setAlignment(Pos.CENTER);
        setPickOnBounds(true);
        getStyleClass().add("card-view");
        getStyleClass().add("hidden");

        shadow = new DropShadow();
        shadow.setRadius(18);
        shadow.setOffsetY(6);
        shadow.setColor(Color.rgb(0, 0, 0, 0.45));
        setEffect(shadow);

        label = new Label("?");
        label.getStyleClass().add("card-label");
        label.getStyleClass().add("hidden");

        getChildren().add(label);

        setOnMouseClicked(event -> {
            flipped = !flipped;
            updateVisualState(text);
        });
    }

    private void updateVisualState(String text) {

        if (flipped) {

            label.setText(text);
            getStyleClass().removeAll("hidden", "revealed");
            getStyleClass().add("revealed");

            label.getStyleClass().removeAll("hidden", "revealed");
            label.getStyleClass().add("revealed");

        } else {

            label.setText("?");
            getStyleClass().removeAll("hidden", "revealed");
            getStyleClass().add("hidden");

            label.getStyleClass().removeAll("hidden", "revealed");
            label.getStyleClass().add("hidden");
        }
    }
}