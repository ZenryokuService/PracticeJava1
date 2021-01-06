package jp.zenryoku.sample.kihojoho;

import java.nio.ByteBuffer;
import java.util.BitSet;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jp.zenryoku.sample.kihonjoho.BasicLogic;

/**
 * @author takunoji
 *
 * 2021/01/04
 */
public class BasicLogicTest {
	/** テスト対象クラス */
	private static BasicLogic target;

	@BeforeClass
	public static void initBasicLogic() {
		target = new BasicLogic();
	}

	private String toBinaryStr(byte val) {
		
		return Byte.toString(val);//Integer.toBinaryString(val);
	}

	private void printBinary(byte b) {
		System.out.print("2進数表現: " + toBinaryStr(b) + " / 10進数表現: " + b);
	}

	private void printlnBinary(byte b, boolean isFirstLine) {
		if (isFirstLine) {
			System.out.println("2進数表現: " + toBinaryStr(b) + " / 10進数表現: " + b);
		} else {
			System.out.println(" | 2進数表現: " + toBinaryStr(b) + " / 10進数表現: " + b);
		}
	}

	private void printlnBinary(String variableName, byte ans1) {
		System.out.println(variableName + " = 2進数表現: " + toBinaryStr(ans1) + " / 10進数表現: " + ans1);
	}

	//@Test
	public void testToBinary() {
		System.out.println("*** 2進数への変換 ***");
		System.out.println("0.625 => " + target.toBinaryString(0.625));
		System.out.println("10.625 => " + target.toBinaryString(10.625));
		// 循環小数なので変換不可能
		//System.out.println("11.235 => " + target.toBinaryString(11.235));
	}

	/** 10進数への変換 */
	//@Test
	public void testToDecimal() {
		System.out.println("*** 10進数への変換 ***");
		System.out.println("10 => " + target.toDecimalString("10"));
		System.out.println("11 => " + target.toDecimalString("11"));
		System.out.println("100 => " + target.toDecimalString("100"));
		System.out.println("101 => " + target.toDecimalString("101"));
		System.out.println("1101 => " + target.toDecimalString("1101"));
	}

	//@Test
	public void testByte() {
		byte maxByte = 127;
		System.out.println("MAX: " + toBinaryStr(maxByte));
	}

	//@Test
	public void binaryOperation_BitSet() {
		// 1010の４ビット
		BitSet bitSet = new BitSet(4);
		for (int i = 0; i < 4; i++) {
			if (i % 2 == 0) {
				bitSet.set(i);
			}
			System.out.println("bitSet: " + bitSet.get(i));
		}
		System.out.println("length: " + bitSet.length());
		System.out.println("size: " + bitSet.size());
		System.out.println("toString: " + bitSet.toString());
		System.out.println("hashCode: " + bitSet.hashCode());
		System.out.print("IntStrame: ");
		bitSet.stream().forEach(b -> {
			System.out.print(b);
		});
		System.out.println();
		System.out.println("sum: " + bitSet.stream().sum());
	}

	// @Test
	public void testBitSetOperation() {
		// boolean配列を使用する
		ByteBuffer buf = ByteBuffer.allocate(4);
		buf.put((byte)0b1010);
		BitSet bin = BitSet.valueOf(buf);

	}
	//@Test
	public void testToBinary_Theory1() {
		// 2進数 10の場合
		BitSet set = new BitSet(4);
		set.set(2, true);		
		System.out.println("ANS1: " + target.convertBitToInt(set));
		// 1010の場合
		set.set(4, true);		
		System.out.println("ANS2: " + target.convertBitToInt(set));

		// 101の場合
		BitSet set2 = new BitSet(4);
		set2.set(1, true);
		set2.set(3, true);
		System.out.println("ANS3: " + target.convertBitToInt(set2));

	}

	@Test
	public void testRsinsuToInt() {
		System.out.println("2進数で101 = " + target.convertRsinsuToInt("101", 2));
		System.out.println("8進数で101 = " + target.convertRsinsuToInt("101", 8));
		System.out.println("16進数で101 = " + target.convertRsinsuToInt("10F", 16));
	}
}
