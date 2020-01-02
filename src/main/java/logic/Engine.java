package logic;

import javafx.scene.input.KeyCode;
import levels.Levels;
import objects.IMapElement;
import objects.Map;
import objects.Obstacle;
import objects.Snake;
import utils.Direction;
import utils.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Engine implements MapObserver{
    private static final int startSpeed = 30;
    private Snake snake;
    private Map map;
    private List<Observer> observers = new ArrayList<>();
    private boolean paused = false;
    private int progress = 0;
    private int currLvl = 1;
    private int speed = startSpeed;

    public Engine(int width, int height) {
        map = new Map(width, height, this);
        snake = new Snake(map);
    }

    public void initialize(){
        paused = true;
        map.reset();
        map.placeElement(snake);
        Obstacle obstacle = Levels.getInstance().getLevel(currLvl);
        map.placeElement(obstacle);
        map.onGrowApple();
    }

    public void update(){
        if(paused) return;
        snake.move();
        if(currLvl > 0){ // TODO change numbers
            if( new Random().nextFloat() > 0.99){
                map.onGrowBlueApple();
            }
        }
    }

    public void onKeyPressed(KeyCode k){
        if(k.equals(KeyCode.P))
            paused = !paused;
        else {
            if(paused) paused = false;
            Direction direction = Direction.keyKodeToDir(k);
            if (direction != null)
                snake.changeDirection(direction);
        }
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void onKill() {
        currLvl = 1;
        progress = 0;
        speed = startSpeed;
        onLevelUpdate(currLvl);
        onScoreUpdate(progress);
        snake = new Snake(map);
        initialize();
    }

    @Override
    public void onProgress() {
        progress++;
        onScoreUpdate(progress);
        if(progress > currLvl*currLvl + 1){
            currLvl ++;
            onNewLevel();
        }
    }

    public void onNewLevel(){
        onLevelUpdate(currLvl);
        speed += currLvl;
        snake.changeLvl(); // reset snake
        initialize();
    }

    @Override
    public void onTileUpdate(IMapElement mapElement, Vector2d position) {
        this.observers.forEach(ob -> ob.onTileUpdate(mapElement, position));
    }

    public void onScoreUpdate(int score) {
        this.observers.forEach(ob -> ob.onScoreUpdate(score));
    }

    public void onLevelUpdate(int lvl) {
        this.observers.forEach(ob -> ob.onLevelUpdate(lvl));
    }

    public int getSpeed() {
        return speed;
    }

}
