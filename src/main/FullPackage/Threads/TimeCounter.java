package main.FullPackage.Threads;

import lombok.Getter;
import lombok.Setter;

public class TimeCounter extends Thread {
    @Getter
    @Setter
    private static int Time = 0;
    @Getter
    @Setter
    private static int Difficulty = 1;

    @Override
    public void run() {
        while (true) {
            Time++;
            if (Time % 5 == 0) {
                Difficulty++;
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Błąd licznika, zrestartuj grę");
            }
        }
    }
}