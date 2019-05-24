/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.ml.nd4j;

import java.awt.image.DataBuffer;

import org.junit.Test;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * ND4JのNd４jクラスを学習のため、テストする
 * @author takunoji
 * 2019/05/24
 */
public class TestNd4j {
	@Test
	public void testCreateINDArray() {
		// INT型データの行列を作成する
		INDArray data = Nd4j.create(new int[] {3, 3});
		System.out.println("*** init zeros***");
		System.out.println(data);
		System.out.println("*** putScalar ***");
		System.out.println(data.putScalar(new int[] {2, 1}, 1.0));
		System.out.println("*** init ones ***");
		INDArray reData = Nd4j.ones(new int[] {3, 3});
		System.out.println(reData);
		System.out.println(Nd4j.pad(reData, new int[] {1,  1}, Nd4j.PadMode.CONSTANT));
	}
}
