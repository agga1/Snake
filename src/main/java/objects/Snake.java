package objects;

import utils.Direction;
import utils.Vector2d;
import visualization.Icon;

import java.util.ArrayList;
import java.util.List;

public class Snake implements IMapElement {
    private List<Vector2d> segments = new ArrayList<>();
    private Direction direction = Direction.RIGHT;
    private int length;
    private Map map;

    public Snake(Map map){
        this(map, List.of(new Vector2d(0, 0)));
    }

    public Snake(Map map, List<Vector2d> segments) {
        this.segments.addAll(segments);
        this.length = segments.size();
        this.map = map;
    }
    public void changeDirection(Direction direction){
        if(! this.direction.equals(direction.opposite()))
            this.direction = direction;
    }
    public void eatApple(Vector2d oldTail){ // regrow tail
        this.length ++;
        segments.add(oldTail);
    }
    public void move(){
        Vector2d tail = segments.get(length-1);
        Vector2d head = segments.get(0).add(direction.toVector()).mapToRect(map.getRect());
        segments.remove(length-1);
        segments.add(0, head);
        map.onSnakeMoved(this, tail, head);
    }

    public void changeMap(Map map){
        shrink();
        this.map = map;
    }

    public void shrink(){
        this.segments = new ArrayList<>();
        segments.add(new Vector2d(0, 0));
        this.length = segments.size();
    }

    @Override
    public Icon getIcon() {
        return Icon.SNAKE;
    }

    @Override
    public List<Vector2d> occupiedSpace() {
        return segments;
    }
}
