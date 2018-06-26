package jp.zenryoku.sample.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.TextArea;

import java.awt.*;

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
        area.setOnKeyTyped(createInputEvent());

        return area;
    }

    private EventHandler<KeyEvent> createInputEvent() {
        EventHandler<KeyEvent> evt = new EventHandler<KeyEvent>() {
            public void handle(KeyEvent evt) {
                System.out.println("EventType: " + evt.getCode());
                System.out.println("Input: " + evt.getCharacter());
            }
        };
        return evt;
    }

    /** メインメソッド */
    public static void main(String[] args) {
        launch();
    }
}
