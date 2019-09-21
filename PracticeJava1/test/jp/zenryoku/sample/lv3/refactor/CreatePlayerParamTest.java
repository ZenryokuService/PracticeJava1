/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.lv3.refactor;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import jp.zenryoku.sample.lv3.refactor.cmd.HelloCommand;
import jp.zenryoku.sample.lv3.refactor.cmd.ReadyCommand;

/**
 * @author takunoji
 * @see https://github.com/ZenryokuService/PracticeJava1/blob/master/PracticeJava1/src/jp/zenryoku/sample/lv3/refactor/CreatePlayerParam.java
 * 2019/08/31
 */
public class CreatePlayerParamTest {
	/** テスト対象クラス */
	private CreatePlayerParam target;
	/**
	 * クラスを起動するときに１回だけ動く
	 */
	@Before
	public void init() {
		System.out.println("テスト開始");
		target = new CreatePlayerParam();
	}

	/**
	 * テストの実行が終わると実行される
	 */
	@After
	public void terminated() {
		System.out.println("テスト終了");
		// メモリの解放
		target = null;
	}

	/**
	 * 正常系テスト。
	 * Nanashinoと入力したら以下のように出力する
	 * 母音：aaio
	 * 子音：Nnshn
	 */
	@Test
	public void testCutOffBoin() {
		String result = null;
		try {
			result = target.cutOffBoin("Nanashino");
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("結果：" + result);
		assertEquals("aaio,Nnshn", result);
	}

	public void test001() {
		int test1 = 0;
		int test2 = 2147483647;
		// サイズオーバーでビルドエラーになる
		char test3 = 65535;
		long test4 = 2147483648L;
		// 配列の初期化
		double[] d = new double[] {1.0, 2.2, 0.1};
		CommandIF[] cmds = new CommandIF[] {new HelloCommand(), new ReadyCommand()};	
	}
	@Test
	public void test0002() {
		
		for(int i = 0; i < 10; i++) {
			System.out.print(" ");
		}
	}
}
