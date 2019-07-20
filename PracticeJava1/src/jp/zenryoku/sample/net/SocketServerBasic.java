/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.net;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * サーバーソケットクラス。
 * コンストラクタで指定されたポート番号でサーバーを起動します。<br/>
 * ローカルホスト(アプリを起動するPC)上の指定ポート番号で受付を開始します。
 * @author takunoji
 * 2019/07/20
 */
public class SocketServerBasic {
    private ServerSocket server;
    /**
     * コンストラクタ。
     * 
     * @param portNo アクセスを受け付けるポート番号
     */
    public SocketServerBasic(int portNo) {
        try {
            server = new ServerSocket(portNo);
            if (server.isBound() == false) {
                server.bind(new InetSocketAddress("localhost", portNo));
                server.setSoTimeout(3000);
            }
        } catch (IOException e) {
            this.finalize();
            e.printStackTrace();
        }
    }

    /**
     * Objectクラスの終了処理(ガベージコレクションに呼ばれる)
     */
    @Override
    public void finalize() {
        // メモリの解放
        server = null;
    }

    /**
     * コンストラクタで作成されたサーバーソケットで
     * 受付処理を開始する。
     */
    public void startServer() {
        try {
            System.out.println("*** SocketServer start " + server.isBound() + "***");
            Socket recieve = server.accept();
            System.out.println("*** Server Get Request start***");
            // 受信データの読み込み
            StringBuilder responseTxt = new StringBuilder("<Response>");
            // 受信状態
            System.out.println("クライアント: " + recieve.getRemoteSocketAddress());
            System.out.println("接続: " + recieve.isConnected());
            System.out.println("入力ストリームが閉じている: " + recieve.isInputShutdown());
            
            // 受信したリクエスト
            InputStream in = recieve.getInputStream();
            // 返却するレスポンス
            OutputStream writer = recieve.getOutputStream();
            System.out.print("Server recieve is ...");
            int c = 0;
            // CRとCRLFの場合で入力が終了している時がある
            while((c = in.read()) != -1) {
                char ch = (char)c;
                responseTxt.append(ch);
                // 空の場合
                if (c == 10 || c == 13) {
                	break;
                }
            }
            System.out.println(responseTxt.toString());
            System.out.println("*** Server Send Response start***");
            // レスポンス送信
            writer.write((responseTxt.toString() + System.getProperty("file.separator")).getBytes());
            writer.flush();
            in.close();
            writer.close();
            System.out.println("*** SocketServer end ***");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 開いたストリームを閉じる
            try {
                closeServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ソケットサーバーを終了します。
     */
    public void closeServer() throws IOException {
        server.close();
        server = null;
    }

    /**
     * メインメソッド
     * @param args プログラム引数
     */
    public static void main(String[] args) {
        SocketServerBasic serverSocket = new SocketServerBasic(8080);
        serverSocket.startServer();
    }
}
