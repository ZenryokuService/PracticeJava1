/**
 * Copyright (c) 2019-present Coder Bank All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Coder Bank nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.apps.atm.manage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.zenryoku.apps.atm.data.Data;

/**
 * 口座の管理(登録、更新、削除)を行うクラス
 * @author takunoji
 *
 * 2019/09/21
 */
public class KozaManager {
	/** ファイルへの書き出しクラス */
	private BufferedWriter write;
	/** ファイルの読み込みクラス */
	private BufferedReader read;
	/** 取得(作成)するファイルクラス */
	private File file;
	/** ファイルのパス */
	private static final String FILE_PATH = "resources/koza.csv";

	/** コンストラクタ */
	public KozaManager() {
		// 操作するファイルを指定する
		file = new File(FILE_PATH);
		try {
			write = new BufferedWriter(new FileWriter(file, true));
			if (file.exists()) {
				read = new BufferedReader(new FileReader(file));
			}
		} catch (IOException ie) {
			ie.printStackTrace();
			System.out.println("ファイルオープンに失敗しました。" + ie.getMessage());
			System.exit(-1);
		}
	}

	/**　デストラクタ */
	@Override
	protected void finalize() throws Throwable {
		// フィールド変数の後始末
		file = null;
		read.close();
		write = null;
		read = null;
	}

	/** 作成するファイルが存在するかチェック */
	public boolean isFile() {
		return file.exists();
	}

	/**
	 * 出力するCSVのヘッダー部分を作成する。
	 * @return CSVヘッダーの文字列(カンマ区切り)
	 */
	private String createCSVHeader() {
		return "名前, パスワード";
	}

	/**
	 * データクラスを受け取り、CSVファイルを出力する(書き出しを行う)
	 * @param data コーダー銀行のユーザー情報
	 */
	public void dataOutput(Data data) throws IOException {
		if (file.canWrite() == false) {
			throw new IOException("ファイルの書き込みができません: " + file.getAbsolutePath());
		}
		// おおよそのデータサイズを指定すると余計なメモリを使用しなくて済む
		StringBuilder build = new StringBuilder(50);
		// ファイルそ新規で作成するとき
		if (file.exists() == false) {
			// ヘッダー部分の出力
			build.append(this.createCSVHeader());
			// ファイル書き込み処理
			write.write(build.toString());
			write.newLine();
		}
		// StringBuilderのクリア
		build.setLength(0);
		// データ部分の書き込み
		build.append(data.getName() + ",");
		build.append(data.getPassword());
		write.write(build.toString());
		write.newLine();
		write.close();
	}

	/**
	 * ファイルを読み込みデータをリストにして返却する
	 * @return List<Data> CSVファイルのデータリスト
	 */
	public List<Data> readFile() {
		List<Data> list = new ArrayList<>();
		String line = null;
		try {
			while((line = read.readLine()) != null) {
				String[] lineData = line.split(",");
				// lineData[0]: 名前, lineData[1]: パスワード
				list.add(new Data(lineData[0], lineData[1]));
			}
		} catch(IOException ie) {
			ie.printStackTrace();
			System.exit(-1);
		}
		return list;
	}

	/**
	 * フィールドのfileを返却する。
	 * @return file
	 */
	public File getFile() {
		return this.file;
	}
}
