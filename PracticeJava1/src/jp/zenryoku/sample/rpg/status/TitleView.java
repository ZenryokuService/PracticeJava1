package jp.zenryoku.sample.rpg.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jp.zenryoku.sample.rpg.ViewStatus;
import jp.zenryoku.sample.rpg.ViewStatus.ViewCommand;

/**
 * ゲーム開始時「タイトル画面」を作成する<BR>
 * 「s」でスタート
 * 「c」でコンテニュー
 * 
 * @author takunoji
 */
public class TitleView extends ViewStatus {

	public TitleView() {
		createViewStr("resources/title.txt");
	}
	@Override
	public void views() {
		Optional<List<String>> out = Optional.ofNullable(this.getViewStr());
		out.ifPresent(viewStr -> viewStr.stream().forEach(System.out::println));
	}
	@Override
	public Optional<Map<String, ViewCommand>> createCommands() {
		Optional<Map<String, ViewCommand>> commands
			= Optional.of(new HashMap<String, ViewCommand>());
		// 「s」が入力されたら次の画面へ遷移する
		// 「c」の時はまだ未実装....
		// 上記以外は何もしない
		commands.get().put("s", new ViewCommand() {
			@Override
			public ViewStatus execute() {
				return null;
				//return new FirstStageView();
			}
		});
		return commands;
	}
}
