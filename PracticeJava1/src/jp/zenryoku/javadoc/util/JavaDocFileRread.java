package jp.zenryoku.javadoc.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * File読み込み用のクラスの読解<BR>
 * Filesクラスを使用する
 * @author takunoji
 */
public class JavaDocFileRread {
	private static final String FILE_PATH = "resources/title.txt";

	public static void main(String[] args) {
		JavaDocFileRread test = new JavaDocFileRread();
		test.testPaths();
	}

	/**
	 * 昔はBufferedFileInputSreamなどを使用していた。<BR>
	 * @see https://docs.oracle.com/javase/jp/6/api/java/io/FileInputStream.html
	 */
	public JavaDocFileRread() {
		// FILEのパス
		Path filePath = Paths.get(FILE_PATH);
		List<String> lines = null;
		try {
			System.out.println(filePath.toString());
			System.out.println("このファイルは読める: " + Files.isReadable(filePath));
			lines = Files.readAllLines(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("*** File ***");
		System.out.println("size = " + lines.size());
		lines.stream().forEach(str -> System.out.println(str));
	}

	/**
	 * Filesクラスとセットにして使うPaths
	 */
	public void testPaths() {
		System.out.println("*** Paths Testing ***");
		Path path = Paths.get("resources", "title.txt");
		// 標準出力
		path.forEach(System.out::println);
		System.out.println("*** read File ***");
		try {
			List<String> list = Files.readAllLines(path, Charset.defaultCharset());
			list.forEach(System.out::println);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
