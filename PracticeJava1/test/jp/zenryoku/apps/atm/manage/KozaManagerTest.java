/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.apps.atm.manage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jp.zenryoku.apps.atm.data.Data;

/**
 * @author takunoji
 *
 * 2019/09/25
 */
public class KozaManagerTest {
	/** テスト対象クラス */
	private KozaManager target;

	/**
	 *  テストの初期化 
	 *  各テスト実行前に起動する
	 */
	@Before
	public void initTest() {
		target = new KozaManager();
	}

	/**
	 * コンストラクタが起動したかどうかを確認する
	 * テストケース
	 */
	@Test
	public void testIsInstance() {
		assertNotNull(target);
	}

	/**
	 * ファイルの存在チェック処理の確認
	 */
	@Test
	public void testIsFile() {
		// コンストラクタでファイルが作成されることに注意
		assertTrue(target.isFile());
	}
	
	/**
	 * ファイルにデータを出力し保存するテストケースです。
	 */
	@Test
	public void testFileCeate() {
		Data data = new Data("test", "passwd");
		try {
			target.dataOutput(data);
		} catch(IOException ie) {
			ie.printStackTrace();
			fail("ファイル入出力に問題があります。");
		} catch(Exception e) {
			e.printStackTrace();
			fail("想定外のエラーが起きました。");
		}
	}

	@Test
	public void testFileRead() {
		if (target.isFile() == false) {
			fail("ファイルが存在していません");
		}
		List<Data> dataList = target.readFile();
		// データは0件ではない
		assertNotEquals(0, dataList.size());
		// ファイルにあるデータ
		Data data = dataList.get(1);
		assertEquals("test", data.getName());
		assertEquals("passwd", data.getPassword());
	}

	
}
