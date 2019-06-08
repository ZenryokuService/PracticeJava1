/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.u16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Arrays;

/**
 * サンプルでアップされているプログラムがHTTPでの接続をしているので動かないと判断。
 * Socket通信を行う方向で作成し直す。
 * [Input: ]でコメントがついている部分は、使用者が入力(記入)する部分です。
 * [例] Input: 1 
 * 
 * @author takunoji
 * @see http://www.zenjouken.com/index.php?action=pages_view_main&block_id=150&active_action=journal_view_main_detail&post_id=55#_150 …
 * 2019/05/19
 */
public class U16ProconClient {

	/** 接続するSocket(クライアントソケット) */
	private Socket socket;
	/** クライアントの行動などの管理 */
	private ClientManager manager;

	/** 
	 * Mainメソッド。
	 * CHaserServerへのアクセスを行う、以下の値を設定して実行する
	 * <dl><dt>1.URL=IPとポート番号</dt>
	 * <dd>String serverHost = "127.0.0.1:2009";</dd>
	 * </dl>
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// Input: 1 サーバーホスト(IPアドレス)
		String serverHost = "127.0.0.1";
		// COOLを使用する: HOTを使用する場合は2010版のポート番号
		int portNo = ClientData.COOL;
		// CHaserServerへの接続作成及びゲーム起動
		U16ProconClient client = new U16ProconClient(serverHost, portNo);
		// サーバーへ接続する(GetReady送信)
		client.connectChaser();
		try {
			// ゲームを開始する
			client.startGame();
		} catch(IOException ie) {
			ie.printStackTrace();
			System.out.println("通信中に例外がありあました、プログラムを終了します。");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("想定外の例外が発生しました。");
		} finally {
			Thread.sleep(7000);
			client.closeIO();
		}
	}

	/**
	 * コンストラクタ。
	 * 接続するサーバーを指定してSocket接続をする。
	 * @param serverHost 接続先のIPとポート番号(ホスト名)
	 */
	private U16ProconClient(String serverHost, int portNo) {
		// マネージャの作成
		manager = new ClientManager();
		try {
			System.out.println("*** 1.Create Socket in Constructor ***");
			socket = new Socket(serverHost, portNo);
			socket.setSoTimeout(ClientData.TIME_OUT);
			
		} catch (IOException e) {
			System.out.println("*** Error:コネクションの作成に失敗 ***");
			e.printStackTrace();
		}
	}

	/**
	 * CHaserサーバーへ接続を行い、ゲームをスタートする。
	 */
	private void connectChaser() {
		// Input: チーム名を設定する
		String sendHttpRequest = "TeamName";
		try {
			System.out.println("*** Send Socket ***");
			OutputStream sendTo = socket.getOutputStream();
			// チーム名を送信する
			sendHttpRequest = sendHttpRequest + "\r\n";
			sendTo.write(sendHttpRequest.getBytes());
			// CHaserServerにアクセスする
			System.out.println("isConnected: "+ socket.isConnected());
			System.out.println("isInputStreamShutdown: "+ socket.isInputShutdown());
			System.out.println("isOutputShutdown: "+ socket.isOutputShutdown());
			// チーム名を送信した結果を取得(サーバーからのレスポンス)
			BufferedReader res = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println("*** 2.Connected ***");
			// レスポンスの値を取得する
			String line = res.readLine();
			System.out.println("Start Command: " + line);
			// 1秒待つ
			waitASecond();
		} catch (IOException e) {
			if ("MissCommand".equals(e.getMessage())) {
				System.out.println("Server Reponse is 0. Finish!!");
			} else {
				e.printStackTrace();
				// プログラムを異常終了する。
				System.exit(-1);
			}
		}
	}

