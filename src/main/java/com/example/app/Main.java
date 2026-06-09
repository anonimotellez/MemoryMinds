package com.example.app;

import com.example.model.ScoreEntry;
import com.example.view.GameView;
import com.example.view.ScoreView;
import com.example.view.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;

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

    public void showScoreView() {
        ScoreView view = new ScoreView(this);

        // Datos de ejemplo — tu compañero reemplaza esto con los datos reales del juego
        ScoreEntry partida = new ScoreEntry("Jugador1", 78, 12, 8, 195, "Ciencias");
        view.showResults(partida, List.of(partida));

        stage.setTitle("Memory Minds — Puntuaciones");
        Scene scene = new Scene(view, 800, 640);
        applyStyles(scene);
        stage.setScene(scene);
        stage.setResizable(false);
    }

    private void applyStyles(Scene scene) {
        scene.getStylesheets().add(
                getClass().getResource("/styles/style.css").toExternalForm());
    }

    public static void main(String[] args) {
        launch();
    }
}
