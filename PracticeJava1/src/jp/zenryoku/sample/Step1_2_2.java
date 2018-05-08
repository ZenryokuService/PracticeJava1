package jp.zenryoku.sample;

/**
 * char型とStringの起動確認をする
 * @author takunoji
 */
public class Step1_2_2 {
	public static void main(String[] args) {
		Step1_2_2 step = new Step1_2_2();
		step.charTest();
		step.stringTest();
	}

	/**
	 * char型のテストです。
	 */
	public void charTest() {
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
