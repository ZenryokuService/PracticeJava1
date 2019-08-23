package jp.zenryoku.sample.lv3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import jp.zenryoku.sample.lv3.refactor.CommandIF;
import jp.zenryoku.sample.lv3.refactor.cmd.HelloCommand;
import jp.zenryoku.sample.lv3.refactor.cmd.ReadyCommand;

/**
 * Java Mid Basic リファクタリングLv2
 * @author takunoji
 *
 * 2019/08/12
 */
public class Lv3_1_RefactorLv2_Main {
	/** コマンドリスト */
	private static  Map<String, CommandIF> cmdMap;
	public static void main(String[] args) {
		// コマンドの用意
		cmdMap = new HashMap<String, CommandIF>();
		cmdMap.put("hello", new HelloCommand());
		cmdMap.put("ready", new ReadyCommand());
		// 標準入力
		Scanner input = new Scanner(System.in);

		while(true) {
			System.out.println("入力してください: ");
			String inStr = input.nextLine();
			CommandIF cmd = cmdMap.get(inStr);
			if (cmd != null) {
				cmd.execute();
			}
			if ("bye".equals(inStr)) {
				System.out.println("Good Byw");
				break;
			}
		}
	}
}
