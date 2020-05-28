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
public class ThirdProgram {
	public static void main(String[] args) {
		int a1 = 2;
		int a2 = 4;
		System.out.println("a1 x a2 = " + (a1 + a2));

		int d1 = a2 / a1;
		System.out.println("a1 / a2 = " + d1);
		
		try {
			int res = a2 / 3;
			System.out.println("res = " + res);
			// どうやらdouble型で計算しないと小数点以下がちゃんと計算されないようです。
			double res1 = 7.0 / 3.0;
			System.out.println("res1 = " + res1);
		} catch(Exception e) {
			System.out.println("エラーにならなかった。。。");
		}

		int aaa = 2034567890;
		// ロング型はデータが大きくてもOK
		long aa1 = 2534567890000L;
		System.out.println("aa1 - aaa = " + (aa1 - aaa));
	}
}
