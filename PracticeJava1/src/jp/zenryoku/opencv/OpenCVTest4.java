package jp.zenryoku.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import jp.zenryoku.opencv.view.ViewFrame;

/**
 * OpenCVで平滑化処理を行う。
 * 
 * @author takunoji
 * 2018/11/22
 */
public class OpenCVTest4 {

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
		// ファイルの読み込み
		Mat src = Imgcodecs.imread(
				OpenCVTest4.class.getClass().getResource("/images/likeEye.png").getPath());
		Point pt1 = new Point(0,0);
		Point pt2 = new Point(10,10);
		Imgproc.line(src, pt1, pt2, new Scalar(30, 30, 40));
		
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
