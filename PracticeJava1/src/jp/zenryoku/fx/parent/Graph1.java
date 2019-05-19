/**
 * Copyright (c) 2012-present Lightweight Java Game Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Lightweight Java Game Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.fx.parent;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;

/**
 * @author takunoji
 *
 * 2019/02/20
 */
public class Graph1 extends Parent {
	/** このクラス(画面)の名前 */
	public static final String VIEW_NAME = "JavaBasic";
	/** このクラスのインスタンス */
	private static JavaBasicParent instance;

	/**
	 * コンストラクタ
	 */
	public Graph1() {
		// レイアウトたて
		VBox vBox = new VBox(5);

	}
}
