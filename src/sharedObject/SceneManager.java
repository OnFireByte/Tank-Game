package sharedObject;

import javafx.stage.Stage;
import logic.GameController;
import scenes.MainGameScene;
import scenes.MainMenuScene;
import scenes.MapSelectionScene;

public class SceneManager {
    private static SceneManager instance;
    private static Stage primaryStage;

    private MainMenuScene mainMenuScene;
    private MainGameScene mainGameScene;
    private MapSelectionScene mapSelectionScene;

    public SceneManager() {
        mainMenuScene = new MainMenuScene();
        mainGameScene = new MainGameScene();
        mapSelectionScene = new MapSelectionScene();
    }

    static public void injectStage(Stage primaryStage) {
        SceneManager.primaryStage = primaryStage;
    }

    static public SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public MainMenuScene getMainMenuScene() {
        return mainMenuScene;
    }

    public MainGameScene getMainGameScene() {
        return mainGameScene;
    }

    public MapSelectionScene getMapSelectionScene() {
        return mapSelectionScene;
    }

    public void setMapSelectionScene(MapSelectionScene mapSelectionScene) {
        this.mapSelectionScene = mapSelectionScene;
    }

    public void setToMainMenu() {
        primaryStage.setScene(mainMenuScene);
        GameController.getInstance().setGameRunning(false);

        mainMenuScene.startMusic();
    }

    public void setToMainGame() {
        primaryStage.setScene(mainGameScene);
        GameController.getInstance().reset();
        GameController.getInstance().setGameRunning(true);
        mainMenuScene.stopMusic();

    }

    public void setToMapSelection() {
        primaryStage.setScene(mapSelectionScene);
        GameController.getInstance().setGameRunning(false);
    }

    public void openUpgradeModal() {
        mainGameScene.openUpgradeModal();
        RenderableHolder.upgradeSound.play();

    }

    public void closeUpgradeModal() {
        mainGameScene.closeUpgradeModal();

    }

    public void openEndGameModal() {
        mainGameScene.openEndGameModal();
        RenderableHolder.gameOverSound.play();
    }

}
