package jp.zenryoku.javadoc.lang;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import jp.zenryoku.sample.cls.Step2_1;

public class JavaDocClassLoader {
	public static void main(String[] args) {
		JavaDocClassLoader tests = new JavaDocClassloader();
		ClassLoader loader = JavaDocClassLoader.class.getClassLoader();
		try {
			// 取得したリソースを出力する
			streamRead(loader.getResourceAsStream("title.txt"));
			// 完全クラス名(文字列)でクラスのインスタンスを取得する
			Step2_1 clazz = (Step2_1) loader.loadClass("jp.zenryoku.sample.cls.Step2_1").newInstance();
			clazz.arrayTest1();

		// この様なcatch文はあまり書かないが、こんなのもあるよ的な感じです。
		} catch(IOException | ClassNotFoundException 
				| InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		
	}

	public static void streamRead(InputStream stream) throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(stream));
		String read = null;
		// 1行読み込み変数readに代入、そのあたいがNull出ないならば。。。
		while((read = buf.readLine()) != null) {
			System.out.println(read);
		}
	}

	/**
	 * デフォルトコンストラクタ<BR>
	 * 中身のないコンストラクタのことを言う
	 */
	public JavaDocClassLoader() {
	}

	public void test01() {
		
	}
}
