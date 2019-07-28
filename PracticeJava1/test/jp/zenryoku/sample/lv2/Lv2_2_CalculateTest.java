/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.lv2;

import org.junit.Test;

/**
 * @author takunoji
 *
 * 2019/07/28
 */
public class Lv2_2_CalculateTest {
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
}
