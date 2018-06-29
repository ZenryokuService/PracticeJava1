package jp.zenryoku.game.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.TextArea;

import java.security.Key;
import java.util.List;
import java.util.ArrayList;

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
    /** コマンドの入力開始文字 */
    private static final String CMD_START = "Cmd $ >";
    /** 改行コード */
    private static final String LINE_SEPARETOR = System.getProperty("line.separator");
    /** 入力前のカーソル位置 */
    private int cursorPos;

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
        // 初期表示文字を設定する
        area.setText("Hello user please input command!" + LINE_SEPARETOR + CMD_START);
        // テキストエリアの文字列数
        int textLen = area.getText().length();
        // 初期カーソル位置
        cursorPos = textLen;
        area.positionCaret(textLen);

        return area;
    }

    private EventHandler<KeyEvent> createKeyPressEvent() {
        return new EventHandler<KeyEvent>() {
            public void handle(KeyEvent evt) {
                // テキストエリアを取得する
                TextArea src = (TextArea) evt.getSource();
                // 入力を無効にする
                if (isDisabledInput(evt)) {
                    System.out.println("*** isDisable ***");
                    resetCursor(src);
                    return;
                }
                if (KeyCode.ENTER.equals(evt.getCode())) {
                    // テキストエリア内の文字列を全て取得
                    String allText = src.getText();
                    String command = getCommand(allText);
                    src.setText(allText + CMD_START);
                    // "Cmd $ "の文字列の位置を取得
                    System.out.println("Command: " + command);
                    cursorPos = allText.length() + CMD_START.length();
                    src.positionCaret(cursorPos);
                }
                if (KeyCode.LEFT.equals(evt.getCode())) {
                    // 左の矢印が押下された時
                    src.positionCaret(cursorPos);
                }
                // チェック用のコンソール出力処理
                System.out.println("EventType: " + evt.getCode());
            }
        };
    }

    /**
     * キーボードより入力したキーで受け付けないものを<BR/>
     * 判定する
     * @param evt
     * @return true: 受け付けない入力  /  false: 受け付ける入力
     */
    private boolean isDisabledInput(KeyEvent evt) {
        boolean isDisable = false;
        // 入力許可キーのKeyCodeリスト
        List<KeyCode> acList = createAcceptList();

        // チェック処理: 入力禁止するキーの有無をチェック
        return acList.contains(evt.getCode());
    }
    /**
     * プロパティファイル、キーを指定して対象のプロパティを<BR/>
     * 取得する
     *
     * @param propNane プロパティファイル名
     * @param key プロパティのキー
     * @return プロパティの値
     */
    @Deprecated // 使用しないメソッド
    private String getTargetProperty(String propNane, String key) {
        String propStr = null;
        return propStr;
    }

    /**
     * チェック用のリストを作成して返却します<BR/>
     * 入力禁止のKeyCodeを追加
     * @return チェック用のリスト
     */
    private List<KeyCode> createAcceptList() {
        List<KeyCode> acList = new ArrayList<KeyCode>();
        acList.add(KeyCode.UP);
        acList.add(KeyCode.DOWN);

        return acList;
    }

    /**
     * カーソルの移動をしないようにします。
     * @param src
     */
    private void resetCursor(TextArea src) {
        // 始めのカーソル位置を設定する
        src.positionCaret(cursorPos);
    }

    /**
     * コマンドを取得します<BR/>
     * @param text テキストエリアにある文字列全部
     * @return 入力した文字列部分のみ
     */
    private String getCommand(String text) {
        String[] lines = text.split(LINE_SEPARETOR);
        String target = lines[lines.length - 1];
        return target.substring(CMD_START.length());
    }

    /** メインメソッド */
    public static void main(String[] args) {
        launch();
    }
}
