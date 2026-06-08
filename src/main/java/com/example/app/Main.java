package com.example.app;

import com.example.model.ScoreEntry;
import com.example.view.ScoreView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ScoreView view = new ScoreView();

        // Datos de ejemplo — tu compañero reemplaza esto con los datos reales del juego
        ScoreEntry partida = new ScoreEntry("Jugador1", 78, 12, 8, 195, "Ciencias");
        view.showResults(partida, List.of(partida));

        // Conectar con las otras pantallas del juego
        view.getPlayAgainButton() .setOnAction(e -> System.out.println("→ Nueva partida"));
        view.getBackToMenuButton().setOnAction(e -> System.out.println("→ Menú principal"));

        stage.setTitle("Memory Minds — Puntuaciones");
        stage.setScene(new Scene(view, 800, 640));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
