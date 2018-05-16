package jp.zenryoku.sample.cls;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Step2_2 {
	/** フィールド変数(定数) */
	private final String TITLE = "インターフェースについて"; 

	public static void main(String[] args) {
		Step2_2 step = new Step2_2();
		step.test01();
		step.test02();
	}

	public void test01() {
		// ビルドエラーになる
		//String[] arg = new String[];
		String[] args = new String[5];
		args[0] = "first";
		args[1] = "second";
		args[2] = "third";
		args[3] = "fourth";
		args[4] = "fifth";

		this.systemOut(args);
	}

	public void test02() {
		List<String> list = new ArrayList<String>();
		list.add("first");
		list.add("second");
		list.add("third");
		list.add("fourth");
		list.add("fifth");

		this.systemOut(list);
	}

	public void test03() {
		// OK牧場(笑)
		List<String> list = new ArrayList<String>();
		list.add("test01");
		list.add("test02");
		list.add("test03");
		List<String> list1 = new LinkedList<String>();
		list1.add("public1");
		list1.add("public2");
		list1.add("public3");

		this.systemOut(list);
		this.systemOut(list1);
	}
	private void systemOut(String[] args) {
		System.out.println("タイトル； " + this.TITLE);
		for(int i = 0; i < 5; i++) {
			System.out.println("配列: " + args[i]);
		}
	}

	/**
	 * オーバーロードしたメソッド
	 * @param moji
	 */
	private void systemOut(String moji) {
		System.out.println(moji);
	}

	public void systemOut(List list) {
		
	}
}
