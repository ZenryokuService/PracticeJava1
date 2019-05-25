/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.u16;

/**
 * U16プログラミングコンテスト、クライアントアプリの移動方向管理クラス。
 * @author takunoji
 * 2019/05/25
 */
public class WalkHandler {
	/** 上に移動可能 */
	private boolean okUp;
	/** 下に移動可能 */
	private boolean okDown;
	/** 右に移動可能 */
	private boolean okRight;
	/** 左に移動可能 */
	private boolean okLeft;
	/**
	 * @return the okUp
	 */
	public boolean isOkUp() {
		return okUp;
	}
	/**
	 * @param okUp the okUp to set
	 */
	public void setOkUp(boolean okUp) {
		this.okUp = okUp;
	}
	/**
	 * @return the okDown
	 */
	public boolean isOkDown() {
		return okDown;
	}
	/**
	 * @param okDown the okDown to set
	 */
	public void setOkDown(boolean okDown) {
		this.okDown = okDown;
	}
	/**
	 * @return the okRight
	 */
	public boolean isOkRight() {
		return okRight;
	}
	/**
	 * @param okRight the okRight to set
	 */
	public void setOkRight(boolean okRight) {
		this.okRight = okRight;
	}
	/**
	 * @return the okLeft
	 */
	public boolean isOkLeft() {
		return okLeft;
	}
	/**
	 * @param okLeft the okLeft to set
	 */
	public void setOkLeft(boolean okLeft) {
		this.okLeft = okLeft;
	}
}
