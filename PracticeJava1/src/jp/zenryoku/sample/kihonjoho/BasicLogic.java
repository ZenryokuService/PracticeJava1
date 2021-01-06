package jp.zenryoku.sample.kihonjoho;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * @author takunoji
 *
 * 2021/01/04
 */
public class BasicLogic {

	public String toBinaryString(String num) {
		int n = Integer.parseInt(num);
		return Integer.toBinaryString(n);
	}

	public String toBinaryString(Double num) {
		int seisu = num.intValue();
		StringBuilder build = new StringBuilder();
		// 小数点第５位で切り捨て
		Double r = nextDouble(num, 2);
		build.append(r.intValue());
		while (r != 1.0 && r != 0.0) {
			r = nextDouble(r, 2);
			System.out.println("次の小数: " + r);
			build.append(r.intValue());
		}
		String bin = toBinaryString(String.valueOf(seisu));
		return bin + "." + build.toString();
	}

	public Double nextDouble(Double num, int kisu) {
		int i = num.intValue();
		double d = new BigDecimal(num - i).setScale(5, RoundingMode.DOWN).doubleValue();
		System.out.println("整数: " + i + " / 小数: " + d);
		return d * kisu;
	}

	public String toBinaryString(int num, int kisu) {
		int p = num / kisu;
		int q = num % kisu;
		StringBuilder build = new StringBuilder();
		while (p != 0) {
			p = num / kisu;
			q = num % kisu;
			System.out.println("商: " + p + " 余り: " + q);
			num = p;
			build.append(q);
		}
		return build.toString();
	}

	public String toDecimalString(String num) {
		char[] ch = num.toCharArray();
		BigDecimal ans = new BigDecimal(0);

		// 左側から計算する
		for (int i = 0; i < ch.length; i++) {
			// ２ビット目は2の2乘になる(
			Double intVal = Double.parseDouble(String.valueOf(ch[i]));
			Double d = 0.0;
			if ('1' == ch[i]) {
				d = Math.pow(2, ch.length - (i + 1));
			} else {
				d = 0.0;
			}
			ans = ans.add(new BigDecimal(d));
			System.out.println(i + "番目 / " + d + "= " + ans.toString());
		}
		return ans.toString();
	}

	/**
	 * bit配列 = 2進数を10進数に変換する
	 * 
	 * @param bitArray
	 * @return
	 */
	public String convertBitToInt(boolean[] bitArray) {
		String result = null;
		int ans = 0;
		System.out.println("Len: " + bitArray.length);
		System.out.println("Len: " + bitArray.toString());
		for (int i = 0; i < bitArray.length; i++) {
			System.out.println( i + " / " + bitArray[i]);
			if (bitArray[i]) {
				ans += Math.pow(2.0, i);
			}
		}
		result = String.valueOf(ans);
		return result;
	}

	public String convertBitToInt(BitSet set) {
		BigDecimal ans = new BigDecimal(0);
		for ( int i = 0; i < set.size(); i++) {
			boolean bitValue = set.get(i);
			if (bitValue) {
				ans = ans.add(new BigDecimal(Math.pow(2, i -1)));
				System.out.println(" / 計算後: " + ans.toString());
			}
		}
		return ans.toString();
	}

	public String convertBitToInt(BitSet set, int r) {
		BigDecimal ans = new BigDecimal(0);
		for ( int i = 0; i < set.size(); i++) {
			boolean bitValue = set.get(i);
			if (bitValue) {
				ans = ans.add(new BigDecimal(Math.pow(r, i -1)));
				System.out.println(" / 計算後: " + ans.toString());
			}
		}
		return ans.toString();
	}

	/**
	 * R進数を10進数に変換
	 * @param 変換元の数字
	 * @param 〜進数に変換するか
	 * @return 10進数の数字 / エラーの場合はNULLを返却
	 */
	public String convertRsinsuToInt(String numeric, int r) {
		System.out.println("*** 計算開始 ***");
		// 文字列でR進数を受け取る
		if (numeric == null && r != 0 && !numeric.matches("[0-9a-fA-F")) {
			return null;
		}
		char[] chList = numeric.toCharArray();
		// 計算結果
		int answer = 0;
		for (int i = 0; i < chList.length; i++) {
			String st = String.valueOf(chList[chList.length - (i + 1)]);
			int value = 0;
			int stVal;
			if (st.matches("[0-9]")) {
				stVal = Integer.parseInt(st);
			} else {
				stVal = Integer.parseInt(st, 16);
			}
			value = stVal * (int) Math.pow(r, i);

			System.out.println(i + "桁目"+ " / 値: " + value);
			answer += value;
		}
		return String.valueOf(answer);
	}
}
