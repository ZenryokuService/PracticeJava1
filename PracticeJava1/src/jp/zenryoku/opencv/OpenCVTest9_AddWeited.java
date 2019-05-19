package jp.zenryoku.opencv;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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

	private static Map<String, String> cate = new HashMap<>();
	private static final String ROOT_DIR = "/categories/";

	/**
	 * メインメソッド、書き方は決まっている。
	 * イメージファイルを読み込んで線を引く
	 * 
	 * @param argsプログラム引数
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		// Lv0カテゴリ
		cate.put("1", "1_create.png");
		cate.put("2", "2_learn.png");
		cate.put("3", "3_life.png");
		// Lv1カテゴリ
		cate.put("A", "A_design.png");
		cate.put("B", "B_music.png");
		cate.put("C", "C_fashion.png");
		cate.put("D", "D_Sports.png");
		cate.put("E", "E_WebPrograming.png");
		cate.put("F", "F_farming.png");
		cate.put("G", "G_automobile.png");
		cate.put("H", "H_game.png");
		cate.put("I", "I_learn.png");
		cate.put("J", "J_horoScope.png");
		cate.put("K", "K_manga.png");
		cate.put("L", "L_gurumet.png");
		cate.put("M", "M_hobby.png");
		// Lv2カテゴリ
		cate.put("a", "a_artifacts.png");
		cate.put("b", "b_noteDocs.png");
		cate.put("c", "c_licenses.png");
		cate.put("d", "d_networking.png");
		cate.put("e", "e_community.png");
		cate.put("f", "f_money.png");
		cate.put("g", "g_collection.png");
		// 出力するカテゴリID
		String[] ids = {
				"1Aa", "1Ab", "1Ac", "1Ad", "1Ae", "1Af", "1Ag"
				,"1Ba", "1Bb", "1Bc", "1Bd", "1Be", "1Bf", "1Bg"
				,"1Ca", "1Cb", "1Cc", "1Cd", "1Ce", "1Cf", "1Cg"
				,"1Da", "1Db", "1Dc", "1Dd", "1De", "1Df", "1Dg"
				,"1Ea", "1Eb", "1Ec", "1Ed", "1Ee", "1Ef", "1Eg"
				,"1Fa", "1Fb", "1Fc", "1Fd", "1Fe", "1Ff", "1Fg"
				,"1Ga", "1Gb", "1Gc", "1Gd", "1Ge", "1Gf", "1Gg"
				,"1Ha", "1Hb", "1Hc", "1Hd", "1He", "1Hf", "1Hg"
				,"1Ia", "1Ib", "1Ic", "1Id", "1Ie", "1If", "1Ig"
				,"1Ja", "1Jb", "1Jc", "1Jd", "1Je", "1Jf", "1Jg"
				,"1Ka", "1Kb", "1Kc", "1Kd", "1Ke", "1Kf", "1Kg"
				,"1La", "1Lb", "1Lc", "1Ld", "1Le", "1Lf", "1Lg"
				,"1Ma", "1Mb", "1Mc", "1Md", "1Me", "1Mf", "1Mg"
				,"2Aa", "2Ab", "2Ac", "2Ad", "2Ae", "2Af", "2Ag"
				,"2Ba", "2Bb", "2Bc", "2Bd", "2Be", "2Bf", "2Bg"
				,"2Ca", "2Cb", "2Cc", "2Cd", "2Ce", "2Cf", "2Cg"
				,"2Da", "2Db", "2Dc", "2Dd", "2De", "2Df", "2Dg"
				,"2Ea", "2Eb", "2Ec", "2Ed", "2Ee", "2Ef", "2Eg"
				,"2Fa", "2Fb", "2Fc", "2Fd", "2Fe", "2Ff", "2Fg"
				,"2Ga", "2Gb", "2Gc", "2Gd", "2Ge", "2Gf", "2Gg"
				,"2Ha", "2Hb", "2Hc", "2Hd", "2He", "2Hf", "2Hg"
				,"2Ia", "2Ib", "2Ic", "2Id", "2Ie", "2If", "2Ig"
				,"2Ja", "2Jb", "2Jc", "1Jd", "2Je", "2Jf", "2Jg"
				,"2Ka", "2Kb", "2Kc", "2Kd", "2Ke", "2Kf", "2Kg"
				,"2La", "2Lb", "2Lc", "2Ld", "2Le", "2Lf", "2Lg"
				,"2Ma", "2Mb", "2Mc", "2Md", "2Me", "2Mf", "2Mg"
				,"3Aa", "3Ab", "3Ac", "3Ad", "3Ae", "3Af", "3Ag"
				,"3Ba", "3Bb", "3Bc", "3Bd", "3Be", "3Bf", "3Bg"
				,"3Ca", "3Cb", "3Cc", "3Cd", "3Ce", "3Cf", "3Cg"
				,"3Da", "3Db", "3Dc", "3Dd", "3De", "3Df", "3Dg"
				,"3Ea", "3Eb", "3Ec", "3Ed", "3Ee", "3Ef", "3Eg"
				,"3Fa", "3Fb", "3Fc", "3Fd", "3Fe", "3Ff", "3Fg"
				,"3Ga", "3Gb", "3Gc", "3Gd", "3Ge", "3Gf", "3Gg"
				,"3Ha", "3Hb", "3Hc", "3Hd", "3He", "3Hf", "3Hg"
				,"3Ia", "3Ib", "3Ic", "3Id", "3Ie", "3If", "3Ig"
				,"3Ja", "3Jb", "3Jc", "3Jd", "3Je", "3Jf", "3Jg"
				,"3Ka", "3Kb", "3Kc", "3Kd", "3Ke", "3Kf", "3Kg"
				,"3La", "3Lb", "3Lc", "3Ld", "3Le", "3Lf", "3Lg"
				,"3Ma", "3Mb", "3Mc", "3Md", "3Me", "3Mf", "3Mg"
				};
		for (String id : ids) {
			System.out.println("*** ID: " + id);
			String lv0 = cate.get(id.substring(0, 1));
			String lv1 = cate.get(id.substring(1, 2));
			String lv2 = cate.get(id.substring(2, 3));
			outputCategoryImage(lv0, lv1, lv2, id);
		}
//		// 真っ白な画像から引き算してみる
//		Mat src = Imgcodecs.imread(OpenCVTest9_Substract.class.getResource("/categories/" + "1_create.png").getPath(), Imgcodecs.IMREAD_UNCHANGED);
//		Mat cart = Imgcodecs.imread(OpenCVTest9_Substract.class.getResource("/categories/" + "A_design.png").getPath(), Imgcodecs.IMREAD_UNCHANGED);
//		Mat cart1 = Imgcodecs.imread(OpenCVTest9_Substract.class.getResource("/categories/" + "a_artifacts.png").getPath(), Imgcodecs.IMREAD_UNCHANGED);
//		System.out.println("*** First ****");
//		Mat dst = new Mat();
//		Core.addWeighted(src, 0.5, cart, 0.5, 0, dst);
//		Core.addWeighted(dst, 0.5, cart1, 0.5, 0, dst);
//		System.out.println("*** Second ****");
//		Imgcodecs.imwrite("/dst/1Aa.png", dst);
//		System.out.println(dst.dump());
//		ViewFrame frame = new ViewFrame(dst);
		System.out.println("実行時間: " + (System.currentTimeMillis() - start) + "ミリ秒");
	}

	private static void outputCategoryImage(String path1, String path2, String path3, String fileId) {
		System.out.println("path1:" + path1);
		Mat src = Imgcodecs.imread(OpenCVTest9_Substract.class.getResource(ROOT_DIR + path1).getPath(), Imgcodecs.IMREAD_UNCHANGED);
		System.out.println("path2:" + path2);
		Mat cart = Imgcodecs.imread(OpenCVTest9_Substract.class.getResource(ROOT_DIR + path2).getPath(), Imgcodecs.IMREAD_UNCHANGED);
		System.out.println("path3:" + path3);
		Mat cart1 = Imgcodecs.imread(OpenCVTest9_Substract.class.getResource(ROOT_DIR + path3).getPath(), Imgcodecs.IMREAD_UNCHANGED);
		Mat dst = new Mat();
		Core.addWeighted(src, 0.5, cart, 0.5, 0, dst);
		Core.addWeighted(dst, 0.5, cart1, 0.5, 0, dst);
		Imgcodecs.imwrite("/Users/takk/IdeaProjects/PracticeJava1/PracticeJava1/resources/dst/" + fileId + ".png", dst);
		dst = null;
		src = null;
		cart = null;
		cart1 = null;
	}
}
