package visualization;

import javafx.scene.image.Image;

import static visualization.resourceParser.parseImage;

public enum Icon {
    GRASS("grass.png"),
    APPLE("apple.png"),
    BLUE_APPLE("blue_apple.png"),
    OBSTACLE("obstacle.png"),
    SNAKE_ICON("snake_icon.png"),
    SNAKE("snake.png");
    public final Image img;

    Icon(String name) {
        this.img = parseImage(name);
    }
}
