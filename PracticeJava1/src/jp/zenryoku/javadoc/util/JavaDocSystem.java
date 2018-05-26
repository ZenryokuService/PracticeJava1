package jp.zenryoku.javadoc.util;

import java.io.IOException;

/**
 * コミットの手違いがあり「javadoc.util」の中にありますが<BR>
 * このクラスは「java.lang.System」です。<BR>
 * JavaDocの読解用のクラス<BR>
 * 記載内容を理解するためのソースを記載する
 * @author takunoji
 */
public class JavaDocSystem {
	/**
	 * Systemクラスの起動確認を行う<BR>
	 * プログラム引数に「val1」「val2」を渡して実行する
	 * 
	 * @param agrs プログラム引数
	 */
	public static void main(String[] agrs) {
		//　毎度おなじみのout(標準出力).println
		System.out.println("*** Testing Start ***");
		System.out.print("こちらは改行が入らない");
		System.out.print("ので１行で表示される");
		// CRLF -> \r\n と LF -> \n がある
		System.out.println("ので改行コードを入れる\n");

		// 標準入力
		byte[] buffer = new byte[3]; // ３文字文のメモリ領域を確保する
		try {
			System.in.read(buffer, 0, 3); 
		} catch(IOException e) {
			e.printStackTrace();
			System.err.println("標準エラー出力です");
		}
		// byteなのでバイト配列が表示される
		System.out.println("入力: " + buffer);
		// こっちはちゃんと文字が出力される
		System.out.println("ちゃんと出力すると: " + new String(buffer));

		// 標準エラー出力(エラー出力時には printlnで改行が入らない？
		System.err.println("意図的に出力する、標準エラー出力です\n");

		System.out.println("*** Test01 ***");
		test01();
	}

	/**
	 * arraycopyメソッドのテスト
	 */
	public static void test01() {
		// コピー元の配列
		String[] stArr = {"No1", "No2", "No3", "No4"};
		String[] copyed = new String[4];
		System.out.println("コピー前のデータ数: " + copyed.length);
		System.arraycopy(stArr, 1, copyed, 0, 2);
		// 結果をコンソール出力
		for(String st : copyed) {
			System.out.println("コピー後: " + st);
		}
	}
}
