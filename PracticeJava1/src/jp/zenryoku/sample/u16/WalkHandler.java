/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.u16;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * U16プログラミングコンテスト、クライアントアプリの移動方向管理クラス。
 * @author takunoji
 * 2019/05/25
 */
public class WalkHandler {
	/** 移動可能ポジション */
	private Map<Integer, Double> walkable;
	/** 上に移動可能 */
	private boolean okUp;
	/** 下に移動可能 */
	private boolean okDown;
	/** 右に移動可能 */
	private boolean okRight;
	/** 左に移動可能 */
	private boolean okLeft;
	/** 侵攻方向優先順位配列 */
	private Integer[] directPriority;

	/**
	 * 進行方向などの移動に関する情報を管理する。
	 */
	public WalkHandler() {
		// 上、右、下、左の時計回りに優先順位を初期化
		directPriority = new Integer[]{1, 5, 7, 3};
		walkable = new HashMap<Integer, Double>();
		walkable.put(1, 0.0);
		walkable.put(3, 0.0);
		walkable.put(5, 0.0);
		walkable.put(7, 0.0);
		
	}
	/**
	 * @return the okUp
	 */
	public boolean isOkUp() {
		return okUp;
	}
	
	/**
	 * 上、右、下、左の時計回りに優先順位を初期化
	 * @return the directPriority
	 */
	public Integer[] getDirectPriority() {
		return directPriority;
	}
	/**
	 * 上、右、下、左の時計回りに優先順位を初期化
	 * @param directPriority the directPriority to set
	 */
	public void setDirectPriority(Integer[] directPriority) {
		this.directPriority = directPriority;
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
	/**
	 * @return the walkable
	 */
	public Map<Integer, Double> getWalkable() {
		return walkable;
	}
	/**
	 * @param walkable the walkable to set
	 */
	public void setWalkable(Map<Integer, Double> walkable) {
		this.walkable = walkable;
	}
	
}
