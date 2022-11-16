package FullPackage.Controller;

import FullPackage.Model.Model;
import FullPackage.Threads.EggLoader;
import FullPackage.Threads.TimeCounter;
import FullPackage.View.GameStage;

import FullPackage.View.HighScoreView;
import FullPackage.View.MenuScene;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;


//please check, if you have all the correct libraries, as well as correct run configuration for javaFX



public class Main extends Application {

    Model model = new Model();
    MenuScene menuScene = new MenuScene();
    HighScoreView HS_Score = new HighScoreView(model);



    @Override
    public void start(Stage Menu) {

        Menu.setTitle("Mickey Mouse");
        Menu.setScene(menuScene.getScene1());
        Menu.show();

//        menu set up
        menuScene.getNewGame().setOnAction(event -> {




            GameStage Game = new GameStage();

            TimeCounter timeCounter = new TimeCounter();
            EggLoader eggLoader = new EggLoader(timeCounter,Game,model,HS_Score);


            eggLoader.start();
            timeCounter.start();
//keyboard shortcut for quiting the game
            KeyCombination kc = new KeyCodeCombination(KeyCode.Q,KeyCombination.CONTROL_DOWN,KeyCombination.SHIFT_ANY);



            Runnable rn = ()-> Model.exitAction(timeCounter,HS_Score,model,Game,eggLoader) ;


//            Mickey controls during the game
            KeyCombination UP = new KeyCodeCombination(KeyCode.UP);
            Runnable RN_UP = ()-> {
                Game.setMickeyPoz(3);
                Game.getImageView().setImage(Game.getImage3());
            };

            KeyCombination DOWN = new KeyCodeCombination(KeyCode.DOWN);
            Runnable RN_DOWN = ()-> {
                Game.setMickeyPoz(1);
                Game.getImageView().setImage(Game.getImage1());
            };

            KeyCombination LEFT = new KeyCodeCombination(KeyCode.LEFT);
            Runnable RN_LEFT = ()-> {
                Game.setMickeyPoz(2);
                Game.getImageView().setImage(Game.getImage2());
            };

            KeyCombination RIGHT = new KeyCodeCombination(KeyCode.RIGHT);
            Runnable RN_RIGHT = ()-> {
                Game.setMickeyPoz(4);
                Game.getImageView().setImage(Game.getImage4());
            };






            Game.getScene().getAccelerators().put(kc, rn);
            Game.getScene().getAccelerators().put(UP, RN_UP);
            Game.getScene().getAccelerators().put(DOWN, RN_DOWN);
            Game.getScene().getAccelerators().put(LEFT, RN_LEFT);
            Game.getScene().getAccelerators().put(RIGHT, RN_RIGHT);

            Game.getGame().show();
            Game.getGame().setOnCloseRequest(e -> Model.exitAction(timeCounter,HS_Score,model,Game,eggLoader));



            });

        menuScene.getHighScore().setOnAction(event -> {

            HS_Score = new HighScoreView(model);

            HS_Score.getHSstage().show();

        });

        menuScene.getExit().setOnAction(e -> Menu.close());

        menuScene.getManual().setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("GAME MANUAL");
            String s ="Instrutcion:\nQUIT GAME:\tCTRL+SHIFT+Q / Close Window"+
                    "\nMickey Top-Left:\t\u2B07"+"\nMickey Bottom-Left:\t\u2B05"+
                    "\nMickey Top-Right:\t\u2B06"+"\nMickey Bottom-Right:\t\u27A1";
            alert.setContentText(s);
            alert.show();
        });




    }


    public static void main(String[] args) {
        launch(args);
    }


}