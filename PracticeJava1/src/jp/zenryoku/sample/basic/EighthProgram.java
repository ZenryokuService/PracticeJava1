/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.basic;

import java.util.Scanner;

/**
 * @author takunoji
 *
 * 2020/06/02
 */
public class EighthProgram {
	public static void main(String[] args) {
		// ゲームループ(無限ループ)
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("*** コマンドを入力してください ***");
			String val = scan.next();
			if ("bye".equals(val)) {
				System.out.println("*** ループ終了 ***");
				break;
			}
			System.out.println("*** 入力コマンド: " + val + " ***");
		}
	}
}
