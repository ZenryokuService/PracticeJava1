package jp.zenryoku.sample.lv2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author takunoji
 * @see https://zenryokuservice.com/wp/2018/07/24/java-basic-level2-%E3%80%9Carithmetic-calculate%E3%80%9C/
 * 2019/07/26
 */
public class Lv2_6_Properties {
	/** 足し算のフラグ: フィールド変数1 */
	public static final int TASHI_ZAN_FLG = 0;
	/** 引き算のフラグ: フィールド変数2 */
	public static final int HIKI_ZAN_FLG = 1;
	/** かけ算のフラグ: フィールド変数3 */
	public static final int KAKE_ZAN_FLG = 2;
	/** わり算のフラグ: フィールド変数4 */
	public static final int WARI_ZAN_FLG = 3;
	/** 足し算の記号: フィールド変数5 */
	public static final String TASHI_ZAN = "+";
	/** 引き算の記号: フィールド変数6 */
	public static final String HIKI_ZAN = "-";
	/** かけ算の記号: フィールド変数7 */
	public static final String KAKE_ZAN = "*";
	/** わり算の記号: フィールド変数8 */
	public static final String WARI_ZAN = "/";

	/** コマンド履歴ようList */
	private List<String> commandList;
	/** プロパティファイルを読み込むクラス */
	private Properties prop;

	public static void main(String[] args) {
		// 今回は作成したこのクラスを使用します。
		Lv2_6_Properties myClass = new Lv2_6_Properties();
		
		Scanner input = new Scanner(System.in);

		while(true) {
			System.out.print("入力してください: ");
			String inStr = input.nextLine();
			// プロパティファイルの値を取得する
			String value = myClass.getPropertes(inStr);
			if (value == null) {
				System.out.println("プロパティのキーがありません");
			} else {
				System.out.println("プロパティのキー: " + inStr);
				System.out.println("プロパティの値: " + value);
				continue;
			}
			if ("hello".equals(inStr)) {
				System.out.println("Hello World!");
			} else if (inStr.matches("[0-9].*[0-9]")) {
				String answer = myClass.calculate(inStr);
				System.out.println("答えは：" + answer + "です。");
			} else if ("dir".equals(inStr)) { 
				// Dirコマンドを実装する
				myClass.dirCommand();
			} else if ("bye".equals(inStr)) { 
				myClass.commandHistory();
				break;
			} else {
				System.out.println("想定外の入力です");
				break;
			}
			myClass.addCommand(inStr);
			System.out.println("Next command ... ");
		}
		System.out.print("AP is fiinished. Bye!");
	}

