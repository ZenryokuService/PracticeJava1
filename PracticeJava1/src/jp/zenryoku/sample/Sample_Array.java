package jp.zenryoku.sample;

import java.util.Scanner;

/**
 * @author takunoji
 *
 * 2018/11/23
 */
public class Sample_Array {
	public static void main(String[] args) {
		// 1次元配列
		String[] lv1Array = new String[] {"a", "b", "c"};
		// 2次元配列
		String[][] lv2Array = new String[][] {{"a", "b", "c"}, {"d", "e", "f"}};
		// 3次元配列
		String[][][] lv3Array = new String[][][] {
			{{"a", "b", "c"}, {"d", "e", "f"}}
			,{{"g", "h", "i"}, {"j", "k", "l"}}
			,{{"m", "n", "o"}, {"p", "q", "r"}}
			};
		// 1次元配列を表示する
		System.out.println("*** 1次元配列を表示 ***");
		for (String nakami : lv1Array) {
			printf("", nakami);
		}
		// 2次元配列
		System.out.println("\n*** 2次元配列を表示 ***");
		for (String[] nakamiLv2 : lv2Array) {
			for (String nakami : nakamiLv2) {
				printf("", nakami);
			}
		}
		// 3次元配列
		System.out.println("\n*** 3次元配列を表示 ***");
		for (String[][] nakamiLv3 :lv3Array) {
			for (String[] nakamiLv2 : nakamiLv3) {
				for (String nakami : nakamiLv2) {
					printf("", nakami);
				}
			}
		}
	}

	private static void printf(String label, String value) {
		System.out.print(label + "" + value);
	}
}
