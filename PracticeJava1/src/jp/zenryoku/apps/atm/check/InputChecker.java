package jp.zenryoku.apps.atm.check;

/**
 * @author takunoji
 *
 * 2019/09/19
 */
public class InputChecker {
	/**
	 * コーダー銀行の入金、引き出しのチェックを行う
	 * @param input 入力した値
	 * @return エラーメッセージ、チェックOKの時はnullを返却する
	 */
	public static String validNyukinHikidashi(String input) {
		String error = null;
		if ("in".equals(input) == false || "out".equals(input) == false) {
			error = "「in」か「out」を入力してください。";
		}
		return error;
	}
}
