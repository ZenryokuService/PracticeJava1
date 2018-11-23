package jp.zenryoku.opencv;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import jp.zenryoku.opencv.view.ViewFrame;

/**
 * OpenCVでイチからイメージを作成する。
 * 
 * @author takunoji
 * 2018/11/23
 */
public class OpenCVTest6 {

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
		// 100x100の白いPNGからのデータを作成する
//		Mat src = Imgcodecs.imread(OpenCVTest6.class.getResource("/images/black.png").getPath());
		Mat src = new Mat(50, 50, CvType.CV_16SC3);
		Point pt1 = new Point(0,0);
		Point pt2 = new Point(50,50);
		Imgproc.line(src, pt1, pt2, new Scalar(240, 255, 240), 1);
		System.out.println(src.dump());
		// 自作のJFrame拡張クラス
		new ViewFrame(src);
	}
}
