package levels;

import objects.Map;
import objects.Obstacle;
import objects.Wasp;
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
        Rect rect = new Rect(new Vector2d(9, 9), new Vector2d(9, 9));
        obstacles.add(new Obstacle(List.of(rect)));
        // lvl 2
        rect = new Rect(new Vector2d(6, 10), new Vector2d(14, 10));
        obstacles.add(new Obstacle(List.of(rect)));
        // lvl 3
        rect = new Rect(new Vector2d(6, 6), new Vector2d(14, 6));
        Rect rect2 = new Rect(new Vector2d(6, 12), new Vector2d(14, 12));
        obstacles.add(new Obstacle(List.of(rect, rect2)));
        // lvl 4
        rect = new Rect(new Vector2d(5, 4), new Vector2d(5, 16));
        rect2 = new Rect(new Vector2d(10, 4), new Vector2d(10, 16));
        Rect rect3 = new Rect(new Vector2d(15, 4), new Vector2d(15, 16));
        obstacles.add(new Obstacle(List.of(rect, rect2, rect3)));
    }

    public Obstacle getObstacles(int lvl){
        lvl--;
        if(lvl >= obstacles.size()) lvl = obstacles.size()-1;
        return obstacles.get(lvl);
    }

    public List<Wasp> getWasps(int lvl, Map map){
        List<Wasp> wasps = new ArrayList<>();
        int x = 18;
        for(int i=1;i<lvl;i++){
            wasps.add(new Wasp(new Vector2d(x--, 0), map));
        }
        return wasps;
    }
}
