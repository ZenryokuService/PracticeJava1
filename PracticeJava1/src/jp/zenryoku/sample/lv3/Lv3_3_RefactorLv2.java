package jp.zenryoku.sample.lv3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import jp.zenryoku.sample.lv3.refactor.CommandIF;
import jp.zenryoku.sample.lv3.refactor.cmd.HelloCommand;
import jp.zenryoku.sample.lv3.refactor.cmd.ReadyCommand;

/**
 * Java Mid Basic リファクタリングLv2 No4
 * 【おかしな実装を治す】
 * ・プロパティファイルを読み込むようにする。
 * ・読み込んだプロパティファイルからコマンドクラスを生成するように修正する。
 * 
 * ＜概要＞
 * 
 * @author takunoji
 * 2019/08/24
 */
public class Lv3_3_RefactorLv2 {
	/** コマンドリスト */
	private  Map<String, CommandIF> cmdMap;
	/** プロパティファイル */
	private Properties prop;

	public static void main(String[] args) {
		Lv3_3_RefactorLv2 main = new Lv3_3_RefactorLv2();
		// 標準入力
		Scanner input = new Scanner(System.in);

		while(true) {
			System.out.println("コマンドを入力してください: ");
			String inStr = input.nextLine();
//			CommandIF cmd = main.getCommand(inStr)
			CommandIF cmd = main.getCommandIF(inStr);
			if (cmd != null) {
				cmd.execute();
				continue;
			}
			if ("bye".equals(inStr)) {
				System.out.println("Good Byw");
				break;
			} else {
				System.out.println("対象のコマンドは登録されていません。: " + inStr);
			}
		}
	}

	/**
	 * コンストラクタで、使用するコマンドのセットを行う。
	 */
	public Lv3_3_RefactorLv2() {
		// コマンドの用意
		this.loadPropertyFile();
	}

	/** 【未使用メソッド】コマンドクラスの取得 */
	@Deprecated
	public CommandIF getCommand(String key) {
		return cmdMap.get(key);
	}

	/** プロパティファイル取得 */
	public void loadPropertyFile() {
		prop = new Properties();
		try {
			// resources/
			prop.load(getClass().getResourceAsStream("/CommandList.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			// エラーコード-1をセットしてプログラム終了
			System.exit(-1);
		}
	}

	public CommandIF getCommandIF(String key) {
		// 完全クラス名を取得する
		String fullClassName = prop.getProperty(key);
		if (fullClassName == null || "".equals(fullClassName)) {
			return null;
		}
		CommandIF cmd = null;
		try {
			@SuppressWarnings("unchecked")
			Class<CommandIF> cmdCls = (Class<CommandIF>) Class.forName(fullClassName);
			cmd = cmdCls.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		return cmd;
	}
}
