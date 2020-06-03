/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.basic.lv2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author takunoji
 *
 * 2020/06/03
 */
public class The11Program {
	public static void main(String[] args) {
		// 配列のデータ
		String[] data1 = new String[] {"A001", "A002", "A003", "A0041", "A005"};

		List<String> list1 = new ArrayList<String>();
		for (int i = 0; i < data1.length; i++) {
			System.out.println("data1[" + i + "]=" + data1[i]);
			list1.add(data1[i]);
		}

		System.out.println("Listの表示１");
		for (int i = 0; i < list1.size(); i++) {
			System.out.println("list[" + i + "]=" + list1.get(i));
		}
		// リストのサイズ変換
		list1.remove(4);
		System.out.println("Listの表示２変換後");
		for (int i = 0; i < list1.size(); i++) {
			System.out.println("list[" + i + "]=" + list1.get(i));
		}
	}
}
