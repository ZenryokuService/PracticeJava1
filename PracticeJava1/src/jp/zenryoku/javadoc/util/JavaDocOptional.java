package jp.zenryoku.javadoc.util;

import java.util.Optional;
import java.util.Random;

public class JavaDocOptional {
	public static void main(String[] args) {
		JavaDocOptional opt = new JavaDocOptional();
		Optional<Integer> one = Optional.ofNullable(new Integer("1"));
		System.out.println("1 + 1 = " + one.get() + 1);
		Optional<Integer> oneNull = Optional.ofNullable(null);
		// orElseメソッドで代わりの値を出力
		System.out.println("1 + null? = " + oneNull.orElse(0) + 1);
		// numにNullか数値が入る
		Optional<Integer> num = Optional.ofNullable(opt.randamInt());
		num.ifPresent(number -> System.out.println("Nullable : " + num.get()));
	}

	/**
	 * 乱数を生成して返す
	 */
	public Integer randamInt() {
		Random random = new Random();
		Integer num = random.nextInt();
		// numが偶数の場合
		if (num % 2 == 0) {
			num = null;
		}
		return num;
	}
}
