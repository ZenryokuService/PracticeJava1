package jp.zenryoku.sample.rpg.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jp.zenryoku.sample.rpg.ViewStatus;

public class FirstStageView extends ViewStatus{

	public FirstStageView() {
		createViewStr("resources/FirstStage.txt");
	}
	/** super */
	@Override
	public void views() {
		Optional<List<String>> out = Optional.ofNullable(this.getViewStr());
		out.ifPresent(viewStr -> viewStr.stream().forEach(System.out::println));
	}

	@Override
	public Optional<Map<String, ViewCommand>> createCommands() {
		Optional<Map<String, ViewCommand>> commands = Optional.ofNullable(new HashMap<String, ViewCommand>());
		commands.get().put("n", new ViewCommand() {
			@Override
			public ViewStatus execute() {
				System.out.println("未実装です。");
				return null;
			}
		});
		return commands;
	}
	@Override
	public void setViewStatus(ViewStatus status) {
		this.status = status;
	}
}
