package jp.zenryoku.opencv;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import jp.zenryoku.opencv.view.ViewFrame;

/**
 * OpenCVで画像データ(Matクラス=行列)をかけ算する処理を行う。
 * 
 * @author takunoji
 * 2018/11/24
 */
public class OpenCVTestA10_subMat {

	static {
		// OpenCVのライブラリをロードする
		// static {~}はMainメソッドの開始前に起動する、起動するプログラムで１つ定義可能
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	/**
	 * メインメソッド、書き方は決まっている。
	 * イメージファイルを読み込んで線を引く
	 * 
	 * @param argsプログラム引数
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		// 画像を２枚読み込む
		Mat src = Imgcodecs.imread(OpenCVTest9_Substract.class.getResource("/categories/1_創造実行.png").getPath());
		Mat cart = Imgcodecs.imread(OpenCVTest9_Substract.class.getResource("/categories/A_デザイン_アート.png").getPath());
		Rect rect = new Rect(600, 400, 50, 50);
		Mat roi = src.submat(rect);
		// 抜き出した部分に掛け算を行う(アルファブレンドを行う)
		Core.addWeighted(roi, 0.5, cart, 0.5, 0, roi);
		ViewFrame frame = new ViewFrame(src);
		System.out.println("実行時間: " + (System.currentTimeMillis() - start) + "ミリ秒");
	}
}
