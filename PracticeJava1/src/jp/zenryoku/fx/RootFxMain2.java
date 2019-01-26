package jp.zenryoku.fx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * JavaFXでのハローワールド〜OpenCVなどの
 * 作成したアプリをテストするための、スタンドアロンアプリ。
 * 
 * @author takunoji
 * 2019/01/23
 */
public class RootFxMain2 extends Application {
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
		// 画面部分とコントロールボタン部分にレイアウト(表示領域を分ける)
		BorderPane baseLayout = new BorderPane();
		baseLayout.setCenter(createJavaBasicPane());
		primaryStage.setScene(new Scene(baseLayout, VIEW_WIDTH, VIEW_HEIGHT));
		primaryStage.show();
	}

	/**
	 * シーンの作成部分を切り出ししました。
	 * このシーンはJavaの基本を実行する時用にします。
	 * 
	 * @see http://zenryokuservice.com/wp/2019/01/25/java-stepupprogr…avafxで画面切り替えを作る〜/
	 * @return JavaBasic用のPane
	 */
	private Pane createJavaBasicPane() {
		// レイアウトたて
		VBox vBox = new VBox(5);
		// レイアウト横
		HBox hBox = new HBox(8);
		// ラベルの設定
		Label label = new Label();
		// ハローワールドを出力する
		label.setText(myFirstProgram());
		label.setFont(new Font("RobotRegular", 24));
		vBox.getChildren().add(label);
	
		// １個目の数値、テキストフィールド
		TextField text1 = new TextField();
		text1.setPrefColumnCount(3);
		text1.setAlignment(Pos.BASELINE_CENTER);
		hBox.getChildren().add(text1);
		// 計算式のラベル
		Label ope = new Label("+");
		hBox.getChildren().add(ope);
	
		// ２個目の数値、テキストフィールド
		TextField text2 = new TextField();
		text1.setPrefColumnCount(3);
		text1.setAlignment(Pos.BASELINE_CENTER);
		hBox.getChildren().add(text2);
	
		// 縦のレイアウトに追加する
		vBox.getChildren().add(hBox);
	
		// シーンの作成
		return vBox;
	}

	/**
	 * HTMLローダーの画面(シーン)を作成します。
	 * 
	 * @return HTンLローダー画面
	 */
	private Scene createHtmlLoaderScene() {
		// ルート・レイアウト
		Group root = new Group();
		
		return new Scene(root, VIEW_WIDTH, VIEW_HEIGHT);
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
		long num = 12345678901L;
		float shosu = 123.09876543f;
		return hyoji;
	}
}
