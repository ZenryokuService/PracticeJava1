package jp.zenryoku.sample;

public class Step1_2_1 {
	///////////////////////////////////////////////////////////////////////////////////
	// 2進数の表記方法は「0b〜」
	// → 0b0001 (4bit)
	// → 0b00010001 (8bit)
	// → 0b0001000100010001 (16bit)
	// → 0b00010001000100010001000100010001 (32bit)
	// → 0b0001000100010001000100010001000100010001000100010001000100010001 (64bit)
	///////////////////////////////////////////////////////////////////////////////////
	
	/** 8bitの２進数表記 */
	private byte binary = (byte) 0b00100100;// 10進数では36
	/** 16bitの２進数表記 */
	private short aShort = (short) 0b10100001010001011010000101000101;
	/** 32bitの２進数表記 */
	private int anInt = 0b10100001010001011010000101000101;
	/** 64bitの２進数表記 */
	private long aLong = 0b1010000101000101101000010100010110100001010001011010000101000101L;
	/** 1byte = 8bitの１６進数表記 */
	private byte hexadecimal = 0x1F;// 10進数では31 
	/** double */
	private double aDouble = 1.5;
	/** float 
	 * 10×10^-4 である 0.0010 が格納される
	 * ＜10かける10の「−４乗」＞
	 */
	private float aFloat = 10e-4f;

	/** float
	 * 2x2^3=16
	 */
	private float bFloat = 2e3f;

	/** int 10進数表記*/
	private int integer = 36;

	
	 /**
	 * Java データ型 変数の扱い方〜Step1-2-1〜
	 * @param args
	 */
	public static void main(String[] args) {
		Step1_2_1 step = new Step1_2_1();
		// 各フィールド変数を表示します。
		step.printField();
		// bit演算
		step.operateBit();
	}

	/**
	 * フィールドに定義した各値を表示します。
	 */
	public void printField() {
		System.out.println("8bitの２進数表記: " + binary);
		System.out.println("16bitの２進数表記: " + aShort);
		System.out.println("32bitの２進数表記: " + anInt);
		System.out.println("64bitの２進数表記: " + aLong);
		System.out.println("8bitの16進数表記: " + hexadecimal);
		System.out.println("double型: " + aDouble);
		System.out.println("aFloat: " + Float.floatToIntBits(aFloat));
		System.out.println("aFloat: " + Float.floatToIntBits(bFloat));
		System.out.println("int 整数: " + integer);
	}

	/**
	 * 論理演算に関しては以下のサイトを参照してください
	 * @see http://www.javaroad.jp/java_operator5.htm
	 */
	public void operateBit() {
		// 元々の値を表示する
		System.out.println("*** 元々の値を表示する(左側の「0」は省略される ***");
		System.out.println("２進数(1byte)その1: " + Integer.toString(binary, 2));
		byte binary2 = (byte) 0b01011011;
		// Integer.toString(値, 〜進数) → Integer.toString(5, 2) => 00000101
		System.out.println("２進数(1byte)その2: " + Integer.toString(binary2, 2));
		// 1010 と 0101の各列(右から<0,1> <1,0> <0,1> <1,0>をそれぞれ論理演算する
		// 1 and 0 = 0, 1 and 1 = 1, 0 and 0 = 0となる
		System.out.println("２進数その1 and その2 = " + Integer.toString(0b1010 & 0b0101, 2));
		// 1 or 1 = 1, 1 or 0 = 1, 0 or 1 = 0, 0 or 0 = 0
		System.out.println("２進数その1 or その2 = " + Integer.toString(0b1010 | 0b0101, 2));
		System.out.println("２進数その1 xor その2 = " + Integer.toString(0b1010 ^ 0b0101, 2));
		System.out.println("２進数その1 not その2 = " + Integer.toString(~0b1010, 2));
	}
}
