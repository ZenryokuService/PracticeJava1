package jp.zenryoku.fx;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

/**
 * JavaFXでのハローワールド〜OpenCVなどの
 * 作成したアプリをテストするための、スタンドアロンアプリ。
 * 
 * @author takunoji
 * 2019/01/23
 */
public class RootFxMain extends Application {
	/** 画面の縦幅 */
	private double VIEW_HEIGHT = 500.0;
	/** 画面の横幅 */
	private double VIEW_WIDTH = 500.0;
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
		// １個目のテキストフィールドのIDをつける
		text1.setId("input1");
		hBox.getChildren().add(text1);

		// 計算式のラベル
		Label ope = new Label("+");
		hBox.getChildren().add(ope);
	
		// ２個目の数値、テキストフィールド
		TextField text2 = new TextField();
		text2.setPrefColumnCount(3);
		text2.setAlignment(Pos.BASELINE_CENTER);
		// ２個目のテキストフィールドのIDをつける
		text2.setId("input2");

		// Hello Worldという文言の下にあるレイアウトに追加
		hBox.getChildren().add(text2);

		// 「=」を追加する
		Label equal = new Label("=");
		hBox.getChildren().add(equal);

		// 答えを表示するラベル
		Label answer = new Label("");
		answer.setId("answer");
		hBox.getChildren().add(answer);

		// 答えの隣に計算ボタンを追加する
		Button keisan = new Button("計算");
		keisan.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("ボタンアクション開始");
				final List<Integer> numbers = new ArrayList<>();
				// ボタンを取得する
				Button pushed = (Button) event.getSource();
				// 親のノード(コンポーネント)を取得する→この場合は「HBox」になる
				Parent parent = pushed.getParent();
				// 親に含まれているノード(コンポーネント)を取得する
				ObservableList<Node> list = parent.getChildrenUnmodifiable();
				list.stream().forEach(node -> {
					System.out.println("ノード取得: " + node.getId());
					try {
						if ("input1".equals(node.getId())) {
							TextField t1 = (TextField) node;
							String input = t1.getText();
							numbers.add(new Integer(input));
						} else if("input2".equals(node.getId())) {
							TextField t2 = (TextField) node;
							String input = t2.getText();
							numbers.add(new Integer(input));
						} else if("answer".equals(node.getId())) {
							Label anser = (Label) node;
							Integer in1 = numbers.get(0);
							Integer in2 = numbers.get(1);
							int kotae = in1 + in2;
							anser.setText(String.valueOf(kotae));
						}
					} catch (Exception e) {
						label.setText(e.getMessage());
					}
				});
				System.out.println("ボタンアクション終了");
			}
		});
		hBox.getChildren().add(keisan);

		// 縦のレイアウトに追加する
		vBox.getChildren().add(hBox);

		// 背景の作成
		createBackGround(vBox);
		// ペインの作成
		Group root = new Group();
		root.getChildren().add(vBox);
	
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
		long num = 12345678901L;
		float shosu = 123.09876543f;
		return hyoji;
	}

	/**
	 * ImageViewを作成する
	 * @param fileName ファイルの名前
	 * @return ImageView 画像
	 */
	public Label createImageLbl(String fileName ) {
		ImageView img =  new ImageView(new Image(getClass().getResourceAsStream("/images/" + fileName)));
		Label tile = new Label();
		tile.setGraphic(img);
		return tile;
	}

	/**
	 * Canvasに背景を描画する
	 * @param vBox Canvasを追加する領域
	 */
	public void createBackGround(VBox vBox) {
		String lightGrass = "grass_light.png";
		String deepGrass = "grass_deep.png";
		String soilBasic = "soil_basic.png";
		GridPane grid = new GridPane();
		for (int i = 1; i <= 5; i++) {
			grid.add(createImageLbl(lightGrass), 0, i);
		}
		vBox.getChildren().add(grid);
	}
}
