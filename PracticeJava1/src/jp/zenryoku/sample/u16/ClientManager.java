/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.u16;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nd4j.linalg.api.buffer.DataBuffer;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Clietプログラムの行動、現在位置などの管理を行う。
 * map; 行列を使用してマップを作成する
 * @author takunoji
 * 2019/05/24
 */
public class ClientManager {
	/** サーバーレスポンスのデータサイズ */
	public static final int RESPONSE_SIZE = 100;
	/** クライアントデータ管理クラス */
	private ClientData data;
	/** ターゲットロックオンのフラグ */
	private boolean isLockOn;

	/**
	 * 【注意】
	 *  相対現在位置(中心はスタート位置)。
	 *  ClientDataには、Map上の位置を持たせるので注意。
	 */
	private static int[] currentPos = {0, 0};

	/**
	 * コンストラクタ
	 */
	public ClientManager() {
		data = new ClientData();
		// デバッカーON
		data.isDebug = true;
		currentPos = new int[] {0, 0};
		data.isJiDo = true;
		isLockOn = false;
		
	}

	public String firstCommand(int lastTurn) {
		String command = null;
		switch(lastTurn) {
		case 99:
			command = "su";
			break;
		case 98:
			command = "sr";
			break;
		case 97:
			command = "sd";
			break;
		case 96:
			command = "sl";
			break;
		default:
			
		}
		return command;
	}
	/**
	 * GetReadyコマンドを送信した後のレスポンスを受けた時の処理。
	 * 1. butMapの更新
	 * 2. 全体(自分を中心に19x19マス)Mapの更新
	 * 3. 周囲の確認を行い、実行コマンドを決定する
	 * @param response サーバーレスポンス
	 * @return 実行するコマンド
	 */
	public String resGetRedy(InputStream response) throws IOException {
		String cmd = null;
		String res = this.getString(response);
		try {
			// 周囲確認用のMapにデータを登録
			// 周囲に何がある確認する(各フラグを更新)
			setBufMap(res);
			if (data.isDebug) {
				showBufMap();
			}
			// 現在位置のマッピングを行う
			updateMap(res, ClientData.GET_READY, null);
			// 周囲にあるものをチェック
			cmd = checkAround(res);
		} catch(Exception e) {
			e.printStackTrace();
			throw new IOException("bufMapにデータを設定中にエラーがありました。");
		}
		return cmd;
	}
	/**
	 * 操作コマンドを送信した後の処理です。
	 * @param response サーバーレスポンス
	 * @param cmd 送信した操作コマンド
	 */
	public void afterCommand(InputStream response, String cmd)  throws IOException, Exception {
		String res = this.getString(response);
		// 送信したコマンドにより処理をハンドル
		String command = cmd.substring(0, 1);
		if (data.isDebug) {
			System.out.println("afterCommand: " + cmd == null ? "GetReady": cmd);
			System.out.println("CommandResponse: " + res);
		}
		String exe = cmd.substring(0, 1);
		String way = cmd.substring(1, 2);
		if (ClientData.SEARCH_CMD.equals(command)) {
			// サーチ済みの方向はサーチしない
			updateMap(res, ClientData.SEARCH_CMD, cmd);
			data.setSearched(data.getWayToPos(cmd.substring(1,2)));
		} else if (ClientData.PUT_CMD.equals(command)) {
			
		} else if (ClientData.LOOK_CMD.equals(command)) {
			if (data.isLooked(data.getWayToPos(way))) {
				cmd = exe + decideDirection(ClientData.LOOK_CMD);
			}
			updateMap(res, ClientData.LOOK_CMD,  cmd);
			data.setLooked(data.getWayToPos(cmd.substring(1,2)));
		} else if (ClientData.WALK_CMD.equals(command)) {
		}
		// 上記以外のコマンドは無視
	}

