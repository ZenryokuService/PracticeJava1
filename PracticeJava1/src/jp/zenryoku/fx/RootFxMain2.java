package jp.zenryoku.fx;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
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
	/** コントロールボタンのリスト */
	private ArrayList<Button> buttonList;
	/** startメソッドから引っ越ししてフィールド変数にします */
	private BorderPane baseLayout;

	/**
	 * 親クラスのメソッドをオーバーライドする。
	 * 画面を作成したり、シーンを作成したり、色々。。。
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// JavaBasic画面用のコントロールボタンを作成する
		this.cretateControllButtonList();
		// 画面部分とコントロールボタン部分にレイアウト(表示領域を分ける)
		baseLayout = new BorderPane();
		
		// Stageの設定
		primaryStage.setHeight(VIEW_HEIGHT);
		primaryStage.setWidth(VIEW_WIDTH);

		// このクラスにあるメソッドなので名前だけで呼び出せる
		baseLayout.setCenter(createJavaBasicPane());
		// このクラスのメソッドであることを明示的に示すのに「this」を使用する
		baseLayout.setBottom(this.createFooterPanel());
		// 土台になるレイアウト(ペイン)をステージに追加する
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
	private Pane createHtmlLoaderPane() {
		StackPane pane = new StackPane();
		WebView web = new WebView();
		web.getEngine().load("https://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.htm");
		pane.getChildren().add(web);
		System.out.println("ロード完了");
		return pane;
	}

	/**
	 * 画面のフッター部分にコントロール用のボタンを配置する。
	 * 
	 * @return Pane レイアウトコンテナ
	 */
	private Pane createFooterPanel() {
		HBox hBox = new HBox(buttonList.size());
		for(Button ctlBtn : buttonList) {
			hBox.getChildren().add(ctlBtn);
		}
		return hBox;
	}

	/**
	 * コントロールボタンを作成する。
	 * 
	 */
	private void cretateControllButtonList() {
		// デザインパターン：シングルトンの実装
		if (buttonList == null) {
			buttonList = new ArrayList<Button>();
		}
		Button viewChangeBtn = new Button("画面切り替え");
		viewChangeBtn.setOnAction(event -> {
			baseLayout.getChildren().remove(0);
			baseLayout.getChildren().add(createHtmlLoaderPane());
		});
		buttonList.add(viewChangeBtn);
		Button closeBtn = new Button("閉じる");
		buttonList.add(closeBtn);
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
