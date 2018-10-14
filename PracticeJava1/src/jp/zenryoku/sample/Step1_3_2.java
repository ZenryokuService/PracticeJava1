package jp.zenryoku.sample;

import jp.zenryoku.sample.annotation.Sample;

@Sample
public class Step1_3_2 {

	/**
	 * while文の基本
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Step1_3_2 step = new Step1_3_2();
		step.exeWhileType1();
		step.exeWhileType2();
		step.exeWhileType3();
	}

	/**
	 * 無限ループ処理を行うパターン
	 */
	public void exeWhileType1() {
		int i = 0;
		while(true) {
			if (i == 3) {
				System.out.println("ループを抜ける: i=" + i);
				break;
			}
			i++;
		}
	}

	/**
	 * 通常のWhile文、条件が上に来る(よく使う)
	 */
	public void exeWhileType2() {
		int i = 0;
		while(i < 3) {
			if (i == 2) {
				System.out.println("The End: i=" + i);
				break;
			}
			i++;
		}
	}

	/**
	 * do while文、条件の部分が下に来る
	 */
	public void exeWhileType3() {
		int i = 0;
		do {
			if (i == 1) {
				System.out.println("doを抜ける: i=" + i);
				break;
			}
			i++;
		} while(i < 4);
	}
}
