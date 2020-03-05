/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.lv2.design.singleton;

/**
 * @author takunoji
 *
 * 2020/03/05
 */
public class SingletonMain {
	private NoSingletonCls noSingle;

	/** インナークラス */
	public class NoSingletonCls {
		/** 設定情報１ */
		private int setting1;
		/** 設定情報２ */
		private String setting2;

		public NoSingletonCls() {
		}
		/**
		 * @return the setting1
		 */
		public int getSetting1() {
			return setting1;
		}

		/**
		 * @param setting1 the setting1 to set
		 */
		public void setSetting1(int setting1) {
			this.setting1 = setting1;
		}

		/**
		 * @return the setting2
		 */
		public String getSetting2() {
			return setting2;
		}

		/**
		 * @param setting2 the setting2 to set
		 */
		public void setSetting2(String setting2) {
			this.setting2 = setting2;
		}
	}
	
	public static void main(String[] args) {
		SingletonMain main = new SingletonMain();
		// シングルトンクラスを取得する(メソッドのstatic呼び出し)
		SingletonCls single = SingletonCls.getInstance();
		single.setSetting1(12);
		single.setSetting2("設定情報２");

		// 設定情報を表示する
		main.showSetting();

		// 設定情報を変更する
		single.setSetting1(99);
		single.setSetting2("こんばんは");
		
		// 設定情報を表示する
		main.showSetting();
		
		//// シングルトンではないパターン ////
		NoSingletonCls noSingle = main.new NoSingletonCls();
		// インナークラスなので参照可能、普通はこんな実装をしない
		noSingle.setting1 = 1;
		noSingle.setSetting2("内部設定");
		main.showInnerSetting(noSingle);
		// 値を変更する
		noSingle.setSetting1(33);
		noSingle.setSetting2("内部情報１２３");
		main.showInnerSetting(noSingle);
	}

	/** 設定情報を表示する */
	public void showSetting() {
		SingletonCls single = SingletonCls.getInstance();
		System.out.println("**** 設定情報 *****");
		System.out.println("設定情報１: " + single.getSetting1());
		System.out.println("設定情報２: " + single.getSetting2());
		System.out.println("*****************");
	}
	
	public void showInnerSetting(SingletonMain.NoSingletonCls noSingle) {
		System.out.println("**** (NoSingleton)設定情報 *****");
		System.out.println("設定情報１: " + noSingle.getSetting1());
		System.out.println("設定情報２: " + noSingle.getSetting2());
		System.out.println("*****************");
	}
}
