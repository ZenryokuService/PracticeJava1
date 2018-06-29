package jp.zenryoku.game.rpg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 画面のステータスクラスを示す。<BR>
 * コマンドの実行は内部インターフェースViewCommand<BR>
 * の実装クラスが行う<BR>
 * 【使い方】
 * 1. このクラスを継承して全ての抽象メソッドを実装します。
 * 2. コンストラクタでcreateViewStr()を呼び出して画面(文字列)を
 *    デザインしたファイルを指定してください読み込んで表示します。
 * 
 * @author takunoji
 */
public abstract class ViewStatus {
	/** 自クラスを保持する　*/
	protected ViewStatus status;
	/** コマンド管理 */
	protected Map<String, ViewCommand> commands;
	/** 表示する画面(文字列) */
	protected List<String> viewStrList; 

	/**
	 * インナークラスのインターフェース
	 * @author takunoji
	 */
	public interface ViewCommand {
		/** コマンドの実行
		 * Nullが返却されるときは画面の遷移なし 
		 */
		public abstract ViewStatus execute();
	}

	/**
	 * コンストラクタ<BR>
	 * 使用するコマンドをMapに設定する
	 */
	public ViewStatus() {
		Optional<Map<String, ViewCommand>> optCommands = createCommands();
		// 取得した結果がNullの場合はからのMapを返す
		commands =  optCommands.isPresent() ? optCommands.get() : new HashMap<String, ViewCommand>();
	}

	/**
	 * コマンド実行処理<BR>
	 * RpgMainクラスで標準入力を受けてコマンドマップに<BR>
	 * 登録されているViewCommandのexecuteを実行する<BR>
	 * Mapに登録されていない時は何も実行しない
	 * @param input
	 * @return
	 */
	public ViewStatus execute(String input) {
		Optional<ViewCommand> command = Optional.ofNullable(commands.get(input));
		// commandがからの場合は何もしない
		command.ifPresent(cmd -> setViewStatus(cmd.execute()));
		return this;
	}

	/**
	 * ファイルを読み込み画面を描画する(文字列)
	 * @param filePath
	 */
	protected void createViewStr(String filePath) {
		Path path = Paths.get(filePath);
		List<String> lines = null;
		try {
			lines = Files.readAllLines(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		lines.stream().forEach(System.out::println);
		// viewの文字列を保存します
		this.setViewStr(lines);
	}

	/** 作成、ファイルから読み込んだView文字を保存する */
	public void setViewStr(List<String> viewStrList) {
		this.viewStrList = viewStrList;
	}
	/** 作成、ファイルから読み込んだView文字を取得する */
	public List<String> getViewStr() {
		return this.viewStrList;
	}

	/** 画面の表示処理 */
	public abstract void views();
	/** 使用するコマンド */
	public abstract Optional<Map<String, ViewCommand>> createCommands();
	/** ViewStatusをセットする */
	public abstract void setViewStatus(ViewStatus status);
}
