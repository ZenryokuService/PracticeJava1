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
 * 2020/05/28
 */
public class FourthProgram {
	public static void main(String[] args) {
		// int型の配列
		int[] intArray = new int[] {0, 1, 2, 3, 4};
		double[] doubleArray = new double[] {1.2, 3.4, 5.6, 7.8, 9.0};
		String[] strArray = new String[] {"あ", "い","う","え","お"};
		
		System.out.println("配列の中身 >>> int[0]" + intArray[0] + "int[1]: " + intArray[1]);

		for (int i = 0; i < intArray.length; i++) {
			int a = intArray[i];
			double b = doubleArray[i];// 配列数が違うので落ちる
			if (i%2 == 0) {
				System.out.println("配列の合計>>> int * double = " + (a * b));
			} else {
				System.out.print("配列の合計>>> int * double = " + (a * b));
			}
			
		}
	}
}