	/**
	 * 周囲の安全確認を行い次の行動を決める。
	 * @res サーバーレスポンス
	 */
	private String checkAround(String res) throws Exception {
		String cmd = null;
		Double[] bufMap = data.getBufMap();
		// 相手プレーヤの有無
		if (data.isPlayer()) {
			System.out.println("isPlayerAction staert");
			for (int i = 0; i < bufMap.length; i++) {
				if (bufMap[i] == 1) {
					// プレーヤがいる時の処理フラグを設定
					cmd = isPlayerAction();
					break;
				}
			}
			if (cmd == null) {
				cmd = isPlayerAtFar();
			}
			return cmd;
		}
		// アイテムがある時
		if (data.isItem()) {
			System.out.println("isItemAction start");
			for (int i = 0; i < bufMap.length; i++) {
				// 対象のポジションにアイテムがある時
				if (bufMap[i] == 3) {
					// プレーヤがいる時の処理フラグを設定
					cmd = isItemAction(i);
					break;
				}
			}
			if (cmd == null) {
				cmd = isItemAtFar();
			}
			return cmd;
		}
		/// 上記の条件に当てはまらない場合はMap作成に走る ///
		
		// 進行方向が決まっていない時はSearchコマンドで広域確認
		int direction = data.getHandler().getDirection();
		if (data.isDebug) {
			System.out.println("direction: " + direction);
			System.out.println("isSearched: " + data.isSearched(direction));
			System.out.println("isLooked: " + data.isLooked(direction));
		}
		if (direction == -1 || data.allSearched() == false) {
			System.out.println("*** IN: Search Method ***");
			cmd = ClientData.SEARCH_CMD + decideDirection(ClientData.SEARCH_CMD);
			data.setSearched(direction);
		} else {
			if (data.isLooked(direction)) {
				cmd = isWalkAction(direction);
			} else {
				cmd = ClientData.LOOK_CMD + decideDirection(ClientData.LOOK_CMD);
				data.setLooked(direction);
			}
		}
		return cmd;
	}

	/**
	 * 周囲にプレーヤがいた時の行動(コマンド)を返却する
	 */
	private String isPlayerAction() throws Exception {
		// PUT(攻撃可能か？)
		if (data.isCanAttack()) {
			// p + 方向
			return ClientData.PUT_CMD + data.getPosToWay(data.getHandler().getPlayerPos());
		}
		// プレイヤーが周囲にいて攻撃ができない時(0, 2, 6, 8)
		int playerIs = data.getHandler().getPlayerPos();
		return ClientData.PUT_CMD + getRandamPos(playerIs);
	}

	/**
	 * Search, Lookコマンドで相手プレーヤを発見した時の処理
	 * @return 近くまで、前進する(想定)
	 * @throws Exception
	 */
	private String isPlayerAtFar() throws Exception {
		return "wd";
	}

	/**
	 * Search, Lookコマンドでアイテムを発見した時の処理
	 * @return 近くまで、前進する(想定)
	 * @throws Exception
	 */
	private String isItemAtFar() throws Exception {
		return "wu";
	}

	/**
	 * 周囲にアイテムがあった時の行動
	 * @param pos アイテムのあるポジション
	 */
	private String isItemAction(Integer pos) throws Exception {
		// PUT(攻撃可能か？)
		if (data.isCanItem()) {
			String cmd = ClientData.WALK_CMD + data.getPosToWay(data.getHandler().getItemPos());
			System.out.println("*** Item is " + cmd + " ***");
			String tmp = isWalkAction(pos);
			// p + 方向
			return tmp;
		}
		// アイテムを取得するのに一度移動する必要がある場合
		String cmd = null;
		// プレイヤーが周囲にいて攻撃ができない時(0, 2, 6, 8)
		int itemIs = data.getHandler().getItemPos();
//		cmd = ClientData.WALK_CMD + getRandamPos(itemIs);
		// 進行方向のチェックを行う TODO: ラムダ式対応でこのコードをなんとかする
		WalkHandler handler = data.getHandler();
		// 自分の周囲の確認
		Double[] bufIdx = data.getBufMap();
		Map<Integer, Double> walkable = handler.getWalkable();
		boolean canWalk = false;
		Integer[] priority = handler.getDirectPriority(pos);
		int canDirect = -1;
		// 優先順序順にいけるか確認、行ければ行動
		for (int j = 0;j < priority.length; j++) {
			for (int i = 0;i < bufIdx.length; i++) {
				if (i == 1 || i == 3 || i == 5 || i == 7) {
					walkable.put(i, bufIdx[i]);
				}
				if (priority[j] == i && bufIdx[i] != 2.0) {
					canWalk = true;
					canDirect = i;
					break;
				}
			}
			if (canDirect != -1 ) {
				break;
			}
		}
		cmd = ClientData.WALK_CMD + data.getPosToWay(canDirect);
		return cmd;
	}

