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
		main.substringTest();
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
		// String#equals()
		if (moji.equals("test")) {
			System.out.println("mojiは\"test\"です。");
		} else {
			System.out.println("mojiは\"test\"ではありません。");
		}
		// String#equalsIgnoreCase()
		if (moji.equalsIgnoreCase("Test")) {
			System.out.println("mojiは\"Test\" or \"Test\"です。");			
		} else {
			System.out.println("mojiは\"Test\" or \"Test\"ではありません。");			
		}
		// String#isEmpty()
		if (moji.isEmpty()) {
			System.out.println("mojiは\"\"です。");
		} else {
			System.out.println("mojiは\"\"ではありません。");
		}
	}
}
