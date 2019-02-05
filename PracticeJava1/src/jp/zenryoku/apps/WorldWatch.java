package jp.zenryoku.apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 世界の標準時刻を取得、カウントする。
 * 
 * @see https://ja.wikipedia.org/wiki/%E6%99%82%E9%96%93%E5%B8%AF_(%E6%A8%99%E6%BA%96%E6%99%82)#UTC_+_14
 * @author takunoji
 * 2018/11/25
 */
public class WorldWatch {
	/** 時クラス */
	private static WorldWatch main;
	/** サーバー */
	private ServerSocket socket;
	/** サーバー起動フラグ */
	private boolean isExe;
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		main = new WorldWatch();
		try {
			main.sartServer();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(InterruptedException inte) {
			inte.printStackTrace();
		}
		main = null;
	}

	/**
	 * 初期化処理。
	 * <ol>
	 * <li>ServerSocketクラスの初期化(LocalHost:8080)</li>
	 * </ol>
	 */
	public WorldWatch() {
		isExe = true;
		try {
			socket = new ServerSocket();
			// タイムアウト3秒
			socket.setSoTimeout(3000);
			socket.bind(new InetSocketAddress("127.0.0.1",  8080));
			System.out.println("isBound: " + socket.isBound());
			System.out.println("LocalPort: " + socket.getLocalPort());
		} catch(IOException ie) {
			ie.printStackTrace();
		}
	}

	/**
	 * 世界中の標準時刻時計を起動する。
	 * <ol>
	 * <li>UTC + 14</li>
	 * <li>1.1	UTC + 14</li>
	 * <li>1.2	UTC + 13</li>
	 * <li>1.3	UTC + 12:45</li>
	 * <li>1.4	UTC + 12</li>
	 * <li>1.5	UTC + 11</li>
	 * <li>1.6	UTC + 10:30</li>
	 * <li>1.7	UTC + 10</li>
	 * <li>1.8	UTC + 9:30</li>
	 * <li>1.9	UTC + 9</li>
	 * <li>1.10	UTC + 8:45</li>
	 * <li>1.11	UTC + 8</li>
	 * <li>1.12	UTC + 7</li>
	 * <li>1.13	UTC + 6:30</li>
	 * <li>1.14	UTC + 6</li>
	 * <li>1.15	UTC + 5:45</li>
	 * <li>1.16	UTC + 5:30</li>
	 * <li>1.17	UTC + 5</li>
	 * <li>1.18	UTC + 4:30</li>
	 * <li>1.19	UTC + 4</li>
	 * <li>1.20	UTC + 3:30</li>
	 * <li>1.21	UTC + 3</li>
	 * <li>1.22	UTC + 2</li>
	 * <li>1.23	UTC + 1</li>
	 * <li>1.24	UTC</li>
	 * <li>1.25	UTC - 1</li>
	 * <li>1.26	UTC - 2</li>
	 * <li>1.27	UTC - 3</li>
	 * <li>1.28	UTC - 3:30</li>
	 * <li>1.29	UTC - 4</li>
	 * <li>1.30	UTC - 5</li>
	 * <li>1.31	UTC - 6</li>
	 * <li>1.32	UTC - 7</li>
	 * <li>1.33	UTC - 8</li>
	 * <li>1.34	UTC - 9</li>
	 * <li>1.35	UTC - 9:30</li>
	 * <li>1.36	UTC - 10</li>
	 * <li>1.37	UTC - 11</li>
	 * <li>1.38	UTC - 12</li>
	 */
	public void initWorldWatch() {
		
	}

	public void sartServer() throws IOException, InterruptedException{
		System.out.println("Accept: ");
		Socket recieve = socket.accept();
//		recieve.connect(new InetSocketAddress("127.0.0.1",  8080));
		BufferedReader in = null;
		while(isExe) {
			System.out.println("Hello Socket: ");
			in = new BufferedReader(new InputStreamReader(recieve.getInputStream()));
			System.out.println("Recieve: " + in.readLine());
			Thread.sleep(500L);
		}
		in.close();
		in = null;
	}

	public void terminated() throws IOException {
		this.socket.close();
		this.socket = null;
	}
}
