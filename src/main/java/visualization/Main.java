package visualization;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import logic.Engine;
import visualization.map.MapPane;

public class Main extends Application {
    private Engine engine;
    private boolean isDone = false;

    public void start(Stage stage) {
        stage.setTitle("Snake");
        stage.getIcons().add(Icon.SNAKE_ICON.img);

        MapPane mapPane = new MapPane(GameConfig.WIDTH, GameConfig.HEIGHT);
        this.engine = new Engine(GameConfig.WIDTH, GameConfig.HEIGHT);
        engine.addObserver(mapPane);
        engine.initialize();

        Scene scene = new Scene(mapPane);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> isDone = true);
        stage.show();
        Thread game;
        game = new Thread(() -> {
            while (engine.update()) {

                try {
                    Thread.sleep(160);
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
