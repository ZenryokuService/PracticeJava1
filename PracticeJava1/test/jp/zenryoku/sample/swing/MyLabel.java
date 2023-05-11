package jp.zenryoku.sample.swing;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel {

    public MyLabel(String title) {
        super(title);
        setFont(createFont());
        setHorizontalAlignment(JLabel.CENTER);
    }

    private Font createFont() {
        return new Font("ＭＳ ゴシック", Font.BOLD, 16);
    }
}
