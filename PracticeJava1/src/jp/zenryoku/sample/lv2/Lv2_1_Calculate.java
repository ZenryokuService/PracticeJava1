package jp.zenryoku.sample.lv2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author takunoji
 * @see https://zenryokuservice.com/wp/2018/07/24/java-basic-level2-%E3%80%9Carithmetic-calculate%E3%80%9C/
 * 2019/07/26
 */
public class Lv2_1_Calculate {
	public static void main(String[] args) {
		// 今回は作成したこのクラスを使用します。
		Lv2_1_Calculate myClass = new Lv2_1_Calculate();
		
		Scanner input = new Scanner(System.in);

		while(true) {
			System.out.print("入力してください: ");
			String inStr = input.nextLine();
			if ("hello".equals(inStr)) {
				System.out.println("Hello World!");
			} else if (inStr.matches("[0-9].*[0-9]")) {
				int answer = myClass.calculate(inStr);
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
	public int calculate(String fomula) {
		// 計算の記号を判定
		/* 「ここもコメント」
		 * 足し算：0 
		 * 引き算：1 
		 * 掛け算：2 
		 * 割り算：3
		 */
		int kigo = -1;
		int tashi = fomula.indexOf("+");
		int hiki = fomula.indexOf("-");
		int kake = fomula.indexOf("*");
		int wari = fomula.indexOf("/");
		
		if (tashi != -1) {
			// 足し算
			kigo = 0;
		} else if (hiki != -1) {
			// 引き算
			kigo = 1;
		} else if (kake != -1) {
			// かけ算
			kigo = 2;
		} else if (wari != -1) {
			// わり算
			kigo = 3;
		} else {
			System.out.println("四則計算の式が不適切です。: " + fomula);
			return 0;
		}
		// 答えの変数を初期化
		int answer = 0;
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
			return 0;
		}
		// 左の値にあるスペースを削除する
		String leftValue = fomulaArray[0].trim();
		int sahen = Integer.parseInt(leftValue);
		// 右の値にあるスペースを削除する
		String rightValue = fomulaArray[1].trim();
		int uhen = Integer.parseInt(rightValue);
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
			answer = sahen / uhen;
			break;
		}
		return answer;
	}
}
