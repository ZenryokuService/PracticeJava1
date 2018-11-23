package jp.zenryoku.opencv;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
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
public class OpenCVTest7 {

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
		Mat src = Imgcodecs.imread(OpenCVTest7.class.getResource("/images/black.png").getPath());
		src = ellipseLabel(src, 4, 16);
		System.out.println(src.dump());
		// 自作のJFrame拡張クラス
		ViewFrame frame = new ViewFrame(src);
		sleepFrame();
		frame.updateLabel(ellipseLabel(src, 16, 4));
		sleepFrame();
		frame.updateLabel(ellipseLabel(src, 2, 4));
	}

	private static Mat ellipseLabel(Mat src, int left, int right) {
		Imgproc.ellipse(src
				, new Point(src.width() /2, src.height() / 2)
				, new Size(src.width() / left, src.width() / right)
				, 0     // angle
				, 0.0   // ??
				, 360.0 // 0と360度の間の弧を延長する
				, new Scalar(255, 0, 0) // BGRの値
				, 2   // thickness
				, 8   // lineType
				, 0); // Shift
		return src;
	}

	private static void sleepFrame() {
		try {
			Thread.sleep(3000L);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}
}
