package jp.zenryoku.sample.cls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import jp.zenryoku.sample.rpg.RpgMain;
import jp.zenryoku.sample.rpg.ViewStatus;
import jp.zenryoku.sample.rpg.status.TitleView;

/**
 * テキストRPGのタイトル表示までの実装
 * 
 * @author takunoji
 */
public class Step3_2 {
	/** フィールド変数で自信を管理 */
	private static Step3_2 game;
	/** 定数:　AP終了コマンド */
	public static final String TERMINATE_GAME = "bye";
	/** 入力を受付るオブジェクト */
	private BufferedReader read;
	/** 画面状態管理オブジェクト */
	private ViewStatus status;

	//////////////////////////////////////
	// staticメソッドの定義
	//////////////////////////////////////
	/** 
	 * ゲームを起動する </br>
	 * 標準入力の受付、「bye」コマンドでAPを終了する</br>
	 * 
	 * @param args プログラム引数
	 */
	public static void main(String[] args) {
		// 初期化処理
		init();
		try {
			// ゲーム起動(GameLoop開始)
			gameLoop();
		} catch(IOException ie) {
			ie.printStackTrace();
		}
		// リソースの解放
		terminated();
	}

	/**
	 * このゲームでは初期化は必ず1回なのでstaticをつける</br>
	 * static -> 静的メソッド=> クラス内にあるがJVMから直で参照される
	 * ※JVM => Java Virtual Machine
	 */
	private static void init() {
		// mainメソッドと同様にクラスをnewする必要あり
		game = new Step3_2();
	}

	/**
	 * この処理もinit()と同様に必ず1回なのでstaticをつける</br>
	 */
	private static void gameLoop() throws IOException{
		
		/*
		 *  <ゲームループ> 
		 *  1.入力
		 *  2.データ更新、
		 *  3.レンダリング(今回はコマンドなので文字出力)
		 *  4.「bye」コマンドでゲームを終了する
		 */
		while(true) {
			// 1. 入力を受け取る
			String command = game.listenInput();
			// command変数がnullになる可能性があるため定数.equals()の形にする
			if(TERMINATE_GAME.equals(command)) {
				break;
			}
			// 2-1.処理を実行する(タイトル、その他初期表示する
			game.execute(command);
			// 画面を表示してループする
			game.view();
		}
	}

	/**
	 * ゲームのリソースを解放する
	 */
	public static void terminated() {
		// クラスの解放を行う
		game = null;
		System.out.println("This game terminated!, See you next time!!");
	}

	//////////////////////////////////////
	// コンストラクタ、メンバメソッドの定義
	//////////////////////////////////////
	/**
	 * ぶっちゃけてここで初期化(init)処理をやっても良い</br>
	 * 最終的にプログラムの設計者の判断に委ねられる</br>
	 */
	public Step3_2() {
		// メンバメソッドなのでコンストラクタで呼び出す
		initialize();
	}

	/**
	 * 入力、出力のためのオブジェクトを生成</br>
	 * RpgMainクラスが存在しないならば使用できない様にするので</br>
	 * このメソッドはメンバメソッドにしてある</br>
	 */
	public void initialize() {
		// 入力受付クラス
		read = new BufferedReader(new InputStreamReader(System.in));
		// *** titleの表示 *** //
		Path filePath = Paths.get("resources/title.txt");
		List<String> lines = null;
		try {
			lines = Files.readAllLines(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		lines.stream().forEach(line -> System.out.println(line));
		// ViewStats生成
		status = new TitleView();
	}

	/**
	 * 入力受付を開始、入力結果を取得する</br>
	 * @return 入力文字列(コマンド)
	 * @throws IOException
	 */
	private String listenInput() throws IOException {
		return read.readLine();
	}

	/**
	 * コマンドの実行を行う<BR>
	 * 1.
	 * @param input
	 * @return
	 */
	private ViewStatus execute(String input) {
		return status.execute(input);
	}

	private void view() {
		status.views();
	}}
