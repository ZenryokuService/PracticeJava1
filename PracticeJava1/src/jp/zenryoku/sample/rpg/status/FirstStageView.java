package jp.zenryoku.sample.rpg.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jp.zenryoku.sample.rpg.ViewStatus;

public class FirstStageView extends ViewStatus{

	public FirstStageView() {
		createViewStr("resouces/FirstStage.txt");
	}
	/** super */
	@Override
	public void views() {
		Optional<List<String>> out = Optional.ofNullable(this.getViewStr());
		out.ifPresent(viewStr -> viewStr.stream().forEach(System.out::println));
	}

	@Override
	public Optional<Map<String, ViewCommand>> createCommands() {
		Map<String, ViewCommand> commands = new HashMap<String, ViewCommand>();
		commands.put("", new ViewCommand() {

			@Override
			public ViewStatus execute() {
				return null;
			}
			
		});
		return null;
	}
}
