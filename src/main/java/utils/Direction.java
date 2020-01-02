package utils;

import javafx.scene.input.KeyCode;
import jdk.nashorn.api.tree.DoWhileLoopTree;

public enum Direction {
    UP(new Vector2d(0, -1)),
    RIGHT(new Vector2d(1, 0)),
    DOWN(new Vector2d(0, 1)),
    LEFT(new Vector2d(-1, 0));

    private Vector2d vector;
    Direction(Vector2d vector) {
        this.vector = vector;
    }

    public Direction opposite(){
        return Direction.values()[(ordinal() + 2) % Direction.values().length];
    }

    public Vector2d toVector(){
        return vector;
    }

    public static Direction keyKodeToDir(KeyCode k){
        switch (k){
            case W: return UP;
            case A: return LEFT;
            case S: return DOWN;
            case D: return RIGHT;
        }
        return null;
    }
}
