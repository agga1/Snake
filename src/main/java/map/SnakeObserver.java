package map;

import map.AnimalObserver;
import mapobjects.Snake;
import utils.Vector2d;

public interface SnakeObserver extends AnimalObserver {
    void onSnakeMoved(Snake snake, Vector2d newHead);
}
