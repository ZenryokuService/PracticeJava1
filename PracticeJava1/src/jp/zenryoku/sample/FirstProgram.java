/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample;

/**
 * この部分はJavaDocと言います。
 * 初めてのプログラムを組んで見る。<br/>
 * 処理として行うことは、コンソールに「Hello World」を表示すること<br/>
 * 出力する内容としてはHTMLになるので<b>HTMLで書ける</b>
 * 
 * @author takunoji
 * 2020/05/26
 */
public class FirstProgram {
	/**
	 * メインメソッド。
	 * @param args プログラム引数
	 */
	public static void main(String[] args) {
		// はじめの一歩
		System.out.println("Hello World");
		// 二歩目
		System.out.print("明日の天気は");
		System.out.println("何でしょうか？");
		System.out.println("晴れ！");
		// 三歩目
		String name = "タイガーマスク";
		System.out.println("私の正体は・・・" + name);
		
		// 四歩目
		String title = "初めてのプログラム";
		String honbun = "本文です。";
		System.out.println("*************************");
		System.out.println("*   " + title + "    *");
		System.out.println("*************************");
		System.out.println(honbun);
		System.out.println("*************************");
		
	}
}
