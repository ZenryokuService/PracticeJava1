package jp.zenryoku.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import jp.zenryoku.opencv.view.ViewFrame;

/**
 * OpenCVで中央値フィルタ処理を行う。
 * 
 * @author takunoji
 * 2018/11/19
 */
public class OpenCVTest3 {
// ＜サンプルソース＞
// @see http://answers.opencv.org/question/7323/java-code-for-smoothing-image-using-opencv-library-functions/
//	public Mat blur(Mat input, int numberOfTimes){
//        Mat sourceImage = new Mat();
//        Mat destImage = input.clone();
//        for(int i=0;i<numberOfTimes;i++){
//            sourceImage = destImage.clone();
//            Imgproc.blur(sourceImage, destImage, new Size(3.0, 3.0));
//        }
//        return destImage;
//    }
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
				OpenCVTest3.class.getClass().getResource("/images/Experience.png").getPath());
		// 出力用変数
		Mat dst = new Mat();
		// ブラーする、種知力用の変数に値をセットする→「参照渡し」
		//Imgproc.blur(mat, source, new Size(2.0,2.0));
		// 中央値フィルタ
		Imgproc.medianBlur(src, dst, 7);
		// 自作のJFrame拡張クラス
		new ViewFrame(dst);
	}
}
