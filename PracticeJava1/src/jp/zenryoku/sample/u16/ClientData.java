/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.u16;

import java.util.ArrayList;
import java.util.List;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * U16プログラミングコンテストのクライアントアプリのデータを管理するクラス。
 * 
 * @author takunoji
 * 2019/05/25
 */
public class ClientData {
	/** デバッカフラグ */
	public boolean isDebug;
	/** タイムアウトする時間(ミリ秒) */
	public static final int TIME_OUT = 10000;
	/** 改行コード */
	public static final String ENTER = "\r\n";
	/** COOLのポート番号 */
	public static final int COOL = 2009;
	/** HOTのポート番号 */
	public static final int HOT = 2010;
	/** GetReadyを送信した時のフラグ */
	public static final String GET_READY = "G"; 
	/** 行動コマンドを送信した時のフラグ */
	public static final String COMMAND_WALK = "W"; 
	/** 行動終了コマンドを送信した時のフラグ */
	public static final String SHARP = "S"; 
	/** ゲームエリアの空白を占めす定数 */
	public static final String SAFETY_ZONE = "0";
	/** ゲームエリアのアイテムを占めす定数 */
	public static final String ITEM_ZONE = "3";
	/** ゲームエリアのブロックを占めす定数 */
	public static final String BLOCK_ZONE = "2";
	/** ゲームエリアの相手プレーヤを占めす定数 */
	public static final String OPPONENT_PLAYER = "1";
	/** ゲームエリアの未知のエリアを占めす定数 */
	public static final String UNKNOWN_ZONE = "4";
	/** サーバーレスポンスの１バイト目(1=Alive, 0:=Dead) */
	public static final String IS_ALIVE = "1";

	/** ゲームのターン数 */
	private int gameStep;
	/** 移動可能な場所 */
	private List<Double> walkable;
	public static String READY_CMD_RES = "@";
	/** Searchコマンドを送信した事を示す */
	public static final String SEARCH_CMD = "s";
	/** Lookコマンドを送信した事を示す */
	public static final String LOOK_CMD = "l";
	/** Putコマンドを送信した事を示す */
	public static final String PUT_CMD = "p";
	/** Walkコマンドを送信したことを示す */
	public static final String WALK_CMD = "w";
	/** 終了コマンド(Stringで使用する) */
	public static final String END_TURN = "#";
	/** 方向を示すコマンド(上) */
	public static final String UP = "u";
	/** 方向を示すコマンド(上) */
	public static final String DOWN = "d";
	/** 方向を示すコマンド(上) */
	public static final String LEFT = "l";
	/** 方向を示すコマンド(上) */
	public static final String RIGHT = "r";
	/** 自分の上方向を示すレスポンスデータの位置 */
	public static final int UP_POS = 1;
	/** 自分の左方向を示すレスポンスデータの位置 */
	public static final int LEFT_POS = 3;
	/** 自分の右方向を示すレスポンスデータの位置 */
	public static final int RIGHT_POS = 5;
	/** 自分の下方向を示すレスポンスデータの位置 */
	public static final int DOWN_POS = 7;
	/** 周囲確認用BufferedMap */
	private Double[] bufMap;
	/** 周囲確認済み(bufMap更新済み)フラグ */
	private boolean isReady;
	/** 周囲に相手プレーヤがいるかどうかのフラグ */
	private boolean isPlayer;
	/** 周囲にアイテムがあるかどうかのフラグ */
	private boolean isItem;
	/** ND4Jの行列部品 */
	private INDArray map;
	/** 作成したMapの中心(19x19のマス) */
	public static final int[] START_POS = {10, 10};
	/** 移動履歴管理List */
	private List<int[]> posLogger;
	/** 移動方向管理クラス */
	private WalkHandler handler;
	

	/** コンストラクタ */
	public ClientData() {
		// 19 x 19のマップ(行列)を作る
		map = createMap();
		// 周囲確認用バッファードマップ(length=9)
		bufMap = new Double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		// bufMap更新フラグ
		isReady = false;
		// WalkHandler生成
		handler = new WalkHandler();
		// 移動履歴管理List
		posLogger = new ArrayList<int[]>();
		// ターン数を設定
		gameStep = 100;
	}

