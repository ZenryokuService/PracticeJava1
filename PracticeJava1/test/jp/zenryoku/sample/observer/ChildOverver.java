package jp.zenryoku.sample.observer;

import java.util.Observable;
import java.util.Observer;

public class ChildOverver implements Observer {
    private String str;
    public ChildOverver(String str) {
        this.str = str;
    }
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Param is " + str);
        System.out.println("Count: " + o.countObservers());
        System.out.println("arg: " + arg);
    }

    public void setStr(String newStr) {
        this.str = newStr;
    }
}
