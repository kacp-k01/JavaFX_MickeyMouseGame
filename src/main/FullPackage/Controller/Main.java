package main.FullPackage.Controller;

import main.FullPackage.Model.Model;
import main.FullPackage.Threads.EggLoader;
import main.FullPackage.Threads.TimeCounter;
import main.FullPackage.View.GameStage;

import main.FullPackage.View.HighScoreView;
import main.FullPackage.View.MenuScene;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class Main extends Application {
    Model model = new Model();
    MenuScene menuScene = new MenuScene();
    HighScoreView HS_Score = new HighScoreView();

    @Override
    public void start(Stage Menu) {

        Menu.setTitle("Mickey Mouse");
        Menu.setScene(menuScene.getScene1());
        Menu.show();

        menuScene.getNewGame().setOnAction(event -> {

            GameStage Game = new GameStage();
            TimeCounter timeCounter = new TimeCounter();
            EggLoader eggLoader = new EggLoader(timeCounter, Game, model, HS_Score);

            eggLoader.start();
            timeCounter.start();
            KeyCombination kc = new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_ANY);

            Runnable rn = () -> Model.exitAction(timeCounter, HS_Score, Game, eggLoader);

            KeyCombination UP = new KeyCodeCombination(KeyCode.UP);
            Runnable RN_UP = () -> {
                Game.setMickeyPoz(3);
                Game.getImageView().setImage(Game.getImage3());
            };

            KeyCombination DOWN = new KeyCodeCombination(KeyCode.DOWN);
            Runnable RN_DOWN = () -> {
                Game.setMickeyPoz(1);
                Game.getImageView().setImage(Game.getImage1());
            };

            KeyCombination LEFT = new KeyCodeCombination(KeyCode.LEFT);
            Runnable RN_LEFT = () -> {
                Game.setMickeyPoz(2);
                Game.getImageView().setImage(Game.getImage2());
            };

            KeyCombination RIGHT = new KeyCodeCombination(KeyCode.RIGHT);
            Runnable RN_RIGHT = () -> {
                Game.setMickeyPoz(4);
                Game.getImageView().setImage(Game.getImage4());
            };

            GameStage.getScene().getAccelerators().put(kc, rn);
            GameStage.getScene().getAccelerators().put(UP, RN_UP);
            GameStage.getScene().getAccelerators().put(DOWN, RN_DOWN);
            GameStage.getScene().getAccelerators().put(LEFT, RN_LEFT);
            GameStage.getScene().getAccelerators().put(RIGHT, RN_RIGHT);
            GameStage.getGame().show();
            GameStage.getGame().setOnCloseRequest(e -> Model.exitAction(timeCounter, HS_Score, Game, eggLoader));
        });

        menuScene.getHighScore().setOnAction(event -> {
            HS_Score = new HighScoreView();
            HS_Score.getHSstage().show();
        });

        menuScene.getExit().setOnAction(e -> Menu.close());
        menuScene.getManual().setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("GAME MANUAL");
            String s = """
                    Instrutcion:
                    QUIT GAME:\tCTRL+SHIFT+Q / Close Window
                    Mickey Top-Left:\t⬇
                    Mickey Bottom-Left:\t⬅
                    Mickey Top-Right:\t⬆
                    Mickey Bottom-Right:\t➡""";
            alert.setContentText(s);
            alert.show();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}