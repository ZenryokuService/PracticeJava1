package jp.zenryoku.sample.rpg.status;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import jp.zenryoku.sample.rpg.ViewStatus;
import jp.zenryoku.sample.rpg.ViewStatus.ViewCommand;

/**
 * ゲーム開始時「タイトル画面」を作成する
 * @author takunoji
 */
public class TitleView extends ViewStatus {

	public TitleView() {
	}
	@Override
	public void views() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Optional<Map<String, ViewCommand>> createCommands() {
		return Optional.of(new HashMap<String, ViewCommand>());
	}

}
