/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.basic.lv2;

import java.util.Scanner;

/**
 * @author takunoji
 *
 * 2020/06/02
 */
public class TenthProgram {
	public static void main(String[] args) {
		// クラスのインスタンスを生成する
		TenthProgram main = new TenthProgram();
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.print("入力してください:");
			String in = scan.next();
			if ("bye".equals(in)) {
				break;
			}
			if ("keisan".equals(in)) {
				main.keisan(scan);
			}
		}
	}

	public void keisan(Scanner scan) {
		System.out.print("数字を入力してください: ");
		String left = scan.next();
		System.out.print("数字を入力してください: ");
		String right = scan.next();
		try {
			int leftNum = Integer.parseInt(left);
			int rightNum = Integer.parseInt(right);
			System.out.println("計算結果" + (leftNum + rightNum));
		} catch(NumberFormatException numE) {
			System.out.println("数字ではありません");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
