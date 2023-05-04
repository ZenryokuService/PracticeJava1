package jp.zenryoku.sample.daily;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class MathSample extends JFrame {
    private int sizeX = 500;
    private int sizeY = 500;
    private int startX = 0;
    private int startY = 27;
    private int centerX = sizeX / 4 - 30;
    private int centerY = sizeY / 4;
    private int x = (centerX + 150);
    private int y = centerY + 150;
    private int r = 150;


    private Consumer<Graphics> drawFunction;

    public static void main(String[] args) {
        MathSample main = new MathSample();
        main.setVisible(true);
    }

    public MathSample() {
        setTitle("MathTest");
        setBounds(450, 250, 500,500);
        //setSize(sizeX, sizeY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        double rad = Math.toRadians(60.0);
        System.out.println((int) (r * Math.cos(rad)));
        System.out.println((int) (r * Math.sin(rad)));
        drawSankakukansu(radian());
    }

    @Override
    public void paint(Graphics g) {
        //super.paintComponents(g);
        if (drawFunction != null) {
            myPaint(g, drawFunction);
        } else {
            g.drawLine(startX, startY, sizeX, sizeY);
        }
    }

    private void myPaint(Graphics g, Consumer<Graphics> f) {
        g.drawOval(centerX, centerY, r * 2, r * 2);
        f.accept(g);
    }

    public void init() {
        new MathSample();
    }

    /**
     * ラジアンとは、「円（扇形）の孤の長さ(L)/円の半径(r)」によって求められる値
     * 中心の角度＝中心の角度 * (円周率 / 180)
     * 30度 = 円周率 * 6;
     * 45度 = 円周率 * 4;
     */

    public void drawSankakukansu(double rad) {
        this.drawFunction = new Consumer<Graphics>() {
            @Override
            public void accept(Graphics graphics) {
                int xValue = (int) (r * Math.cos(rad));
                int yValue = (int) (r * Math.sin(rad));
                graphics.drawLine(x, y, x + xValue, y - yValue);
            }
        };
        setVisible(true);
    }

    public double radian() {
        return Math.toRadians(45);
    }
}
