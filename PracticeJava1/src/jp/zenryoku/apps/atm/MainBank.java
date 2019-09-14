package jp.zenryoku.apps.atm;

import java.util.Scanner;

/**
 * @author takunoji
 *
 * 2019/09/14
 */
public class MainBank {
	/** 金銭管理クラス */
	private Calcuration cal;
	
	public static void main(String[] args) {
		MainBank main = new MainBank();
		main.atm();
	}

	/**
	 * コンストラクタ
	 */
	public MainBank() {
		cal = new Calcuration();
	}

	public void atm() {
		System.out.println("コーダー銀行へようこそ、入金しますか？引き出しますか？");
		System.out.println("あなたの、預金額は ¥" + cal.getYokingaku() + "-です。");
		System.out.println("入金の時は「in」、引き出しの時は「out」を、終了する時は「bye」を入力してください。");

		Scanner input = new Scanner(System.in);
		while(true) {
			String inStr = input.nextLine();
			if ("in".equals(inStr)) {
				System.out.println("入金処理を行います。");
				cal.nyukin(input, true);
			} else if ("out".equals(inStr)) {
				System.out.println("引出し処理を行います。");
				cal.nyukin(input, false);
			} else if ("bye".equals(inStr)) {
				break;
			} else {
				System.out.println("「in」か「out」を入力してください。");
			}
		}
		System.out.println("ATM処理を終了します。ご利用ありがとうございました。");
	}
}
