package jp.zenryoku.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * ファイル読み込み
 *
 * @author ZenryokuService
 */
public class Step1_6 {
	public static void main(String[] args) {
		// 読込するファイル
		File f = new File("./resources/test.txt");
		// リーダクラス
		BufferedReader buf = null;
		// 文字列の格納用クラス(100バイト分)
		StringBuilder builder = new StringBuilder(100);
// デバック用コード
System.out.println(System.getProperty("user.dir"));
		try {
			buf = new BufferedReader(new FileReader(f));
			while(buf.read() != -1) {
				builder.append( buf.readLine());
			}
			System.out.println(builder.toString());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
