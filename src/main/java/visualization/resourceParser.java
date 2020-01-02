package visualization;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class resourceParser {
    private static final String resources = "src/main/resources/".replace("/", File.separator);
    private static final String images = resources + "images" + File.separator;

    public static Image parseImage(String name) {
        try {
            final FileInputStream input = new FileInputStream(images + name);
            System.out.println("success");
            return new Image(input);
        } catch (FileNotFoundException ignore) {
            System.out.println("no image at: " + images + name);
        }
        return null;
    }
}
