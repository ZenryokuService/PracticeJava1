package jp.zenryoku.game.rpg;

/**
 * Playerキャラクターを表すインターフェース</br>
 * このインターフェースを実装して、Playerクラスを作成する</br>
 * プレイヤー共通の動き(メソッド)を定義する</br>
 * 
 * @author takunoji
 */
public interface Player {
	// 返却値のStringはコンソールに出力する文字列
	/** 話す */
	public String talk();
	/** 調べる */
	public String investigate();
	/** 拾う */
	public String pickup();
	/** 投げる(throwは予約後なので使用不可) */
	public String threw();
	/** 特殊(アビリティなど) */
	public String special();
}
