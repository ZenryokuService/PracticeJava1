package jp.zenryoku.sample.swing;

import javax.swing.*;
import java.awt.*;

public class PracSwingMain extends JFrame {
    public static void main(String[] args) {
        // 土台クラス
        JFrame frame = new JFrame("FirstSwing");
        // 土台のコンポーネントを載せる部分(位置などは後で指定する)
        // コンテナーと呼びます
        Container con = frame.getContentPane();
        //コンテナの大記載をしています
        con.setSize(300, 300);
        // コンテナの上にラベルを配置します
        con.add(new JLabel("Hello Swing"));
        // 土台の上に乗せたものをおきます
        frame.setContentPane(con);
        // お約束ごとで閉じる時にこのクラスの起動を終了する設定など。。。
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // 画面を表示する設定→これをFALSEにすると画面が非表示になる
        frame.setVisible(true);
        int i = 2147483647;
        short shor = 32767;
    }
}