	/** コンストラクタ */
	public Lv2_6_Properties() {
		commandList = new ArrayList<String>();
		prop = new Properties();
		try {
			// resources/
			prop.load(getClass().getResourceAsStream("/test.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			// エラーコード-1をセットしてプログラム終了
			System.exit(-1);
		}
	}

	/**
	 * プロパティファイルから値が取得できた、値を返し
	 * 取得できない時はから文字("")を返す
	 * @param inStr キー(コマンド)
	 * @return プロパティファイルの値
	 */
	public String getPropertes(String inStr) {
		String value = prop.getProperty(inStr);
		if ("".equals(value)) {
			listPropertyKeys();
			return "";
		}
		return value;
	}

	public void listPropertyKeys() {
		Enumeration<Object> enm = prop.keys();
		System.out.println("*** Keys ***");
		while(enm.hasMoreElements()) {
			System.out.println(enm.nextElement().toString());
		}
		System.out.println("************");
	}
	/**
	 * コマンド履歴をコンソールに出力
	 */
	public void commandHistory() {
		System.out.println("/** コマンド履歴 */");
		for (String command : commandList) {
			System.out.println("・" + command);
		}
		System.out.println("/***************/");
		System.out.println("Good bye!");
	}

	/** コマンド履歴に追加する */
	public void addCommand(String cmd) {
		commandList.add(cmd);
	}

	/**
	 * Dirコマンドの実装
	 */
	public void dirCommand() {
		File dir = new File(".");
		String[] files = dir.list();
		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i]);
		}
	}
	/** JavaDocコメント
	 * 文字列を数値に変換して計算をする
	 * @param fomula 入力された計算式
	 * @return 計算結果
	 */
	public String calculate(String fomula) {
		// 計算の記号を判定
		int kigo = -1;
		// 計算記号の文字列を初期化(NULLを設定する)
		String calc = null;
		try {
			kigo = jadgeKigo(fomula);
		} catch (Exception e) {
			e.printStackTrace();
			return "不適切な計算式です。: " + fomula;
		}
		calc = getKigo(kigo);
		// 数式から値を取得する
		String[] fomulaArray = fomula.split(calc);
		if (fomulaArray.length != 2) {
			System.out.println("式が不適当です。「1 + 1」のように入力してください。");
			return "想定外の例外(Exception)のため不明";
		}
		return sisokuKeisan(kigo, fomulaArray);
	}

	/**
	 * 入力された記号の部分を判定する
	 * @param fomula 入力されたもじ(計算式の型になっている) 
	 * @param calc 計算する記号、参照渡しなので値を変更できる
	 * @return 判定結果のフラグ(int型)
	 */
	public int jadgeKigo(String fomula) throws Exception {
		/* 「ここもコメント」
		 * 足し算：0 
		 * 引き算：1 
		 * 掛け算：2 
		 * 割り算：3
		 */
		int kigo = -1;
		int tashi = fomula.indexOf(TASHI_ZAN);
		int hiki = fomula.indexOf(HIKI_ZAN);
		int kake = fomula.indexOf(KAKE_ZAN);
		int wari = fomula.indexOf(WARI_ZAN);
		
		if (tashi != -1) {
			// 足し算
			kigo = TASHI_ZAN_FLG;
		} else if (hiki != -1) {
			// 引き算
			kigo = HIKI_ZAN_FLG;
		} else if (kake != -1) {
			// かけ算
			kigo = KAKE_ZAN_FLG;
		} else if (wari != -1) {
			// わり算
			kigo = WARI_ZAN_FLG;
		} else {
			System.out.println("四則計算の式が不適切です。: " + fomula);
			throw new Exception("想定外の例外(Exception)のため不明");
		}
		return kigo;
	}

	/**
	 * 
	 * @param kigo 四則計算の記号
	 * @param fomulaArray 計算式の右辺と左辺(配列)
	 * @return 答え
	 */
	public String sisokuKeisan(int kigo, String[] fomulaArray) {
		// 左の値にあるスペースを削除する
		String leftValue = fomulaArray[0].trim();
		int sahen = Integer.parseInt(leftValue);
		// 右の値にあるスペースを削除する
		String rightValue = fomulaArray[1].trim();
		int uhen = Integer.parseInt(rightValue);
		int answerInt = 0;
		double anserDouble = 0.0;
		// 計算をする
		switch(kigo) {
		case TASHI_ZAN_FLG:
			answerInt = sahen + uhen;
			break;
		case HIKI_ZAN_FLG:
			answerInt = sahen - uhen;
			break;
		case KAKE_ZAN_FLG:
			answerInt = sahen * uhen;
			break;
		case WARI_ZAN_FLG:
			// 割り算の場合
			double sahenDouble = sahen;
			double uhenDouble = uhen;
			anserDouble = sahenDouble  / uhenDouble ;
			break;
		}
		// 三項演算子(記号が3ならばansDoubleを、そうでないならばanswerを文字列に変換
		return kigo == 3 ? String.valueOf(anserDouble) : String.valueOf(answerInt);
	}

	/**
	 * 計算フラグから対象の計算記号(文字)を取得する
	 * @param kigo
	 * @return 記号(+, -, *, /)
	 */
	public String getKigo(int kigo) {
		String retKigo = null;
		// 計算をする
		switch(kigo) {
		case TASHI_ZAN_FLG:
			retKigo = "\\+";
			break;
		case HIKI_ZAN_FLG:
			retKigo = "\\-";
			break;
		case KAKE_ZAN_FLG:
			retKigo = "\\*";
			break;
		case WARI_ZAN_FLG:
			retKigo = "\\/";
			break;
		}
		return retKigo;
	}
}
