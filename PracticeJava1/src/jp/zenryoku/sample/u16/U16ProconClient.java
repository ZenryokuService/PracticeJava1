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
	private static final int TIME_OUT = 3000;
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
		String sendHttpRequest = "GET /UserCheck?user=cool&pass=cool HTTP/1.1\r\nAccept-Language: ja;q=0.7,en;q=0.3\r\nUser-Agent: Java/1.8.0_144\r\nHost: 127.0.0.1:2009\r\nAccept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2\r\nConnection: keep-alive\r\n\r\n";
		try {
			System.out.println("*** Send Socket ***");
			OutputStream sendTo = socket.getOutputStream();
			sendTo.write(sendHttpRequest.getBytes());
			// CHaserServerにアクセスする
			System.out.println("isConnected: "+ socket.isConnected());
			System.out.println("isOutputShutdown: "+ socket.isOutputShutdown());
			BufferedReader res = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = null;
			while((line = res.readLine()) != null) {
				System.out.println(line);
			}


			sendTo.close();
			res.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void accessChaser() throws IOException {
		System.out.println("Response");
		SocketChannel channel = socket.getChannel();
		channel.configureBlocking(true);
		System.out.println("Remote: " + channel.getRemoteAddress());
		System.out.println("Connected: " + channel.isConnected());
		System.out.println("isOpen: " + channel.isOpen());
	}
}
