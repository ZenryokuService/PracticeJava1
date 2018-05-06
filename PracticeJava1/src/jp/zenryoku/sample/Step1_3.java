package jp.zenryoku.sample;

/**
 * Step1-3:char配列の扱い<br/>
 * 
 * <br/>はJavaDoc用の改行タグです
 * 
 * @see http://takunoji.hatenablog.com/entry/2015/11/03/165638
 * @author ZenryokuService
 */
public class Step1_3 {

	/**
	 * Step1-3サンプルソース<br/>
	 * 「¥n」改行コード
	 * @param args
	 */
	public static void main(String[] args) {
		// char型の変数の扱い
		char ch = 'a';
		ch = 'b';
		System.out.println("char=" + ch);
		// 配列の場合
		char[] ch1 = {'A', 'B', 'C', 'D', 'E'};
		System.out.println("０番:" + ch1[0] + "¥n"
				+ ch1[1] + "１番:" + "¥n"
				+ ch1[2] + "２番:" + "¥n"
				+ ch1[3] + "３番:" + "¥n"
				+ ch1[4] + "４番:");
	}
	/**
	 * 問1:サンプルソースを参考に下記のような表示を出力してください<br/>
	 * ---コンソールに出力する↓----<br/>
	 * ０番:E<br/>
	 * １番:A<br/>
	 * ２番:C<br/>
	 * ３番:B<br/>
	 * ４番:D<br/>
	 */
	public void mondai1() {
		// ヒントなし
	}

	/**
	 * 問2:ch1の配列の中身を入れ替えて"BACED"と表示させてください
	 *     *注意printArray(char[] ch1()のメソッドは書き換えないでください
	 */
	public void mondai2() {
		char[] ch1 = {'A', 'B', 'C', 'D', 'E'};
		printArray(ch1);
	}

	/**
	 * 問2の表示用のメソッド<br/>
	 * 書き換えないでください
	 * @param ch1
	 */
	private void printArray(char[] ch1) {
		System.out.println(ch1[0] + ch1[1] + ch1[2] + ch1[3]);
	}
	/**
	 * 問3のサンプルソース<br/>
	 * このメソッドを書き換えず、sortChar()を書き換えて<br/>
	 * 「edcba」と表示させてください
	 */
	public void mondai3() {
		// char型の配列
		char[] ch = {'a', 'b', 'c', 'd', 'e'};
		char[] result = sortChar(ch);

		System.out.println("答え："
				+ result[0] + result[1] + result[2] + result[3] + result[4]);
	}

	/**
	 * 問3の配列をソートするメソッド<br/>
	 * @param ch char型の配列
	 * @return char[] ソートを完了した配列
	 */
	private char[] sortChar(char[] ch) {
		
		return ch;
	}

	/**
	 * 問4：メソッドhenshuを書き換えて<br/>
	 * 　　　答えに40を表示してください
	 */
	public void mondai4() {
		// int型の配列
		int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int kekka = 0;
		// 配列の編集処理
		nums = henshu(nums);
		/*
		 * 繰り返しのFOR文
		 * 配列を０番目から最後まで繰り返し処理を行う
		 */
		for(int i=0; i < nums.length; i++) {
			kekka += nums[i];
		}
		System.out.println("答え：" + kekka);
	}
	/**
	 * 問4の配列を編集するメソッド<br/>
	 * ここのソースをjp.zenryoku.sample以下に<br/>
	 * コピーして作成してください
	 */
	private int[] henshu(int[] nums) {
		return nums;
	}

	/**
	 * 問5-1：メソッドsort2を書き換えて<br/>
	 *     上から順に「番号1から10」までを表示してください
	 */
	public void mondai5_1() {
		// int型の配列
		int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int kekka = 0;
		// 配列の編集処理
		nums = sort2(nums, true);
		/*
		 * 繰り返しのFOR文
		 * 配列を０番目から最後まで繰り返し処理を行う
		 */
		for(int i=0; i < nums.length; i++) {
			System.out.println("番号" + nums[i]);
		}
	}
	/**
	 * 問5-2：メソッドsort2を書き換えて<br/>
	 *     上から順に「番号10から1」までを表示してください
	 *     5-1と逆の順序になります
	 */
	public void mondai5_2() {
		// int型の配列
		int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int kekka = 0;
		// 配列の編集処理
		nums = sort2(nums, false);
		/*
		 * 繰り返しのFOR文
		 * 配列を０番目から最後まで繰り返し処理を行う
		 */
		for(int i=0; i < nums.length; i++) {
			System.out.println("番号" + nums[i]);
		}
	}

	/**
	 * 問5の配列を並び替える処理<br/>
	 * 第二引数をtrueの時とfalseのときそれぞれ実行結果を<br/>
	 * 確認してください
	 * @param nums int型の配列
	 * @param flg true :昇順に並び替えるフラグ
	 *            false:降順に並び替えるフラグ
	 * @return int[] 並べ替えをした後の配列
	 */
	private int[] sort2(int[] nums, boolean flg) {
		int[] henko = null;
		return henko;
	}
}
