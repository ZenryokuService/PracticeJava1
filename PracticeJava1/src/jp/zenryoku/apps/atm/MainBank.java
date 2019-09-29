/**
 * Copyright (c) 2019-present Coder All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Coder Bank nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.apps.atm;

import java.util.Scanner;

import jp.zenryoku.apps.atm.check.InputChecker;

/**
 * @author takunoji
 *
 * 2019/09/14
 */
public class MainBank {
	/** 金銭管理クラス */
	private Calcuration cal;
	
	public static void main(String[] args) {
		MainBank main = new MainBank();
		main.atm();
	}

	/**
	 * コンストラクタ
	 */
	public MainBank() {
		cal = new Calcuration();
	}

	public void atm() {
		System.out.println("コーダー銀行へようこそ、入金しますか？引き出しますか？");
		System.out.println("あなたの、預金額は ¥" + cal.getYokingaku() + "-です。");
		System.out.println("入金の時は「in」、引き出しの時は「out」を、終了する時は「bye」を入力してください。");

		Scanner input = new Scanner(System.in);
	while(true) {
		String inStr = input.nextLine();
		String errorMessage = InputChecker.validNyukinHikidashi(inStr);

		if (errorMessage != null) {
			System.out.println(errorMessage);
			continue;
		}
		if ("in".equals(inStr)) {
			System.out.println("入金処理を行います。");
			cal.nyukin(input, true);
		} else if ("out".equals(inStr)) {
			System.out.println("引出し処理を行います。");
			cal.nyukin(input, false);
		} else if ("bye".equals(inStr)) {
			break;
		}
	}
	System.out.println("ATM処理を終了します。ご利用ありがとうございました。");
}
}
