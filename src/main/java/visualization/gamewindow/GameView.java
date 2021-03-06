package visualization.gamewindow;

import javafx.scene.layout.VBox;
import logic.EngineObserver;
import mapobjects.IMapElement;
import utils.Vector2d;

public class GameView extends VBox implements EngineObserver {
    private MapPane mapPane;
    private ScorePane scorePane;

    public GameView(int width, int height){
        mapPane = new MapPane(width, height);
        scorePane = new ScorePane();
        this.getChildren().addAll(scorePane, mapPane);
    }
    @Override
    public void onTileUpdate(IMapElement mapElement, Vector2d position) {
        mapPane.onTileUpdate(mapElement, position);
    }

    @Override
    public void onScoreUpdate(int score) {
        scorePane.onScoreUpdate(score);
    }

    @Override
    public void onLevelUpdate(int lvl) {
        scorePane.onLevelUpdate(lvl);
    }
}
