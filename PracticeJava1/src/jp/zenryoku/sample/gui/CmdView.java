package jp.zenryoku.sample.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.TextArea;

import java.awt.*;

/**
 * @see <a href="https://docs.oracle.com/javase/jp/8/javafx/api/javafx/event/EventType.html"/>
 * @see <a href="https://docs.oracle.com/javase/jp/8/javafx/api/javafx/scene/input/KeyEvent.html"/>
 * @see <a href="https://docs.oracle.com/javase/jp/8/javafx/api/toc.htm"/>
 */
public class CmdView extends Application {
    /** 画面の横サイズ */
    private static final int VIEW_WIDTH = 300;
    /** 画面のたてサイズ */
    private static final int VIEW_HEIGHT = 300;

    @Override
    public void start(Stage primary) {
        TextArea area = createTextArea();
        Group root = new Group();
        root.getChildren().add(area);
        Scene scene = new Scene(root, VIEW_WIDTH, VIEW_HEIGHT);
        primary.setScene(scene);
        primary.show();
    }

    /**
     * TextAreaを作成して返却する
     * @return TextArea
     */
    private TextArea createTextArea() {
        TextArea area = new TextArea();
        // 縦横の幅を設定する
        area.setPrefWidth(VIEW_WIDTH);
        area.setPrefHeight(VIEW_HEIGHT);
        area.setOnKeyPressed(createKeyPressEvent());

        return area;
    }

    private EventHandler<KeyEvent> createKeyPressEvent() {
        return new EventHandler<KeyEvent>() {
            public void handle(KeyEvent evt) {
                if (KeyCode.ENTER.equals(evt.getCode())) {
                    // Enter キーを謳歌した時の処理、テキストエリアを取得する
                    TextArea src = (TextArea) evt.getSource();
                    System.out.println("Press Enter: " + src.getText());
                }
                // チェック用のコンソール出力処理
                System.out.println("EventType: " + evt.getCode());
                System.out.println("Input: " + evt.getCharacter());
            }
        };
    }

    /** メインメソッド */
    public static void main(String[] args) {
        launch();
    }
}
