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
import java.util.Map;

/**
 * U16プログラミングコンテスト、クライアントアプリの移動方向管理クラス。
 * ネーミング的には移動方向管理だが、動きも管理する。PUT, WALK
 * @author takunoji
 * 2019/05/25
 */
public class WalkHandler {
	/** 左上方向を示す(3x3マス) */
	public static final int LEFT_UP = 0;
	/** 上方向を示す(3x3マス) */
	public static final int UP = 1;
	/** 右上方向を示す(3x3マス) */
	public static final int RIGHT_UP = 2;
	/** 左方向を示す(3x3マス) */
	public static final int LEFT = 3;
	/** 右方向を示す(3x3マス) */
	public static final int RIGHT = 5;
	/** 左下方向を示す(3x3マス) */
	public static final int LEFT_DOWN = 6;
	/** 下方向を示す(3x3マス) */
	public static final int DOWN = 7;
	/** 右下向を示す(3x3マス) */
	public static final int RIGHT_DOWN = 8;
	/** 進行方向優先順位：左上への進行方向(上, 左, 右, 下)  */
	private final static Integer[] PRIRITY_UP_LEFT= new Integer[] {1, 3, 5, 7};
	/** 進行方向優先順位：デフォルト上への進行方向(上, 右, 左, 下)  */
	private final static Integer[] PRIRITY_UP = new Integer[] {1, 5, 3, 7};
	/** 進行方向優先順位：右上への進行方向デフォルトに同じ(上, 右, 左, 下)  */
	private final static Integer[] PRIRITY_UP_RIGHT= new Integer[] {1, 5, 7, 3};
	/** 進行方向優先順位：左への進行方向(左, 上, 下, 右)  */
	private final static Integer[] PRIRITY_LEFT= new Integer[] {3, 1, 7, 5};
	/** 進行方向優先順位：右への進行方向(右, 下, 上, 左)  */
	private final static Integer[] PRIRITY_RIGHT= new Integer[] {5, 7, 1, 3};
	/** 進行方向優先順位：下への進行方向(下, 右, 左, 上)  */
	private final static Integer[] PRIRITY_DOWN= new Integer[] {7, 5, 3, 1};
	/** 進行方向優先順位：左下への進行方向下地方向と同じ(下, 左, 右, 上)  */
	private final static Integer[] PRIRITY_DOWN_LEFT= new Integer[] {7, 3, 5, 1};
	/** 進行方向優先順位：右下への進行方向(下, 右, 左, 上)  */
	private final static Integer[] PRIRITY_DOWN_RIGHT= new Integer[] {7, 5, 3, 1};
	/** 移動可能ポジション */
	private final Map<Integer, Double> walkable;
	/** Lookコマンド実行時の周囲情報 */
	private Double[] nextBufMap;
	/** 上に移動可能 */
	private boolean okUp;
	/** 下に移動可能 */
	private boolean okDown;
	/** 右に移動可能 */
	private boolean okRight;
	/** 左に移動可能 */
	private boolean okLeft;
	/** サーチ済み(上) */
	private boolean okSearchUp;
	/** サーチ済み(右) */
	private boolean okSearchRight;
	/** サーチ済み(下) */
	private boolean okSearchDown;
	/** サーチ済み(左) */
	private boolean okSearchLeft;
	/** Look済み(上) */
	private boolean okLookUp;
	/** Look済み(右) */
	private boolean okLookRight;
	/** Look済み(下) */
	private boolean okLookDown;
	/** Look済み(左) */
	private boolean okLookLeft;
	/** 進行方向 */
	private Integer direction;
	/** 進行方向優先順位配列 */
	private Integer[] directPriority;
	/** 目的とする方向 */
	private Integer targetDirection;
	/** 他のプレーヤがいるポジション(3x3) */
	private Integer playerPos;
	/** アイテムのあるポジション */
	private Integer itemPos;
	/** 壁までの距離 */

