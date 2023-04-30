package jp.zenryoku.sample.observer;

import java.util.Observable;
import java.util.function.Function;

public class ObserverSample extends Observable {
    /** 引数String, 返却値Stringのメソッド */
    private Function<String, String> func;

    public ObserverSample(Function fun) {
        this.func = fun;
    }

    public void execute(String str) {
        System.out.println(func.apply(str));
    }

    public static void main(String[] ags) {

        ObserverSample sample = new ObserverSample(str -> {
            return "aaa: " + str;
        });


        ChildOverver ovs = new ChildOverver("aaa");
        ChildOverver ovs1 = new ChildOverver("bbb");
        ChildOverver ovs2 = new ChildOverver("ccc");
        sample.addObserver(ovs);
        sample.addObserver(ovs1);
        sample.addObserver(ovs2);

        sample.setChanged();
        sample.notifyObservers();
        System.out.println("^^^^^^^^^^^^^^^^^^^^");
        sample.setChanged();
        sample.notifyObservers("pppppp");
    }
}
