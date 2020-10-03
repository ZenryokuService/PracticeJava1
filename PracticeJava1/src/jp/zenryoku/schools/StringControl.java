package jp.zenryoku.schools;

/**
 * 文字列の操作。
 * 
 * 2020/10/03
 */
public class StringControl {

	/**
	 * 動かしたいメソッドを呼び出してやれば動かせます。
	 * 例として「1.1文字列処理とは」のサンプルコードを呼び出しています。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		StringControl main = new StringControl();
		main.sample1_1StringCheck();
	}

	/**
	 * 「1.1.1文字列処理とは」のサンプルコード。
	 * 文字列を切り取る処理のサンプル<br/>
	 * <br/>
	 * <b>「こんにちは世界」という文字列の"ちは"を切り取る</b>
	 */
	public void substringTest() {
		// 変数の初期化
		String moji = "こんにちは世界";
		// *******************************************************//
		// * やり方１：Stringクラスのメソッド「substring()」を呼び出す *
		// *******************************************************//
		String rightStr = moji.substring(0, 3);
		String leftStr  = moji.substring(5, 7);
		System.out.println("切り取った結果1は「" + rightStr + leftStr + "」です。");

		// *******************************************************//
		// * やり方２：char型の配列を取り出してやる                   *
		// *******************************************************//
		char[] charMoji = moji.toCharArray();
		// 切り取った結果を格納する
		char[] resArray = new char[charMoji.length];
		int resCount = 0;
		for (int i = 0; i < charMoji.length; i++) {
			if (i != 3 && i != 4) {
				// 表示する文字を設定(セット)する
				resArray[resCount] = charMoji[i];
				resCount++;
			}
		}
		// char型の配列をStringとして生成
		String resString = new String(resArray);
		System.out.println("切り取った結果2は「" + resString + "」です。");
	}

	/**
	 * 「1.2.1とは」のサンプルコード。
	 * 文字列を調査するサンプル<br/>
	 * <table>
	 *     <thead>
	 *         <tr><th>操作</th></tr></tr>
	 *         <th>メソッド定義(シグニチャ)</th></tr>
	 *     </thead>
	 *     <tbody>
	 *         <tr><td>内容が等しいか調べる</td></tr>
	 *         <tr><td>public boolean equals(Object o)</td></tr>
	 *     </tbody>
	 * </table>
	 */
	public void checkString() {
		
		String moji = "test";
		String moji2 = "test";
		if (moji == moji2) {
			System.out.println("1.mojiは\"test\"です。");
		} else {
			System.out.println("1.mojiはStringクラスなのでequalsメソッドで等価判定を行う。");
		}
		// String#equals()
		if (moji.equals("test")) {
			System.out.println("2.mojiは\"test\"です。");
		} else {
			System.out.println("2.mojiは\"test\"ではありません。");
		}
		// String#equalsIgnoreCase()
		if (moji.equalsIgnoreCase("Test")) {
			System.out.println("3.mojiは\"Test\" or \"Test\"です。");			
		} else {
			System.out.println("3.mojiは\"Test\" or \"Test\"ではありません。");			
		}
		// String#isEmpty()
		if (moji.isEmpty()) {
			System.out.println("4.mojiは\"\"です。");
		} else {
			System.out.println("4.mojiは\"\"ではありません。");
		}
	}

	/**
	 * 文字列調査(文字列が等しいか?)のサンプル。
	 * 「リスト1-1 文字列調査メソッドを利用した例」
	 */
	public void sample1_1StringCheck() {
		// 文字列を比較した場合
		System.out.println("*** String#equals() Sample1 ****");
		if ("test".equals("tezt")) {
			System.out.println("\"test\"と\"tezt\"は等しいです。");
		} else if ("test".equals("tezt") == false) {
			System.out.println("\"test\"と\"tezt\"は等しくありません。");
		} else {
			// 実際は例外しか返ってこないので意味のないコード
			// 想定外のケースも考慮に入れる
			System.out.println("trueもfalseも帰ってこなかった場合。");
		}
		System.out.println("*** String#equals() Sample2 ****");
		// 変数に入れた文字列を比較した場合
		String str = "test";
		String str2 = "tezt";
		if (str.equals(str2)) {
			System.out.println("\"test\"と\"tezt\"は等しいです。");
		} else {
			System.out.println("\"test\"と\"tezt\"は等しくありません。");
		}

		System.out.println("*** String#equalsIgnoreCase() Sample1 ****");
		if ("test".equalsIgnoreCase("tezt")) {
			System.out.println("\"test\"と\"tezt\"は等しいです。");
		} else if ("test".equalsIgnoreCase("Test") == false) {
			System.out.println("\"test\"と\"Test\"は等しくありません。");
		} else if ("test".equalsIgnoreCase("Test")) {
			System.out.println("\"test\"と\"Test\"は等しいです。");
		} else {
			// 実際は例外しか返ってこないので意味のないコード
			// 想定外のケースも考慮に入れる
			System.out.println("trueもfalseも帰ってこなかった場合。");
		}

		System.out.println("*** String#isEmpty() Sample1 ****");
		if ("".isEmpty()) {
			System.out.println("文字列は空です。");
		}
		String tmp = null;

		System.out.println("*** String#isEmpty() Sample2 ****");
		try {
			if (tmp.isEmpty()) {
				System.out.println("文字列はNULLです。");
			}
		} catch (NullPointerException e) {
			System.out.println("実行するとNullPoineterExceptionで落ちる。");
		}
	}

	/**
	 * 文字列調査(文字列が等しいか?)のサンプル。
	 * 「リスト1-1 文字列調査メソッドを利用した例」
	 */
	public void sample1_2StringSearch() {
		String str = "0123456789ABCABC";
		System.out.println("*** String#contains() Sample1 ****");
		if (str.contains("012")) {
			System.out.println(str + "は「012」を含んでいます。");
		}
		System.out.println("*** String#contains() Sample1 ****");
		if (str.contains("014")) {
			System.out.println(str + "は「014」を含んでいます。");
		} else {
			System.out.println(str + "は「014」を含んでいません。");
		}

		System.out.println("*** String#endsWith() Sample1 ****");
		if (str.endsWith("ABC")) {
			System.out.println(str + "は「ABC」を末尾にあります。");
		}

		System.out.println("*** String#endsWith() Sample1 ****");
		System.out.println(str + "は「ABC」の文字列が" + str.indexOf("ABC") + "番目に出現します。");
		System.out.println(str + "は「ABC」の文字列が最後に出現するのは" + str.lastIndexOf("ABC") + "番目に出現します。");
	}
}
