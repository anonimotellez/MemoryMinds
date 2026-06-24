package com.example.view;
import com.example.app.Main;
import com.example.model.ScoreEntry;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.List;

public final class ScoreView extends StackPane {

    private Label titleLabel;
    private Label modeLabel;
    private Label scoreLabel;
    private Label attemptsLabel;
    private Label efficiencyLabel;
    private final Button backToMenuButton = createOutlineButton("⌂   Salir al Menú");

    public ScoreView(Main app) {
        getStyleClass().add("score-view");

        VBox content = new VBox(
                24,
                buildHeader(),
                buildStatsCards(),
                buildButtons()
        );
        content.setPadding(new Insets(32, 48, 32, 48));
        content.setAlignment(Pos.TOP_CENTER);
        content.getStyleClass().add("score-content");

        ScrollPane scroll = new ScrollPane(content);
        scroll.getStyleClass().add("score-scroll");
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        backToMenuButton.setOnAction(e -> {
            app.showStartView();
        });

        getChildren().add(scroll);
    }

    public void showResults(ScoreEntry current, List<ScoreEntry> topScores) {

        boolean victory = current.score() >= 70;

        titleLabel.setText(victory ? "🏆  ¡VICTORIA!" : "💪  ¡BIEN HECHO!");

        titleLabel.getStyleClass().removeAll("victory", "good-effort");
        titleLabel.getStyleClass().add(victory ? "victory" : "good-effort");

        modeLabel.setText("Modo: " + current.gameMode());
        scoreLabel.setText(String.valueOf(current.score()));
        attemptsLabel.setText(String.valueOf(current.attempts()));
        efficiencyLabel.setText(String.format("%.0f%%", current.efficiencyPercent()));
    }
    // ── Construcción del layout ───────────────────────────────────────────────

    private VBox buildHeader() {
        titleLabel = new Label("🏆  ¡VICTORIA!");
        titleLabel.getStyleClass().addAll("score-title", "victory");

        Label subtitle = new Label("Memory Minds  ·  Resultados de tu partida");
        subtitle.getStyleClass().add("score-subtitle");

        modeLabel = new Label("Modo: —");
        modeLabel.getStyleClass().add("score-mode-pill");

        VBox header = new VBox(10, titleLabel, subtitle, modeLabel);
        header.setAlignment(Pos.CENTER);
        return header;
    }

    private HBox buildStatsCards() {
        scoreLabel = valueLabel("—");
        scoreLabel.getStyleClass().add("score-value-score");
        attemptsLabel = valueLabel("—");
        efficiencyLabel = valueLabel("—%");
        efficiencyLabel.getStyleClass().add("score-value-efficiency");
        HBox row = new HBox(
                16,
                card("PUNTUACIÓN", scoreLabel, true),
                card("INTENTOS", attemptsLabel, false),
                card("EFICIENCIA", efficiencyLabel, false)
        );
        row.setAlignment(Pos.CENTER);
        return row;
    }

    private HBox buildButtons() {
        HBox box = new HBox(20, backToMenuButton);
        box.setAlignment(Pos.CENTER);
        VBox.setMargin(box, new Insets(8, 0, 0, 0));
        return box;
    }

    // ── Utilidades de estilo ──────────────────────────────────────────────────

    private static VBox card(String title, Label value, boolean primary) {
        Label lbl = new Label(title);
        lbl.getStyleClass().add("score-card-title");

        VBox c = new VBox(6, lbl, value);
        c.setAlignment(Pos.CENTER);
        c.setPadding(new Insets(18, 24, 18, 24));
        c.setMinWidth(primary ? 160 : 130);
        c.getStyleClass().add("score-card");
        if (primary) {
            c.getStyleClass().add("score-card-primary");
        }
        return c;
    }

    private static Label valueLabel(String text) {
        Label l = new Label(text);
        l.getStyleClass().add("score-value");
        return l;
    }

    private static Button createOutlineButton(String text) {
        Button b = new Button(text);
        b.getStyleClass().add("score-btn-outline");
        return b;
    }
}