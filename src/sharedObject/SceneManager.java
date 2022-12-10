package sharedObject;

import javafx.stage.Stage;
import logic.GameController;
import scenes.MainGameScene;
import scenes.MainMenuScene;

public class SceneManager {
    private static SceneManager instance;
    private static Stage primaryStage;

    private MainMenuScene mainMenuScene;
    private MainGameScene mainGameScene;

    public SceneManager() {
        mainMenuScene = new MainMenuScene();
        mainGameScene = new MainGameScene();
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

    public void setToMainMenu() {
        primaryStage.setScene(mainMenuScene);
        GameController.getInstance().setGameRunning(false);
    }

    public void setToMainGame() {
        primaryStage.setScene(mainGameScene);
        GameController.getInstance().reset();
        GameController.getInstance().setGameRunning(true);
    }

    public void openUpgradeModal() {
        mainGameScene.openUpgradeModal();

    }

    public void closeUpgradeModal() {
        mainGameScene.closeUpgradeModal();

    }

    public void openEndGameModal() {
        mainGameScene.openEndGameModal();

    }

}
