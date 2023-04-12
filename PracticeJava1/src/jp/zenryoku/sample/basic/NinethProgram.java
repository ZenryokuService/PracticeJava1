/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author takunoji
 *
 * 2020/06/02
 */
public class NinethProgram {
		public static void main(String[] args) {
			jp.zenryoku.sample.basic.NinethProgram main = new jp.zenryoku.sample.basic.NinethProgram();
			Scanner scan = new Scanner(System.in);

			while(true) {
				System.out.print("入力してください: ");
				String in = scan.next();

				if("bye".equals(in)) {
					System.out.println("アプリ終了");
					break;
				}
				String[] tmp = new String[] {"aaa"};
				try {
					if (in.equals("test")) {
						System.out.println("*** Testing now! ***");
					} else if ("file".equals(in)) {
						main.readFile("resources/FirstStage.txt");
					} else if (tmp.length == 1) {
						System.out.println("*** Not Error! ***");
					}
				} catch (FileNotFoundException e) {
					System.out.println("*** No File! ***");
				} catch (IOException e) {
					System.out.println("*** What's up!? ***");
				}
			}
		}

	private void readFile(String fileName) throws FileNotFoundException, IOException {
		File f = new File(fileName);
		BufferedReader buf = new BufferedReader(new FileReader(f));
		String line = null;
		while((line = buf.readLine()) != null) {
			System.out.println(line);
		}
		buf.close();
	}
}
