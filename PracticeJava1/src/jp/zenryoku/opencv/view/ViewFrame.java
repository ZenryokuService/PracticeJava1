/**
 * Copyright (c) 2012-present Lightweight Java Game Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Lightweight Java Game Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.opencv.view;

import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * @author takunoji
 *
 * 2018/11/19
 */
public class ViewFrame extends JFrame {

	public ViewFrame(Mat image) {
		// 受け取ったMatを表示する
		super("Show Image");
		// JFrameのメソッドを呼び出す(親クラス)
		Container con = super.getContentPane();
		
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
		JLabel label = new JLabel(new ImageIcon(buf));
		con.add(label);
		// JFrameを閉じるときにアプリケーションも終わるようにする
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// コンポーンエントの配置を整理する
		super.pack();
		// JFrameを表示する(これをやらないと何も表示されない)
		setVisible(true);
	}
}
