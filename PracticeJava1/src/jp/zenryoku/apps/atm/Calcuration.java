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

/**
 * @author takunoji
 *
 * 2019/09/14
 */
public class Calcuration {
	/** 預金額 */
	private int yokingaku;

	public Calcuration() {
		// 預金額は1000円スタート
		yokingaku = 1000;
	}

	/**
	 * @return the yokingaku
	 */
	public int getYokingaku() {
		return yokingaku;
	}

	/**
	 * @param yokingaku the yokingaku to set
	 */
	public void setYokingaku(int yokingaku) {
		this.yokingaku = yokingaku;
	}

	/**
	 * 入金処理 or 引出し
	 * @param input 標準入力
	 * @param isNyukin
	 */
	public void nyukin(Scanner input, boolean isNyukin) {
		String shoriName = null;
		if (isNyukin) {
			shoriName = "入金";
		} else {
			shoriName = "引出し";
		}
		System.out.println(shoriName + "する金額を入力してください。");
		while(true) {
			String nyukin = input.nextLine();
			if (this.validate(nyukin)) {
				continue;
			}
			if(this.calcurate(nyukin, isNyukin)) {
				System.out.println("あなたの預金額は¥" + this.yokingaku + "です。");
				break;
			}
		}

	}

	/**
	 * 入力チェック処理
	 * @param in
	 * @return true: 入力エラー false: 入力OK!
	 */
	private boolean validate(String in) {
		// 必須入力チェック
		if (in == null || "".equals(in)) {
			System.out.println("必須入力です、数字を入力してください。");
			return true;
		}
		// 数字の入力チェック
		if (in.matches("[0-9]*") == false) {
			System.out.println("数字を入力してください。");
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param in 数字文字
	 * @param isNyukin ture: 入金処理 false: 引出し処理
	 */
	private boolean calcurate(String in, boolean isNyukin) {
		// 数字の入力チェック済み(本当はチェック処理を行った直後の方が良い)
		if (isNyukin) {
			// 入金処理
			int nyukin = Integer.parseInt(in);
			this.yokingaku = this.yokingaku + nyukin;
		} else {
			// 引出し処理
			int nyukin = Integer.parseInt(in);
			this.yokingaku = this.yokingaku - nyukin;
		}
		return true;
	}
}
