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
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * サンプルでアップされているプログラムがHTTPでの接続をしているので動かないと判断。
 * Socket通信を行う方向で作成し直す。
 * このクラスは、単体で使用する目的で作成しているので、<br/>
 * コンストラクタ及びそのほかのメソッドは外部からアクセスできません。
 * 
 * @author takunoji
 * @see http://www.zenjouken.com/index.php?action=pages_view_main&block_id=150&active_action=journal_view_main_detail&post_id=55#_150 …
 * 2019/05/19
 */
public class U16ProconClient {
	/** タイムアウトする時間(ミリ秒) */
	private static final int TIME_OUT = 10000;
	/** 接続するSocket(クライアントソケット) */
	private Socket socket;
	/** 
	 * Mainメソッド。
	 * CHaserServerへのアクセスを行う、以下の値を設定して実行する
	 * <dl><dt>1.URL=IPとポート番号</dt>
	 * <dd>String serverHost = "127.0.0.1:2009";</dd>
	 * </dl>
	 */
	public static void main(String[] args) {
		String serverHost = "127.0.0.1";
		int portNo = 2009; // COOLを使用する
		// CHaserServerへの接続作成及びゲーム起動
		U16ProconClient client = new U16ProconClient(serverHost, portNo);
		// サーバーへ接続する(GetReady送信)
		client.connectChaser();
	}

	/**
	 * コンストラクタ。
	 * 接続するサーバーを指定してSocket接続をする。
	 * @param serverHost 接続先のIPとポート番号(ホスト名)
	 */
	private U16ProconClient(String serverHost, int portNo) {
		try {
			System.out.println("*** 1.Start Socket ***");
			socket = new Socket(serverHost, portNo);
			System.out.println("*** 2.Connect ***");
			socket.setSoTimeout(TIME_OUT);
			
		} catch (IOException e) {
			System.out.println("*** Error:コネクションの作成に失敗 ***");
			e.printStackTrace();
		}
	}

	private void connectChaser() {
//		String sendHttpRequest = "GET /UserCheck?user=cool&pass=cool HTTP/1.1\r\nAccept-Language: ja;q=0.7,en;q=0.3\r\nUser-Agent: Java/1.8.0_144\r\nHost: 127.0.0.1:2009\r\nAccept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\r\nConnection: keep-alive\r\n\r\n";
		String sendHttpRequest = "TeamName";
		try {
			System.out.println("*** Send Socket ***");
			OutputStream sendTo = socket.getOutputStream();
			sendHttpRequest = sendHttpRequest + "\r\n";
			sendTo.write(sendHttpRequest.getBytes());
			// CHaserServerにアクセスする
			System.out.println("isConnected: "+ socket.isConnected());
			System.out.println("isInputStreamShutdown: "+ socket.isInputShutdown());
			System.out.println("isOutputShutdown: "+ socket.isOutputShutdown());
			BufferedReader res = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// 1秒待つ
			waitASecond();

			String line = null;
			System.out.println("*** Game Start ***");
			// 接続確認フラグ
			boolean connected = false;
			while((line = res.readLine()) != null) {
				connected = true;
				System.out.println(line);
				if("@".equals(line)) {
					break;
				}
			}
			if (connected) {
				System.out.println("send 'gr'");
				startGame(sendTo, res);
			}
			// ChaserServerから"@"が送られてくるのでゲーム処理を開始する
//			while(socket.isConnected()) {
//				
//			}
			sendTo.close();
			res.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void startGame(OutputStream sendTo, BufferedReader response) throws IOException {
		sendTo.write("gr\r\n".getBytes());

		int gameStep = 100;
		System.out.println("*** Loop Start ***");
		ByteBuffer buf = ByteBuffer.allocate(100);
		while(true) {
			String line = response.readLine();
			System.out.println(line);
			sendTo.write("lu".getBytes());
			if ("1202200202".equals(line)) {
				System.out.println("サーバーでエラー？起動を終了する。");
				break;
			}
		}
	}
	/**
	 * 1秒待つ
	 */
	private void waitASecond() {
		try {
			// 1秒待つ
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}