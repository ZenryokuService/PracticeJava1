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
 * OpenCVで部分的に画像の一部を修正する処理を行う。
 * 
 * @author takunoji
 * 2018/11/23
 */
public class OpenCVTest8 {

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
		// 100x100の白いPNGからのデータを作成する
		Mat src = Imgcodecs.imread(OpenCVTest8.class.getResource("/images/mountain.png").getPath());
		/* 1.トリミングを行なってみる
		 * x座標:100, y座標:100, 幅: 100, 高さ:100
		 * この処理で取得したRectでMatを再作成する
		 * Mat src = new Mat(src, rect);
		 */
		// Rect rect = new Rect(100, 100, 100, 100);
		// 領域を指定、白の四角を描く new Scalar(R, G, B);
		Imgproc.rectangle(src, new Point(100, 100), new Point(200, 200), new Scalar(255, 255, 255));
		ViewFrame frame = new ViewFrame(src);
		System.out.println("実行時間: " + (System.currentTimeMillis() - start) + "ミリ秒");
		
	}
}
