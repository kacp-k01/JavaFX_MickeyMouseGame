package FullPackage.Threads;

// thread for counting the time during the game and difficulty changer for the speed of eggs
public class TimeCounter extends Thread{

    private static int time = 0;
    private static int difficulty = 1;

    @Override
    public void run(){
        while(true){

           time++;

            if(time%5==0) {
                difficulty++;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Błąd licznika, zrestartuj grę");
            }

        }
    }


    public static void setTime(int time) {
        TimeCounter.time = time;
    }


    public static void setDifficulty(int difficulty) {
        TimeCounter.difficulty = difficulty;
    }


    public static int getTime() {
        return time;
    }

    public static int getDifficulty() {
        return difficulty;
    }


}