/**
 * Copyright (c) 2012-present Lightweight Java Game Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Lightweight Java Game Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.fx.parent;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * JavaBasic用の画面クラス。
 * Paneクラスを継承して、画面の表示部分を作成します。
 * 
 * @author takunoji
 * 2019/01/28
 */
public class JavaBasicParent extends Parent {
	/** このクラス(画面)の名前 */
	public static final String VIEW_NAME = "JavaBasic";
	/** このクラスのインスタンス */
	private static JavaBasicParent instance;
	
	/**
	 * コンストラクタ。
	 * デフォルト設定でのJavaBasic画面を作成します。
	 */
	private JavaBasicParent() {
		// 暗黙的に起動される親クラスのコンストラクタ
		super();
		// レイアウトたて
		VBox vBox = new VBox(5);
		// レイアウト横
		HBox hBox = new HBox(8);
		// ラベルの設定
		Label label = new Label();
		// ハローワールドを出力する
		label.setText("Hello World");
		label.setFont(new Font("RobotRegular", 24));
		vBox.getChildren().add(label);
	
		// １個目の数値、テキストフィールド
		TextField text1 = new TextField();
		text1.setPrefColumnCount(3);
		text1.setAlignment(Pos.BASELINE_CENTER);
		hBox.getChildren().add(text1);
		// 計算式のラベル
		Label ope = new Label("+");
		hBox.getChildren().add(ope);
	
		// ２個目の数値、テキストフィールド
		TextField text2 = new TextField();
		text1.setPrefColumnCount(3);
		text1.setAlignment(Pos.BASELINE_CENTER);
		hBox.getChildren().add(text2);
	
		// 縦のレイアウトに追加する
		vBox.getChildren().add(hBox);

		// 子供(中身)のペインを追加
		this.getChildren().add(vBox);
	}

	/** 
	 * インスタンを取得する。
	 * このクラスのインスタンスは、必ず１つなので「static」をつけて良い。
	 * @return JavaBasicPane
	 */
	public static JavaBasicParent getInstance() {
		if (instance == null) {
			instance = new JavaBasicParent();
		}
		return instance;
	}
}
