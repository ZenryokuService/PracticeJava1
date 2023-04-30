package jp.basic.javautil;

import java.util.TimerTask;

public class TimerTaskImpl extends TimerTask {
    @Override
    public void run() {
        System.out.println("TimerTask Execute");
    }
}
