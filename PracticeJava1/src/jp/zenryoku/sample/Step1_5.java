package jp.zenryoku.sample;

/**
 * Step1-5:クラスの配列<br/>
 * String[], Integer[]などの配列の扱い方をれ臭します<br/>
 * 
 * @see http://takunoji.hatenablog.com/entry/2015/11/05/184839
 * @author ZenryokuService
 */
public class Step1_5 {
	/** フィールド変数 */
	private String[] HAIRETU = {"第1問", "第2問", "第3問", "第4問", "daigomon"};

	/**
	 * メインメソッド、配列の練習<br/>
	 * IF文、FOR文の練習も同時にやります
	 * プログラム引数もString[]ですので
	 * @param args プログラム引数に渡した値が入ります
	 * 
	 */
	public static void main(String[] args) {
		for(int i = 0; i < args.length; i++) {
			System.out.println("第" + (i + 1) + "引数は「" + args[i] + "」です");
		}
	}
}
