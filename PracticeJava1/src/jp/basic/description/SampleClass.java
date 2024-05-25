package jp.basic.description;

import javax.swing.*;
import java.awt.*;

public class SampleClass extends JFrame {

    public void execute() {
        System.out.println("Execute");
    }

    public void execute(String message) {
        System.out.println(message);

        setTitle(message);
        setBounds(200, 200
                , 300, 400);
        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        Container cont = getContentPane();
        MtLabel lbl = new MtLabel(message);
        JPanel pn = new JPanel();
        pn.setLayout(new BorderLayout());
        pn.add(lbl, BorderLayout.NORTH);
        JPanel butPn = this.createButPanel();
        for (int i = 0; i < 20; i++) {
            butPn.add(new MtButton(
                    this.buttonNames()[i]
                    , lbl));
        }
        pn.add(butPn, BorderLayout.CENTER);
        pn.add(new JButton("South"), BorderLayout.SOUTH);

        cont.add(pn);

        setVisible(true);

    }

    public JPanel createButPanel() {
        JPanel butPn = new JPanel();
        butPn.setLayout(new GridLayout(4, 5));
        return butPn;
    }

    private String[] buttonNames() {
        return new String[] {"AC", "7", "8", "9", "/"
                , "%", "4", "5", "6", "*"
                , "^", "1", "2", "3", "-"
                , "√", "0", ".", "=", "+"};
    }
}
