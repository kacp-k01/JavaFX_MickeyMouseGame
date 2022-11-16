package FullPackage.Threads;

import FullPackage.Model.Model;
import FullPackage.View.GameStage;
import FullPackage.View.HighScoreView;
import javafx.application.Platform;


//thread class for loading falling eggs in the game
public class EggLoader extends Thread {
    TimeCounter timeCounter;
    GameStage gameStage;
    Model model;
    HighScoreView HS_Score;

    public EggLoader(TimeCounter timeCounter, GameStage gameStage,Model model,  HighScoreView HS_Score){

        this.timeCounter=timeCounter;
        this.gameStage=gameStage;
        this.model=model;
        this.HS_Score=HS_Score;
    }

    @Override
    public void run() {
        int a = 1;
        int poz = 1;

        while (true) {

            int speed = 2000 - timeCounter.getDifficulty() * 10;
            if (speed < 500)
                speed = 500;

            int HighFallSpeed = 800 - timeCounter.getDifficulty() * 5;
            if (HighFallSpeed < 400)
                HighFallSpeed = 400;

            int LowlFallSpeed = 400 - timeCounter.getDifficulty() * 5;
            if (LowlFallSpeed < 200)
                LowlFallSpeed = 200;


            while (a == 1) {
                if (gameStage.getCounterLife() == 0) {
                    Platform.runLater(() -> model.exitActionEGGLoad(timeCounter, HS_Score, model, gameStage));
                    stop();
                }

                int los = (int) (4 * Math.random());


                if (los == 0) {
//eggs from top-left nest - 1
                    poz = 1;
                    gameStage.getEGG().setCenterX(100.0f);
                    gameStage.getEGG().setCenterY(275.0f);
                    gameStage.getEGG().setRadiusX(10.0f);
                    gameStage.getEGG().setRadiusY(15.0f);
                    int finalSpeed = speed;
                    Platform.runLater(() -> {
                        gameStage.getBackG().getChildren().add(gameStage.getEGG());
                        model.EggAnimation(gameStage, 100, 275, 300, 380, finalSpeed);
                    });

                }

                if (los == 1) {
//eggs from bottom-left nest - 2
                    poz = 2;
                    gameStage.getEGG().setCenterX(100.0f);
                    gameStage.getEGG().setCenterY(445.0f);
                    gameStage.getEGG().setRadiusX(10.0f);
                    gameStage.getEGG().setRadiusY(15.0f);
                    int finalSpeed1 = speed;
                    Platform.runLater(() -> {
                        gameStage.getBackG().getChildren().add(gameStage.getEGG());
                        model.EggAnimation(gameStage, 100, 445, 300, 550, finalSpeed1);
                    });
                }

                if (los == 2) {
//eggs from top-right nest - 3
                    poz = 3;
                    gameStage.getEGG().setCenterX(1000.0f);
                    gameStage.getEGG().setCenterY(275.0f);
                    gameStage.getEGG().setRadiusX(10.0f);
                    gameStage.getEGG().setRadiusY(15.0f);
                    int finalSpeed2 = speed;
                    Platform.runLater(() -> {
                        gameStage.getBackG().getChildren().add(gameStage.getEGG());
                        model.EggAnimation(gameStage, 1000, 275, 800, 380, finalSpeed2);
                    });
                }
                if (los == 3) {
//eggs from bottom-right nest - 4
                    poz = 4;
                    gameStage.getEGG().setCenterX(1000.0f);
                    gameStage.getEGG().setCenterY(445.0f);
                    gameStage.getEGG().setRadiusX(10.0f);
                    gameStage.getEGG().setRadiusY(15.0f);
                    int finalSpeed3 = speed;
                    Platform.runLater(() -> {
                        gameStage.getBackG().getChildren().add(gameStage.getEGG());
                        model.EggAnimation(gameStage, 1000, 445, 800, 550, finalSpeed3);
                    });
                }

                try {
                    Thread.sleep(speed);
                } catch (InterruptedException e) {
                    System.out.println("Błąd licznika, zrestartuj grę");
                }

                a++;
            }


            while (a == 2) {

                if (poz == 2 || poz == 4) {
                    if (gameStage.getMickeyPoz() == poz)
                        gameStage.setCounterPoint(gameStage.getCounterPoint() + 1);
                    else {
                        gameStage.setCounterLife(gameStage.getCounterLife() - 1);
                        if (poz == 2) {
                            int finalLowlFallSpeed = LowlFallSpeed;
                            Platform.runLater(() -> model.LowFallAnimation(gameStage, 300, 550, finalLowlFallSpeed));
                        } else {
                            int finalLowlFallSpeed1 = LowlFallSpeed;
                            Platform.runLater(() -> model.LowFallAnimation(gameStage, 800, 550, finalLowlFallSpeed1));
                        }
                        try {
                            Thread.sleep(LowlFallSpeed);
                        } catch (InterruptedException e) {
                            System.out.println("Błąd licznika, zrestartuj grę");
                        }
                    }
                    Platform.runLater(() -> gameStage.getBackG().getChildren().remove(gameStage.getEGG()));

                } else if((poz==3 || poz ==1) && gameStage.getMickeyPoz() == poz){
                    gameStage.setCounterPoint(gameStage.getCounterPoint() + 1);
                    Platform.runLater(() -> gameStage.getBackG().getChildren().remove(gameStage.getEGG()));
                    poz = 0;
                }

                else {
                    if (poz == 1) {
                        int finalHighFallSpeed = HighFallSpeed;
                        Platform.runLater(() -> model.HighFallAnimation(gameStage, 300, 380, finalHighFallSpeed));
                    } else if (poz == 3) {
                        int finalHighFallSpeed1 = HighFallSpeed;
                        Platform.runLater(() -> model.HighFallAnimation(gameStage, 800, 380, finalHighFallSpeed1));
                    }
                    try {
                        Thread.sleep(HighFallSpeed);
                    } catch (InterruptedException e) {
                        System.out.println("Błąd licznika, zrestartuj grę");
                    }
                }
                a++;
            }


            while (a == 3) {
                if ((poz == 1&& gameStage.getMickeyPoz()==2)||(poz == 3&& gameStage.getMickeyPoz()==4)) {
                    gameStage.setCounterPoint(gameStage.getCounterPoint() + 1);
                    Platform.runLater(() -> gameStage.getBackG().getChildren().remove(gameStage.getEGG()));
                }
                    else if (poz==1||poz==3){
                        gameStage.setCounterLife(gameStage.getCounterLife() - 1);

                    if (poz == 1) {
                        int finalLowlFallSpeed = LowlFallSpeed;
                        Platform.runLater(() -> model.LowFallAnimation(gameStage, 300, 550, finalLowlFallSpeed));
                    } else {
                        int finalLowlFallSpeed1 = LowlFallSpeed;
                        Platform.runLater(() -> model.LowFallAnimation(gameStage, 800, 550, finalLowlFallSpeed1));
                    }

                    try {
                        Thread.sleep(LowlFallSpeed);
                    } catch (InterruptedException e) {
                        System.out.println("Błąd licznika, zrestartuj grę");
                    }
                    Platform.runLater(() -> gameStage.getBackG().getChildren().remove(gameStage.getEGG()));
                }
                a = a - 2;
            }
        }

    }
}







