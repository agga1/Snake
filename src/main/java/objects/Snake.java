package objects;

import utils.Direction;
import utils.Vector2d;
import visualization.Icon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Snake implements IMapElement {
    private List<Vector2d> segments = new ArrayList<>();
    private Direction direction = Direction.RIGHT;
    private int length;
    private Vector2d prevTail;
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
    public void eatApple(){ // regrow tail
        this.length ++;
        segments.add(prevTail);
        map.takeSpace(this, prevTail);
    }
    public void move(){
        prevTail = segments.get(length-1);
        Vector2d head = segments.get(0).add(direction.toVector()).mapToRect(map.getRect());
        segments.remove(length-1);
        map.freeSpace(prevTail);
        segments.add(0, head);
        map.onSnakeMoved(this, head);
    }

    public void changeLvl(){
        shrink(new Vector2d(0, 0));
        direction = Direction.RIGHT;
    }

    public void shrinkBy(int by){
        while(segments.size() > 1 && by-- >0){
            Vector2d oldSeg = segments.get(length-1);
            segments.remove(length-1);
            length--;
            map.freeSpace(oldSeg);
        }
    }

    public void shrink(Vector2d newPos){
        Set<Vector2d> oldPos = new HashSet<>(segments);
        oldPos.remove(newPos);
        oldPos.forEach(v -> map.freeSpace(v));
        this.segments = new ArrayList<>();
        segments.add(newPos);
        this.length = segments.size();
    }

    @Override
    public Icon getIcon() {
        return Icon.SNAKE;
    }

    @Override
    public String toString() {
        return "Snake " +
                "length = " + length;
    }

    @Override
    public List<Vector2d> occupiedSpace() {
        return segments;
    }
}
