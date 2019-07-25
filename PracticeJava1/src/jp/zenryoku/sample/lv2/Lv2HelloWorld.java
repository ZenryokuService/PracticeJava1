/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.lv2;

import java.util.Scanner;

/**
 * @author takunoji
 *
 * 2019/07/25
 */
public class Lv2HelloWorld {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		while(true) {
			System.out.print("入力してください: ");
			String inStr = input.nextLine();
			if ("hello".equals(inStr)) {
				System.out.println("Hello World!");
			} else {
				System.out.println("想定外の入力です");
				break;
			}
			System.out.println("Next command ... ");
		}
		System.out.print("AP is fiinished. Bye!");
	}
}
