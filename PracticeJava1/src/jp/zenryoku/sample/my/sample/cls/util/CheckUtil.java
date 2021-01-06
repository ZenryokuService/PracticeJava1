/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.my.sample.cls.util;

/**
 * @author takunoji
 *
 * 2020/06/11
 */
public class CheckUtil {
	/** 0-9(数字)判定をする時の正規表現 */
	public static final String REGREX = "[0-9]";

	/** 1-3までの判定に使用する正規表現 */
	public static final String REG_1_TO_3 = "[1-3]";

	/**
	 * 必須入力チェック、第二引数にあう正規表現でのチェックを行う
	 * 
	 * @param numberStr 検証する文字列
	 * @param range 判定用の正規表現
	 * @return true: 正規表現にマッチ false: マッチしない
	 */
	public static boolean isNumber(String numberStr, String range) {
		// 正規表現で判定します。数字のrangeに当てはまる時にtrue
		if (isEmpty(numberStr) && numberStr.length() != 1) {
			return false;
		}
		if (numberStr.matches(range)) {
			return true;
		}
		return false;
	}

	/**
	 * 引数の１文字が数字かどうかの判定をする。。
	 * 
	 * @param numberStr
	 * @return true: 引数が0-9 false: 0-9ではない
	 */
	public static boolean isNumber(String numberStr) {
		// 正規表現で判定します。数字の0-9に当てはまる時にtrue
		if (isEmpty(numberStr) && numberStr.length() != 1) {
			return false;
		}
		if (numberStr.matches("[0-9]")) {
			return true;
		}
		return false;
	}

	/**
	 * 引数に渡した文字列が、Null、またはから文字("")
	 * @param str 判定する文字列
	 * @return ture; 
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}
}
