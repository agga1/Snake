package visualization.map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ScorePane extends VBox {
    private Text score;
    private Text level;

    public ScorePane() {
        this.level = new Text("level: 1");
        this.score = new Text("score: 0");
        setAlignment(Pos.CENTER_RIGHT);
        setPadding(new Insets(0, 20, 0, 0));
        setPrefHeight(30);
        this.getChildren().addAll(level, score);
    }

    public void onScoreUpdate(int score) {
        this.score.setText("score: "+(score));
    }

    public void onLevelUpdate(int lvl) {
        this.level.setText("level: "+(lvl));
    }
}
