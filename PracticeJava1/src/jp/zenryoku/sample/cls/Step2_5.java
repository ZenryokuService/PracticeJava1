/**
 * Copyright (c) 2012-present Lightweight Java Game Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Lightweight Java Game Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.cls;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

/**
 * @author takunoji
 *
 * 2019/01/26
 */
public class Step2_5 {
	public static void main(String[] args) {
		Map<String, Integer> hashMap = new HashMap<String, Integer>();
		hashMap.put("C", new Integer(3));
		hashMap.put("B", new Integer(2));
		hashMap.put("A", new Integer(1));
		hashMap.forEach(new BiConsumer<String, Integer>() {
			@Override
			public void accept(String t, Integer u) {
				System.out.println("Key: "+ t + " / Value: " + u);
			}
		});;
		Map<String, Integer> linkedMap = new TreeMap<String, Integer>();
		linkedMap.put("C", new Integer(3));
		linkedMap.put("B", new Integer(2));
		linkedMap.put("A", new Integer(1));
		linkedMap.forEach(new BiConsumer<String, Integer>() {
			@Override
			public void accept(String t, Integer u) {
				System.out.println("Key: "+ t + " / Value: " + u);
			}
		});;
	}
}
