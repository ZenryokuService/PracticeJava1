package jp.zenryoku.javadoc.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * JavaDocの読解用のクラス<BR>
 * 記載内容を理解するためのソースを記載する
 * @author takunoji
 */
public class JavaDocMap {
	// レベルに応じたHPを示す
	private Map<String, Integer> hitpoint;
	// プレーヤーの能力値(プロパティ)を示す
	private Properties prop;

	public static void main(String[] args) {
		JavaDocMap test = new JavaDocMap();
		test.test01();
	}

	/**
	 * コンストラクタ<BR>
	 * テスト実行時に使用するMapに値を設定する<BR>
	 * Map<K, V> => Kはキーを示し、Vは値を示す
	 */
	public JavaDocMap() {
		// MapインターフェースはHashMapクラスを実装されている
		hitpoint = new HashMap<>();
		// putメソッドでMapに値を設定する
		hitpoint.put("Lv1", 20);
		hitpoint.put("Lv2", 30);
		hitpoint.put("Lv3", 40);
		hitpoint.put("Lv4", 50);

		// PropertiesクラスもMapを実装している
		// Mapと違い、ファイルからロードすることも考慮されているので
		// 「put」でなく「setProperty」を使用することが推奨されている
		prop = new Properties();
		// 力
		prop.setProperty("power", "10");
		// 知力
		prop.setProperty("knowege", "3");
		// 器用さ
		prop.setProperty("dexterity", "5");
	}

	/**
	 * 【前提】：コンストラクタで初期値が設定されている<BR>
	 * Map, Propertiesから値を取得する
	 */
	public void test01() {
		System.out.println("Lv1のHP = " + hitpoint.get("Lv1"));
		// ラムダ式: Mapなので表示順序は保証されない(順番に表示されない場合がある)
		hitpoint.forEach((key, value) -> System.out.println(key + " : " + value));
		// keyとvalueをセットにしてやる必要がある
		prop.forEach((key, value) -> System.out.println("K=" + key + " V=" + value));
	}
}
