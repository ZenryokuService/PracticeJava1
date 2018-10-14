package jp.zenryoku.sample;

import jp.zenryoku.sample.annotation.Sample;

import java.io.IOException;

@Sample
public class Step1_3_3 {

	public static void main(String[] args) {
		Step1_3_3 step = new Step1_3_3();
		String input = step.tryTest();
		System.out.println("あなたの入力自他文字: " + input);

		// 例外を検知しない
		testPrint();
		// 例外を検知する
		try {
			step.testTrow();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * try catchで入力じの例外をキャッチ
	 * するのでメソッド内で解決している。
	 * @return 標準入力からの文字列
	 * @see https://docs.oracle.com/javase/jp/8/docs/api/java/lang/System.html
	 */
	public String tryTest() {
		byte[] b = new byte[5];
		System.out.println("*** 何か文字を入力してください　***");
		try {
			// Systemクラスのstaticメソッド(静的メソッド)
			System.in.read(b);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return new String(b);
	}

	public void tryTest2() {
		byte[] b = new byte[5];
		try {
			// Systemクラスのstaticメソッド(静的メソッド)
			System.in.read(b);
			String result = new String(b);
			if (result.indexOf("a") == -1) {
				// 入力文字に　「a」がないときはエラー
				throw new Exception("入力違反です");
			} else {
				System.out.println("入力OK: " + result);
			}
		} catch(IOException e) {
			// 本当に想定外の例外
			e.printStackTrace();
		} catch(Exception err) {
			// 意図的に投げた例外
			err.printStackTrace();
		}
	}

	public static void testPrint() {
		System.out.println("例外を検知しないケース");
		try {
			throw new Exception("必ず例外を投げます");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void testTrow() throws Exception{
		System.out.println("throws文のメソッド");
		throw new Exception("throws文からの例外");
	}
}
