package jp.zenryoku;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * JavaFXでのハローワールド〜OpenCVなどの
 * 作成したアプリをテストするための、スタンドアロンアプリ。
 *
 * @author takunoji
 * 2019/01/23
 */
public class RootFxMain extends Application {
    /** 画面の縦幅 */
    private double VIEW_HEIGHT;
    /** 画面の横幅 */
    private double VIEW_WIDTH;
    /**
     * 親クラスのメソッドをオーバーライドする。
     * 画面を作成したり、シーンを作成したり、色々。。。
     *
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Stageの設定
        primaryStage.setHeight(VIEW_HEIGHT);
        primaryStage.setWidth(VIEW_WIDTH);
        // ラベルの設定
        Label label = new Label();

        // ハローワールドを出力する
        label.setText(myFirstProgram());
        label.setFont(new Font("RobotRegular", 24));

        // ペインの作成
        Group root = new Group();
        root.getChildren().add(label);

        // シーンの作成
        Scene scene = new Scene(root, VIEW_WIDTH, VIEW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * メインメソッド
     * @param args プログラム引数
     */
    public static void main(String[] args) {
        // 親クラスのメソッドを呼び出す、これは上のstart()を呼び出す。
        launch();
    }

    /**
     * JavaFX版のハローワールド実装用のメソッドになります。
     * @return 画面に出力する文字列
     */
    public String myFirstProgram() {
        // この「hyoji = ""」を「"hyoji = "Hello World"」と修正してください。
        String hyoji = "Hello World";
        return hyoji;
    }
}