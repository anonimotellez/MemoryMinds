package com.example.app;

import com.example.view.GameView;
import com.example.view.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        showStartView();
        stage.show();
    }

    public void showStartView() {

        StartView view = new StartView(this);

        Scene scene = new Scene(view, 800, 600);

        applyStyles(scene);

        stage.setScene(scene);
    }

    public void showGameView() {

        GameView view = new GameView(this);

        Scene scene = new Scene(view, 800, 600);

        applyStyles(scene);

        stage.setScene(scene);
    }

    private void applyStyles(Scene scene) {
        scene.getStylesheets().add(
                getClass().getResource("/styles/style.css").toExternalForm());
    }

    public static void main(String[] args) {
        launch();
    }
}