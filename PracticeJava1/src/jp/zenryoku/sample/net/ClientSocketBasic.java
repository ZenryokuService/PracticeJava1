/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author takunoji
 *
 * 2019/07/20
 */
public class ClientSocketBasic {
	private static final String HOST = "localhost";
	private static final int PORT = 8080;
	/**
	 * コンストラクタ
	 * @param portNo ポート番号
	 */
	public static void main(String[] args) {
		System.out.println("*** SocketClient start ***");
		try (Socket sock = new Socket(HOST, PORT)) {
			// 受信したレスポンス
			InputStream reader = sock.getInputStream();
			// 送信するリクエスト
			OutputStream writer = sock.getOutputStream();
			System.out.println("接続済み: " + sock.isConnected());
			System.out.println("接続サーバー: " + sock.getRemoteSocketAddress());
			// リクエスト送信
			writer.write("ping...\n".getBytes());
			writer.flush();

			System.out.println("*** Testing start " + sock.getInetAddress() + " ***");
			// レスポンス受信
			int c = 0;
			while((c = reader.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println("*** Testing end***");
			
			// 終了処理
			reader.close();
			writer.close();
			sock.close();
			System.out.println("*** SocketClient end ***");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
