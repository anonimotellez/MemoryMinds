package com.example.view;

import com.example.app.Main;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class StartView extends StackPane {

    private static final String START_STYLESHEET = "/styles/start.css";

    public StartView(Main app) {
        getStyleClass().add("start-view");
        getStylesheets().add(getClass().getResource(START_STYLESHEET).toExternalForm());

        VBox content = new VBox(28);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(40, 50, 40, 50));
        content.setMaxWidth(920);
        content.getStyleClass().add("start-content");

        Label title = new Label("MEMORY MINDS");
        title.getStyleClass().add("start-title");

        Label subtitle = new Label("Selecciona una categoría para comenzar la partida");
        subtitle.getStyleClass().add("start-subtitle");

        HBox categories = new HBox(28,
                createCategoryButton("Matemáticas", "[ START ]", "category-math", app::showGameView),
                createCategoryButton("Ciencia", "[ START ]", "category-science", app::showGameView),
                createCategoryButton("Historia", "[ START ]", "category-history", app::showGameView));
        categories.setAlignment(Pos.CENTER);
        categories.getStyleClass().add("category-row");

        HBox controls = new HBox(16,
                createControlButton("Puntuaciones", app::showScoreView),
                createControlButton("Instrucciones",
                        () -> showInfo("Instrucciones",
                                "Elige una categoría para iniciar y luego encuentra todos los pares.")),
                createControlButton("Salir", Platform::exit));
        controls.setAlignment(Pos.CENTER);
        controls.getStyleClass().add("control-row");

        content.getChildren().addAll(title, subtitle, categories, controls);
        getChildren().add(content);
    }

    private Button createCategoryButton(String titleText, String hintText, String styleClass, Runnable action) {
        Label title = new Label(titleText);
        title.getStyleClass().add("category-title");

        Label hint = new Label(hintText);
        hint.getStyleClass().add("category-hint");

        VBox card = new VBox(12, title, hint);
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().addAll("category-button", styleClass);

        Button button = new Button();
        button.setGraphic(card);
        button.getStyleClass().addAll("menu-button", styleClass);
        button.setMinSize(240, 260);
        button.setMaxSize(240, 260);
        button.setOnAction(e -> action.run());
        return button;
    }

    private Button createControlButton(String text, Runnable action) {
        Button button = new Button(text);
        button.getStyleClass().add("control-button");
        button.setPrefSize(180, 46);
        button.setOnAction(e -> action.run());
        return button;
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText("Instrucciones");
        alert.setContentText(message);
        alert.showAndWait();
    }
}