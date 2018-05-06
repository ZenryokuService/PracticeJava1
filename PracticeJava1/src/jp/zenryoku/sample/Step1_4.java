package jp.zenryoku.sample;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**　この行からJavaDocコメントです<br/>
 * Step1-4:Stringクラスの扱い<br/>
 * クラス型（オブジェクト型）の変数の扱い、メソッドの扱いの練習<br/>
 * <br/>
 * 下にある＠マーク次の行は、JavaDocに付帯情報を表示するためのものです<br/>
 * JavaDocについては<a href="https://ja.wikipedia.org/wiki/Javadoc">こちら</a>
 * <br/>
 *  <a href="http://takunoji.hatenablog.com/entry/2015/11/05/184839">参照</a>
 * @author ZenryokuService
 */
/*
 *  [public class]はどこからでもアクセス可能なクラスである事を示します
 *  上記に対して[private class] Step1-4.txtの「インナークラス」参照
 *  　　　　　　[abstract class] Step1-4.txtの「抽象クラス」参照
 */
public abstract class Step1_4 {
	/**
	 * インナークラス
	 * @author ZenryokuService
	 */
	private class SampleCode1_4 {
		/** フィールド変数 */
		private String str = "文字";
		/**
		 * 文字を返却するメソッド
		 * @return String フィールドを返却します
		 */
		public String getStr() {
			// thisと記述したクラスを示す
			// この場合はSampleCode4クラス
			return this.str;
		}
		/**
		 * java.swingを使用してフレーム、パネル、ボタンコンポーネントを<br/>
		 * 作成し、表示します。
		 * @see https://docs.oracle.com/javase/jp/6/api/
		 */
		public void createSwing() {
			JFrame frame = new JFrame("Swingサンプル");
			///// JFrameの設定を行います ////

			// 表示画面のサイズを指定します
			frame.setSize(300, 200);
			// 画面が閉じる時の処理を指定します。JFrameクラスのフィールドEXIT_ON_CLOSE→値は3
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			/*
			 *  java.awt.Containerクラスを取得
			 *  このクラスで画面（フレーム）に各コンポーネントを追加する
			 *  ※コンポーネント＝＞ボタン、テキストラベル、テキストボックスなど...
			 */
			Container con = frame.getContentPane();
			// JPanelを作成
			JPanel panel = new JPanel();
			// パネルにラベルを追加
			panel.add(new JLabel("createSwingのラベル"));
			// Containerへパネルを追加
			con.add(panel);
			
		}
	}
	/**
	 * サンプルコード１<br/>
	 * クラスの外からでもアクセスのできる<br/>
	 * 返却値なし<br/>
	 * 引数なしのメソッド<br/>
	 */
	public void sample1() {
		System.out.println("サンプル１");
	}

	/**
	 * サンプルコード2<br/>
	 * クラスの内からしかアクセスのできない<br/>
	 * 返却値はchar型<br/>
	 * 引数なしのメソッド<br/>
	 */
	private char sample2() {
		System.out.println("サンプル２");
		return 'A';
	}

	/**
	 * サンプルコード３<br/>
	 * クラスの内からしかアクセスのできない<br/>
	 * 返却値なし<br/>
	 * 引数String型のメソッド<br/>
	 */
	private void sample3(String str) {
		System.out.println("サンプル3" + str);
	}
	/**
	 * サンプルコード４<br/>
	 * 同じパッケージ内、継承関係のあるクラスからしかアクセスのできない<br/>
	 * 返却値なし<br/>
	 * 引数、String型<br/>
	 */
	protected void sample4(String str) {
		/* インスタンスを生成し使用する */
		// インナークラスをインスタンス生成
		SampleCode1_4 smp = new SampleCode1_4();
		System.out.println("サンプル4" + smp.getStr());
	}
	/**
	 * サンプルコード5<br/>
	 * 抽象メソッド<br/>
	 * 返却値なし<br/>
	 * 引数String型のメソッド<br/>
	 * 
	 * このクラスを継承するクラスでこのメソッドを<br/>
	 * オーバーライドして使用する
	 */
	public abstract void sample5(String str);
	/**
	 * Step1-4のサンプルソース<br/>
	 * 変数flgには true/false のどちらかが入ります<br/>
	 * 下のソースではどちらの値がはいるでしょうか？<br/>
	 * サンプルを起動してみてください<br/>
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("***** String *****");
		// String型の宣言
		String moji = "これは文字列";
		// String.charAt→引数の位置にあるchar型を返却します
		System.out.println("No:" + moji.charAt(4));
		boolean flg = moji.equals("これは数値です");
		System.out.println("boolean = " + flg);

		/***********************
		 * IF分の書き方          *
		 * *********************/
		System.out.println("***** if文の使い方 *****");
		if(flg) {
			// flgの値がtrueのとき
			System.out.println("flg = true");
		} else {
			// flgの値がfalseのとき
			System.out.println("flg = flase");
		}
		if (moji.charAt(2) == 'は') {
			// mojiの２番目が'は’のときは「あたり」を表示
			System.out.println("あたり");
		} else if(moji.charAt(2) == 'れ') {
			// 同様に「れ」のとき
			System.out.println("おしい");
		} else {
			// 上記以外の時
			System.out.println("はずれ");
		}
		/***********************
		 * Switch文の書き方      *
		 * *********************/
		/*
		 * switchぶんでは、beak;をいれないと次のcase文も通ってしまう
		 */
		System.out.println("***** switch文の使い方 *****");
		switch(moji.charAt(1)) {
		case 'こ':
			System.out.println("「こ」のとき");
			break;
		case 'れ':
			System.out.println("「れ」のとき");
			break;
		case 'は':// beak;がない
			System.out.println("「は」のとき");
		default:
			System.out.println("その他");
		}

		/*
		 * java.swingパッケージを使用して、サーバーに接続しない
		 * スタンドアロン・アプリケーションを起動する
		 */
		
	}
 }
