package jp.zenryoku.sample.swing;

import javax.swing.*;
import java.awt.*;

public class SwingSample extends JFrame {
    private static SwingSample main;

    public SwingSample() {
        setTitle("テスト用Swing画面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 300, 600, 300);
    }

    public static void init() {
        // メインメソッドのある、このクラス
        main = new SwingSample();
        Container con = main.getContentPane();

        // JPanelクラス
        JPanel pn = new JPanel();
        pn.setLayout(new BorderLayout());
        con.add(pn);
        // マイラベル
        MyLabel lbl = new MyLabel("Hello World");
        pn.add(lbl, BorderLayout.NORTH);
        // マイチェックボックス
        MyCheckBox chk = new MyCheckBox();
        pn.add(chk, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        init();
        main.setVisible(true);
    }
}
