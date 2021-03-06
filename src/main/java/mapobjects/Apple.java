package mapobjects;

import utils.Vector2d;
import visualization.Icon;

import java.util.List;

public class Apple implements IMapElement{
    private Vector2d position;

    public Apple(Vector2d position) {
        this.position = position;
    }

    @Override
    public Icon getIcon() {
        return Icon.APPLE;
    }

    @Override
    public List<Vector2d> occupiedSpace() {
        return List.of(position);
    }

    @Override
    public String toString() {
        return "Apple" + position;
    }
}
