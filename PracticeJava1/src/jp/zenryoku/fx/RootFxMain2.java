package jp.zenryoku.fx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jp.zenryoku.fx.pane.JavaBasicParent;
import jp.zenryoku.fx.pane.WebLoaderParent;

/**
 * JavaFXでのハローワールド〜OpenCVなどの
 * 作成したアプリをテストするための、スタンドアロンアプリ。
 * 
 * @author takunoji
 * 2019/01/23
 */
public class RootFxMain2 extends Application {
	/** 画面の縦幅 */
	private static final double VIEW_HEIGHT = 500.0;
	/** 画面の横幅 */
	private static final double VIEW_WIDTH = 500.0;
	/** コントロールボタンのリスト */
	private ArrayList<Button> buttonList;
	/** 各画面のインスタンスを格納する */
	private Map<String, Parent> parentMap;
	/** startメソッドから引っ越ししてフィールド変数にします */
	private BorderPane baseLayout;

	/**
	 * 親クラスのメソッドをオーバーライドする。
	 * 画面を作成したり、ペインを作成したり、色々。。。
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// JavaBasic画面用のコントロールボタンを作成する
		this.cretateControllButtonList();
		parentMap = new HashMap<String, Parent>();
		parentMap.put(JavaBasicParent.VIEW_NAME, JavaBasicParent.getInstance());
		parentMap.put(WebLoaderParent.VIEW_NAME, WebLoaderParent.getInstance());

		// 画面部分とコントロールボタン部分にレイアウト(表示領域を分ける)
		baseLayout = new BorderPane();
		
		// Stageの設定
		primaryStage.setHeight(VIEW_HEIGHT);
		primaryStage.setWidth(VIEW_WIDTH);

		// このクラスにあるメソッドなので名前だけで呼び出せる
		baseLayout.setCenter(parentMap.get(JavaBasicParent.VIEW_NAME));
		// このクラスのメソッドであることを明示的に示すのに「this」を使用する
		baseLayout.setBottom(this.createFooterPanel());
		// 土台になるレイアウト(ペイン)をステージに追加する
		primaryStage.setScene(new Scene(baseLayout, VIEW_WIDTH, VIEW_HEIGHT));
		primaryStage.show();
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
			baseLayout.setCenter(parentMap.get(WebLoaderParent.VIEW_NAME));
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
