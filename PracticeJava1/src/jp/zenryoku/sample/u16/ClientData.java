/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.u16;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * U16プログラミングコンテストのクライアントアプリのデータを管理するクラス。
 * 
 * @author takunoji
 * 2019/05/25
 */
public class ClientData {
	/** タイムアウトする時間(ミリ秒) */
	public static final int TIME_OUT = 10000;
	/** 改行コード */
	public static final String ENTER = "\r\n";
	/** COOLのポート番号 */
	public static final int COOL = 2009;
	/** HOTのポート番号 */
	public static final int HOT = 2010;
	/** GetReadyを送信した時のフラグ */
	public static final char GET_READY = 'G'; 
	/** 行動コマンドを送信した時のフラグ */
	public static final char COMMAND = 'C'; 
	/** 行動終了コマンドを送信した時のフラグ */
	public static final char SHARP = 'S'; 
	/** ゲームエリアの空白を占めす定数 */
	public static final String SAFETY_ZONE = "0";
	/** ゲームエリアのアイテムを占めす定数 */
	public static final String ITEM_ZONE = "1";
	/** ゲームエリアのブロックを占めす定数 */
	public static final String BLOCK_ZONE = "2";
	/** ゲームエリアの相手プレーヤを占めす定数 */
	public static final String OPPONENT_PLAYER = "3";
	/** ゲームエリアの未知のエリアを占めす定数 */
	public static final String UNKNOWN_ZONE = "4";
	/** サーバーレスポンスの１バイト目(1=Alive, 0:=Dead) */
	public static final String IS_ALIVE = "1";

	/** ゲームのターン数 */
	private int gameStep;
	/** Searchコマンドを送信した事を示す */
	public static final char SEARCH_CMD = 's';
	/** Lookコマンドを送信した事を示す */
	public static final char LOOK_CMD = 'l';
	/** Putコマンドを送信した事を示す */
	public static final char PUT_CMD = 'p';
	/** Walkコマンドを送信したことを示す */
	public static final char WALK_CMD = 'w';
	/** 終了コマンド(Stringで使用する) */
	public static final String END_TURN = "#";
	/** 自分の上方向を示すレスポンスデータの位置 */
	public static final int UP_POS = 1;
	/** 自分の左方向を示すレスポンスデータの位置 */
	public static final int LEFT_POS = 3;
	/** 自分の右方向を示すレスポンスデータの位置 */
	public static final int RIGHT_POS = 5;
	/** 自分の下方向を示すレスポンスデータの位置 */
	public static final int DOWN_POS = 7;
	/** 周囲確認用BufferedMap */
	private String[] bufMap;
	/** 周囲確認済み(bufMap更新済み)フラグ */
	private boolean isReady;
	/** 周囲に相手プレーヤがいるかどうかのフラグ */
	private boolean isPlayer;
	/** 周囲にアイテムがあるかどうかのフラグ */
	private boolean isItem;
	/** ND4Jの行列部品 */
	private INDArray map;
	/** 移動方向管理クラス */
	private WalkHandler handler;

	/** コンストラクタ */
	public ClientData() {
		// 19 x 19のマップ(行列)を作る
		map = createMap();
		// 周囲確認用バッファードマップ(length=9)
		bufMap = new String[] {"0", "0", "0", "0", "0", "0", "0", "0", "0"};
		// bufMap更新フラグ
		isReady = false;
		// WalkHandler生成
		handler = new WalkHandler();
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
	public String[] getBufMap() {
		return bufMap;
	}

	/**
	 * @param bufMap the bufMap to set
	 */
	public void setBufMap(String[] bufMap) {
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
}
