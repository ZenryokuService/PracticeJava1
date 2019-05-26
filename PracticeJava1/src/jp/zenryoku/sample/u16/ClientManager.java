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
		currentPos = new int[] {0, 0};
	}

	/**
	 * GetReadyコマンドを送信した後のレスポンスを受けた時の処理
	 * @param response サーバーレスポンス
	 */
	public void resGetRedy(InputStream response) throws IOException {
		String res = this.getString(response);
		try {
			// 周囲確認用のMapにデータを登録
			setBufMap(res);
			// 周囲に何がある確認する(各フラグを更新)
			showBufMap();
			// 周囲にあるものをチェック
			checkAround(res);
			// 現在位置のマッピングを行う
			updateMap(res, ClientData.GET_READY, null);
			
			// 安全確認を行う
			checkAround(res);
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
		String res = this.getString(response);
		// 送信したコマンドにより処理をハンドル
		String command = cmd.substring(0, 1);
		if (ClientData.SEARCH_CMD.equals(command)) {
			acceptSearch(res, cmd);
		} else if (ClientData.PUT_CMD.equals(command)) {
			
		} else if (ClientData.LOOK_CMD.equals(command)) {
			
		} else if (ClientData.WALK_CMD.equals(command)) {
		}
		// 上記以外のコマンドは無視
	}

	/**
	 * 周囲の安全確認を行う。
	 * @res サーバーレスポンス
	 */
	private void checkAround(String res) throws Exception {
		// 相手プレーヤの有無
		if (data.isPlayer()) {
			// プレーヤがいる時の処理フラグを設定
		}
		// アイテムがの有無
		if (data.isItem()) {
			// アイテムがある時の処理フラグを設定
		}
	}

	/**
	 * 自分の位置などのMapを更新する。bufMapは更新済み。
	 * @param response サーバーレスポンス
	 * @param cmdFlg ClientData.GET_READYなどのレスポンスを受けるタイミング
	 * @param searchの時のみ使用するので他の場合はNULLを設定する
	 */
	private void updateMap(String response, String cmdFlg, String command) throws Exception {
		// 現在の自分の周囲情報
		Double[] currentMap = data.getBufMap();
		// 自分の保持しているマップ
		INDArray map = data.getMap();
		if (ClientData.READY_CMD_RES.equals(cmdFlg)
				|| ClientData.WALK_CMD.equals(cmdFlg)) {
			System.out.println("CurrentPos: " + currentPos[0]+ ", " +  currentPos[1]);
			data.loggingPos(currentPos, map);
			System.out.println(map);
		} else if (ClientData.SEARCH_CMD.equals(cmdFlg)){
			data.loggingSearch(response, currentPos, command);
		}
	}

	/**
	 * Searchコマンドのレスポンスを受け他時の処理。
	 * 
	 * @param res Searchコマンドのレスポンス
	 */
	private void acceptSearch(String res, String cmd) throws Exception {
		System.out.println("IN: afterSearcj.SEARCH");
		data.loggingSearch(res, currentPos, cmd);
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
		if (ClientData.END_TURN.equals(res) || ClientData.READY_CMD_RES.equals(res)) {
			return;
		}
		Double[] bufMap = data.getBufMap();
		boolean playerIs = false;
		boolean itemIs = false;
		String errorStr = null;
		try {
			// バイト数が10のはずだが?
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
					setOkHandler(resData, i);
				}
	 		}
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
	 * 自分の周りの情報からMapを描画(行列にデータを設定)する。
	 * @param response サーバーレスポンス
	 */
	private void drawMapAround() throws Exception {
		// bufMapが更新されているか確認する
		if (data.isReady()) {
		}
		throw new Exception("bufMapが更新されていません。" + data.isReady());
	}

	/**
	 * 移動可能場所の設定を行う。
	 * 渡された位置が０であれば移動可能を設定する。
	 * @param resData 対象のマスにあるもの[0: 空白, 1: Item, 2: block, 3: player, 4:unknown]
	 * @param pos 3x3(周囲)のポジション左上から1~9となる
	 * @throws Exception
	 */
	private void setOkHandler(String resData, int pos) throws Exception {
		WalkHandler handler = data.getHandler();
		boolean isOk = ClientData.BLOCK_ZONE.equals(resData) == false;
		switch (pos) {
		case ClientData.UP_POS:
			handler.setOkUp(isOk);
			break;
		case ClientData.LEFT_POS:
			handler.setOkLeft(isOk);
			break;
		case ClientData.RIGHT_POS:
			handler.setOkRight(isOk);
			break;
		case ClientData.DOWN_POS:
			handler.setOkDown(isOk);
			break;
		default:
			throw new Exception("想定外のポジションです。" + pos);
		}
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
	 * レスポンスの値をINDArrayに変換する
	 * @param res サーバーのレスポンス
	 * @return INDArray
	 */
	private INDArray parseRreponse(String res) {
		Double[] values = null;
		for (int i = 1; i < 10; i++) {
			values[i-1] = Double.parseDouble(res.substring(i, i + 1));
		}
		return null;
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
	 * <dl><dt>ついでに、以下のチェックも行う。設計的によろしくない。<dt>
	 * <dd>周囲に相手プレーヤがいるかチェック：this.isPlayerに結果をセット</dd>
	 * <dd>周囲にアイテムがあるかチェック：this.isItemに結果をセット</dd>
	 * </dl>
	 */
	public void showBufMap() {
		System.out.println("*************");
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
	 * レスポンスの値が数字でない改行など不明の値が
	 * 帰ってくることがあるのでそれらを弾く処理
	 * @param res
	 * @return 
	 */
	private boolean isFunny(char res) {
		boolean isFunny = false;
		return isFunny;
	}
}
