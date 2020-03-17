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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jp.zenryoku.sample.lv3.refactor.CommandIF;

/**
 * @author takunoji
 *
 * 2020/03/15
 */
public class JsoupHtmlReader implements CommandIF{

	/* (non-Javadoc)
	 * @see jp.zenryoku.sample.lv3.refactor.CommandIF#execute()
	 */
	@Override
	public void execute() {
		System.out.println("*** execute ***");
		String url = "https://ja.wikipedia.org/wiki/%E6%AD%A6%E5%99%A8";
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("*** Get Content ***");
		Element body = doc.body();
		Elements eles = body.getElementsByTag("h2");
		System.out.println("size: " + eles.size());
		for (int i = 0; i < eles.size(); i++) {
			Element item = eles.get(i);
			System.out.print("wholeText(): " + item.wholeText());
			System.out.println(" / text(): " + item.text());
		}
	}

	public static void main(String[] args) {
		JsoupHtmlReader main = new JsoupHtmlReader();
		main.execute();
	}
}
