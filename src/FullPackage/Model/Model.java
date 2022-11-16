package FullPackage.Model;

import FullPackage.Threads.EggLoader;
import FullPackage.Threads.TimeCounter;
import FullPackage.View.GameStage;
import FullPackage.View.HighScoreView;
import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.*;
import java.util.Optional;

public class Model {

    public static int highScResNum;


//    high score result number getter, probalby not in the most elegant way
    public static void setLiczbaWyników() throws Exception {
        BufferedReader br = null;
        int temp;

        try {
            br = new BufferedReader(new FileReader("HighScoreResultNumber.txt"));
            temp = Integer.parseInt(br.readLine());

        } finally {


            if (br != null)
                br.close();

        }

        highScResNum = temp;
    }

//    HighScoreResultsList loader
    public static void LoadHS(ObservableList<String> ranking) throws Exception {

        ObjectInputStream ois = null;
        BufferedReader br = null;
        setLiczbaWyników();
        int LiczbaWyników;


        try {
            br = new BufferedReader(new FileReader("HighScoreResultNumber.txt"));
            LiczbaWyników = Integer.parseInt(br.readLine());


            ois = new ObjectInputStream(new FileInputStream("HighScoreResultsList.txt"));

            for (int i = 0; i < LiczbaWyników; i++) {
                Object o = ois.readObject();
                ranking.add(o.toString());
            }

        } finally {

            if (ois != null)
                ois.close();
            if (br != null)
                br.close();

        }
    }


//    score saver method
    public static void saveScore(TimeCounter time, HighScoreView HSview, GameStage game) {
        if (game.getCounterPoint() != 0) {

            Dialog<String> dialog = new TextInputDialog();
            dialog.setTitle("Game Save");
            dialog.setHeaderText("Enter your name");

            Optional<String> result = dialog.showAndWait();
            String Nick="";

            if (result.isPresent()) {
                int newInt = game.getCounterPoint()+time.getTime();
                Nick = result.get()+" "+newInt;
                HSview.getItems().add(Nick);
                highScResNum++;
                saveList(HSview);
            }

        }

    }


//    result saver method
    public static void saveList(HighScoreView HSview) {
        PrintWriter pw = null;
        ObjectOutputStream oos = null;

        try {
            int LW = HSview.getItems().size();

            oos = new ObjectOutputStream(new FileOutputStream("HighScoreResultsList.txt"));
            pw = new PrintWriter("HighScoreResultNumber.txt");
            String finalLevel = String.valueOf(LW);
            pw.write(finalLevel);

            for (int a = 0; a < LW; a++) {
                Object o = HSview.getItems().get(a);
                oos.writeObject(o);
            }

            if (oos != null)
                oos.close();
            if (pw != null)
                pw.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

//    exit the game action method
    public static void exitAction(TimeCounter timeCounter, HighScoreView HSview, Model model, GameStage GameS,
                                  EggLoader eggLoader){

        timeCounter.stop();
        eggLoader.stop();
        model.saveScore(timeCounter,HSview,GameS);

        timeCounter.setTime(0);
        timeCounter.setDifficulty(1);
        GameS.setCounterPoint(0);
        GameS.setCounterLife(4);
        System.out.println("Game Over");
        GameS.getGame().close();

    }

//    exit the game action method for the event loader

    public static void exitActionEGGLoad(TimeCounter timeCounter, HighScoreView HSview, Model model, GameStage GameS){

        timeCounter.stop();
        model.saveScore(timeCounter,HSview,GameS);

        timeCounter.setTime(0);
        timeCounter.setDifficulty(1);
        GameS.setCounterPoint(0);
        GameS.setCounterLife(4);
        System.out.println("Game Over");
        GameS.getGame().close();

    }


//    falling eggs animations method and two methods bellow for higher chicken nest and lower chicken nest
    public static void EggAnimation (GameStage gameStage, int StartX, int StartY, int EndX, int EndY, int speed) {


        int rotateStart = 0;
        int rotateEND = 360;

        if (StartX == 1000) {
            rotateStart = 360;
            rotateEND = 0;
        }


        RotateTransition rotateTransition = new RotateTransition(Duration.millis(speed),gameStage.getEGG());
        rotateTransition.setFromAngle(rotateStart);
        rotateTransition.setToAngle(rotateEND);

        PathTransition pathTransition = new PathTransition(Duration.millis(speed),gameStage.getEGG());

        Path path = new Path();
        path.getElements().add(new MoveTo(StartX,StartY));
        path.getElements().add(new LineTo(EndX,EndY));
        pathTransition.setNode(gameStage.getEGG());
        pathTransition.setPath(path);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(rotateTransition,pathTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.setAutoReverse(true);
        parallelTransition.play();

    }

    public static void HighFallAnimation(GameStage gameStage, int StartX, int StartY,int speed){

        PathTransition Fall = new PathTransition(Duration.millis(speed),gameStage.getEGG());
        Path path2 = new Path();
        path2.getElements().add(new MoveTo(StartX,StartY));
        path2.getElements().add(new LineTo(StartX,(StartY+170)));
        Fall.setNode(gameStage.getEGG());
        Fall.setPath(path2);
        Fall.play();
    }

    public static void LowFallAnimation(GameStage gameStage, int StartX, int StartY,int speed){

        PathTransition Fall = new PathTransition(Duration.millis(speed),gameStage.getEGG());
        Path path2 = new Path();
        path2.getElements().add(new MoveTo(StartX,StartY));
        path2.getElements().add(new LineTo(StartX,(StartY+50)));
        Fall.setNode(gameStage.getEGG());
        Fall.setPath(path2);
        Fall.play();
    }

}
