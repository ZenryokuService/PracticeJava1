/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample;

import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author takunoji
 *
 * 2020/10/12
 */
public class StringTest {
	@Test
	public void test01() {
		String val = "123456";
		if (val.matches("[0-9]{4,}")) {
			System.out.println("Hello!");
		} else {
			fail();
		}
	}
}
