/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.lv2;

import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author takunoji
 *
 * 2019/07/27
 */
public class Lv2__1_CalculateTest {
	/** Lv2__1_Calculate */
	@Test
	public void test01() {
		String input = "1+1";
		String regrex = "[0-9].*[0-9]";

		if (input.matches(regrex)) {
			System.out.println("マッチ");
		} else {
			System.out.println("ミスマッチ");
		}
	}

	@Test
	public void test02() {
		String input = "move 1";
		String regrex = "move [0-9]";
		if (input.matches(regrex)) {
			System.out.println("OK");
		} else {
			fail("不適切な正規表現です: " + regrex);
		}
	}

	@Test
	public void test03() {
		String input = "move 1";
		String regrex = "move [0-9]";
		if (input.startsWith("move")) {
			System.out.println("OK");
		} else {
			fail("不適切な正規表現です: " + regrex);
		}
	}
}
