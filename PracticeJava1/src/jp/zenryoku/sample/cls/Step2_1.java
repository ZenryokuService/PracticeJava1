package jp.zenryoku.sample.cls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * javaAPIの使い方
 * Listの使い方
 * @author takk
 *
 */
public class Step2_1 {

	public static void main(String[] args) {
		Step2_1 step = new Step2_1();
		// 入力を受け取り切らない場合は次のStreamに読み込まれる
		//123456step.arrayTest1();
		step.arrayTest2();
	}

	public void arrayTest1() {
		System.out.println("test1 入力開始");
		// 配列は動的に扱うときに大変だ。。。
		byte[] moji = new byte[5];
		try {
			System.in.read(moji);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("入力じた文字:" + new String(moji));
	}

	public void arrayTest2() {
		System.out.println("test2 入力開始");
		// 配列は動的に扱うときに大変だ。。。
		BufferedReader read;
		read = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		try {
			line = read.readLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("入力じた文字:" + line);
	}
}
