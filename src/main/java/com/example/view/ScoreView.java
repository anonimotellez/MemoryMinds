package com.example.view;

import com.example.app.Main;
import com.example.model.ScoreEntry;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.List;
import java.util.function.Function;

/**
 * Pantalla de puntuación de Memory Minds.
 *
 * USO (desde el GameController de tu compañero):
 *
 * ScoreEntry resultado = new ScoreEntry("Jugador", score, intentos, pares,
 * segundos, "Modo");
 * ScoreView vista = new ScoreView();
 * vista.showResults(resultado, listaTopScores);
 * vista.getPlayAgainButton() .setOnAction(e -> { ... });
 * vista.getBackToMenuButton().setOnAction(e -> { ... });
 * stage.setScene(new Scene(vista, 800, 640));
 */
public final class ScoreView extends StackPane {

    // Labels que se actualizan con showResults()
    private Label titleLabel;
    private Label modeLabel;
    private Label scoreLabel;
    private Label timeLabel;
    private Label attemptsLabel;
    private Label efficiencyLabel;

    private TableView<ScoreEntry> table;
    private ScoreEntry currentEntry;

    private final Button playAgainButton = createPrimaryButton("▶   Volver a Jugar");
    private final Button backToMenuButton = createOutlineButton("⌂   Salir al Menú");

    public ScoreView(Main app) {
        getStyleClass().add("score-view");
        setPrefSize(800, 640);
        getStylesheets().add(getClass().getResource("/styles/score.css").toExternalForm());

        VBox content = new VBox(24,
                buildHeader(),
                buildStatsCards(),
                buildLeaderboard(),
                buildButtons());
        content.setPadding(new Insets(32, 48, 32, 48));
        content.setAlignment(Pos.TOP_CENTER);
        content.getStyleClass().add("score-content");

        ScrollPane scroll = new ScrollPane(content);
        scroll.getStyleClass().add("score-scroll");
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        playAgainButton.setOnAction(e -> {
            app.showGameView();
        });
        backToMenuButton.setOnAction(e -> {
            app.showStartView();
        });

        getChildren().add(scroll);
    }

    // ── API pública ───────────────────────────────────────────────────────────

    /**
     * Actualiza la pantalla con los datos de la partida recién terminada
     * y la lista de mejores puntuaciones.
     *
     * @param current   resultado de la partida actual
     * @param topScores lista ordenada de mejores puntajes (puede incluir current)
     */
    public void showResults(ScoreEntry current, List<ScoreEntry> topScores) {
        currentEntry = current;

        boolean victory = current.score() >= 70;
        titleLabel.setText(victory ? "🏆  ¡VICTORIA!" : "💪  ¡BIEN HECHO!");
        titleLabel.getStyleClass().removeAll("victory", "good-effort");
        titleLabel.getStyleClass().add(victory ? "victory" : "good-effort");

        modeLabel.setText("Modo: " + current.gameMode());
        scoreLabel.setText(String.valueOf(current.score()));
        timeLabel.setText(current.formattedTime());
        attemptsLabel.setText(String.valueOf(current.attempts()));
        efficiencyLabel.setText(String.format("%.0f%%", current.efficiencyPercent()));

        table.setItems(FXCollections.observableArrayList(topScores));
        table.refresh();
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

        timeLabel = valueLabel("00:00");
        attemptsLabel = valueLabel("—");

        efficiencyLabel = valueLabel("—%");
        efficiencyLabel.getStyleClass().add("score-value-efficiency");

        HBox row = new HBox(16,
                card("PUNTUACIÓN", scoreLabel, true),
                card("TIEMPO", timeLabel, false),
                card("INTENTOS", attemptsLabel, false),
                card("EFICIENCIA", efficiencyLabel, false));
        row.setAlignment(Pos.CENTER);
        return row;
    }

    private VBox buildLeaderboard() {
        Label sectionTitle = new Label("MEJORES PUNTUACIONES");
        sectionTitle.getStyleClass().add("score-section-title");

        table = buildTable();

        VBox section = new VBox(10, sectionTitle, table);
        section.setAlignment(Pos.CENTER_LEFT);
        return section;
    }

    private HBox buildButtons() {
        HBox box = new HBox(20, playAgainButton, backToMenuButton);
        box.setAlignment(Pos.CENTER);
        VBox.setMargin(box, new Insets(8, 0, 0, 0));
        return box;
    }

    // ── Tabla ─────────────────────────────────────────────────────────────────

    private TableView<ScoreEntry> buildTable() {
        TableView<ScoreEntry> t = new TableView<>();
        t.getStyleClass().add("score-table");
        t.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        t.setFixedCellSize(44);
        t.setPrefHeight(44 * 8 + 44);
        t.setSelectionModel(null);

        Label placeholder = new Label("Sin partidas aún. ¡Sé el primero!");
        placeholder.getStyleClass().add("score-placeholder");
        t.setPlaceholder(placeholder);

        t.getColumns().addAll(
                rankColumn(),
                col("JUGADOR", ScoreEntry::playerName, 200),
                col("MODO", ScoreEntry::gameMode, 110),
                col("PUNTAJE", e -> String.valueOf(e.score()), 90),
                col("TIEMPO", ScoreEntry::formattedTime, 90),
                col("EFICIENCIA", e -> String.format("%.0f%%", e.efficiencyPercent()), 100));

        t.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(ScoreEntry item, boolean empty) {
                super.updateItem(item, empty);
                getStyleClass().remove("current-score-row");
                if (!empty && item == currentEntry) {
                    getStyleClass().add("current-score-row");
                }
            }
        });

        return t;
    }

    private TableColumn<ScoreEntry, String> rankColumn() {
        TableColumn<ScoreEntry, String> col = new TableColumn<>("#");
        col.setMinWidth(48);
        col.setMaxWidth(48);
        col.setCellValueFactory(cd -> new SimpleStringProperty(""));
        col.setCellFactory(c -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : switch (getIndex()) {
                    case 0 -> "🥇";
                    case 1 -> "🥈";
                    case 2 -> "🥉";
                    default -> String.valueOf(getIndex() + 1);
                });
            }
        });
        return col;
    }

    private static TableColumn<ScoreEntry, String> col(String header,
            Function<ScoreEntry, String> value,
            double minWidth) {
        TableColumn<ScoreEntry, String> col = new TableColumn<>(header);
        col.setMinWidth(minWidth);
        col.setCellValueFactory(cd -> new SimpleStringProperty(value.apply(cd.getValue())));
        return col;
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

    private static Button createPrimaryButton(String text) {
        Button b = new Button(text);
        b.getStyleClass().add("score-btn-primary");
        return b;
    }

    private static Button createOutlineButton(String text) {
        Button b = new Button(text);
        b.getStyleClass().add("score-btn-outline");
        return b;
    }
}
