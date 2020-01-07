package levels;

import objects.Map;
import objects.Obstacle;
import objects.Wasp;
import utils.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private Obstacle obstacle;
    private int lvl;
    private double blueAppleChance;
    private Map map;
    private List<Wasp> wasps = new ArrayList<>();

    public Level(Obstacle obstacle, int lvl, Map map) {
        this.obstacle = obstacle;
        this.lvl = lvl;
        this.map = map;
        blueAppleChance = lvl > 1 ? (lvl*0.4)/100.0 : 0.0;
    }

    public void initializeObstacle() {
        map.placeElement(obstacle);
    }

    public List<Wasp> initializeWasps() {
        int x = 18;
        for(int i=1;i<lvl;i++){
            wasps.add(new Wasp(new Vector2d(x--, 1), map));
        }
        wasps.forEach(w -> map.placeElement(w));
        return wasps;
    }

    public double getBlueAppleChance() {
        return blueAppleChance;
    }
}
