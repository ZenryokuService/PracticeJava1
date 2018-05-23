package jp.zenryoku.sample.cls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import jp.zenryoku.sample.rpg.RpgMain;

public class Step3_1 {
	/** フィールド変数で自信を管理 */
	private static Step3_1 game;
	/** 定数:　AP終了コマンド */
	public static final String TERMINATE_GAME = "bye";
	/** 入力を受付るオブジェクト */
	private BufferedReader read;
	/** 出力用オブジェクト */
	private BufferedWriter write;

	/** 画面状態管理オブジェクト*/
	private Map<String, String> gameState;

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
//		System.out.println("Well come to Command RPG. Let's go adventure!");
//		System.out.println("<Command Reference> \n'bye' -> exit game");
		game = new Step3_1();
	}

	/**
	 * この処理もinit()と同様に必ず1回なのでstaticをつける</br>
	 */
	private static void gameLoop() throws IOException{
//  この部分でnewするのは不適切なのでinit()メソッドに引っ越し
//		// mainメソッドと同様にクラスをnewする必要あり
//		game = new Step3_1();
		
		/*
		 *  <ゲームループ> 
		 *  1.入力
		 *  2.データ更新、
		 *  3.レンダリング(今回はコマンドなので文字出力)
		 *  4.「bye」コマンドでゲームを終了する
		 */
		while(true) {
			String command = game.listenInput();
			// command変数がnullになる可能性があるため定数.equals()の形にする
			if(TERMINATE_GAME.equals(command)) {
				break;
			}
			System.out.println("コマンド入力値: " + command);
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
	public Step3_1() {
		// メンバメソッドなのでコンストラクタで呼び出す
		initialize();
	}

	/**
	 * 入力、出力のためのオブジェクトを生成</br>
	 * RpgMainクラスが存在しないならば使用できない様にするので</br>
	 * このメソッドはメンバメソッドにしてある</br>
	 */
	public void initialize() {
		read = new BufferedReader(new InputStreamReader(System.in));
		write = new BufferedWriter(new OutputStreamWriter(System.out));
		System.out.println("Create Map for GameState");
		// Listと同様にMapはインターフェース
		// HashMapが実態クラスになります。
		// Map<キーの型, 値の型>今回は両方とも「String」
		gameState = new HashMap<String, String>();
		// map.put("キーになる文字", "値になる文字")
		// String value = map.get("キー");
		gameState.put("title", "タイトル画面の文字列を設定します");
		gameState.put("firstStage", "序章を設定します");
	}

	/**
	 * 入力受付を開始、入力結果を取得する</br>
	 * @return 入力文字列(コマンド)
	 * @throws IOException
	 */
	public String listenInput() throws IOException {
		return read.readLine();
	}
}
