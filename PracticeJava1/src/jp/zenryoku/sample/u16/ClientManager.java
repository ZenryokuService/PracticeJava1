/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.u16;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * Clietプログラムの行動、現在位置などの管理を行う。
 * map; 行列を使用してマップを作成する
 * @author takunoji
 * 2019/05/24
 */
public class ClientManager {
	/** ND4Jの行列部品 */
	private INDArray map;

	/**
	 * コンストラクタ
	 */
	public ClientManager() {
		// 19 x 19のマップ(行列)を作る
		map = createMap();
	}

	/**
	 * 19 x 19の行列を作成する。
	 * 値は「４」で初期化する
	 * [値: 意味]
	 * [0: 空の領域]
	 * [1: アイテム]
	 * [2: ブロック]
	 * [3: 相手プレーヤ]
	 * [4: 未知の領域]
	 * @see https://github.com/ZenryokuService/AsahikawaProcon-Server/blob/master/README.md
	 * @return 19x19のマップ(行列)
	 */
	private INDArray createMap() {
		return Nd4j.zeros(19, 19).addi(10);
	}
}
