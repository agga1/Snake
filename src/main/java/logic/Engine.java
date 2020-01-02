package logic;

import javafx.scene.input.KeyCode;
import levels.Levels;
import objects.IMapElement;
import objects.Map;
import objects.Obstacle;
import objects.Snake;
import utils.Direction;
import utils.Rect;
import utils.Vector2d;
import visualization.map.MapPane;

import java.util.ArrayList;
import java.util.List;

public class Engine implements MapObserver{
    private Snake snake;
    private Map map;
    private List<Observer> observers = new ArrayList<>();
    private boolean alive = true;
    private int progress = 0;
    private int currLvl = 0;
    private int width = 20;
    private int height = 20;

    public Engine(int width, int height) {
        map = new Map(width, height, this);
        snake = new Snake(map);
    }
    public void initialize(){
        map.placeElement(snake);
        Obstacle obstacle = Levels.getInstance().getLevel(currLvl);
        map.placeElement(obstacle);
        map.onGrowApple();
        map.getRect().toVectors().forEach(v -> onUpdate(map.objectAt(v), v));
    }

    public boolean update(){
        snake.move();
        return alive;
    }
    public void onKeyPressed(KeyCode k){
        Direction direction = Direction.keyKodeToDir(k);
        if(direction != null)
            snake.changeDirection(direction);
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void onKill() {
        // restart game
//        alive = false;
        currLvl = 0;
        progress = 0;
        map = new Map(width, height, this);
        snake = new Snake(map);
        initialize();

    }

    @Override
    public void onProgress() {
        progress++;
        if(progress > currLvl*2){
            progress = 0;
            currLvl ++;
            onNewLevel();
        }
    }

    public void onNewLevel(){
        System.out.println("now lvl " + currLvl);
        map = new Map(width, height, this);
        snake.changeMap(map); // reset snake
        initialize();
    }

    @Override
    public void onUpdate(IMapElement mapElement, Vector2d position) {
        this.observers.forEach(ob -> ob.onUpdate(mapElement, position));
    }
}
