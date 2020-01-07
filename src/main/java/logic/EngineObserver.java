package logic;

import mapobjects.IMapElement;
import utils.Vector2d;

public interface EngineObserver {
    void onTileUpdate(IMapElement mapElement, Vector2d position);
    void onScoreUpdate(int score);
    void onLevelUpdate(int lvl);
}
