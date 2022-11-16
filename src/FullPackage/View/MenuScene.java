package FullPackage.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


//menu scene build class

public class MenuScene extends BorderPane {

    private Button NewGame = new Button("New Game");

    private Button HighScore = new Button("High Scores");
    private Button Manual = new Button("Game Manual");
    private Button Exit = new Button("Exit");
    private BorderPane borderPane = new BorderPane();
    private VBox vbox = new VBox();
    Scene Scene1;


    public MenuScene() {

        vbox.setPadding(new Insets(30));
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(NewGame, HighScore, Manual,Exit);

        borderPane.setCenter(vbox);
        Scene1 = new Scene(borderPane,300 ,300);

        Scene1.getStylesheets().add("styleMenu.css");

    }



    public Button getNewGame() {
        return NewGame;
    }

    public Button getHighScore() {
        return HighScore;
    }

    public Button getExit() {
        return Exit;
    }

    public Scene getScene1() {
        return Scene1;
    }

    public Button getManual() {
        return Manual;
    }

}





