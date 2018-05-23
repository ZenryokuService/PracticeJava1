package jp.zenryoku.javadoc.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * JavaDocの読解用のクラス<BR>
 * 記載内容を理解するためのソースを記載する
 * @author takunoji
 */
public class JavaDocList {
	List<String> arrayList = null;
	List<String> linkedList = null;
	List<String> vector = null;

	public static void main(String[] argd) {
		JavaDocList test = new JavaDocList();
		test.test01();
		test.test02();
		test.test03();
	}

	public JavaDocList() {
		arrayList = new ArrayList<>();
		linkedList = new LinkedList<>();
		vector = new Vector<>();
		String added = "テストで追加する";
		for (int i = 0; i < 4; i++) {
			arrayList.add(added + i);
			linkedList.add(added + i);
			vector.add(added + i);
		}
	}

	/**
	 * ラムダ式を使用してみました。
	 */
	public void test01() {
		System.out.println("*** Test01 ***");
		// みんな「Listインターフェース」を実装しているので
		// 単純に中身を表示する処理上記の３種類でほぼ処理は変わらない
		// がソートの仕方とかデータの扱い方が違う
		arrayList.stream().forEach(str -> System.out.println(str)); // 配列
		linkedList.stream().forEach(str -> System.out.println(str));// ポインター
		// 古いのでArrayListを使用する方が良い　JavaDoc参照
		vector.stream().forEach(str -> System.out.println(str)); 
	}

	/**
	 * 追加機能
	 */
	public void test02() {
		System.out.println("*** Test02 ***");
		// リストに追加する, コンストラクタで追加したものに追加している
		arrayList.add("test");
		// リストインターフェースを実装しているもの全てを丸のまま追加する
		arrayList.addAll(vector);
		arrayList.stream().forEach(str -> System.out.println(str)); // 配列
	}

	/**
	 * チェック機能
	 */
	public void test03() {
		System.out.println("*** Test03 ***");
		System.out.println("「テストで追加する1」を含んでいるか: " + linkedList.contains("テストで追加する1"));

		// Iteratorを使用している
		for(String str: vector) {
			System.out.println("拡張for: " + str);
		}
		// stream()を取得してラムダ式を使用する
		arrayList.stream().forEach(str -> System.out.println(str));
		// 初めの要素(String)を削除する
		arrayList.remove(0);
		// 配列化(Object[]なのでキャストする)
		Object[] res =  arrayList.toArray();
	}
}
