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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import jp.zenryoku.sample.u16.ClientManager;

/**
 * プロコンのJavaクライアントクラスのマネージャをテストする
 * テスト対象クラス；jp.zenryoku.sample.u16.ClientManager
 * @author takunoji
 * 2019/05/24
 */
public class INDArrayTest {
	/** テスト対象クラス */
	private ClientManager target;

	public INDArrayTest() {
		target = new ClientManager();
	}

	private Method getTargetMethod(String methodName) {
		Method method = null;
		try {
			method = target.getClass().getDeclaredMethod(methodName);
			// 公開レベルをテストのために変更する
			method.setAccessible(true);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return method;
	}
	
	@Test
	public void test1() {
		Method method = this.getTargetMethod("createMap");
		boolean isError = false;
		try {
			System.out.println(method.invoke(target));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			isError = true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			isError = true;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			isError = true;
		} catch (Exception e) {
			e.printStackTrace();
			isError = true;
		}finally {
			if (isError) {
				fail("テスト失敗");
			}
		}
	}

	@Test
	public void test02() {
		Method method = this.getTargetMethod("mapping");
		boolean isError = false;
		try {
			System.out.println(method.invoke(target, "1000000000"));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			isError = true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			isError = true;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			isError = true;
		} catch (Exception e) {
			e.printStackTrace();
			isError = true;
		}finally {
			if (isError) {
				fail("テスト失敗");
			}
		}
	}
}
