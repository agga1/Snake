package levels;

import objects.Obstacle;
import utils.Rect;
import utils.Vector2d;

import java.util.ArrayList;
import java.util.List;
// 20 x 20
public class Levels {
    private static Levels instance;
    public List<Obstacle> obstacles = new ArrayList<>();

    public static Levels getInstance(){
        if(instance == null) {
            instance = new Levels();
        }
        return instance;
    }

    private Levels(){
        // lvl 1
        Rect rect = new Rect(new Vector2d(6, 10), new Vector2d(14, 10));
        obstacles.add(new Obstacle(List.of(rect)));
        // lvl 2
        rect = new Rect(new Vector2d(6, 6), new Vector2d(14, 6));
        Rect rect2 = new Rect(new Vector2d(6, 12), new Vector2d(14, 12));
        obstacles.add(new Obstacle(List.of(rect, rect2)));
        // lvl 3
        rect = new Rect(new Vector2d(5, 4), new Vector2d(5, 16));
        rect2 = new Rect(new Vector2d(10, 4), new Vector2d(10, 16));
        Rect rect3 = new Rect(new Vector2d(15, 4), new Vector2d(15, 16));
        obstacles.add(new Obstacle(List.of(rect, rect2, rect3)));
    }

    public Obstacle getLevel(int i){
        if(i >= obstacles.size()) i = obstacles.size()-1;
        return obstacles.get(i);
    }
}
