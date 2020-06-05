/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.my.sample.cls;

/**
 * @author takunoji
 *
 * 2020/06/05
 */
public class FirstCls {

	public void handleInput(String input) {
		System.out.println("入力値: " + input);
		if (isNumberString(input)) {
			System.out.println(input + "は、数字です");
		} else {
			System.out.println(input + "は、数字ではありません");
		}
	}

	public boolean isNumberString(String str) {
		// [0-9]は正規表現と言います。
		boolean isNumber = str.matches("[0-9]");
		return isNumber;
	}

	/** 使用しないので下のアノテーションをつける */
	@Deprecated
	public void printSomething(String[] args) {
		//  プログラム引数に値があるときは表示する
		if (args.length != 0) {
			for (int i = 0; i < args.length; i++) {
				System.out.println("プログラム引数[" + i + "]: " + args[i]);
			}
		}
		System.out.println("1 + 1 = " + (1 + 1));
	}
}
