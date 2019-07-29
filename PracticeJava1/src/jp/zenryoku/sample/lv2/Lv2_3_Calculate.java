package jp.zenryoku.sample.lv2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author takunoji
 * @see https://zenryokuservice.com/wp/2018/07/24/java-basic-level2-%E3%80%9Carithmetic-calculate%E3%80%9C/
 * 2019/07/26
 */
public class Lv2_3_Calculate {
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

	public static void main(String[] args) {
		// 今回は作成したこのクラスを使用します。
		Lv2_3_Calculate myClass = new Lv2_3_Calculate();
		
		Scanner input = new Scanner(System.in);

		while(true) {
			System.out.print("入力してください: ");
			String inStr = input.nextLine();
			if ("hello".equals(inStr)) {
				System.out.println("Hello World!");
			} else if (inStr.matches("[0-9].*[0-9]")) {
				String answer = myClass.calculate(inStr);
				System.out.println("答えは：" + answer + "です。");
			} else {
				System.out.println("想定外の入力です");
				break;
			}
			System.out.println("Next command ... ");
		}
		System.out.print("AP is fiinished. Bye!");
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

		// 入力された式を取り出す
		switch(kigo) {
		case 0:
			calc = "\\+";
			break;
		case 1:
			calc = "\\-";
			break;
		case 2:
			calc = "\\*";
			break;
		case 3:
			calc = "\\/";
			break;
		}
		// 数式から値を取得する
		String[] fomulaArray = fomula.split(calc);
		if (fomulaArray.length != 2) {
			System.out.println("式が不適当です。「1 + 1」のように入力してください。");
			return "想定外の例外(Exception)のため不明";
		}
		// 左の値にあるスペースを削除する
		String leftValue = fomulaArray[0].trim();
		int sahen = Integer.parseInt(leftValue);
		// 右の値にあるスペースを削除する
		String rightValue = fomulaArray[1].trim();
		int uhen = Integer.parseInt(rightValue);
		// 答えの変数を初期化
		int answer = 0;
		// 割り算用の変数
		double ansDouble = 0.0;
		// 計算をする
		switch(kigo) {
		case 0:
			answer = sahen + uhen;
			break;
		case 1:
			answer = sahen - uhen;
			break;
		case 2:
			answer = sahen * uhen;
			break;
		case 3:
			// 割り算の場合
			double sahenDouble = sahen;
			double uhenDouble = uhen;
			ansDouble = sahenDouble  / uhenDouble ;
			break;
		}
		// 三項演算子(記号が3ならばansDoubleを、そうでないならばanswerを文字列に変換
		return kigo == 3 ? String.valueOf(ansDouble) : String.valueOf(answer);
	}

	/**
	 * 入力された記号の部分を判定する
	 * @param fomula 入力されたもじ(計算式の型になっている) 
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
}
