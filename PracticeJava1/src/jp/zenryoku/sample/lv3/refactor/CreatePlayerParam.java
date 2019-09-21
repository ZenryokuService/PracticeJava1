package jp.zenryoku.sample.lv3.refactor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 数秘術から社会人基礎力を算出する。
 * 
 * @author takunoji
 * 2019/08/29
 */
public class CreatePlayerParam implements CommandIF {
	/** 母音 */
	private String boIn;
	/** 子音 */
	private String shiIn;
	/** 母音のリスト */
	private List<Character> boInList;

	public CreatePlayerParam() {
		// 母音のリスト
		boInList = new ArrayList<Character>();
		boInList.add('A');
		boInList.add('a');
		boInList.add('I');
		boInList.add('i');
		boInList.add('U');
		boInList.add('u');
		boInList.add('E');
		boInList.add('e');
		boInList.add('O');
		boInList.add('o');
	}
	/** 
	 * CommandIFを実装する、コマンドクラス。
	 * 
	 * @see jp.zenryoku.sample.lv3.refactor.CommandIF#execute()
	 */
	@Override
	public void execute() {
		// 標準入力
		Scanner input = new Scanner(System.in);
		System.out.println("あなたの名前をローマ字(ヘボン式)で入力してください: ");
		// 入力された文字列
		String inStr = input.nextLine();

		// 母音を切り取る
		boIn = cutOffBoin(inStr);
		// 子音
		shiIn = inStr;
		// 人格数
		int jinkakuSu = getJinkakuSu(boIn, shiIn);
		// 運命数
		int unmeSu = getUnmeSu(boIn, shiIn);
		// ハート数
		int heartSu = getHeartSu(boIn, shiIn);
		// 意思数
		int ishiSu = getIshiSu(boIn, shiIn);
		// 成熟数
		int seijukuSu = getSeijukuSu(boIn, shiIn);
		/* 社会人基礎力の算出 */
	}

	/**
	 * 母音を切り出します。
	 * @param inStr 入力された文字列
	 * @return 母音のみを切り出して出力する
	 */
	public String cutOffBoin(String inStr) {
		// 母音格納
		StringBuilder charStr = new StringBuilder();
		// 子音格納
		StringBuilder shiInStr = new StringBuilder();
		// 母音を切り出します
		String dstString = "";
		for (int i = 0; i < inStr.length(); i++) {
			char ch = inStr.charAt(i);
			if (boInList.contains(ch)) {
				charStr.append(ch);
			} else {
				shiInStr.append(ch);
			}
		}
		// 最後に子音をお尻に追加する(カンマで区切る)
		charStr.append("," + shiInStr.toString());
		return charStr.toString();
	}

	public int getJinkakuSu(String boIn, String shiIn) {
		// 未実装
		return 0;
	}

	public int getUnmeSu(String boIn, String shiIn) {
		// 未実装
		return 0;
	}

	public int getHeartSu(String boIn, String shiIn) {
		// 未実装
		return 0;
	}

	public int getIshiSu(String boIn, String shiIn) {
		// 未実装
		return 0;
	}

	public int getSeijukuSu(String boIn, String shiIn) {
		// 未実装
		return 0;
	}
	/*
	<iframe src="https://zenryokuservice.com/project/rakuten/php/rakutenCatalog.php?category=guitar" style="height: 1200px; margin: auto;" />
	 */
}

