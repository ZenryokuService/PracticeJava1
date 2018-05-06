package jp.zenryoku.sample;

/**
 * Step1-2:数値の扱い<br/>
 * 変数の扱い方のサンプルソース<br/>
 * 
 * @see http://takunoji.hatenablog.com/entry/2015/11/01/141246
 * @author ZenryokuService
 */
public class Step1_2 {
	/**
	 * メインメソッド：変数の扱い方のサンプル<br/>
	 * 下にあるmondai1()やmondai2()とは違うので注意<br/>
	 * @param args プログラム引数
	 */
	public static void main(String[] args) {
		// 変数宣言
		int num1;
		// 変数に値０を再セット、以前の値は削除される
		num1 = 0;

		/* 変数の初期化
		 * 変数の宣言時に、値を代入する
		 * 変数 = 値（代入）
		 */
		int num2 = 10;

		// 1.足し算
		num1 = num1 + num2 + 2;

		// 2.引き算
		num1 = num1 * (num1 - num2);

		// 3.掛け算
		num1 = num1 * 3;

		// 4.割り算
		num1 = num1 / 2;
		// 計算結果を表示
		System.out.println("計算結果" + num1);
	}

	/**
	 * 問1のメソッド<br/>
	 * 問題、mondai1というメソッドを作成して、コンソールに以下を出力してください。
	 * 出力内容
	 * 「2 + 2 は 4です」
	 * 
	 * 注意：メインメソッドと違い、コマンド（ファイルを右クリックして）起動できない
	 */
	public void mondai1() {
		
	}

	/**
	 * 問2のメソッド<br/>
	 * 問題、mondai2というメソッドを作成して、コンソールに以下を出力してください。
	 * 出力内容
	 * 「10 % 3 は 1です」
	 * 
	 * 注意：メインメソッドと違い、コマンド（ファイルを右クリックして）起動できない
	 */
	public void mondai2() {
		
	}
}
