package jp.zenryoku.sample.rpg;

import java.util.HashMap;
import java.util.Map;

/**
 * 画面のステータスクラスを示す。<BR>
 * コマンドの実行は内部インターフェースViewCommand<BR>
 * の実装クラスが行う
 * @author takunoji
 */
public class ViewStatus {
	/** コマンド管理 */
	private Map<String, ViewCommand> command;

	/**
	 * インナークラスのインターフェース
	 * @author takunoji
	 */
	public interface ViewCommand {
		/** コマンドの文字列を返す */
		public abstract String getCommand();
		/** コマンドの実行 */
		public abstract void executePrint(String command);
	}

	public ViewStatus() {
		command = new HashMap<String, ViewCommand>();
	}
}
