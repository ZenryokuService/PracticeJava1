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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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
	/** VS自動くん */
	public boolean isJiDo;
	/// 定数 ///
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
	/** ターン終了コマンドのレスポンス */
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
	/** 自分の左上を示すレスポンスデータの位置 */
	public static final int UPPER_LEFT_POS = 0;
	/** 自分の左上を示すレスポンスデータの位置 */
	public static final int UPPER_RIGHT_POS = 2;
	/** 自分の左上を示すレスポンスデータの位置 */
	public static final int DOWN_LEFT_POS = 6;
	/** 自分の左上を示すレスポンスデータの位置 */
	public static final int DOWN_RIGHT_POS = 8;
	/** 作成したMapの中心(19x19のマス) */
	public static final int[] START_POS = {10, 10};
	/** ポジションと方向の関連HashMap */
	private static final Map<Integer, String> posWay = new HashMap<Integer, String>();

	/// クライアントが使用する変数 ///
	/** ゲームのターン数 */
	private int gameStep;
	/** 周囲確認用BufferedMap */
	private Double[] bufMap;
	/** Lookコマンド実行時の周囲マップ */
	private Double[] nextBufMap;
	/** ND4Jの行列部品(全体のマップ) */
	private INDArray map;
	/** 移動履歴管理List */
	private List<int[]> posLogger;
	/** 移動方向管理クラス */
	private WalkHandler handler;
	/** 周囲確認済み(bufMap更新済み)フラグ */
	private boolean isReady;
	/** 周囲に相手プレーヤがいるかどうかのフラグ */
	private boolean isPlayer;
	/** PUTコマンドが可能を示すフラグ */
	private boolean canAttack;
	/** 周囲にアイテムがあるかどうかのフラグ */
	private boolean isItem;
	/** アイテム取得が可能か示すフラグ */
	private boolean canItem;
	/** 移動開始フラグ */
	private boolean isMove;

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
		// ポジションと方向をセット
		posWay.put(UP_POS, UP);
		posWay.put(LEFT_POS, LEFT);
		posWay.put(RIGHT_POS, RIGHT);
		posWay.put(DOWN_POS, DOWN);
		// 移動開始フラグ
		isMove = false;
		
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
	 * @return the canAttack
	 */
	public boolean isCanAttack() {
		return canAttack;
	}

	/**
	 * @param canAttack the canAttack to set
	 */
	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}

	/**
	 * @return the canItem
	 */
	public boolean isCanItem() {
		return canItem;
	}

	/**
	 * @param canItem the canItem to set
	 */
	public void setCanItem(boolean canItem) {
		this.canItem = canItem;
	}

	/// ロジックのあるメソッド ///
	/**
	 * 現在のポジションをロギングする。
	 * ClientManager用のポジションをClientData用のポジションに更新して登録する。
	 * @param pos 現在のポジション
	 */
	public void loggingPos(int[] pos, INDArray map) {
		updateBigMap(pos);
		// Loggin用リストに追加
		this.posLogger.add(pos);
		// 更新する座標の中心を算出
		int[][] matrixIndexes = getAroundUpdatePosArray(pos);
		@SuppressWarnings("unused")
		int rowCounter = 0;
		for (int i = 0; i < bufMap.length; i++) {
			if (isDebug) {
//				System.out.println("mapX: " + matrixIndexes[i][0] + " / mapY: " + matrixIndexes[i][1]);
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
		updateBigMap(currentPos);
		// 更新する行列のIndex[cols, rows]
		int[][] matrixIndexes = getSearchUpdatePosArray(currentPos, cmd);
		boolean playerIs = false;
		boolean itemIs = false;
		for (int i = 1; i <  res.length()-2; i++) {
			String place = res.substring(i, i+1);
			map.putScalar(matrixIndexes[i], Double.parseDouble(place));
			if (ClientData.OPPONENT_PLAYER.equals(place)) {
				playerIs = true;
			}
			if (ClientData.ITEM_ZONE.equals(place)) {
				itemIs = true;
			}
		}
		// コマンドは２文字[sX] X = u,r,d,l
		String directStr = cmd.substring(1, 2);
		if (isDebug) {
			System.out.println("OkSearch: update " + directStr);
			System.out.println("playerIs: " + playerIs);
			System.out.println("itemIs: " + itemIs);
		}
		// プロパティの更新
		setPlayer(playerIs);
		setItem(itemIs);
		// 進行方向設定メソッド
		Consumer<String> func = str -> {
			int direction = this.getWayToPos(directStr);
			handler.setDirection(direction);
			try {
				handler.setDirectPriority(WalkHandler.getPriprityArray(direction));
			} catch (Exception e) {
				System.out.println("進行方向の設定時エラー[値が不適切です]：" + direction);
				e.printStackTrace();
			}
		};
		if (isPlayer()) {
			// 目的地を設定するように変更する
			func.accept(directStr);
			setPlayer(true);
		}
		if (isItem()) {
			func.accept(directStr);
			setItem(true);
		}
		if (UP.equals(directStr)) {
			handler.setOkSearchUp(true);
		} else if (RIGHT.equals(directStr)) {
			handler.setOkSearchRight(true);
		} else if (DOWN.equals(directStr)) {
			handler.setOkSearchLeft(true);
		} else if (LEFT.equals(directStr)) {
			handler.setOkSearchLeft(true);
		} else {
			throw new Exception("想定外のコマンドです: " + cmd);
		}
	}

	/**
	 * 移動している地点が18以上になったらマップを両サイドに３拡張する
	 * @param currentPos 現在ポジション
	 */
	public void updateBigMap(int[] currentPos) {
		// 移動していうポジションが18に来たらMapを拡張する
		if (currentPos[0] >= map.columns() -1 || currentPos[1] >= map.rows() -1) {
			System.out.println("*** Update NDArray ***");
			map = Nd4j.pad(map, new int[] {3, 3}, Nd4j.PadMode.CONSTANT);
		}
	}

	/** ４方向全てをサーチしたか */
	public boolean allSearched() {
		return handler.isOkSearchUp() 
				&& handler.isOkSearchRight()
				&& handler.isOkSearchDown()
				&& handler.isOkSearchLeft();
	}
	/**
	 * ルックコマンドのレスポンスをnextBufMapに反映する。
	 * @param res サーバーレスポンス
	 * @param cmd 実行したコマンド
	 * @param 現在位置(座標系)
	 */
	public void updateNextBufMap(String res, String cmd, int[] currentPos) throws Exception {
		Double[] nextBufMap = handler.getNextBufMap();
		for (int i = 1; i <  res.length()-2; i++) {
			double d = Double.parseDouble(res.substring(i, i+1));
			nextBufMap[i] = d;
		}
	}

	/**
	 * 1, 3, 5, 7のポイントに対してOkUp-Leftまで(うちの１つ)をTrueに更新する。
	 * @param point 更新するポイント
	 */
	private void setOkTrue(int point) {
		if (UP_POS == point) {
			handler.setOkUp(true);
		} else if (RIGHT_POS == point) {
			handler.setOkRight(true);
		} else if (DOWN_POS == point) {
			handler.setOkDown(true);
		} else if (LEFT_POS == point) {
			handler.setOkLeft(true);
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

	/**
	 * ポジション情報(0-8)から方向(u,r,d,l)を取得する
	 * @param pos ポジション情報
	 * @return 方向文字列(u,r,d,l)
	 */
	public String getPosToWay(int pos) {
		return posWay.get(pos);
	}

	/**
	 * 方向からポジション情報を取得する
	 * @param way 方向(u, r, d, l)
	 * @return 1, 3, 5, 7
	 */
	public int getWayToPos(final String way) {
		// ラムダ式対応のため配列で値を保持する
		final Integer[] res = new Integer[] {0};
		posWay.entrySet().forEach(ent -> {
			if (ent.getValue().equals(way)) {
				res[0] = ent.getKey();
			}
		});
		return res[0];
	}

	/**
	 * 指定したポジション(方向)はサーチ済みか判定する。
	 * @param direction ポジション(方向)
	 * @return サーチ済み(true)
	 */
	public boolean isSearched(int direction) throws Exception {
		if(isDebug && direction == -1) {
			return false;
		}
		boolean result = false;
		if (UP_POS == direction) {
			result = handler.isOkSearchUp();
		} else if (RIGHT_POS == direction) {
			result = handler.isOkSearchRight();
		} else if (DOWN_POS == direction) {
			result = handler.isOkSearchDown();
		} else if (LEFT_POS == direction) {
			result = handler.isOkSearchLeft();
		} else {
			throw new Exception("想定外のポジション(方向)です：" + direction);
		}
		return result;
	}

	/**
	 * 指定したポジション(方向)はサーチ済みに更新する。
	 * @param direction ポジション(方向)
	 * @return サーチ済み(true)
	 */
	public boolean setSearched(int direction) throws Exception {
		if(isDebug && direction == -1) {
			return false;
		}
		boolean result = false;
		if (UP_POS == direction) {
			handler.setOkSearchUp(true);
		} else if (RIGHT_POS == direction) {
			handler.setOkSearchRight(true);
		} else if (DOWN_POS == direction) {
			handler.setOkSearchDown(true);
		} else if (LEFT_POS == direction) {
			handler.setOkSearchLeft(true);
		} else {
			throw new Exception("想定外のポジション(方向)です：" + direction);
		}
		return result;
	}
	/**
	 * 指定した方向はLook済みかどうか判定する。
	 * また、Look済みでないものはLookできないところも含む。
	 * @param direction 方向(1-3,5,7)
	 * @return true/false
	 */
	public boolean isLooked(int direction) throws Exception {
		if(isDebug && direction == -1) {
			return false;
		}
		boolean result = false;
		if (UP_POS == direction) {
			result = handler.isOkLookUp();
		} else if (RIGHT_POS == direction) {
			result = handler.isOkLookRight();
		} else if (DOWN_POS == direction) {
			result = handler.isOkLookDown();
		} else if (LEFT_POS == direction) {
			result = handler.isOkLookLeft();
		} else {
			throw new Exception("想定外の方向です。: " + direction);
		}
		return result;
	}

	/**
	 * Look済みに指定したdirectionを更新する
	 * @param direction 進行方向
	 */
	public void setLooked(int direction) throws Exception {
		if (UP_POS == direction) {
			handler.setOkLookUp(true);
		} else if (RIGHT_POS == direction) {
			handler.setOkLookRight(true);
		} else if (DOWN_POS == direction) {
			handler.setOkLookDown(true);
		} else if (LEFT_POS == direction) {
			handler.setOkLookLeft(true);
		} else {
			throw new Exception("想定外の方向です。: " + direction);
		}
	}
}
