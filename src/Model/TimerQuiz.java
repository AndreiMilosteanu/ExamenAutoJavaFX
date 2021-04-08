package Model;

import com.sun.glass.ui.Application;
import javafx.application.Platform;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Label;

public class TimerQuiz implements Runnable{

    static Timer timer;
    static int counter = 1800;

    public void resetCounter(){
        counter = 1800;
    }
    public void createTimer(Label l) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                counter--;
            }
        };

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //System.out.println("counter: " + counter);
                        Platform.setImplicitExit(false);
                        Platform.runLater(()->l.setText("Timp ramas: "+counter/60+" minute "+counter%60+" secunde"));
                        if (counter == 0) {
                            System.out.println("Counter has reached 0 now will terminate");
                            timer.cancel();
                            break;
                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        Timer timer = new Timer("MyTimer");
        timer.scheduleAtFixedRate(timerTask, 30, 1000);
        thread.start();
    }

    @Override
    public void run() {

    }
}
