package logic;

import objects.IMapElement;
import utils.Vector2d;

public interface MapObserver {
    void onTileUpdate(IMapElement mapElement, Vector2d position);
    void onKill();
    void onProgress();
}
