package jp.zenryoku.apps;

import javafx.scene.control.TextArea;
import jp.zenryoku.game.gui.CommandIF;

/**
 * このクラスは、指定したURLへのアクセスをします。<BR/>
 *
 */
public class UrlAccessor implements CommandIF {
    @Override
    public String execute() {
        System.out.println("Hello Command Interface!!");
        return "";
    }
}