	/**
	 * CHaserServerに接続した後にゲームを開始する。
	 * 前提として、すでに接続済みのソケットであること。
	 * @throws IOException
	 */
	private void startGame() throws IOException, Exception {
		// 送信用の出力ストリーム、ネットワーク間をストリーム経由でデータの送受信を行う
		OutputStream outputStream = socket.getOutputStream();
		// 受信用の入力ストリーム、同様にストリーム経由でデータを受信する
		InputStream inputStream = socket.getInputStream();

		System.out.println("*** Game Start ***");
		// ゲームループ開始
		startLoop(outputStream, inputStream);
		outputStream.close();
		inputStream.close();
	}
	/**
	 * ゲームのループを実装する。
	 * GetReady,コマンド送信,'#'(行動終了)を送信、描くレスポンスを取得する。
	 * @param sendTo 送信用ストリーム
	 * @param response 受信用ストリーム
	 * @throws IOException 入出力の例外
	 */
	private void startLoop(OutputStream sendTo, InputStream response) throws IOException, Exception {
		System.out.println("*** Loop Start ***");
//		String[] arr = new String[] {"su", "sl", "sd", "sr", "pp"};
//		int loop = 0;
		while(true) {
			// コマンドの送信[gr + "コマンド" + \r\n]
			sendTo.write(("gr" + ClientData.ENTER).getBytes());
			// GetReadyのレスポンスに対する処理
			String cmd = manager.resGetRedy(response);
			// レスポンスを受けコンソールに出力する
//			showResponse(sendTo, response, "GetReady");
			// 操作コマンドを送信する
			if (manager.getClientData().isDebug) {
				System.out.println("送信コマンド:" + cmd);
			}
			sendTo.write((cmd + ClientData.ENTER).getBytes());
			waitASecond();
			manager.afterCommand(response, cmd);
//			// レスポンスを受けコンソールに出力する
//			showResponse(sendTo, response, arr[loop]);
			sendTo.write(("#" + ClientData.ENTER).getBytes());
			waitASecond();
			manager.terminatedTurn(response);			
//			// レスポンスを受けコンソールに出力する
//			showResponse(sendTo, response, "#");
//			loop++;
			if (manager.getClientData().getGameStep() <= 0) {
				// 意味不明のコマンドを送信
				sendTo.write("pp".getBytes());
				// レスポンスが返ってこないのでタイムアウト
				byte[] b = new byte[10];
				response.read(b);
				break;
			}
		}
		System.out.println("*** Finish ***");
	}

	/**
	 * 実行したコマンドのレスポンスをコンソール出力する
	 * @param sendTo 送信用の出力ストリーム
	 * @param response 受信用の入力ストリーム
	 * @param exeCommand　送信したコマンド
	 * @return isDead 生きているか死んでいるか、死んでいるときは終了
	 * @throws IOException 通信時の例外
	 * @throws Exception 想定外の例外
	 */
	private boolean showResponse(
			OutputStream sendTo, InputStream response, String exeCommand) throws IOException, Exception {
		// 受信するデータのメモリ領域を確保
		byte[] res = new byte[ClientManager.RESPONSE_SIZE];
		boolean isDead = false;
		// レスポンスを受ける
		response.read(res);
		String resCode = new String(res);
		System.out.println( "[" + exeCommand + "] Response: " + resCode);
		dumpResponseCode(resCode);
		if ("0000000000".equals(resCode)) {
			System.out.println("サーバーより終了の通知を受信しました。");
			isDead = true;
		}
		return isDead;
	}

	/**
	 * レスポンスを人間に見やすいよう編集してコンソールに出力する。
	 * @param resCode サーバーからのレスポンス
	 */
	private void dumpResponseCode(String resCode) {
		try {
			String resVal = resCode.substring(0, 1);
			if (ClientData.IS_ALIVE.equals(resVal)) {
				System.out.println("生きています");
			} else if (ClientData.END_TURN.equals(resVal) || ClientData.READY_CMD_RES.equals(resVal)) {
				System.out.println("#コマンドです");
				return;
			} else {
				System.out.println("死にました: " + resVal);
			}
			char[] mapData = resCode.substring(1, 10).toCharArray();
			System.out.println("******************");
			System.out.println("[" + mapData[0] + "]     ["+ mapData[1] + "]      ["+ mapData[2] +"]");
			System.out.println("******************");
			System.out.println("[" + mapData[3] + "]     [" + mapData[4] + "]      ["+ mapData[5] +"]");
			System.out.println("******************");
			System.out.println("[" + mapData[6] + "]     ["+ mapData[7] + "]      [" + mapData[8] +"]");
			System.out.println("******************");
		} catch (NumberFormatException e) {
			if ("@".equals(resCode.substring(0, 1))) {
				System.out.println("ターン終了");
			} else {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 変換処理を行う(C＃のコードからパクってきた)
	 * @param response サーバーレスポンス
	 * @return int[]
	 */
	private int[] convert(byte[] response) {
		System.out.println("*** ByteLength: " + response.length + " ***");
		// 返却する値
		int[] retInt = new int[response.length -2];
		dump(response, retInt);
		for (int i = 0; i < retInt.length; i++) {
			// \rと\nの分を差し引く
			retInt[i] = response[i] - '0';
		}
		System.out.println(retInt);
		return retInt;
	}

	private void dump(byte[] response, int[] retInt) {
		try {
			System.out.println("Arrays.toString: " + Arrays.toString(response));
			System.out.println("new String: " + new String(response, System.getProperty("file.encoding")));
			System.out.println("Return: " + retInt);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 1秒待つ
	 */
	private void waitASecond() {
		try {
			// 1秒待つ
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * フィールド変数のオブジェクトを解放する
	 */
	private void closeIO() {
		try {
			this.socket.close();
		} catch(IOException e) {
			System.out.println("ソケットクローズ中の例外です");
			e.printStackTrace();
		}
	}
}
