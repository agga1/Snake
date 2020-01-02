package visualization;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Engine;
import visualization.map.GameView;
import visualization.map.MapPane;

public class Main extends Application {
    private Engine engine;
    private boolean isDone = false;

    public void start(Stage stage) {
        stage.setTitle("Snake");
        stage.getIcons().add(Icon.SNAKE_ICON.img);

        GameView mapPane = new GameView(GameConfig.WIDTH, GameConfig.HEIGHT);
        this.engine = new Engine(GameConfig.WIDTH, GameConfig.HEIGHT);
        engine.addObserver(mapPane);
        engine.initialize();

        Scene scene = new Scene(mapPane);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> isDone = true);
        stage.show();
        Thread game;
        game = new Thread(() -> {
            while (!isDone) {
                engine.update();
                try {
                    Thread.sleep(200-engine.getSpeed());
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        });
        game.start();
        scene.setOnKeyPressed(event -> engine.onKeyPressed(event.getCode()));

    }
    public static void main(String[] args) {
        launch(args);
    }

}
