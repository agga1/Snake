package logic;

import objects.IMapElement;
import utils.Vector2d;

public interface Observer {
    void onUpdate(IMapElement mapElement, Vector2d position);
}
