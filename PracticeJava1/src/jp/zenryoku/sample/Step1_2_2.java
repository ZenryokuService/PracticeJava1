package jp.zenryoku.sample;

import jp.zenryoku.sample.annotation.Sample;

/**
 * char型とStringの起動確認をする
 * @author takunoji
 */
@Sample
public class Step1_2_2 {
	private int BB = 1;
	public static void main(String[] args) throws Exception{
		Step1_2_2 cls = new Step1_2_2();
		cls.testBoolean();
		// プログラム引数を取得する
		System.out.println("プログラム引数１番目" + args[0]);
		byte[] input = new byte[5]; // ５文字
		// 入力するタイミングがちょっとわかりづらい
		// コンソールをクリックして何か入力してください
		System.in.read(input);
		System.out.println("入力: " + input);
		int count = 0;
//		charTest();
//		while ((c =  != -1) {
//			System.out.print(c);
//			count++;
//		}
		String in = new String(input);
		System.out.println("入力した文字は: " + in + " です");
		if ("tiger".equals(in)) {
			int kk;
			System.out.println("タイガー！アカパ");
		} else {
		
			System.out.println("トラじゃねーな...");
		}
		
		Step1_2_2 step = new Step1_2_2();
		step.testBoolean();
		step.charTest();
		step.stringTest();
	}

	public void testBoolean() {
		char a = 'a';
		char b = 'b';
		int test = 0;
		charTest();
		System.out.println("'a' = 'a' = " + ('a' == a ));
		System.out.println("'a' = 'b' = " + ('a' == b ));
		System.out.println("\"文字\".equals(\"文字\")" + "文字".equals("文字"));
		String ttt = "ttt";
		System.out.println("ttt.equals(\"ttt\")" + "ttt".equals(ttt));
	}
	/**
	 * char型のテストです。
	 */
	public void charTest() {
		int bb = BB + 1;
		/////////////////////////////////////////////
		// char型は「'」シングルクォーテーションで囲みます
		/////////////////////////////////////////////
		char a = 'a';
		char b = 'b';
		char c = 'c';
		char d = 'd';
		char e = 'e';
		System.out.println("*** charの操作 ***");
		System.out.println("char a = " + a);
		System.out.println("char b = " + b);
		System.out.println("char c = " + c);
		System.out.println("charの比較: " + (a == b));
		System.out.println("charの比較: " + (a == 'a'));
		// 配列
		char[] hako = {'a', 'b', 'c'};
		System.out.println("charの配列: " + hako); // 警告が出ます→「String」を使った方が良い
		System.out.println("charの配列の中身: " + hako[0] + hako[1] + hako[2]);
	}
	/**
	 * 文字列(String型)操作のテストです。
	 */
	public void stringTest() {
		// 変数の初期化
		String moji = "abcdef";
		String safix = "xyz";
		System.out.println("*** 文字の操作 ***");
		System.out.println("文字連結: " + moji + safix);
		System.out.println("文字列比較:" + moji.equals(safix));
		// Stringは参照型なので == では比較しない(使用すると想定外の判定結果が出る)
		System.out.println("文字列比較2: " + "abcdef".equals(moji));
	}
	
}