	/**
	 * 19 x 19の行列を作成する。
	 * 値は「４」で初期化する
	 * [値: 意味]
	 * [0: 空の領域]
	 * [1: アイテム]
	 * [2: ブロック]
	 * [3: 相手プレーヤ]
	 * [4: 未知の領域]
	 * @see https://github.com/ZenryokuService/AsahikawaProcon-Server/blob/master/README.md
	 * @return 19x19のマップ(行列)
	 */
	private INDArray createMap() {
		// 行列を4で初期化する
		return Nd4j.zeros(19,19).addi(4);
	}

	/**
	 * ポジションは[row , col]で示す
	 * @return the map
	 */
	public INDArray getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(INDArray map) {
		this.map = map;
	}

	/**
	 * @return the bufMap
	 */
	public Double[] getBufMap() {
		return bufMap;
	}

	/**
	 * @param bufMap the bufMap to set
	 */
	public void setBufMap(Double[] bufMap) {
		this.bufMap = bufMap;
	}

	/**
	 * @return the gameStep
	 */
	public int getGameStep() {
		return gameStep;
	}

	/**
	 * @param gameStep the gameStep to set
	 */
	public void setGameStep(int gameStep) {
		this.gameStep = gameStep;
	}

	public void countGameStep() {
		this.gameStep--;
	}

	/**
	 * 移動可能な方向の配列を取得。
	 * 上: 1, 左: 3, 右: 5, 下: 7
	 * @return the walkable
	 */
	public List<Double> getWalkable() {
		return walkable;
	}

	/**
	 * 移動可能な方向の配列を設定。
	 * 上: 1, 左: 3, 右: 5, 下: 7
	 * @param walkable the walkable to set
	 */
	public void setWalkable(List<Double> walkable) {
		this.walkable = walkable;
	}

	/**
	 * @return the isReady
	 */
	public boolean isReady() {
		return isReady;
	}

	/**
	 * @param isReady the isReady to set
	 */
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	/**
	 * @return the isPlayer
	 */
	public boolean isPlayer() {
		return isPlayer;
	}

