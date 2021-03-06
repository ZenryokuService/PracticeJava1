/**
 * Copyright (c) 2019-present Math Kit JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name Math Kit JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.apps;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Node;

import jp.zenryoku.sample.lv3.refactor.CommandIF;
import jp.zenryoku.sample.lv3.refactor.CommandIfParam1;

/**
 * @author takunoji
 *
 * 2020/03/15
 */
public class JsoupHtmlReader implements CommandIfParam1{
	private String rootUrl;
	/* (non-Javadoc)
	 * @see jp.zenryoku.sample.lv3.refactor.CommandIF#execute()
	 */
	@Override
	public void execute(String url) {
		rootUrl = url;
		// System.out.println("*** execute ***");
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("*** Get Content ***");
		Elements eles = doc.getElementById("toc").children();
		for (int i = 0; i < eles.size(); i++) {
			Element item = eles.get(i);
			printIndex(item, i);
		}
	}

	private void printIndex(Element row, int num) {
//		System.out.println("index" + num + ": " + row.text());
		if (row.childrenSize() != 0) {
			printChild(row.children());
		}
	}
	
	private void printChild(Elements eles) {
		for (Element ele: eles) {
			if (ele.childrenSize() != 0) {
				if (ele.tagName().equals("a")) {
					Elements link = ele.getElementsByTag("a");
					System.out.println("Attr: " + link.attr("href"));
				}
				printChild(ele.children());
			} else {
				printElement(ele);
			}
		}
	}
	private void printElement(Element ele) {
		if (ele.text().contains(".")) {
			print(ele,"-   ", ": ");
		} else if (ele.text().matches("[0-9]{1,2}")) {
			print(ele,"", ": ");
		} else {
			if (ele.text().equals("")) {
				return;
			}
			println(ele, "", "");
		}
	}
	private void print(Element ele, String prefix, String safix) {
		if (safix != "") {
			System.out.print(ele.text() + safix);
		} else {
			System.out.print(prefix + ele.text());
		}
	}
	private void println(Element ele, String prefix, String safix) {
		if (safix != "") {
			System.out.println(ele.text() + safix);
		} else {
			System.out.println(prefix + ele.text());
		}
	}
	public static void main(String[] args) {
		JsoupHtmlReader main = new JsoupHtmlReader();
		String url = "https://ja.wikipedia.org/wiki/%E6%AD%A6%E5%99%A8";
		main.execute(url);
	}
}
