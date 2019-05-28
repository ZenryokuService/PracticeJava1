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
	public static final int RESPONSE_SIZE = 15;
	/** クライアントデータ管理クラス */
	private ClientData data;

	/**
	 * 【注意】
	 *  相対現在位置(中心はスタート位置)。
	 *  ClientDataには、Map上の位置を持たせるので注意。
	 */
	private int[] currentPos = {0, 0};

	/**
	 * コンストラクタ
	 */
	public ClientManager() {
		data = new ClientData();
		// デバッカーON
		data.isDebug = true;
		currentPos = new int[] {0, 0};
	}

	/**
	 * 次に実行するコマンドを決定する。
	 * @return 次に実行するコマンド
	 */
	public String decideCommand() {
		String nextCommand = null;
		// 1.他のプレーヤがいる場合
		
		// 2.Itemがある場合
		
		// 何もなければ予定のコマンドを実行する
		int last = data.getGameStep();
		if (last >= 96) {
			nextCommand = firstCommand(last);
		}
		return nextCommand;
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
	 * GetReadyコマンドを送信した後のレスポンスを受けた時の処理
	 * @param response サーバーレスポンス
	 */
	public void resGetRedy(InputStream response) throws IOException {
		String res = this.getString(response);
		try {
			// 周囲確認用のMapにデータを登録
			// 周囲に何がある確認する(各フラグを更新)
			setBufMap(res);
			if (data.isDebug) {
				showBufMap();
			}
			// 周囲にあるものをチェック
			checkAround(res);
			// 現在位置のマッピングを行う
			updateMap(res, ClientData.GET_READY, null);
		} catch(Exception e) {
			e.printStackTrace();
			throw new IOException("bufMapにデータを設定中にエラーがありました。");
		}
	}
	/**
	 * 操作コマンドを送信した後の処理です。
	 * @param response サーバーレスポンス
	 * @param cmd 送信した操作コマンド
	 */
	public void afterCommand(InputStream response, String cmd)  throws IOException, Exception {
		String res = this.getString(response).trim();
		// 送信したコマンドにより処理をハンドル
		String command = cmd.substring(0, 1);
		if (data.isDebug) {
			System.out.println("afterCommand: " + cmd == null ? "GetReady": cmd);
			System.out.println("CommandResponse: " + res);
		}
		if (ClientData.SEARCH_CMD.equals(command)) {
			updateMap(res, ClientData.SEARCH_CMD, cmd);
		} else if (ClientData.PUT_CMD.equals(command)) {
			
		} else if (ClientData.LOOK_CMD.equals(command)) {
			
		} else if (ClientData.WALK_CMD.equals(command)) {
		}
		// 上記以外のコマンドは無視
	}

	/**
	 * 周囲の安全確認を行い次の行動を決める。
	 * @res サーバーレスポンス
	 */
	private void checkAround(String res) throws Exception {
		// 移動可能な場所の判定をして移動優先順位を決める
		
		// 相手プレーヤの有無
		if (data.isPlayer()) {
			// プレーヤがいる時の処理フラグを設定
		}
		// アイテムがの有無
		if (data.isItem()) {
			// アイテムがある時の処理フラグを設定
		}
	}

	private void setDirection() {
		// 移動可能な場所のマス(ブロック)を確認する(必ず1->7)の順番で設定されている)
		Map<Integer, Double> walkable = data.getHandler().getWalkable();
		// 移動優先順位
		Integer[] direct = data.getHandler().getDirectPriority();
		// コピーを作成しておく
		Integer[] newDirect = direct;
		// walkableには移動可能なマスの配列番号入っている
		// 改めて優先順位を付け直す(１ターン毎に更新)
		for (Integer i : direct) {
			System.out.println(walkable.get(i));
		}
		int[] listIdx = new int[] {1, 3, 5, 7};
		for (int i = 0; i < direct.length; i++) {
			if (walkable.get(listIdx[i]) == 2.0) {
				Integer first = direct[i];
				Integer tmp = direct[direct.length-(i+1)];
				direct[i] = tmp;
				direct[direct.length - (i+1)] = first;
			}
		}
		System.out.println("****");
		for (Integer i : direct) {
			System.out.println(walkable.get(i));
		}
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
	 * 3.行動可能な方向をWalkHanderクラスにint[]で設定
	 * @param res サーバーレスポンス
	 */
	private void setBufMap(String res) throws Exception {
		if (ClientData.END_TURN.equals(res)) {
			this.terminatedTurn();
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
				if (playerIs) {
					data.setPlayer(true);
				}
				if (itemIs) {
					data.setItem(true);
				}
				if (i == ClientData.UP_POS || i == ClientData.DOWN_POS
						|| i == ClientData.LEFT_POS || i == ClientData.RIGHT_POS) {
					walkable.put(i, Double.parseDouble(resData));
				}
	 		}
			handler.setWalkable(walkable);
		} catch (NumberFormatException e) {
			System.out.println("レスポンスが数字ではありません。" + errorStr);
			e.printStackTrace();
		}
		// bufMap更新フラグをtrueにする
		data.setReady(true);
		data.setBufMap(bufMap);
		// ClientDataのマップを更新する
		//data.loggingPos(currentPos);
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
		INDArray mat = Nd4j.create(3, 3);
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
		// 改行コード、スペースを削除
		return new String(b).replaceAll("\r", "").replaceAll("\n", "").trim();
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
	public void terminatedTurn() {
		// ゲームステップ数をマイナス
		data.countGameStep();
	}
}