	/**
	 * Walkコマンドの移動方向を決定して送信コマンドを返す
	 * @param direction Look済みの方向
	 * @return 実行コマンド
	 */
	private String isWalkAction(Integer direction) throws Exception {
		if (data.isDebug) {
			System.out.println("direction: " + direction + " / " );
		}
		WalkHandler handler = data.getHandler();
		// 自分の周囲の確認
		Double[] bufIdx = data.getBufMap();
		Map<Integer, Double> walkable = handler.getWalkable();
		boolean canWalk = false;
		Integer[] priority = handler.getDirectPriority(direction);
		int canDirect = -1;
		// 優先順序順にいけるか確認、行ければ行動
		for (int j = 0;j < priority.length; j++) {
			for (int i = 0;i < bufIdx.length; i++) {
				if (i == 1 || i == 3 || i == 5 || i == 7) {
					walkable.put(i, bufIdx[i]);
				}
				if (priority[j] == i && bufIdx[i] != 2.0) {
					canWalk = true;
					canDirect = i;
					break;
				}
			}
			if (canDirect != -1 ) {
				// 進行方向変更
				handler.setDirection(canDirect);
				// 進行方向優先順位変更
				handler.setDirectPriority(WalkHandler.getPriprityArray(canDirect));
				break;
			}
		}
		
		String cmd = ClientData.WALK_CMD + data.getPosToWay(canDirect);
		if (data.isDebug) {
			System.out.println("isWalkAction canDirect ='" + canDirect + "';");
			System.out.println("isWalkAction return '" + cmd + "';");
		}
		// Looked済みであれば先の情報確認する
		if (data.isLooked(canDirect)) {
			
		}
		// CurrentPosの更新
		this.currentPos = setCurrentPos(direction);
		return cmd;
	}

	/**
	 * CurrentPos(座標系)なので中心が(0,0)になり
	 * 上に行くと(0,1)となる
	 * @param direction
	 * @return
	 */
	private int[] setCurrentPos(int direction) {
		int addX = 0;
		int addY = 0;
		if (direction == 1) {
			addY = 1;
		} else if (direction == 3) {
			addX = -1;
		} else if (direction == 5) {
			addX = 1;
		} else if (direction == 7) {
			addY = -1;
		}
		return new int[] {(currentPos[0] + addX), currentPos[1] + addY};
	}
	/**
	 * 対象posに対してどちらか隣接する方向(u,r,d,l)を返す。
	 * @param pos 対象pos
	 * @return 方向(u,r,d,l)
	 */
	private String getRandamPos(Integer pos) throws Exception {
		int zeroOne = new Random().nextInt(2);
		// 返却する方向
		String retWay = null;
		switch(pos) {
		case ClientData.UPPER_LEFT_POS:
			retWay = zeroOne == 1 ? ClientData.LEFT : ClientData.UP;
			break;
		case ClientData.UP_POS:
			retWay = zeroOne == 1 ? ClientData.LEFT : ClientData.UP;
			break;
		case ClientData.UPPER_RIGHT_POS:
			retWay = zeroOne == 1 ? ClientData.RIGHT : ClientData.UP;
			break;
		case ClientData.DOWN_LEFT_POS:
			retWay = zeroOne == 1 ? ClientData.LEFT : ClientData.DOWN;
			break;
		case ClientData.DOWN_POS:
			retWay = zeroOne == 1 ? ClientData.LEFT : ClientData.UP;
			break;
		case ClientData.DOWN_RIGHT_POS:
			retWay = zeroOne == 1 ? ClientData.RIGHT : ClientData.DOWN;
			break;
		default:
			throw new Exception("指定するposが不適切です。: " + pos);
		}
		return retWay;
	}