	/**
	 * 進行方向などの移動に関する情報を管理する。
	 */
	public WalkHandler() {
		nextBufMap = null;
		// 優先順位をセット
		// 上、右、下、左の時計回りに優先順位を初期化
		directPriority = new Integer[]{1, 5, 7, 3};
		// 移動可能かを示す(1, 0.0)->上方向は空白(移動可能)
		walkable = new HashMap<Integer, Double>();
		walkable.put(1, 0.0);
		walkable.put(3, 0.0);
		walkable.put(5, 0.0);
		walkable.put(7, 0.0);
		playerPos = 0;
		itemPos = 0;
		direction = -1;
		nextBufMap = new Double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		// 進行方向優先順位を設定する
		this.setDirection(UP);
		this.setDirectPriority(PRIRITY_UP);
	}

	public static Integer[] getPriprityArray(int direc) throws Exception {
		Integer[] priority = null;
		switch(direc) {
		case LEFT_UP:
			priority = PRIRITY_UP_LEFT;
			break;
		case UP:
			priority = PRIRITY_UP;
			break;
		case RIGHT_UP:
			priority = PRIRITY_UP_RIGHT;
			break;
		case LEFT:
			priority = PRIRITY_LEFT;
			break;
		case RIGHT:
			priority = PRIRITY_RIGHT;
			break;
		case LEFT_DOWN:
			priority = PRIRITY_DOWN_LEFT;
			break;
		case DOWN:
			priority = PRIRITY_DOWN;
			break;
		case RIGHT_DOWN:
			priority = PRIRITY_DOWN_RIGHT;
			break;
		default:
			throw new Exception("想定外の方向指定です。:" + direc);
		}
		return priority;
	}

	/**
	 * @return the pRIRITY_UP
	 */
	public Integer[] getPRIRITY_UP() {
		return PRIRITY_UP;
	}

	/**
	 * @return the pRIRITY_RIGHT
	 */
	public Integer[] getPRIRITY_RIGHT() {
		return PRIRITY_RIGHT;
	}

	/**
	 * @return the targetDirection
	 */
	public Integer getTargetDirection() {
		return targetDirection;
	}

	/**
	 * @param targetDirection the targetDirection to set
	 */
	public void setTargetDirection(Integer targetDirection) {
		this.targetDirection = targetDirection;
	}

	/**
	 * @return the pRIRITY_DOWN
	 */
	public Integer[] getPRIRITY_DOWN() {
		return PRIRITY_DOWN;
	}

	/**
	 * @return the pRIRITY_UP_LEFT
	 */
	public Integer[] getPRIRITY_UP_LEFT() {
		return PRIRITY_UP_LEFT;
	}

	/**
	 * @return the pRIRITY_UP_RIGHT
	 */
	public Integer[] getPRIRITY_UP_RIGHT() {
		return PRIRITY_UP_RIGHT;
	}

	/**
	 * @return the pRIRITY_DOWN_LEFT
	 */
	public Integer[] getPRIRITY_DOWN_LEFT() {
		return PRIRITY_DOWN_LEFT;
	}

	/**
	 * @return the pRIRITY_DOWN_RIGHT
	 */
	public Integer[] getPRIRITY_DOWN_RIGHT() {
		return PRIRITY_DOWN_RIGHT;
	}
	
	/**
	 * @return the nextBufMap
	 */
	public Double[] getNextBufMap() {
		return nextBufMap;
	}

