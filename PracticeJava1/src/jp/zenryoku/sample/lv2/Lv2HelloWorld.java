package jp.zenryoku.sample.lv2;

import java.util.Scanner;

/**
 * @author takunoji
 *@see http://zenryokuservice.com/wp/2018/07/20/java-basic-level-1-%E3%80%9Chello-java%E3%80%9C/
 * 2019/07/25
 */
public class Lv2HelloWorld {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		while(true) {
			System.out.print("入力してください: ");
			String inStr = input.nextLine();
			if ("hello".equals(inStr)) {
				System.out.println("Hello World!");
			} else {
				System.out.println("想定外の入力です");
				break;
			}
			System.out.println("Next command ... ");
		}
		System.out.print("AP is fiinished. Bye!");
	}
}