	/**
	 * 進行方向を設定する。
	 * walkHandlerには周囲情報設定済みになっている。
	 */
	private String decideDirection(String searchOrLook) throws Exception {
		WalkHandler handler = data.getHandler();
		// 移動優先順位
		Integer[] priority = handler.getDirectPriority();
		if (data.isDebug) {
			System.out.println("directPriority.length: " + priority.length); 
			System.out.println("SearchUp: " + data.getHandler().isOkSearchUp());
			System.out.println("SearchRight: " + data.getHandler().isOkSearchRight());
			System.out.println("SearchDown: " + data.getHandler().isOkSearchDown());
			System.out.println("SearchLeft: " + data.getHandler().isOkSearchLeft());
		}
		Double[] bufMap = data.getBufMap();
//		Double[] nextBufMap = data.getNextBufMap();
		Function<Integer, Boolean> funcLooked = pos -> {
			boolean flg = false;
			try {
				System.out.println("LOOK FUNC" + pos);
				flg = data.isLooked(pos) == false;
			} catch(Exception e) {
				flg = false;
			}
			return flg;
		};
		Function<Integer, Boolean> funcSearched = pos -> {
			boolean flg = false;
			try {
				System.out.println("SEARCH FUNC: " + pos);
				flg = data.isSearched(pos) == false;
			} catch(Exception e) {
				flg = false;
			}
			return flg;
		};
		Function<Integer, Boolean> func = null;
		func = ClientData.SEARCH_CMD.equals(searchOrLook) ? funcSearched : funcLooked;
		for (Integer p: priority) {
			if (bufMap[p] != 2.0 && func.apply(p)) {
				System.out.println("進行方向変更：" + p);
				handler.setDirection(p);
				// 進行方向優先順位も更新する
				handler.setDirectPriority(WalkHandler.getPriprityArray(p));
				break;
			} else if (bufMap[p] == 2) {
				if (ClientData.SEARCH_CMD.equals(searchOrLook)) {
					data.setSearched(p);
				} else {
					data.setLooked(p);
				}
			}
//			if (nextBufMap != null && nextBufMap[p] != 2.0 && handler.getDirection() != p) {
//				System.out.println("進行方向変更：" + p);
//				handler.setDirection(p);
//				break;
//			}
		}
		return data.getPosToWay(handler.getDirection());
	}
	/**
	 * 自分の位置などのMapを更新する。bufMapは更新済み。
	 * @param response サーバーレスポンス
	 * @param cmdFlg ClientData.GET_READYなどのレスポンスを受けるタイミング
	 * @param searchの時のみ使用するので他の場合はNULLを設定する
	 */
	private void updateMap(String response, String cmdFlg, String command) throws Exception {
		// 自分の保持しているマップ
		INDArray map = data.getMap();
		if (ClientData.GET_READY.equals(cmdFlg)
				|| ClientData.WALK_CMD.equals(cmdFlg)) {
			data.loggingPos(currentPos, map);
		} else if (ClientData.SEARCH_CMD.equals(cmdFlg)){
			data.updateSearchMap(response, command, currentPos);
		} else if (ClientData.LOOK_CMD.equals(command)) {
			data.updateNextBufMap(response, command, currentPos);
		}
		if (data.isDebug) {
			System.out.println("CurrentPos: " + currentPos[0]+ ", " +  currentPos[1]);
			System.out.println(map);
		}
	}

