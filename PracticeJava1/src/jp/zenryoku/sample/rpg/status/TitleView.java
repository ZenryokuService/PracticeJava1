package jp.zenryoku.sample.rpg.status;

import java.util.Map;

import jp.zenryoku.sample.rpg.ViewStatus;
import jp.zenryoku.sample.rpg.ViewStatus.ViewCommand;

/**
 * ゲーム開始時
 * @author takunoji
 */
public class TitleView extends ViewStatus implements ViewCommand{

	@Override
	public String getCommand() {
		return "title";
	}

	@Override
	public void printExecute() {
	}

	@Override
	public void executePrint(String command) {
	}
	
}
