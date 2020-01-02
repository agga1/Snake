package objects;
import utils.Vector2d;
import visualization.Icon;

import java.util.List;

public class BlueApple implements IMapElement{
    private Vector2d position;

    public BlueApple(Vector2d position) {
        this.position = position;
    }

    @Override
    public Icon getIcon() {
        return Icon.BLUE_APPLE;
    }

    @Override
    public List<Vector2d> occupiedSpace() {
        return List.of(position);
    }
}
