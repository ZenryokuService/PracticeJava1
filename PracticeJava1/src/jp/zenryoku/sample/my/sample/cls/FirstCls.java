/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.my.sample.cls;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import jp.zenryoku.sample.my.sample.cls.util.CheckUtil;


/**
 * @author takunoji
 *
 * 2020/06/05
 */
public class FirstCls {
	/** システム(OS)で使用する改行コード */
	private static final String SEPARATOR = System.getProperty("line.separator");
	/** プレーヤの勝利フラグ */
	private static final Integer WIN = 1;
	/** プレーヤの敗北フラグ */
	private static final Integer LOOSE = 2;
	/** プレーヤの引き分けフラグ */
	private static final Integer DRAW = 3;
	
	public void handleInput(String input) {
		System.out.println("入力値: " + input);
		if (isNumberString(input)) {
			System.out.println(input + "は、数字です");
		} else {
			System.out.println(input + "は、数字ではありません");
		}
	}

	public boolean isNumberString(String str) {
		// [0-9]は正規表現と言います。
		boolean isNumber = str.matches("[0-9]{1,}");
		return isNumber;
	}

	public void execute(Scanner scan) {
		System.out.println("*** EXECUTEを起動します ***");
		// じゃんけんの手のマップ
		Map<String, String> map = createJankenTe();
		// じゃんけんの勝敗マップ
		Map<String, Integer> judgeMap = createJudgement();
		Random rnd = new Random();
		while(true) {
			System.out.print("じゃんけん。。。");
			String player = scan.next();			

			if (CheckUtil.isNumber(player, CheckUtil.REG_1_TO_3) == false) {
				System.out.println("想定外の入力です。" + player);
				continue;
			}
			int cpu = rnd.nextInt(3) + 1;
			System.out.println("あなた：" + map.get(player) + " CPU：" + map.get(String.valueOf(cpu)));
			if (judgeMap.get(player + cpu) == WIN) {
				System.out.println("You win!");
			} else if (judgeMap.get(player + cpu) == LOOSE) {
				System.out.println("You loose!");
			} else if (judgeMap.get(player + cpu) == DRAW) {
				System.out.println("Draw! one more time ...");
				continue;
			}
			System.out.println(SEPARATOR + "もう一度やる？" + SEPARATOR + "y: もう一度 n: やめる");

			String more = scan.next();
			if ("n".equals(more)) {
				break;
			} 
		}
	}

	/**
	 * じゃんけんの手を設定したMapを返却する
	 * @return Map
	 */
	private Map<String, String> createJankenTe() {
		Map<String, String> map = new HashMap<>();
		map.put("1", "グー");
		map.put("2", "チョキ");
		map.put("3", "パー");
		return map;
	}

	/**
	 * じゃんけんの勝敗パターンをMapに設定
	 * @return Map
	 */
	private Map<String, Integer> createJudgement() {
		Map<String, Integer> map = new HashMap<>();
		map.put("12", WIN);
		map.put("23", WIN);
		map.put("31", WIN);
		map.put("21", LOOSE);
		map.put("32", LOOSE);
		map.put("13", LOOSE);
		map.put("11", DRAW);
		map.put("22", DRAW);
		map.put("33", DRAW);
		return map;
	}
	/**
	 * じゃんけんの判定を行います。
	 * @param player プレーヤーの出した手
	 * @param cpu コンピュータの出した手
	 * @return true: プレーヤの勝ち false: プレーヤーの負け
	 */
	private boolean judge(String player, String cpu, Map<String, String> map) {
		boolean isWin = false;
		System.out.println("あなた：" + map.get(player) + " CPU：" + map.get(cpu));
		int res = (Integer.parseInt(player) + Integer.parseInt(cpu)) % 2;
		if (res == 0) {
			
		}
		return isWin;
	}

	/** 使用しないので下のアノテーションをつける */
	@Deprecated
	public void printSomething(String[] args) {
		//  プログラム引数に値があるときは表示する
		if (args.length != 0) {
			for (int i = 0; i < args.length; i++) {
				System.out.println("プログラム引数[" + i + "]: " + args[i]);
			}
		}
		System.out.println("1 + 1 = " + (1 + 1));
	}
}
