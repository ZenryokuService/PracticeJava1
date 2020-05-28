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
public class FifthProgram {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("プログラム引数を入力してください");
			System.exit(0);
		}
		if ("hello".equals(args[0])) {
			System.out.println("Hello World!");
		} else if ("world".equals(args[0])) {
			System.out.println("ジョジョもびっくり！");
		} else {
			System.out.println("想定外の値です。");
		}
		try {
			System.out.println("テスト: " + args[2]);
		} catch (Exception e) {
			System.out.println("プログラム引数は２以下でした。。。");
		}
		
	}
}
