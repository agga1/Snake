package objects;

import utils.Rect;
import utils.Vector2d;
import visualization.Icon;

import java.util.ArrayList;
import java.util.List;

public class Obstacle implements IMapElement {
    private List<Vector2d> occupiedSpace = new ArrayList<>();

    public Obstacle(List<Rect> rects) {
        rects.forEach(r -> occupiedSpace.addAll(r.toVectors()));
    }

    @Override
    public Icon getIcon() {
        return Icon.OBSTACLE;
    }

    @Override
    public List<Vector2d> occupiedSpace() {
        return occupiedSpace;
    }
}
