package jp.zenryoku.opencv.lv2;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

import jp.zenryoku.opencv.view.ViewFrame;

/**
 * OpenCVで画像データの平均をだす処理を行う。
 * 
 * @author takunoji
 * 2018/11/25
 */
public class OpenCVTest13 {

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
		Mat src = Imgcodecs.imread(OpenCVTest13.class.getResource("/images/4color.png").getPath());
		Mat calc = Imgcodecs.imread(OpenCVTest13.class.getResource("/images/4color.png").getPath());
		Core.calcCovarMatrix(src, calc, src, Core.COVAR_COLS);
		// 論理演算処理を行う
//		Mat dst = new Mat(new Size(50, 50), CvType.CV_8UC1);
		ViewFrame frame = new ViewFrame(src);
		System.out.println("実行時間: " + (System.currentTimeMillis() - start) + "ミリ秒");
	}
}