/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.lv2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author takunoji
 *
 * 2019/07/28
 */
public class Lv2_3_CalculateTest {
	/** Lv2_2_Calculate(整数で割り算) */
	@Test
	public void testCalcDouble1() {
		double ans = 4 / 3;
		System.out.println("整数で割り算: " + ans);
	}
	/** Lv2_2_Calculate(小数で割り算) */
	@Test
	public void testCalcDouble2() {
		double ans = 4.0 / 3.0;
		System.out.println("少数で割り算: " + ans);
	}

	/*
	 * judgeKigo()のテスト
	 */
	@Test
	public void testJudgeKigo() {
		Lv2_3_Calculate test = new Lv2_3_Calculate();
		try {
			int kigo = test.jadgeKigo("1+1");
			assertEquals(0, kigo);
			int kigo1 = test.jadgeKigo("1-1");
			assertEquals(1, kigo1);
			int kigo2 = test.jadgeKigo("1*1");
			assertEquals(2, kigo2);
			int kigo3 = test.jadgeKigo("1/1");
			assertEquals(3, kigo3);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	/*
	 * Lv2_3_Calculate#calculate()のテスト
	 * リファクタリング用
	 * Switch文を何度も書かないように修正
	 */
	public void testCalculate() {
		Lv2_3_Calculate testClass = new Lv2_3_Calculate();
		String answer = testClass.calculate("1 + 1");
		assertEquals("2", answer);
	}
}
