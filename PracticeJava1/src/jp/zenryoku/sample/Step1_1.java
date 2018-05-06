package jp.zenryoku.sample;
/*
 * 7行目〜14行目までの部分を「JavaDocコメント」と呼びます。
 * クラス、メソッドの説明に記述されます。
 * そして「JavaDoc」を出力する時に記載される内容になります。
 */
/**
 * Step1-1:Hello World
 * クラスに対する、JavaDocコメント部分
 * 「ドキュメント/ReadMe.txt」＜Step1＞でのサンプルプログラム
 * このクラスは、メインメソッドを起動するためのクラス
 * 
 * @see http://takunoji.hatenablog.com/entry/2015/10/25/134301
 * @author ZenryokuService → このプログラムの作成者の名前を記述する
 *                           「@」はjavadoc.exeが作成者名を認識するための記述
 */
public class Step1_1 {
	/**
	 * メソッドに対するJavaDocコメント部分
	 * 単純クラス定義の上に書けばクラス、メソッド定義の上で書けばメソッドに対応するJavaDocとなる
	 * 
	 * ＜メインメソッドについて＞
	 * Javaではプリグラムを起動する時には必ずこのメソッドが起動する。
	 * Webアプリケーションなどは、メインメソッドの場所がわかりずらいので別の機会に説明する
	 * 
	 * ＜メインメソッドの書き方＞
	 * 必ず下記のような書き方に成る。[注意]→(String args[])でもよいが今はそういう事にしておきます。。。
	 * 「public static void main{String[] args) { 〜プログラム処理〜 })」のようになる。
	 * 
	 * ↓パラメータ(引数)の内容を説明する。時に使用する
	 *  プログラムを起動する時に「javaファイルを右クリック」→「実行」→「実行の構成」の引数タブを参照
	 *  「プログラムの引数」に「aaa」と入力した時、
	 *  変数argsに"aaa"が渡されるスペースで区切ると複数渡す事もできる
	 *  
	 * @param args　メインメソッドの引数はプログラム引数を示す
	 */
	public static void main(String[] args) {
		// 1行コメント部分
		/*
		 * 複数行コメント部分
		 * 処理の説明など複数行に渡りコメントを記述する
		 */
		System.out.println("Step1");
	}

	/**
	 * 下記「yobareru()」はクラス内にある「メンバメソッドとよばれる
	 * public void yobareru()
	 * 修飾子  返却値 メソッド名　右のような決まりがあり
	 * クラス外部からも参照できる（公開）返却値なしの「yobareru」という名の引数なしメソッド
	 * という意味になる
	 * [例]
	 * private String mojiwoKaesu(int number)
	 * クラスの内部からのみ参照できる 文字列を返却する「mojiwoKaesu」という名の
	 * 引数「int型のnumberという変数を持つ」メソッド
	 */
	public void yobareru() {
		System.out.println("Nice to meet you!");
	}
}
