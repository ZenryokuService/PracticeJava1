package jp.zenryoku.swing;

import javax.swing.*;
import java.awt.*;

public class SwingMain {
    public static void main(String[] args) {
        SwingMain main = new SwingMain();
        JFrame frame = new JFrame("Window Test");
        frame.setLayout(new BorderLayout());

        Container cont = frame.getContentPane();
        main.darwNorth(cont);
        main.darwNCenter(cont);
        main.darwWest(cont);
        main.darwEast(cont);
        main.drawSouth(cont);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(500, 200));
        frame.setVisible(true);
    }

    /** 北を描く */
    private void darwNorth(Container cont) {
        cont.add(new JLabel("Hello World."), BorderLayout.NORTH);
    }
    /** 中央を描く */
    private void darwNCenter(Container cont) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("*************"));
        panel.add(new JLabel("**        **"));
        panel.add(new JLabel("**        **"));
        panel.add(new JLabel("**        **"));
        panel.add(new JLabel("*************"));
        cont.add(panel, BorderLayout.CENTER);
    }
    /** 西を描く */
    private void darwWest(Container cont) {
        JLabel west = new JLabel("西");
        west.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        cont.add(west, BorderLayout.WEST);
    }
    /** 東を描く */
    private void darwEast(Container cont) {
        JLabel east = new JLabel("東");
        east.setBorder(BorderFactory.createLineBorder(Color.RED));
        cont.add(east, BorderLayout.EAST);

    }
    /** 南を描く */
    private void drawSouth(Container cont) {
        JLabel south = new JLabel("南");
        south.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        cont.add(south, BorderLayout.SOUTH);

    }

}
