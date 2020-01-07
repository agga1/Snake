package map;

import mapobjects.IMapElement;
import utils.Vector2d;

public interface AnimalObserver {
    void takeSpace(IMapElement mapElement, Vector2d position);
    void freeSpace(Vector2d position);
}
