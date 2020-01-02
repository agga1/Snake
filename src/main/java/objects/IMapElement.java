package objects;

import utils.Vector2d;
import visualization.Icon;

import java.util.List;

public interface IMapElement {
    Icon getIcon();
    List<Vector2d> occupiedSpace();
}
