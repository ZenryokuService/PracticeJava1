/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.oracle.doc;

/**
 * @author takunoji
 *
 * 2019/10/27
 */
public class Cup {
	// ＜状態＞
	/** 液体が入っている(いない) */
	private boolean inLiquid;
	/** テーブルの上、地面の上などにある */
	private boolean onTable;
	/** 壊れた(壊れてない) */
	private boolean isBroked;
	/** コップの口が上を向いている(下を向いている) */
	private boolean stateNormal;
	// ＜動作＞
	/** 
	 * 飲む 
	 * @param drinked 飲んだ量
	 */
	public void drink(int drinked) {
	}
	/** 
	 * 注ぐ
	 * @param pour 注いだ量
	 */
	public void pourItUp(int pour) {
	}
	/** 
	 * 投げる
	 * @param power
	 */
	public void throwCup(double power) {
	}
	/**
	 *  ぶつかる
	 * @param hardness ぶつかったものの硬さ
	 */
	public void crashed(double hardness) {
	}
}
