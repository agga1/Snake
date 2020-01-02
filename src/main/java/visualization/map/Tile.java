package visualization.map;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import objects.IMapElement;
import utils.Vector2d;
import visualization.Icon;

public class Tile extends ImageView {
    private Tooltip tooltip;

    public Tile(int edge, Vector2d position) {
        this.setFitWidth(edge);
        this.setFitHeight(edge);
        setImage(Icon.GRASS.img);
        tooltip = new Tooltip(position.toString());
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.getStyleClass().add("tile-tooltip");
        Tooltip.install(this, tooltip);
    }

    public void update(IMapElement mapElement){
        if(mapElement == null)
            this.setImage(Icon.GRASS.img);
        else {
            this.setImage(mapElement.getIcon().img);
        }
    }
}
