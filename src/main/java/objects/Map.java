package objects;

import logic.MapObserver;
import utils.Rect;
import utils.Vector2d;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Map {
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
            takeSpace(mapElement, pos);
        }
    }

    public void onGrowApple(){
        if(freeSpace == null) return;
        Vector2d newApplePos = Vector2d.randomFromSet(freeSpace);
        Apple apple = new Apple(newApplePos);
        takeSpace(apple, newApplePos);
    }

    public void onGrowBlueApple() {
        if(freeSpace == null) return;
        Vector2d newApplePos = Vector2d.randomFromSet(freeSpace);
        BlueApple apple = new BlueApple(newApplePos);
        takeSpace(apple, newApplePos);
    }

    public void onSnakeMoved(Snake snake, Vector2d newHead){

        IMapElement objectOnCollision = allObjects.get(newHead);
        takeSpace(snake, newHead);

        if(objectOnCollision != null){
            if(objectOnCollision instanceof Apple){
                snake.eatApple();
                onGrowApple();
                mapObserver.onProgress();
            }
            else if(objectOnCollision instanceof BlueApple){
                snake.shrinkBy(1);
            }
            else { // div into obstacle and snake?
                mapObserver.onKill();
            }
        }
    }

    public void freeSpace(Vector2d position) { // snake no longer occupies this position
        allObjects.remove(position);
        freeSpace.add(position);
        notifyObserverOfChange(position);
    }

    public void takeSpace(IMapElement mapElement, Vector2d position ){ // object takes up this position
        allObjects.put(position, mapElement);
        freeSpace.remove(position);
        notifyObserverOfChange(position);
    }

    public void reset(){
        new HashSet<>(allObjects.keySet()).forEach(this::freeSpace);
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
