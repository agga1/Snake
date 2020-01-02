package objects;

import logic.MapObserver;
import utils.Rect;
import utils.Vector2d;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Map { // TODO Rect class for obstacles
    private Set<Vector2d> freeSpace = new HashSet<>();
    private HashMap<Vector2d, IMapElement> allObjects = new HashMap<>();
    private MapObserver mapObserver;
    private Rect rect;

    public Map(int width, int height, MapObserver mapObserver) throws IllegalArgumentException{
        if(width < 1 || height < 1) throw new IllegalArgumentException("map dimensions physically impossible");
        rect = new Rect(new Vector2d(0, 0), new Vector2d(width-1, height-1));
        freeSpace.addAll(rect.toVectors());
        this.mapObserver = mapObserver;
    }

    public void placeElement(IMapElement mapElement){
        for(Vector2d pos : mapElement.occupiedSpace()){
            allObjects.put(pos, mapElement); // dont care if sth was here before: overridden
            freeSpace.remove(pos);
            notifyObserverOfChange(pos);
        }
    }

    public void onGrowApple(){
        if(freeSpace == null) return;
        Vector2d newApplePos = Vector2d.randomFromSet(freeSpace);
        System.out.println("growing apple on pos "+ newApplePos );
        Apple apple = new Apple(newApplePos);
        freeSpace.remove(newApplePos);
        allObjects.put(newApplePos, apple);
        notifyObserverOfChange(newApplePos);
    }

    public void onSnakeMoved(Snake snake, Vector2d oldTail, Vector2d newHead){
        allObjects.remove(oldTail);
        freeSpace.add(oldTail);
        IMapElement previous = allObjects.get(newHead);
        freeSpace.remove(newHead);
        if(previous == null){
            allObjects.put(newHead, snake);
            notifyObserverOfChange(newHead);
            notifyObserverOfChange(oldTail);
        }
        else if(previous instanceof Apple){
            // eat apple and append the snake TODO notify?
            snake.eatApple(oldTail);
            allObjects.put(oldTail, snake);
            freeSpace.remove(oldTail);
            allObjects.put(newHead, snake);
            notifyObserverOfChange(newHead);
            onGrowApple();
            mapObserver.onProgress();
        }
        else if(previous instanceof BlueApple){
            List<Vector2d> oldSnake = snake.occupiedSpace();
            snake.shrink();
            oldSnake.forEach(this::notifyObserverOfChange);
        }
        else { // div into obstacle and snake?
            // notify engine of game over
            mapObserver.onKill();
        }
    }

    private void notifyObserverOfChange(Vector2d position){
        mapObserver.onTileUpdate(allObjects.get(position), position);
    }

    public IMapElement objectAt(Vector2d position){
        return allObjects.get(position);
    }

    public Rect getRect(){
        return this.rect;
    }
}
