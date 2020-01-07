package visualization;

import javafx.scene.image.Image;

import static visualization.ImageParser.parseImage;

public enum Icon {
    GRASS("grass.png"),
    APPLE("apple.png"),
    BLUE_APPLE("blue_apple.png"),
    OBSTACLE("obstacle.png"),
    SNAKE_ICON("snake_icon.png"),
    WASP("wasp.png"),
    SNAKE("snake.png");
    public final Image img;

    Icon(String name) {
        this.img = parseImage(name);
    }
}
