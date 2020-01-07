package objects;

import utils.Vector2d;

public interface SnakeObserver extends AnimalObserver{
    void onSnakeMoved(Snake snake, Vector2d newHead);
}
