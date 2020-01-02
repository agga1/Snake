package visualization.map;

import javafx.scene.layout.GridPane;
import logic.Engine;
import logic.Observer;
import objects.IMapElement;
import utils.Vector2d;
import visualization.ViewConfig;

import static java.lang.Math.min;

public class MapPane extends GridPane implements Observer {
    private Tile[][] tiles;

    public MapPane(int width, int height) {
        int tileEdge = Math.min(ViewConfig.WINDOW_HEIGHT/height, ViewConfig.WINDOW_WIDTH/width);
        tiles = new Tile[width][];
        for(int i=0;i<width;i++)
            tiles[i] = new Tile[height];
        setHgap(1);
        setVgap(1);
        for(int i=0;i<width; i++){
            for(int j=0 ;j<height; j++){
                tiles[i][j] = new Tile(tileEdge-2, new Vector2d(i, j));
                this.add(tiles[i][j], i, j, 1, 1);
            }
        }
    }

    public void onUpdate(IMapElement mapElement, Vector2d position){
        tiles[position.x][position.y].update(mapElement); // TODO swap needed?
    }
}
