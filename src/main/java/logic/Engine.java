package logic;

import javafx.scene.input.KeyCode;
import levels.Level;
import levels.LevelManager;
import map.Map;
import map.MapObserver;
import mapobjects.*;
import utils.Direction;
import utils.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Engine implements MapObserver {
    private static final int startSpeed = 30;

    private Map map;
    private Snake snake;
    private List<Wasp> wasps = new ArrayList<>();

    private LevelManager levelManager;
    private boolean paused = false;
    private int progress = 0;
    private int currLvl = 1;
    private int speed = startSpeed;
    private double blueAppleChance = 0;

    private List<EngineObserver> observers = new ArrayList<>();

    public Engine(int width, int height) {
        map = new Map(width, height, this);
        levelManager = new LevelManager(map);
        snake = new Snake(map);
    }

    public void initialize(){
        paused = true;
        map.reset();

        Level level = levelManager.createLevel(currLvl);
        wasps = level.initializeWasps();
        level.initializeObstacle();
        blueAppleChance = level.getBlueAppleChance();

        map.placeElement(snake);
        map.onGrowApple();
    }

    public void update(){
        if(paused) return;
        snake.move();
        wasps.forEach(Wasp::move); // TODO move to level.update()?
        if( new Random().nextFloat() < blueAppleChance){ // -- also this
            map.onGrowBlueApple();
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

    public void addObserver(EngineObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void onKill() {  // TODO evoked from lvl? (lvl as map observer)
        currLvl = 1;
        progress = 0;
        speed = startSpeed;
        notifyOfLevelUpdate(currLvl);
        notifyOfScoreUpdate(progress);
        snake = new Snake(map);
        initialize();
    }

    @Override
    public void onProgress() {
        progress++;
        notifyOfScoreUpdate(progress);
        if(progress > currLvl*currLvl + 1){
            currLvl ++;
            onNewLevel();
        }
    }

    public void onNewLevel(){
        notifyOfLevelUpdate(currLvl);
        speed += currLvl;
        snake.changeLvl(); // reset snake
        initialize();
    }

    @Override
    public void onTileUpdate(IMapElement mapElement, Vector2d position) {
        this.observers.forEach(ob -> ob.onTileUpdate(mapElement, position));
    }

    public void notifyOfScoreUpdate(int score) {
        this.observers.forEach(ob -> ob.onScoreUpdate(score));
    }

    public void notifyOfLevelUpdate(int lvl) {
        this.observers.forEach(ob -> ob.onLevelUpdate(lvl));
    }

    public int getSpeed() {
        return speed;
    }

}
