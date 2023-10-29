package main.FullPackage.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.Getter;

@Getter
public class MenuScene extends BorderPane {

    private final Button NewGame = new Button("New Game");
    private final Button HighScore = new Button("High Scores");
    private final Button Manual = new Button("Game Manual");
    private final Button Exit = new Button("Exit");
    private final BorderPane borderPane = new BorderPane();
    private final VBox vbox = new VBox();
    Scene Scene1;

    public MenuScene() {
        vbox.setPadding(new Insets(30));
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(NewGame, HighScore, Manual, Exit);
        borderPane.setCenter(vbox);
        Scene1 = new Scene(borderPane, 300, 300);
        Scene1.getStylesheets().add("CSSstyles/styleMenu.css");
    }
}





