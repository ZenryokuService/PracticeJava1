package jp.zenryoku.sample.lv3.refactor.cmd;

import jp.zenryoku.sample.lv3.refactor.CommandIF;

/**
 * @author takunoji
 *
 * 2019/08/23
 */
public class ReadyCommand implements CommandIF {

	/* (non-Javadoc)
	 * @see jp.zenryoku.sample.lv3.refactor.CommandIF#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Ready?");
	}

	@Override
	public boolean equals(Object cls) {
		return true;
	}
}
