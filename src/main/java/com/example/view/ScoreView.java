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

    // Paleta de colores Memory Minds
    private static final String CYAN = "#00d4ff";
    private static final String GREEN = "#27ae60";
    private static final String MUTED = "rgba(255,255,255,0.55)";
    private static final String CARD_BG = "rgba(255,255,255,0.06)";
    private static final String BORDER = "rgba(0,212,255,0.25)";
    private static final String ROW_HL = "rgba(0,212,255,0.18)";

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
        setStyle("-fx-background-color: linear-gradient(to bottom, #1a1a2e, #16213e);");
        setPrefSize(800, 640);
        getStylesheets().add(getClass().getResource("/com/example/score.css").toExternalForm());

        VBox content = new VBox(24,
                buildHeader(),
                buildStatsCards(),
                buildLeaderboard(),
                buildButtons());
        content.setPadding(new Insets(32, 48, 32, 48));
        content.setAlignment(Pos.TOP_CENTER);
        content.setStyle("-fx-background-color: transparent;");

        ScrollPane scroll = new ScrollPane(content);
        scroll.getStyleClass().add("score-scroll");
        scroll.setFitToWidth(true);
        scroll.setStyle(
                "-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");
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
        titleLabel.setStyle(titleStyle(victory ? CYAN : "#f0c040"));

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
        titleLabel.setStyle(titleStyle(CYAN));

        Label subtitle = new Label("Memory Minds  ·  Resultados de tu partida");
        subtitle.setStyle("-fx-font-size: 13px; -fx-text-fill: " + MUTED + ";");

        modeLabel = new Label("Modo: —");
        modeLabel.setStyle(
                "-fx-font-size: 12px; -fx-text-fill: " + CYAN + ";" +
                        "-fx-background-color: rgba(0,212,255,0.10); -fx-background-radius: 20;" +
                        "-fx-padding: 4 16 4 16; -fx-border-color: " + CYAN + ";" +
                        "-fx-border-radius: 20; -fx-border-width: 1;");

        VBox header = new VBox(10, titleLabel, subtitle, modeLabel);
        header.setAlignment(Pos.CENTER);
        return header;
    }

    private HBox buildStatsCards() {
        scoreLabel = valueLabel("—", CYAN, 38);
        timeLabel = valueLabel("00:00", "#ffffff", 28);
        attemptsLabel = valueLabel("—", "#ffffff", 28);
        efficiencyLabel = valueLabel("—%", GREEN, 28);

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
        sectionTitle.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: " + MUTED + ";");

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
        placeholder.setStyle("-fx-text-fill: " + MUTED + "; -fx-font-size: 14px;");
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
                setStyle(!empty && item == currentEntry
                        ? "-fx-background-color: " + ROW_HL + "; -fx-background-radius: 6;"
                        : "");
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
        lbl.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: " + MUTED + ";");

        VBox c = new VBox(6, lbl, value);
        c.setAlignment(Pos.CENTER);
        c.setPadding(new Insets(18, 24, 18, 24));
        c.setMinWidth(primary ? 160 : 130);
        c.setStyle(
                "-fx-background-color: " + CARD_BG + "; -fx-background-radius: 14;" +
                        "-fx-border-color: " + BORDER + "; -fx-border-radius: 14; -fx-border-width: 1;" +
                        (primary ? "-fx-effect: dropshadow(gaussian, rgba(0,212,255,0.22), 16, 0, 0, 0);" : ""));
        return c;
    }

    private static Label valueLabel(String text, String color, int size) {
        Label l = new Label(text);
        l.setStyle("-fx-font-size: " + size + "px; -fx-font-weight: bold; -fx-text-fill: " + color + ";");
        return l;
    }

    private static String titleStyle(String color) {
        return "-fx-font-size: 34px; -fx-font-weight: bold; -fx-text-fill: " + color + ";" +
                "-fx-effect: dropshadow(gaussian, " + color + ", 12, 0.6, 0, 0);";
    }

    private static Button createPrimaryButton(String text) {
        String base = "-fx-background-color:#00d4ff;-fx-text-fill:#0d1117;-fx-font-size:14px;" +
                "-fx-font-weight:bold;-fx-background-radius:10;-fx-cursor:hand;" +
                "-fx-pref-width:210;-fx-pref-height:46;" +
                "-fx-effect:dropshadow(gaussian,rgba(0,212,255,0.4),10,0,0,4);";
        String hover = base.replace("#00d4ff", "#33dfff");
        Button b = new Button(text);
        b.setStyle(base);
        b.setOnMouseEntered(e -> b.setStyle(hover));
        b.setOnMouseExited(e -> b.setStyle(base));
        return b;
    }

    private static Button createOutlineButton(String text) {
        String base = "-fx-background-color:transparent;-fx-text-fill:#00d4ff;-fx-font-size:14px;" +
                "-fx-font-weight:bold;-fx-border-color:#00d4ff;-fx-border-radius:10;" +
                "-fx-background-radius:10;-fx-border-width:1.5;-fx-cursor:hand;" +
                "-fx-pref-width:210;-fx-pref-height:46;";
        String hover = "-fx-background-color:rgba(0,212,255,0.10);-fx-text-fill:#00d4ff;" +
                "-fx-font-size:14px;-fx-font-weight:bold;-fx-border-color:#00d4ff;" +
                "-fx-border-radius:10;-fx-background-radius:10;-fx-border-width:1.5;" +
                "-fx-cursor:hand;-fx-pref-width:210;-fx-pref-height:46;";
        Button b = new Button(text);
        b.setStyle(base);
        b.setOnMouseEntered(e -> b.setStyle(hover));
        b.setOnMouseExited(e -> b.setStyle(base));
        return b;
    }

    public Button getPlayAgainButton() {
        return playAgainButton;
    }

    public Button getBackToMenuButton() {
        return backToMenuButton;
    }
}
