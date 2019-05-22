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
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * サンプルでアップされているプログラムがHTTPでの接続をしているので動かないと判断。
 * Socket通信を行う方向で作成し直す。
 * このクラスは、単体で使用する目的で作成しているので、<br/>
 * コンストラクタ及びそのほかのメソッドは外部からアクセスできません。
 * [Input: ]でコメントがついている部分は、使用者が入力(記入)する部分です。
 * Input: 1 
 * 
 * @author takunoji
 * @see http://www.zenjouken.com/index.php?action=pages_view_main&block_id=150&active_action=journal_view_main_detail&post_id=55#_150 …
 * 2019/05/19
 */
public class U16ProconClient {
	/** タイムアウトする時間(ミリ秒) */
	private static final int TIME_OUT = 10000;
	/** 改行コード */
	private static final String ENTER = "\r\n";
	/** COOLのポート番号 */
	private static final int COOL = 2009;
	/** HOTのポート番号 */
	private static final int HOT = 2010;
	/** 接続するSocket(クライアントソケット) */
	private Socket socket;
	/** 接続フラグ */
	boolean isConnected;


	/** 
	 * Mainメソッド。
	 * CHaserServerへのアクセスを行う、以下の値を設定して実行する
	 * <dl><dt>1.URL=IPとポート番号</dt>
	 * <dd>String serverHost = "127.0.0.1:2009";</dd>
	 * </dl>
	 */
	public static void main(String[] args) {
		// Input: 1 サーバーホスト(IPアドレス)
		String serverHost = "127.0.0.1";
		// COOLを使用する: HOTを使用する場合は2010版のポート番号
		int portNo = COOL;
		// CHaserServerへの接続作成及びゲーム起動
		U16ProconClient client = new U16ProconClient(serverHost, portNo);
		// サーバーへ接続する(GetReady送信)
		client.connectChaser();
		try {
			// ゲームを開始する
			client.startGame();
		} catch(IOException ie) {
			System.out.println("通信中に例外がありあました、プログラムを終了します。");
		} catch(Exception e) {
			System.out.println("想定外の例外が発生しました。");
		} finally {
			client.closeIO();
		}
	}

	/**
	 * コンストラクタ。
	 * 接続するサーバーを指定してSocket接続をする。
	 * @param serverHost 接続先のIPとポート番号(ホスト名)
	 */
	private U16ProconClient(String serverHost, int portNo) {
		// 接続フラグを初期化する
		isConnected = false;
		try {
			System.out.println("*** 1.Create Socket in Constructor ***");
			socket = new Socket(serverHost, portNo);
			socket.setSoTimeout(TIME_OUT);
			
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
			if("@".equals(line)) {
				isConnected = true;
			}
			// 1秒待つ
			waitASecond();
		} catch (IOException e) {
			if ("MissCommand".equals(e.getMessage())) {
				System.out.println("Server Reponse is 0. Finish!!");
			} else {
				e.printStackTrace();
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
		// 受信したデータをラッパークラスを使用して読み込む
		BufferedReader res = new BufferedReader(new InputStreamReader(inputStream));

		System.out.println("*** Game Start ***");
		String line = null;
		// ゲーム開始のデータを送信する
		outputStream.write(("gr" + ENTER).getBytes());
		System.out.println("send 'gr'");
		// ゲームループ開始
		startLoop(outputStream, inputStream);
		outputStream.close();
		inputStream.close();
	}
	/**
	 * ゲームのループを実装する
	 * @param sendTo
	 * @param response
	 * @throws IOException
	 */
	private void startLoop(OutputStream sendTo, InputStream response) throws IOException, Exception {
		// ゲームのターン数
		int gameStep = 100;
		System.out.println("*** Loop Start ***");
		ByteBuffer buf = ByteBuffer.allocate(100);
		String[] arr = new String[] {"lu", "wd", "ld", "ou"};
		int loop = 0;
		byte[] res = new byte[100];
		boolean isDead = false;
		while(true) {
			// コマンドの送信[gr + "コマンド" + \r\n]
			sendTo.write(("gr" + ENTER).getBytes());
			// レスポンスを受ける
			showResponse(sendTo, response, "GetReady");
			//convert(res);
			sendTo.write((arr[loop] + ENTER).getBytes());
			// レスポンスを受ける
			showResponse(sendTo, response, arr[loop]);
			waitASecond();
			sendTo.write(("#" + ENTER).getBytes());
			// レスポンスを受ける
			showResponse(sendTo, response, "#");
			gameStep--;
			loop++;
			if (4 <= loop) {
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
		byte[] res = new byte[100];
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

	private void dumpResponseCode(String resCode) {
		try {
			int isAlive = Integer.parseInt(resCode.substring(0, 1));
			if (isAlive == 1) {
				System.out.println("生きています");
			} else if ("#".equals(isAlive)) {
				System.out.println("#コマンドです");
				return;
			} else {
				System.out.println("死にました");
			}
			char[] mapData = resCode.substring(1, 10).toCharArray();
			System.out.println("******************");
			System.out.println("|  " + mapData[0] + "     "+ mapData[1] +"      "+ mapData[2] +" |");
			System.out.println("******************");
			System.out.println("|  " + mapData[3] + "     "+ mapData[4] +"      "+ mapData[5] +" |");
			System.out.println("******************");
			System.out.println("|  " + mapData[6] + "     "+ mapData[7] +"      "+ mapData[8] +" |");
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
	 * 変換処理を行う
	 * @param response
	 * @return
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
			Thread.sleep(1500);
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
