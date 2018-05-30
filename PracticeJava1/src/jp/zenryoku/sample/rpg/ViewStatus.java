package jp.zenryoku.sample.rpg;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import jp.zenryoku.sample.rpg.ViewStatus.ViewCommand;

/**
 * 画面のステータスクラスを示す。<BR>
 * コマンドの実行は内部インターフェースViewCommand<BR>
 * の実装クラスが行う
 * @author takunoji
 */
public abstract class ViewStatus {
	/** コマンド管理 */
	private Map<String, ViewCommand> commands;

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

	/**
	 * コンストラクタ<BR>
	 * 使用するコマンドをMapに設定する
	 */
	public ViewStatus() {
		System.out.println("*** Set Command ***");
		Optional<Map<String, ViewCommand>> optCommands = createCommands();
		// 取得した結果がNullの場合はからのMapを返す
		commands =  optCommands.isPresent() ? optCommands.get() : new HashMap<String, ViewCommand>();
	}

	public ViewStatus execute(String input) {
		Optional<ViewCommand> command = Optional.ofNullable(commands.get(input));
		// commandがからの場合は何もしない
		command.ifPresent(cmd -> cmd.executePrint(input));
		return this;
	}

	/** 画面の表示処理 */
	public abstract void views();
	/** 使用するコマンド */
	public abstract Optional<Map<String, ViewCommand>> createCommands();
}
