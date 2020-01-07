package levels;

import map.Map;
import mapobjects.Obstacle;
import utils.Rect;
import utils.Vector2d;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class LevelManager {
    private List<Obstacle> obstacles = new ArrayList<>();
    private Map map;

    public LevelManager(Map map) {
        this.map = map;
        initializeObstacles();
    }

    public Level createLevel(int lvl){
        Obstacle obstacle = obstacles.get(min(obstacles.size()-1, lvl-1));
        return new Level(obstacle, lvl, map);
    }

    private void initializeObstacles() {
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
}
