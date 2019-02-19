package jp.zenryoku.ml.nd4j;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * @author takunoji
 *
 * 2019/02/14
 */
public class FirstNd4j {
	public static void main(String[] args) {
		/*
		 * 配列の計算をする
		 * x＝(1.0, 2.0), y=(5.0, 7.0)を計算する
		 */
		INDArray x = Nd4j.create(new double[] {1.0, 2.0, 1.5, 2,5}, new int[] {2, 2});
		INDArray y = Nd4j.create(new double[] {5.0, 7.0, 5.5, 7.5}, new int[] {2, 2});
		// 行列の足し算「x + y」
		INDArray answer = x.add(y);
		System.out.println("行列の足し算: x + y = " + answer);
		// 行列の引き算「x + y」
		answer = x.sub(y);
		System.out.println("行列の引き算: x - y = " + answer);
		/* 下の計算はまた別の機会にします。ちょっとややこしい(笑) */
		// 行列のかけ算「x * y」
		answer = x.mmul(y);
		System.out.println("行列の掛け算: x * y = " + answer);
		// 行列のわり算「x / y」
		answer = x.div(y);
		System.out.println("行列の割算: x / y = " + answer);
		
		System.out.println("割り算の答えの０番目(INT)" + answer.getInt(0, 0)); 
		System.out.println("割り算の答えの０番目(INT)" + answer.getInt(0, 1)); 
		System.out.println("割り算の答えの０番目(Double)" + answer.getDouble(1, 0)); 
		System.out.println("割り算の答えの０番目(Double)" + answer.getDouble(1, 1)); 
	}

	/**
	 * 初めてのND4Jです、ベクトル(数値配列)を生成します。
	 */
	private void helloNd4j() {
		INDArray arr = Nd4j.zeros(3);
		System.out.println(arr);
	}
}
