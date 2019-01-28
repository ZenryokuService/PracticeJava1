/**
 * Copyright (c) 2012-present Lightweight Java Game Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Lightweight Java Game Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.fx.pane;

import javafx.concurrent.Worker.State;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * @author takunoji
 *
 * 2019/01/28
 */
public class WebLoaderPane extends Pane {
	/** 初期値(デフォルト値)の縦幅 */
	private final double VIEW_HEIGHT = 500.0;
	/** 初期値(デフォルト値)の横幅 */
	private final double VIEW_WIDTH = 500.0;
	/** このクラスのインスタンス */
	private static WebLoaderPane instance;
	/** WebViewクラスのインスタンス */
	private WebView web;
	
	/**
	 * コンストラクタ。
	 * デフォルト値でWeb画面を表示する画面を作成する
	 */
	private WebLoaderPane() {
		web = new WebView();
		
		web.setPrefWidth(VIEW_WIDTH);
		web.setPrefHeight(VIEW_HEIGHT - 20);
		this.getChildren().add(web);
		System.out.println("非同期ロード処理開始");
	}

	/** 
	 * インスタンを取得する。
	 * このクラスのインスタンスは、必ず１つなので「static」をつけて良い。
	 * @return WebLoaderPane
	 */
	public static WebLoaderPane getInstance() {
		if (instance == null) {
			instance = new WebLoaderPane();
		}
		WebEngine engine = instance.getWebView().getEngine();
		engine.getLoadWorker().stateProperty()
			.addListener((observer, oldValue, newValue) -> {
				System.out.println(newValue);
				if (newValue == State.SUCCEEDED) {
					System.out.println("*** Load is finished! ***");
				}
			});
		engine.load("https://docs.oracle.com/javase/jp/8/javafx/api/javafx/scene/web/WebView.html");
		return instance;
	}

	/**
	 * このクラスのWebViewを返却する。
	 * @return WebView
	 */
	private WebView getWebView() {
		return this.web;
	}
}
