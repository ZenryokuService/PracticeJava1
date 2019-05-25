/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.ml.nd4j;

import static org.junit.Assert.fail;

import java.awt.image.DataBuffer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import jp.zenryoku.sample.u16.ClientManager;

/**
 * ND4JのNd４jクラスを学習のため、テストする
 * @author takunoji
 * 2019/05/24
 */
public class TestNd4j {
	/** テスト対象クラス */
	private static ClientManager target;

	/**
	 * テストクラスの実行時に一度だけ起動するメソッド。
	 * Afterは終了時に呼ばれる
	 */
	@BeforeClass
	public static void init() {
		 System.out.println("*** BeforeClass ***");
		target = new ClientManager();
	}

	@AfterClass
	public static void after() {
		 System.out.println("*** AfterClass ***");
		target = null;
	}

	/**
	 * 
	 * @param <T> 取得するメソッドの引数の型
	 * @param methodName
	 * @return
	 */
	private <T> Method getPrivateMethod(String methodName, Class<T> ... args ) {
		Method method = null;
		try {
			if (args == null) {
				method = target.getClass().getDeclaredMethod(methodName);
			} else {
				method = target.getClass().getDeclaredMethod(methodName, String.class);
			}
			// 公開レベルをテストのために変更する
			method.setAccessible(true);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			fail("メソッドの取得に失敗");
		}
		return method;
	}

	/**
	 * ClientManagerのコンストラクタで、マッピング用行列を初期化する。
	 * 1.初期化時に中身を４で埋める<br>
	 * 2.移動した時にMapを拡張するので配列の拡張方法も確認
	 */
	@Test
	public void testCreateINDArray() {
		// INT型データの行列を作成する
		INDArray data = Nd4j.create(new int[] {3, 3});
		System.out.println("*** init zeros***");
		System.out.println(data);
		System.out.println("*** putScalar ***");
		System.out.println(data.putScalar(new int[] {2, 1}, 1.0));
		System.out.println("*** init ones ***");
		// １の値で初期化された配列に全て３を足す
		INDArray reData = Nd4j.ones(new int[] {3, 3}).addi(3);
		System.out.println(reData);
		System.out.println(Nd4j.pad(reData, new int[] {6,  6}, Nd4j.PadMode.CONSTANT));
	}

	/**
	 * 現在の状況を確認するためのマップを初期化する確認。
	 * 1. サーバーレスポンスを受けてそれをマップ配列に格納する
	 */
	@Test
	public void testSetBufMap() {
		Method test = this.getPrivateMethod("setBufMap", String.class);
		try {
			test.invoke(target, "1002001222");
			String[] res = target.getBufMap();
			for (String val: res) {
				System.out.print(val + ", ");
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail("メソッドの実行に失敗");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail("メソッドの実行に失敗");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			fail("メソッドの実行に失敗");
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("メソッドの実行に失敗");
		}
	}
}
