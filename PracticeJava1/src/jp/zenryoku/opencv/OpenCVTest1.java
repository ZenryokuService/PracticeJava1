/**
 * Copyright (c) 2012-present Lightweight Java Game Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Lightweight Java Game Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.opencv;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * OpenCVでのイメージファイル表示を行うメインメソッドを実装
 * ＜ポイント＞
 * Swingでイメージを表示してしまう。
 * ImageIconとJLabelを使用する。
 * @author takunoji
 *
 * 2018/11/18
 */
public class OpenCVTest1 {
	static {
		// OpenCVライグラリの読み込み、Mainメソッドが起動する前に読み込む
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	/**
	 * メインメソッド。
	 * シンプルに、イメージを読んで、JLabelでJFrameに表示する。
	 * 
	 * @param args プログラム引数
	 */
	public static void main(String[] args) {
		// OpenCVの部品でテスト用のイメージファイルを読み、Mat型で受け取る
		Mat image = Imgcodecs.imread(OpenCVTest1.class.getClass().getResource("/images/Experience.png").getPath());
		// イメージのバイト配列を受ける変数
		MatOfByte bytes = new MatOfByte();
		// 上の変数にpngファイルを書き込む
		Imgcodecs.imencode(".png", image, bytes);
		// プリミティブ型に変換
		byte[] b = bytes.toArray();
		// 入力ストリームを用意する
		InputStream in = new ByteArrayInputStream(b);
		
		BufferedImage buf = null;
		try {
			// イメージとして読み込む
			buf = ImageIO.read(in);
		} catch(IOException e) {
			e.printStackTrace();
		}
		// JFrameをインスタンス化
		JFrame frame = new JFrame("Show Image");
		/* 
		 * JFrameの描画コンポーネントContainerクラスを取得して
		 * 、そのコンテナに、
		 * JLabelにImageIcanを乗せてセットする
		 */
		frame.getContentPane().add(new JLabel(new ImageIcon(buf)));
		// JFrameを閉じるときにアプリケーションも終わるようにする
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// コンポーンエントの配置を整理する
		frame.pack();
		// JFrameを表示する(これをやらないと何も表示されない)
		frame.setVisible(true);
	}
}
