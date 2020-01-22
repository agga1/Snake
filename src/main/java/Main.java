import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.Engine;
import visualization.GameConfig;
import visualization.GameState;
import visualization.Icon;
import visualization.ViewConfig;
import visualization.gamewindow.GameView;

public class Main extends Application {
    private Engine engine;
    private boolean isDone = false;

    public void start(Stage stage) {
        stage.setTitle(ViewConfig.TITLE);
        stage.getIcons().add(Icon.SNAKE_ICON.img);

        GameState gameState = new GameState();

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
            Runnable runnable = this::updateGame;
            while (!isDone) {
                try {
                    Thread.sleep(200-engine.getSpeed());
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
                if (gameState.isRunning) {
                    Platform.runLater(runnable);
                }
            }
        });
        game.start();
        scene.setOnKeyPressed(event -> engine.onKeyPressed(event.getCode()));

    }
    private void updateGame(){
        engine.update();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
