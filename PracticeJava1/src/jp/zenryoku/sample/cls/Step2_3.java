package jp.zenryoku.sample.cls;

import java.util.ArrayList;
import java.util.List;

public class Step2_3 {
	/** プレイヤー */
	private Player player;
	/** プレイヤーのリスト */
	private List<Player> list;

	/**
	 * プレイヤーを表すインターフェース
	 * ・勇者、戦士など好きな職業クラスに実装する
	 * ・インターフェースに定義されているメソッドは中身がない
	 * 　のでそれを実装する必要がある
	 * @author takunoji
	 */
	private interface Player {
		/** 通常攻撃を示す定数 */
		public static final String USE_ARMS = "amrs_atack";
		/** 魔法攻撃を示す定数 */
		public static final String USE_MAGIC = "magic_atack";

		/**
		 * 攻撃コマンドで実行する
		 * @param 攻撃方法
		 * @return 攻撃時の威力
		 */
		public abstract int atack(String say);
		/**
		 * 防御時の防御する
		 * @return 防御力
		 */
		public abstract int defence();
		/** 名前を取得する(上記のメソッドと同じ意味) */
		public String getName();
	}
	/**
	 * 勇者を表すクラス
	 * インナークラスクラスの中に作成するクラス
	 * あまり使わない。。。
	 * @author takunoji
	 */
	private class Yusha implements Player {
		/** 名前 */
		private String name;
		/** 年齢 */
		private int age;
		/** 攻撃力 */
		private int atack;
		/** 防御力 */
		private int defence;

		public Yusha(String name) {
			this.setName(name);
			this.setAge(15);
			this.setAtack(1);
			this.setDefence(1);
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public int getAtack() {
			return atack;
		}
		public void setAtack(int atack) {
			this.atack = atack;
		}
		public int getDefence() {
			return defence;
		}
		public void setDefence(int defence) {
			this.defence = defence;
		}

		/**
		 * 攻撃方法を引数のtypeで指定する。
		 */
		@Override
		public int atack(String type) {
			System.out.println(this.getName() + "の攻撃！");
			int power = 0;
			if(USE_ARMS.equals(type)) {
				power = this.getAtack();
			} else if (USE_MAGIC.equals(type)) {
				power = this.getAtack();
			}
			return power;
		}
		@Override
		public int defence() {
			System.out.println(this.getName() + "は身構えている");
			// 年齢を取得して返却する
			return this.getAge();
		}
	}

	private class Monster implements Player {
		public String getName() {
			return "モンスター１";
		}
		@Override
		public int atack(String say) {
			System.out.println(this.getName() + "の攻撃！");
			return 0;
		}

		@Override
		public int defence() {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	public static void main(String[] args) {
		Step2_3 step = new Step2_3();
		step.action();
	}

	/**
	 * コンストラクタ
	 */
	public Step2_3() {
		list = new ArrayList<Player>();
		player = new Yusha("takao");
		list.add(player);
		// 匿名クラスの実装：java8ではよく使う書き方
		// 
		Player player1 = new Player() {
			public String getName() {
				return "町人１";
			}
			@Override
			public int atack(String say) {
				// TODO Auto-generated method stub
				return 0;
			}
			@Override
			public int defence() {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		// 町人を登録する
		list.add(player1);
		// モンスターを登録する
		list.add(new Monster());
	}

	public void action() {
		for(Player player : list) {
			System.out.println("名前: " + player.getName());
			System.out.println("攻撃：" + player.atack(Player.USE_ARMS));
		}
	}
}