	/**
	 * @param isPlayer the isPlayer to set
	 */
	public void setPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}

	/**
	 * @return the isItem
	 */
	public boolean isItem() {
		return isItem;
	}

	/**
	 * @param isItem the isItem to set
	 */
	public void setItem(boolean isItem) {
		this.isItem = isItem;
	}

	/**
	 * @return WalkHandler
	 */
	public WalkHandler getHandler() {
		return handler;
	}

	/**
	 * @return the posLogger
	 */
	public List<int[]> getPosLogger() {
		return posLogger;
	}

	/**
	 * @param posLogger the posLogger to set
	 */
	public void setPosLogger(List<int[]> posLogger) {
		this.posLogger = posLogger;
	}

	/**
	 * 現在のポジションをロギングする。
	 * ClientManager用のポジションをClientData用のポジションに更新して登録する。
	 * @param pos 現在のポジション
	 */
	public void loggingPos(int[] pos, INDArray map) {
		// Loggin用リストに追加
		this.posLogger.add(pos);
		// 更新する座標の中心を算出
		int[][] matrixIndexes = getAroundUpdatePosArray(pos);
		@SuppressWarnings("unused")
		int rowCounter = 0;
		for (int i = 0; i < bufMap.length; i++) {
			if (isDebug) {
				System.out.println("mapX: " + matrixIndexes[i][0] + " / mapY: " + matrixIndexes[i][1]);
			}
			map.putScalar(matrixIndexes[i], bufMap[i]);
			if (i < 3 &&i % 2 == 0) {
				rowCounter++;
			}
		}
	}

	/**
	 * サーチコマンドのレスポンスをbufMapに反映する。
	 * @param res サーバーレスポンス
	 * @param cmd 実行したコマンド
	 */
	public void updateSearchMap(String res, String cmd, int[] currentPos) throws Exception {
		// 更新する行列のIndex[cols, rows]
		int[][] matrixIndexes = getSearchUpdatePosArray(currentPos, cmd);
		for (int i = 1; i <  res.length()-1; i++) {
			map.putScalar(matrixIndexes[i], Double.parseDouble(res.substring(i, i+1)));
		}
	}

	/**
	 * 現在位置から、左上の作成マップ上のポジションを取得する。
	 * @param currentPos 現在位置
	 * @return 左上のポジション
	 */
	public int[] getUpLeftPos(int[] currentPos) {
		int[] tmp = {0, 0};
		tmp[0] = currentPos[0] - 1;
		tmp[1] = currentPos[1] + 1;
		return tmp;
	}


	/**
	 * 現在位置から、上の作成マップ上のポジションを取得する。
	 * @param currentPos 現在位置
	 * @return 左上のポジション
	 */
	public int[] getUpperPos(int[] currentPos) {
		int[] tmp = {0, 0};
		tmp[0] = currentPos[0];
		tmp[1] = currentPos[1] + 1;
		return tmp;
	}

	/**
	 * 現在位置から、右上の作成マップ上のポジションを取得する。
	 * @param currentPos 現在位置
	 * @return 左上のポジション
	 */
	public int[] getUpRightPos(int[] currentPos) {
		int[] tmp = {0, 0};
		tmp[0] = currentPos[0] + 1;
		tmp[1] = currentPos[1] + 1;
		return tmp;
	}

	/**
	 * 現在位置から、左の作成マップ上のポジションを取得する。
	 * @param currentPos 現在位置
	 * @return 左上のポジション
	 */
	public int[] getLeftPos(int[] currentPos) {
		int[] tmp = {0, 0};
		tmp[0] = currentPos[0] - 1;
		tmp[1] = currentPos[1];
		return tmp;
	}

	/**
	 * 現在位置から、右の作成マップ上のポジションを取得する。
	 * @param currentPos 現在位置
	 * @return 左上のポジション
	 */
	public int[] getRighttPos(int[] currentPos) {
		int[] tmp = {0, 0};
		tmp[0] = currentPos[0] + 1;
		tmp[1] = currentPos[1];
		return tmp;
	}

	/**
	 * 現在位置から、左下の作成マップ上のポジションを取得する。
	 * @param currentPos 現在位置
	 * @return 左上のポジション
	 */
	public int[] getDownLeftPos(int[] currentPos) {
		int[] tmp = {0, 0};
		tmp[0] = currentPos[0] + 1;
		tmp[1] = currentPos[1] - 1;
		return tmp;
	}
	/**
	 * 現在位置から、下の作成マップ上のポジションを取得する。
	 * @param currentPos 現在位置
	 * @return 左上のポジション
	 */
	public int[] getDownPos(int[] currentPos) {
		int[] tmp = {0, 0};
		tmp[0] = currentPos[0];
		tmp[1] = currentPos[1] - 1;
		return tmp;
	}
	/**
	 * 現在位置から、右下の作成マップ上のポジションを取得する。
	 * @param currentPos 現在位置
	 * @return 左上のポジション
	 */
	public int[] getDownRightPos(int[] currentPos) {
		int[] tmp = {0, 0};
		tmp[0] = currentPos[0] + 1;
		tmp[1] = currentPos[1] - 1;
		return tmp;
	}

	/**
	 * 周囲のマップを更新するときに使用する更新する行列の位置(int[][])を返却する。
	 * 
	 * @param currentPos 現在位置(座標系:スタート地点を(0, 0))
	 * @return 更新するMap行列の位置[col, row]とXYが逆になるので注意
	 */
	private int[][] getAroundUpdatePosArray(int[] currentPos) {
		int centerX = (map.columns() / 2) + 1;
		int centerY = (map.rows() / 2) + 1;
		int updateX = centerX + currentPos[1];
		int updateY = centerY + currentPos[0];
		// 現在位置の座標(ClientManager)からRowCol(ClientData)を取得する
		return  new int[][] {new int[] {updateY-2, updateX-2}
								, new int[] {updateY-2,updateX-1}
								, new int[] {updateY-2,updateX}
								, new int[] {updateY-1,updateX-2}
								, new int[] {updateY-1,updateX-1}
								, new int[] {updateY-1,updateX}
								, new int[] {updateY,updateX-2}
								, new int[] {updateY,updateX-1}
								, new int[] {updateY,updateX}};
	}

	/**
	 * サーチコマンドのレスポンスからマップを更新するときに使用する。
	 * 更新する行列の位置(int[][])を返却する。
	 * 
	 * @param currentPos 現在位置(座標系:スタート地点を(0, 0))
	 * @param cmd 実行したコマンド
	 * @return 更新するMap行列の位置[col, row]とXYが逆になるので注意
	 */
	private int[][] getSearchUpdatePosArray(int[] currentPos, String cmd) throws Exception {
		// コマンドは必ず２文字
		String way = cmd.substring(1,2);
		int centerX = (map.columns() / 2) + 1;
		int centerY = (map.rows() / 2) + 1;
		int updateX = centerX + currentPos[1];
		int updateY = centerY + currentPos[0];
		int[][] matrixIndexes = null;

		if (ClientData.LEFT.equals(way)) {
			matrixIndexes = createUpDownIndexes(updateX, updateY, -1);
		} else if (ClientData.UP.equals(way)) {
			matrixIndexes = createLeftRightIndexes(updateX, updateY, -1);
		} else if (ClientData.DOWN.equals(way)) {
			matrixIndexes = createLeftRightIndexes(updateX, updateY, 1);
		} else if (ClientData.RIGHT.equals(way)) {
			matrixIndexes = createUpDownIndexes(updateX, updateY, 1);
		} else {
			throw new Exception("コマンドの入力が想定外です。: " + way);
		}
		return matrixIndexes;
	}

	/**
	 * UP, DOWN用のint[][]を作成する、引数のcounterにより
	 * UP: -1, DOWN: 1を区別する。
	 * そして【updateX, updateYはそれぞれ-1した値(配列のため)】
	 * @param counter 1 or -1が設定される。
	 * @return
	 */
	private int[][] createUpDownIndexes(int updateX, int updateY, int counter) {
		return new int[][] {new int[] {updateX, updateY}
							, new int[] {updateX-1,updateY+(counter * 1)-1}
							, new int[] {updateX-1,updateY+(counter * 2)-1}
							, new int[] {updateX-1,updateY+(counter * 3)-1}
							, new int[] {updateX-1,updateY+(counter * 4)-1}
							, new int[] {updateX-1,updateY+(counter * 5)-1}
							, new int[] {updateX-1,updateY+(counter * 6)-1}
							, new int[] {updateX-1,updateY+(counter * 7)-1}
							, new int[] {updateX-1,updateY+(counter * 8)-1}};
	}

	/**
	 * LEFT, RIGHT用のint[][]を作成する、引数のcounterにより
	 * LEFT: -1, RIGHT: 1
	 * @param counter 1 or -1が設定される。
	 * @return
	 */
	private int[][] createLeftRightIndexes(int updateX, int updateY, int counter) {
		return new int[][] {new int[] {updateX, updateY}
							, new int[] {updateX + (counter * 1)-1, updateY-1}
							, new int[] {updateX + (counter * 2)-1, updateY-1}
							, new int[] {updateX + (counter * 3)-1, updateY-1}
							, new int[] {updateX + (counter * 4)-1, updateY-1}
							, new int[] {updateX + (counter * 5)-1, updateY-1}
							, new int[] {updateX + (counter * 6)-1, updateY-1}
							, new int[] {updateX + (counter * 7)-1, updateY-1}
							, new int[] {updateX + (counter * 8)-1, updateY-1}};
	}
}
