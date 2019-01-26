/**
 * Copyright (c) 2012-present Lightweight Java Game Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Lightweight Java Game Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.cls;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author takunoji
 *
 * 2019/01/26
 */
public class Step2_4 {
	public static void main(String[] args) {
		String[] array = new String[] {"abc", "def", "ghi"};
		List<String> arrayList = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			arrayList.add(array[i]);
		}
		System.out.println("\n*** ArrayList *** ");
		// ラムダ式な書き方
		arrayList.stream().forEach(System.out::print);
		System.out.println("\n*** ArrayListにSet *** ");
		arrayList.set(2, "テスト");
		arrayList.stream().forEach(System.out::print);

		List<String> linkedList = new LinkedList<String>();
		for (int i = 0; i < 3; i++) {
			linkedList.add(array[i]);
		}
		System.out.println("\n*** LinkedList *** ");
		linkedList.stream().forEach(System.out::print);
		System.out.println("\n*** LinkedListにセット *** ");
		linkedList.set(2,"テスト");
		linkedList.stream().forEach(System.out::print);
	}
}
