package jp.zenryoku.sample;

import jp.zenryoku.sample.annotation.Sample;

@Sample
public class Step1_3_1 {
	public static void main(String[] args) {
		// String型の配列
		String[] moji = {"abc", "def", "ghi", "jkl"};
		// コンソール出力用の変数
		String result = "";
		System.out.println("*** for文その１ ***");
		// for文
		for(int i = 0; i < 4; i++) {
			System.out.println("Hello World: " + moji[i]);
			result = result + moji[i];
		}
		System.out.println("文字列の連結: " + result);
		System.out.println(""); // 改行を入れる
		System.out.println("*** for文その2 ***");
		for(String str : moji) {
			System.out.println("拡張for文: " + str);
		}
		// メソッド呼び出しを行うためにこのクラスをインスタンス化
		// staticがついているからメインメソッドは特別なので
		Step1_3_1 step = new Step1_3_1();
		step.inttegerPrint();
		step.stringPrint(moji);
	}

	/**
	 * 引数なしの返却値なし
	 */
	public void inttegerPrint() {
		// int型の配列
		int[] nums = {1, 2, 3, 4, 5};
		// 合計を入れるための変数
		int goukei = 0;
		// 配列.length => 配列の長さを示す
		for(int i = 0; i < nums.length; i++) {
			goukei += nums[i];
		}
		System.out.println("合計値: " + goukei);
	}

	/**
	 * メソッドの初めの文字は小文字で記載する
	 * クラスの初めの文字は大文字で記載する
	 */
	public void stringPrint(String[] hako) {
		for(int i = 0; i < hako.length; i++) {
			System.out.println("Hello World: " + hako[i]);
		}
	}
}
