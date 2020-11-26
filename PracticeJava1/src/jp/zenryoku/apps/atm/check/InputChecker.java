/**
 * Copyright (c) 2019-present Coder All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Coder Bank nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.apps.atm.check;

/**
 * @author takunoji
 *
 * 2019/09/19
 */
public class InputChecker {
	/**
	 * コーダー銀行の入金、引き出しのチェックを行う
	 * @param input 入力した値
	 * @return エラーメッセージ、チェックOKの時はnullを返却する
	 */
	public static String validNyukinHikidashi(String input) {
		String error = null;
		if ("in".equals(input) == false && "out".equals(input) == false) {
			error = "「in」か「out」を入力してください。";
		}
		return error;
	}
}