	/**
	 * @param nextBufMap the nextBufMap to set
	 */
	public void setNextBufMap(Double[] nextBufMap) {
		this.nextBufMap = nextBufMap;
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
	 * @return the playerPos
	 */
	public Integer getPlayerPos() {
		return playerPos;
	}

	/**
	 * @param playerPos the playerPos to set
	 */
	public void setPlayerPos(Integer playerPos) {
		this.playerPos = playerPos;
	}

	/**
	 * @return the itemPos
	 */
	public Integer getItemPos() {
		return itemPos;
	}

	/**
	 * @param itemPos the itemPos to set
	 */
	public void setItemPos(Integer itemPos) {
		this.itemPos = itemPos;
	}

	/**
	 * @return the direction
	 */
	public Integer getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	/// ロジックを含む処理 ///

	/**
	 * @return the okSearchUp
	 */
	public boolean isOkSearchUp() {
		return okSearchUp;
	}


	/**
	 * @param okSearchUp the okSearchUp to set
	 */
	public void setOkSearchUp(boolean okSearchUp) {
		this.okSearchUp = okSearchUp;
	}


	/**
	 * @return the okSearchRight
	 */
	public boolean isOkSearchRight() {
		return okSearchRight;
	}


	/**
	 * @param okSearchRight the okSearchRight to set
	 */
	public void setOkSearchRight(boolean okSearchRight) {
		this.okSearchRight = okSearchRight;
	}


	/**
	 * @return the okSearchDown
	 */
	public boolean isOkSearchDown() {
		return okSearchDown;
	}


	/**
	 * @param okSearchDown the okSearchDown to set
	 */
	public void setOkSearchDown(boolean okSearchDown) {
		this.okSearchDown = okSearchDown;
	}


	/**
	 * @return the okSearchLeft
	 */
	public boolean isOkSearchLeft() {
		return okSearchLeft;
	}


	/**
	 * @param okSearchLeft the okSearchLeft to set
	 */
	public void setOkSearchLeft(boolean okSearchLeft) {
		this.okSearchLeft = okSearchLeft;
	}


	/**
	 * @return the okLookUp
	 */
	public boolean isOkLookUp() {
		return okLookUp;
	}


	/**
	 * @param okLookUp the okLookUp to set
	 */
	public void setOkLookUp(boolean okLookUp) {
		this.okLookUp = okLookUp;
	}


	/**
	 * @return the okLookRight
	 */
	public boolean isOkLookRight() {
		return okLookRight;
	}


	/**
	 * @param okLookRight the okLookRight to set
	 */
	public void setOkLookRight(boolean okLookRight) {
		this.okLookRight = okLookRight;
	}


	/**
	 * @return the okLookDown
	 */
	public boolean isOkLookDown() {
		return okLookDown;
	}


	/**
	 * @param okLookDown the okLookDown to set
	 */
	public void setOkLookDown(boolean okLookDown) {
		this.okLookDown = okLookDown;
	}


	/**
	 * @return the okLookLeft
	 */
	public boolean isOkLookLeft() {
		return okLookLeft;
	}


	/**
	 * @param okLookLeft the okLookLeft to set
	 */
	public void setOkLookLeft(boolean okLookLeft) {
		this.okLookLeft = okLookLeft;
	}

	/**
	 * 現在の進行方向の次の進行方向を取得する。
	 * @param direction 現在の進行方向
	 * @return 次の進行方向
	 */
	public int getNextPriority(int direction) {
		int num = 0;
		for(int i = 0; i < directPriority.length; i++) {
			if (directPriority[i] == direction) {
				num = i + 1;
				break;
			}
		}
		return directPriority[num];
	}

	/** getDirectPriority()のオーバーロード */
	public Integer[] getDirectPriority(int direction) throws Exception {
		Integer[] responsePriority;
		switch(direction) {
		case LEFT_UP:
			responsePriority = WalkHandler.PRIRITY_UP_LEFT;
			break;
		case UP:
			responsePriority = WalkHandler.PRIRITY_UP;
			break;
		case RIGHT_UP:
			responsePriority = WalkHandler.PRIRITY_UP_RIGHT;
			break;
		case LEFT:
			responsePriority = WalkHandler.PRIRITY_LEFT;
			break;
		case RIGHT:
			responsePriority = WalkHandler.PRIRITY_RIGHT;
			break;
		case LEFT_DOWN:
			responsePriority = WalkHandler.PRIRITY_DOWN_LEFT;
			break;
		case DOWN:
			responsePriority = WalkHandler.PRIRITY_DOWN;
			break;
		case RIGHT_DOWN:
			responsePriority = WalkHandler.PRIRITY_DOWN_RIGHT;
			break;
		default:
			throw new Exception("想定外の方向です。:" + direction);
		}
		return responsePriority;
	}
}
