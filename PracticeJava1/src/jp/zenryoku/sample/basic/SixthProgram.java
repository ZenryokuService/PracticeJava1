/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package jp.zenryoku.sample.basic;

/**
 * @author takunoji
 *
 * 2020/05/30
 */
public class SixthProgram {
	/** 普段使用しないけどインナークラスを作る */
	private static SixthProgram.PrimitiveSample sample;

	/** 
	 * プリミティブ型データ一覧クラス(説明用)
	 * @author takunoji
	 */
	private class PrimitiveSample {
	   /** INTは整数で ±2147483647まで使用可能 */
	   private int population;

	   /** Doubleは小数点を使用する */
	   private double footSize;

	   /** Shortの年齢は　±32767まで使用可能 */
	   private short age;

	   /** BooleanはTRUEかFALSE */
	   private boolean flg;

	   /** Charは一文字 */
	   private char ch;

	   /** １Byteは8Bit(２進数8桁)-> 01010101 = 85 */
	   private byte byt = 0x7F; // １６進表現：１０進数の127

	   /** LongはINTよりも大きい整数 */
	   private long lng;

	   /** FloatはDoubleよりも大きい小数点 */
	   private float flt;

	   /** 
	    * コンストラクタ:この処理を通るとインスタンスができる
	    */
	   public PrimitiveSample() {
	     population = 100;
	     footSize = 24.6;
	     age = 100; // intはより大きな数値を使える
	     flg = false;
	     ch= 'A';
	     byt = 0x7F;
	     lng = 123456;
	     flt = 12345.6f;
	   }

		/**
		 * @return the population
		 */
		public int getPopulation() {
			return population;
		}
	
		/**
		 * @param population the population to set
		 */
		public void setPopulation(int population) {
			this.population = population;
		}
	
		/**
		 * @return the footSize
		 */
		public double getFootSize() {
			return footSize;
		}
	
		/**
		 * @param footSize the footSize to set
		 */
		public void setFootSize(double footSize) {
			this.footSize = footSize;
		}
	
		/**
		 * @return the age
		 */
		public short getAge() {
			return age;
		}
	
		/**
		 * @param age the age to set
		 */
		public void setAge(short age) {
			this.age = age;
		}
	
		/**
		 * @return the flg
		 */
		public boolean isFlg() {
			return flg;
		}
	
		/**
		 * @param flg the flg to set
		 */
		public void setFlg(boolean flg) {
			this.flg = flg;
		}
	
		/**
		 * @return the ch
		 */
		public char getCh() {
			return ch;
		}
	
		/**
		 * @param ch the ch to set
		 */
		public void setCh(char ch) {
			this.ch = ch;
		}
	
		/**
		 * @return the byt
		 */
		public byte getByt() {
			return byt;
		}
	
		/**
		 * @param byt the byt to set
		 */
		public void setByt(byte byt) {
			this.byt = byt;
		}
	
		/**
		 * @return the lng
		 */
		public long getLng() {
			return lng;
		}
	
		/**
		 * @param lng the lng to set
		 */
		public void setLng(long lng) {
			this.lng = lng;
		}
	
		/**
		 * @return the flt
		 */
		public float getFlt() {
			return flt;
		}
	
		/**
		 * @param flt the flt to set
		 */
		public void setFlt(float flt) {
			this.flt = flt;
		}
		   
	}

	public static void main(String[] args) {
		// PrimitiveSampleクラスを使用する
		SixthProgram main = new SixthProgram();
		sample = main.new PrimitiveSample();

		char a1 = sample.getCh();
		System.out.println("char: " + a1);

		String s1 = "abcd";
		char[] a2 = s1.toCharArray();

		for (int i = 0; i < a2.length; i++) {
			System.out.print(a2[i]);
		}
	}
}
