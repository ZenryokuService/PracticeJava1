package jp.zenryoku.swing;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class SwingHtml extends JFrame {
    private static final String HTML =
            "<H1>Hello JEditorPane!</H1>" +
                    "<UL>" +
                    "<LI>HTML ならリスト形式で表示できます。" +
                    "<LI><B>ボールド体</B>、<I>イタリック体</I>、<U>アンダーライン</U>" +
                    "<LI><FONT color='blue'>青色</FONT>、<FONT color='red'>赤色</FONT>" +
                    "<LI><BIG>BIG</BIG>、NORMAL、<SMALL>SMALL</SMALL>" +
                    "</UL>" +
                    "<img src='./resources/images/Experience.png' />" +
                    "<TABLE border='1'>" +
                    "<TR><TH>テーブル</TH><TH>も一応</TH></TR>" +
                    "<TR><TD>表示</TD><TD>できるみたいです。</TD>" +
                    "</TABLE>";
    public static void main(String[] args) {
        SwingHtml main = new SwingHtml();
//        JEditorPane editorPane = new JEditorPane("text/html", HTML);
        JEditorPane editorPane = null;
        try  {
            URL url = new File("PracticeJava1/resources/012.html").toPath().toUri().toURL();
            editorPane = new JEditorPane(url);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        editorPane.setEditable(false);
        editorPane.setPreferredSize(new Dimension(200, 150));
        JScrollPane scrollPane = new JScrollPane(editorPane);

        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        main.getContentPane().add(panel);
        main.setSize(new Dimension(400, 300));
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
    }

    private void createHtml() {

    }
}
