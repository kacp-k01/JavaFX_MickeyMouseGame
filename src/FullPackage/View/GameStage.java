package FullPackage.View;


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

import java.io.FileInputStream;
import java.io.FileNotFoundException;


//actual game screen build class


public class GameStage {

    private Label TimeTimer = new Label("Time:");
    Label timer = new Label();

    private Label lifeLife = new Label("Life:");
    IntegerProperty counterLife = new SimpleIntegerProperty(4);
    Label life = new Label();


    private  Label pointPoint = new Label("Points:");
    IntegerProperty counterPoint = new SimpleIntegerProperty(0);
    Label point = new Label();

    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    Image image4 = null;

    ImageView imageView;



    private Group backG = new Group();



    Ellipse EGG = new Ellipse();




    private int MickeyPoz = 1;


    private BorderPane gamePane = new BorderPane();
    private HBox LabelBox = new HBox();
    private static Scene gameS;


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


        LabelBox.getChildren().addAll(TimeTimer,timer,lifeLife,life,pointPoint,point);
        LabelBox.setStyle("-fx-background-color: #59AB7C");


        EGG.setFill(Color.WHITE);


        try {
            image1 = new Image(new FileInputStream("Board1.jpg"));
            image2 = new Image(new FileInputStream("Board2.jpg"));
            image3 = new Image(new FileInputStream("Board3.jpg"));
            image4 = new Image(new FileInputStream("Board4.jpg"));



        }
        catch (FileNotFoundException e){
            e.printStackTrace();
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


        gameS = new Scene(gamePane, 1100, 750);
        gameS.getStylesheets().add("styleGame.css");



        Game = new Stage();

        Game.setTitle("Game");
        Game.initModality(Modality.APPLICATION_MODAL);

        Game.setScene(gameS);
        timeline.play();
    }



    public static Stage getGame() {
        return Game;
    }
    public static Scene getScene() {
        return gameS;
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


    public Image getImage1() {
        return image1;
    }

    public Image getImage2() {
        return image2;
    }

    public Image getImage3() {
        return image3;
    }

    public Image getImage4() {
        return image4;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Group getBackG() {
        return backG;
    }

    public Ellipse getEGG() {
        return EGG;
    }

    public int getMickeyPoz() {
        return MickeyPoz;
    }

}
