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
public class OpenCVTest9_AddWeited {

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
		// 真っ白な画像から引き算してみる
		Mat src = Imgcodecs.imread(OpenCVTest9_Substract.class.getResource("/categories/" + "1_create.png").getPath(), Imgcodecs.IMREAD_UNCHANGED);
		Mat cart = Imgcodecs.imread(OpenCVTest9_Substract.class.getResource("/categories/" + "A_design.png").getPath(), Imgcodecs.IMREAD_UNCHANGED);
		Mat cart1 = Imgcodecs.imread(OpenCVTest9_Substract.class.getResource("/categories/" + "a_artifacts.png").getPath(), Imgcodecs.IMREAD_UNCHANGED);
		System.out.println("*** First ****");
		Mat dst = new Mat();
		Core.addWeighted(src, 0.5, cart, 0.5, 0, dst);
		Core.addWeighted(dst, 0.5, cart1, 0.5, 0, dst);
		System.out.println("*** Second ****");
		Imgcodecs.imwrite("/dst/1Aa.png", dst);
//		System.out.println(dst.dump());
		ViewFrame frame = new ViewFrame(dst);
		System.out.println("実行時間: " + (System.currentTimeMillis() - start) + "ミリ秒");
	}
}
