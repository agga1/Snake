package logic;

import objects.IMapElement;
import utils.Vector2d;

public interface Observer {
    void onTileUpdate(IMapElement mapElement, Vector2d position);
    void onScoreUpdate(int score);
    void onLevelUpdate(int lvl);
}
