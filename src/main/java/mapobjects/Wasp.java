package mapobjects;

import map.Map;
import utils.Direction;
import utils.Vector2d;
import visualization.Icon;

import java.util.List;
import java.util.Random;

public class Wasp implements IMapElement{
    private Vector2d position;
    private Map map;

    public Wasp(Vector2d startPosition, Map map) {
        this.position = startPosition;
        this.map = map;
    }

    public void move(){
        Vector2d move = Direction.values()[new Random().nextInt(4)].toVector();
        Vector2d newPos =  position.add(move).mapToRect(map.getRect());
        if(map.objectAt(newPos) instanceof Obstacle) return;
        Vector2d oldPos = this.position;
        this.position = newPos;
        map.freeSpace(oldPos);
        map.takeSpace(this, position);
    }

    @Override
    public Icon getIcon() {
        return Icon.WASP;
    }

    @Override
    public List<Vector2d> occupiedSpace() {
        return List.of(position);
    }
}
