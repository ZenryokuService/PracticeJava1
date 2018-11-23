package jp.zenryoku.opencv;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import jp.zenryoku.opencv.view.ViewFrame;

/**
 * OpenCVでを行う。
 * 
 * @author takunoji
 * 2018/11/23
 */
public class OpenCVTest5 {

	static {
		// OpenCVのライブラリをロードする
		// static {~}はMainメソッドの開始前に起動する、起動するプログラムで１つ定義可能
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	/**
	 * メインメソッド、書き方は決まっている。
	 * イメージファイルを読み込んでぼかす Imgproc.blur()
	 * 
	 * @param argsプログラム引数
	 */
	public static void main(String[] args) {
		// 100x100の白いPNGからのデータを作成する
		Mat src = Imgcodecs.imread(OpenCVTest5.class.getResource("/images/white100x100.png").getPath());
		Point pt1 = new Point(0,0);
		Point pt2 = new Point(100,100);
		Imgproc.line(src, pt1, pt2, new Scalar(240, 255, 240), 4);
//		Imgproc.boxPoints(new RotatedRect(pt1, new Size(20, 20), 1.0), src);
		
		System.out.println(src.dump());
//		// 出力用変数
//		Mat dst = new Mat();
//		// ブラーする、種知力用の変数に値をセットする→「参照渡し」
//		//Imgproc.blur(mat, source, new Size(2.0,2.0));
//		// 中央値フィルタ
//		Imgproc.medianBlur(src, dst, 7);
//		// 自作のJFrame拡張クラス
		new ViewFrame(src);
	}
}
