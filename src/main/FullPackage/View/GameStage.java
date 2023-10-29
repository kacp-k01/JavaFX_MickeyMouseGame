package main.FullPackage.View;


import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Getter
@Setter
public class GameStage {
    Label timer = new Label();
    IntegerProperty counterLife = new SimpleIntegerProperty(4);
    Label life = new Label();
    IntegerProperty counterPoint = new SimpleIntegerProperty(0);
    Label point = new Label();
    Image image1;
    Image image2;
    Image image3;
    Image image4;
    ImageView imageView;
    private final Group backG = new Group();
    Ellipse EGG = new Ellipse();
    private int MickeyPoz = 1;
    private final BorderPane gamePane = new BorderPane();
    private final HBox LabelBox = new HBox();
    @Getter
    private static Scene scene;
    @Getter
    private static Stage Game;
    public GameStage() {
        LabelBox.setPadding(new Insets(15, 12, 15, 12));
        LabelBox.setSpacing(20);
        LabelBox.setAlignment(Pos.TOP_CENTER);
        IntegerProperty counter = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1000), new KeyValue(counter, 1000)));
        timer.textProperty().bind(Bindings.createStringBinding(() -> Integer.toString(counter.get()), counter));
        life.textProperty().bind(Bindings.createStringBinding(() -> Integer.toString(counterLife.get()), counter));
        point.textProperty().bind(Bindings.createStringBinding(() -> Integer.toString(counterPoint.get()), counter));
        Label timeTimer = new Label("Time:");
        Label lifeLife = new Label("Life:");
        Label pointPoint = new Label("Points:");
        LabelBox.getChildren().addAll(timeTimer,timer, lifeLife,life, pointPoint,point);
        LabelBox.setStyle("-fx-background-color: #59AB7C");
        EGG.setFill(Color.WHITE);

        try {
            image1 = new Image(new FileInputStream("src/main/resources/Board1.jpg"));
            image2 = new Image(new FileInputStream("src/main/resources/Board2.jpg"));
            image3 = new Image(new FileInputStream("src/main/resources/Board3.jpg"));
            image4 = new Image(new FileInputStream("src/main/resources/Board4.jpg"));
        }
        catch (FileNotFoundException e){
            System.err.println(e.getMessage());
            System.out.println("Brak plików graficznych");
        }

        imageView = new ImageView();
        imageView.setImage(image1);
        imageView.setX(0);
        imageView.setY(50);
        imageView.setFitWidth(1100);
        imageView.setFitHeight(700);
        imageView.setPreserveRatio(true);
        backG.getChildren().add(imageView);
        gamePane.setTop(LabelBox);
        gamePane.setBottom(backG);
        scene = new Scene(gamePane, 1100, 750);
        scene.getStylesheets().add("CSSstyles/styleGame.css");
        Game = new Stage();
        Game.setTitle("Game");
        Game.initModality(Modality.APPLICATION_MODAL);
        Game.setScene(scene);
        timeline.play();
    }


    public int getCounterLife() {
        return counterLife.get();
    }


    public void setCounterLife(int counterLife) {
        this.counterLife.set(counterLife);
    }

    public int getCounterPoint() {
        return counterPoint.get();
    }


    public void setCounterPoint(int counterPoint) {
        this.counterPoint.set(counterPoint);
    }

    public void setMickeyPoz(int mickeyPoz) {
        MickeyPoz = mickeyPoz;
    }




}
