package jp.zenryoku.sample.lv3.refactor.cmd;

import java.util.ArrayList;
import java.util.List;

import jp.zenryoku.sample.lv3.refactor.CommandIF;

/**
 * @author takunoji
 *
 * 2019/08/23
 */
public class HelloCommand implements CommandIF {

	/* (non-Javadoc)
	 * @see jp.zenryoku.sample.lv3.refactor.CommandIF#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
	}

	public void test() {
		List<String> list = new ArrayList<>();
		list.add("一郎");
		list.add("二郎");
	    list.add("三郎");
		list.forEach(System.out::println);
	}
}
