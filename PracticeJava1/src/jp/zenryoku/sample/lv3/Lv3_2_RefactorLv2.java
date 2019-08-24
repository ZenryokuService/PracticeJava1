package jp.zenryoku.sample.lv3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import jp.zenryoku.sample.lv3.refactor.CommandIF;
import jp.zenryoku.sample.lv3.refactor.cmd.HelloCommand;
import jp.zenryoku.sample.lv3.refactor.cmd.ReadyCommand;

/**
 * Java Mid Basic リファクタリングLv2 No3
 * 【おかしな実装を治す】
 * ・意味もなく「static」修飾子を使用していたのでやめる。
 * ・クラスを使用していないので「どこで何をする？」が不明確、それを修正
 * 
 * ＜概要＞
 * ・コンストラクタで使用できるコマンドを作成(Mapにセット)
 * ・メインメソッドで無限ループ、ループからの抜け出し。全体の処理フローを実装
 * 
 * @author takunoji
 * 2019/08/24
 */
public class Lv3_2_RefactorLv2 {
	/** コマンドリスト */
	private  Map<String, CommandIF> cmdMap;
	public static void main(String[] args) {
		Lv3_2_RefactorLv2 main = new Lv3_2_RefactorLv2();
		// 標準入力
		Scanner input = new Scanner(System.in);

		while(true) {
			System.out.println("入力してください: ");
			String inStr = input.nextLine();
			CommandIF cmd = main.getCommand(inStr);
			if (cmd != null) {
				cmd.execute();
			}
			if ("bye".equals(inStr)) {
				System.out.println("Good Byw");
				break;
			}
		}
	}

	/**
	 * コンストラクタで、使用するコマンドのセットを行う。
	 */
	public Lv3_2_RefactorLv2() {
		// コマンドの用意
		cmdMap = new HashMap<String, CommandIF>();
		cmdMap.put("hello", new HelloCommand());
		cmdMap.put("ready", new ReadyCommand());
	}

	/** コマンドクラスの取得 */
	public CommandIF getCommand(String key) {
		return cmdMap.get(key);
	}
}
