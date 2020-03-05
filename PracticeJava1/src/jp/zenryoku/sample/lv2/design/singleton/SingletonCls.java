/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.lv2.design.singleton;

/**
 * @author takunoji
 *
 * 2020/03/05
 */
public class SingletonCls {
	/** このクラスのインスタンス */
	private static SingletonCls instance;
	/** 設定情報１ */
	private int setting1;
	/** 設定情報２ */
	private String setting2;
	
	public static SingletonCls getInstance() {
		if (instance == null) {
			instance = new SingletonCls();
		}
		return instance;
	}

	/** コンストラクタは外部から参照不可 */
	private SingletonCls() {
	}

	/**
	 * @return the setting1
	 */
	public int getSetting1() {
		return setting1;
	}

	/**
	 * @param setting1 the setting1 to set
	 */
	public void setSetting1(int setting1) {
		this.setting1 = setting1;
	}

	/**
	 * @return the setting2
	 */
	public String getSetting2() {
		return setting2;
	}

	/**
	 * @param setting2 the setting2 to set
	 */
	public void setSetting2(String setting2) {
		this.setting2 = setting2;
	}
}
