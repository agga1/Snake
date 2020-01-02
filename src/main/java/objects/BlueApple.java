package objects;
import utils.Vector2d;
import visualization.Icon;

import java.util.List;

public class BlueApple implements IMapElement{
    private Vector2d position;
    private long birthday;

    public BlueApple(Vector2d position) {
        this.position = position;
        this.birthday = System.currentTimeMillis();
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
