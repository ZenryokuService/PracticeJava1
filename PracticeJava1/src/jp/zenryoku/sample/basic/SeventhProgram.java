/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.basic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author takunoji
 *
 * 2020/05/30
 */
public class SeventhProgram {
	public static void main(String[] args) {
		   byte[] input = new byte[5]; // 5文字までの入力分
		   try {
			   System.out.println("入力してください");
			   System.in.read(input);
		   } catch (IOException e) {
			   e.printStackTrace();
			   System.exit(-1);
		   }
		   String out = new String(input, StandardCharsets.UTF_8);
		   System.out.print("あなたが入力したはじめの５文字は");
		   System.out.println(out); // コンソール出力
		}
}
