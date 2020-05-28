/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.basic;

/**
 * @author takunoji
 *
 * 2020/05/27
 */
public class SecondProgram {
	public static void main(String[] args) {
		int num1 = 0;
		int num2 = 1;
		// 文字が連結されてしまうので先に計算をする必要がある。
		System.out.println("num1 + num2 = " + num1 + num2);
		
		int num3 = 2;
		int result = num2 * num3;
		System.out.println("今度はnum2 x num3 = " + result);
		
		// 今度は余計なことをやってみる
		int[] numArray = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int answer = 0;
		// 1から10までの数字を足すと。。。
		for (int i = 0; i < 10; i++) {
			answer += numArray[i];
		}
		System.out.println("1から10までの数字を足すと。。。" + answer);
	}
}