	/**
	 * bufMapをTrueに更新して、周囲確認用String[]に現在の状況を設定する。
	 * ついでなので、周囲の状況確認も行う。
	 * 1.Playerが周囲にいるかのフラグを設定
	 * 2.Itemがあるかのフラグを設定
	 * 3.行動可能な方向をWalkHanderクラスに設定
	 * @param res サーバーレスポンス
	 */
	private void setBufMap(String res) throws Exception {
		if (ClientData.END_TURN.equals(res)) {
			return;
		} else if (ClientData.READY_CMD_RES.equals(res)) {
			return;
		}
		Double[] bufMap = data.getBufMap();
		boolean playerIs = false;
		boolean itemIs = false;
		String errorStr = null;
		WalkHandler handler = data.getHandler();
		Map<Integer, Double> walkable = handler.getWalkable();

		try {
			// バイト数が10のはずだが?
			// →サーバーレスポンスゴミが入る時があるのでTRIMをかけるようにする
			for (int i = 0; i < (res.length()-1); i++) {
				String resData = res.substring(i+1, i + 2);
				errorStr = resData;
				playerIs = ClientData.OPPONENT_PLAYER.equals(resData);
				itemIs = ClientData.ITEM_ZONE.equals(resData);
				bufMap[i] = Double.parseDouble(resData);
				// 1.他のプレーヤがいるかの確認
				if (playerIs) {
					isLockOn = true;
					data.setPlayer(true);
					data.getHandler().setPlayerPos(i);
					if (i == ClientData.UP_POS || i == ClientData.RIGHT_POS
							|| i == ClientData.DOWN_POS || i == ClientData.LEFT_POS) {
						data.setCanAttack(true);
					}
				}
				// 2.アイテムがあるかの確認
				if (itemIs) {
					data.setItem(true);
					data.getHandler().setItemPos(i);
				}
				// 3.移動可能な方向の確認(移動可能配列に1:上にマスの情報(2=ブロック))
				if (i == ClientData.UP_POS || i == ClientData.DOWN_POS
						|| i == ClientData.LEFT_POS || i == ClientData.RIGHT_POS) {
					walkable.put(i, Double.parseDouble(resData));
				}
	 		}
		} catch (NumberFormatException e) {
			System.out.println("レスポンスが数字ではありません。" + errorStr);
			e.printStackTrace();
		}
		// bufMap更新フラグをtrueにする
		data.setReady(true);
		data.setBufMap(bufMap);
	}

	/**
	 * 行動コマンド送信後のレスポンス。
	 * @param response サーバーレスポンス
	 */
	public void resCommand(InputStream response, char action) throws IOException {
		String res = this.getString(response);
		if (ClientData.SEARCH_CMD.equals(action)) {
			// Searchコマンド送信後に対応する処理
		} else if (ClientData.LOOK_CMD.equals(action)) {
			// Lookコマンド送信後に対応する処理
		} else if (ClientData.PUT_CMD.equals(action)){
			// Putコマンド送信後に対応する処理
		}
	}

	/**
	 * サーバーからのレスポンスよりマッピングを行う。
	 * @param res CHaserServerからのレスポンス
	 */
	public void mapping(String res, boolean isSearch) {
//		INDArray mat = Nd4j.create(3, 3);
//		mat.put(arg0, arg1)
	}

	/**
	 * InputStreamからデータを取得してStringを返却する
	 * @param inp InputStream
	 * @return 読み込んだ文字列
	 * @throws IOException
	 */
	private String getString(InputStream inp) throws IOException {
		byte[] b = new byte[RESPONSE_SIZE];
		inp.read(b);
		String res = new String(b).replaceAll("\r", "").replaceAll("\n", "").trim();
		if (res.contains("@")) {
			return "@";
		}
		// 改行コード、スペースを削除
		// ゴミが改行などの文字以外のものあるのできっちり10バイト取得して他は捨てることにする。
		StringBuilder buf = new StringBuilder(new String(b).replaceAll("\r", "").replaceAll("\n", "").trim());
		return buf.substring(0, 10);
	}

	/**
	 * 周囲の確認状況をコンソール出力する。
	 */
	public void showBufMap() {
		System.out.println("*** showBufMap ***");
		// 結局は３回ループする
		Double[] bufMap = data.getBufMap();
		for (int i = 0; i < bufMap.length; i = i + 3) {
			System.out.println("* " + bufMap[i] + " * " + bufMap[i + 1] + " * " + bufMap[i + 2] + " *");
			System.out.println("*************");
		}
	}

	/**
	 * ClientDataを取得する。
	 * @return ClientData
	 */
	public ClientData getClientData() {
		return this.data;
	}

	/**
	 * １ターンの終了時の処理
	 */
	public void terminatedTurn(InputStream response) throws IOException {
		// ターン終了コマンドを受ける
		byte[] b = new byte[RESPONSE_SIZE];
		response.read(b);
		if (data.isDebug) {
			System.out.println("ターン終了レスポンス：" + new String(b));
		}
		// ゲームステップ数をマイナス
		data.countGameStep();
		// 各GetReadyで設定するフラグを初期化する
		data.setPlayer(false);
		data.setItem(false);
		data.setCanAttack(false);
		data.setCanItem(false);
	}
}
