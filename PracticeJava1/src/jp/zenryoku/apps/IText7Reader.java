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

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

/**
 * @author takunoji
 *
 * 2020/03/18
 */
public class IText7Reader {
	private PdfReader reader;
	private PdfDocument doc;

	private static final String TARGET_FILE = "resources/blackEagleSeint.pdf";
	public IText7Reader() {
		try {
			reader = new PdfReader(TARGET_FILE);
			doc = new PdfDocument(reader);
			System.out.println("page count: " + doc.getNumberOfPages());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public void execute() throws IOException {		
		String val = PdfTextExtractor.getTextFromPage(doc.getPage(2));
		System.out.println(val);
	}
	
	public static void main(String[] args) {
		IText7Reader main = new IText7Reader();
		try {
			main.execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
