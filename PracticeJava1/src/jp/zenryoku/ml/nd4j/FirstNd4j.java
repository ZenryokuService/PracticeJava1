package jp.zenryoku.ml.nd4j;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.cpu.nativecpu.NDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * @author takunoji
 *
 * 2019/02/14
 */
public class FirstNd4j {
	public static void main(String[] args) {
		// クラスのインスタンス化
		FirstNd4j test = new FirstNd4j();
		
		INDArray zeros5 = Nd4j.zeros(5);
		System.out.print("Nd4j.zeros(5) -> ");
		System.out.println(zeros5);
		// 自分で作成したメソッドを使います。
		INDArray zero5_3 = test.createZeroINDArray(5, 3);
		System.out.print("Nd4j.zeros(5, 3) -> ");
		System.out.println(zero5_3);

		// 生成したNDArrayを3で埋める
		INDArray add3 = zero5_3.add(3);
		// 自作メソッドで表示します。
		printArray("INDArray.add(3)", add3);

		System.out.println("*** 0-1の間で乱数を生成する(2次元配列) ***");
		printArray("Nd4j.rand(2, 3)", Nd4j.rand(2, 3));

		System.out.println("*** ３次元以上 ***"); 
		printArray("Nd4j.rand(new int[] {1, 2, 3}])", Nd4j.rand(new int[] {1, 2, 3}));

		System.out.println("*** 平均ゼロ、標準偏差1のガウス乱数を生成する ***");
		printArray("Nd4j.randn(2, 3)", Nd4j.randn(2, 3));
		printArray("Nd4j.randn(new int[] {1, 2, 3}])", Nd4j.randn(new int[] {1, 2, 3}));
	}

	/**
	 * 静的メソッドです。
	 * このクラスを起動するときに他の読み込むクラスに同じメソッドは定義できません。
	 * 実行時エラーになります。
	 * 
	 * @param formula 表示する計算式
	 * @param vector ベクトル(配列)
	 */
	public static void printArray(String formula, INDArray vector) {
		System.out.print(formula + " -> ");
		System.out.println(vector);
	}
	/**
	 * このクラスのインスタンスメソッドです。
	 * ０のベクトルを生成します。
	 * 
	 * @param row 行の数
	 * @param col 列の数
	 * @return 生成された行列
	 */
	private INDArray createZeroINDArray(int row, int col) {
		return Nd4j.zeros(row, col);
	}

	/**
	 * 1.初めてのND4Jです、ベクトル(数値配列)を生成します。
	 */
	private void helloNd4j() {
		INDArray arr = Nd4j.zeros(3);
		System.out.println(arr);
	}

	/**
	 * 2.NDArrayを作成する処理
	 *   配列の演算処理(簡単なもの)。
	 */
	public void createNDArray_1() {
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
}
