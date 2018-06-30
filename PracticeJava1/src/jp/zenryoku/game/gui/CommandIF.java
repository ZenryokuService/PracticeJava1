package jp.zenryoku.game.gui;

import javafx.scene.control.TextArea;

/**
 * コマンド画面で入力したコマンドを実装するインターフェース<BR/>
 * 1. このインターフェースを実装するクラスを追加する
 * 2. CmdCls.propertiesにコマンドと追加した完全クラス名を追加する<BR/>
 *
 * @see jp.zenryoku.game.gui.CmdView
 *
 */
public interface CommandIF {
    public abstract String execute();
}
