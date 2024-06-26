package jp.zenryoku.game.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    /** プロパティファイル名 */
    private static final String CMD_PROPERTY = "CmdCls";
    /** 入力前のカーソル位置 */
    private int currentPos;
    /** 改行した時のカーソル位置 */
    private int startCursorPos;
    /** リソースバンドル */
    private ResourceBundle bndle;

    @Override
    public void start(Stage primary) {
        TextArea area = createTextArea();
        Group root = new Group();
        root.getChildren().add(area);
        Scene scene = new Scene(root, VIEW_WIDTH, VIEW_HEIGHT);
        primary.setScene(scene);
        primary.show();

        // プロパティファイルのロード
        bndle = ResourceBundle.getBundle(CMD_PROPERTY);
        System.out.println("Properties: " + bndle.getString("acc"));
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
        currentPos = textLen;
        startCursorPos = textLen;
        area.positionCaret(textLen);

        return area;
    }

    /**
     * イベント処理を行うクラスを生成<BR/>
     * ・入力チェックを行い、禁止入力があった場合はカーソルを元の位置に戻す。
     * @return キー入力のイベント処理クラス
     */
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
                    String formatted = formatText(allText);
                    //String command = getCommand(formatted);
                    // "Cmd $ "の文字列の位置を取得
                    System.out.println("Fprmated: " + formatted);
                    //System.out.println("Command: " + command);
                    // コマンドの実行
                    allText = allText + LINE_SEPARETOR + executeCmd(formatted) + LINE_SEPARETOR;

                    // コマンド実行後
                    src.setText(allText + CMD_START);
                    currentPos = allText.length() + CMD_START.length();
                    startCursorPos = currentPos;
                    src.positionCaret(currentPos);
                }
                if (KeyCode.LEFT.equals(evt.getCode())) {
                    System.out.println("Sart: " + startCursorPos + " Current: " + currentPos + " getCaret: " + src.getCaretPosition());
                    if (startCursorPos > src.getCaretPosition()) {
                        // 左の矢印が押下された時
                        src.positionCaret(currentPos);
                    }
                }
                currentPos = src.getCaretPosition();
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
        // 削除ボタンが押下されたとき
        if (KeyCode.DELETE.equals(evt.getCode())) {

        }
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
        src.positionCaret(currentPos);
    }

    /**
     * コマンドを取得します<BR/>
     * @param text テキストエリアにある文字列全部
     * @return 入力した文字列部分のみ
     */
    private String getCommand(String text) {
        System.out.println(text);
        String[] lines = text.split(LINE_SEPARETOR);
        String target = lines[lines.length - 1];
        // コマンドが途切れていないかチェック
        int chkInt = target.indexOf(CMD_PROPERTY);
        if (chkInt == -1) {
            System.out.println("line -1: " + lines[lines.length - 2]);
            System.out.println("line: " + lines[lines.length - 1]);

        }
        return target.substring(CMD_START.length());
    }

    /**
     * コマンドを実行します<BR/>
     * 登録したコマンドにない入力の時は<BR/>
     * エラーメッセージをテキストエリアに出力します。
     * @param command コマンド文字列
     */
    private String executeCmd(String command) {
        String response = "";
        // 終了コマンドはすぐに動く
        if ("exit".equals(command)) {
            System.exit(0);
        }
        try {
            String className = bndle.getString(command);
            System.out.println("ClassName: " + className);
            // 完全クラス名よりクラスのインスタンスを取得する
            Class<?> cls = Class.forName(className);
            Constructor<?> cons = cls.getConstructor();
            CommandIF exe = (CommandIF) cons.newInstance();
            response = exe.execute();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private String formatText(String areaTxt) {
        return areaTxt.split(">")[1];
    }
    /** メインメソッド */
    public static void main(String[] args) {
        launch();
    }
}
