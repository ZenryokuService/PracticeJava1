package jp.zenryoku.apps;

import javafx.scene.control.TextArea;
import jp.zenryoku.game.gui.CommandIF;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * このクラスは、指定したURLへのアクセスをします。<BR/>
 *
 */
public class UrlAccessor implements CommandIF {
    @Override
    public String execute() {
        String targetUrl = "http://zenryokuservice.com/wp/2018/06/30/java-basic-gui%E4%BD%9C%E6%88%90%E3%80%9C%E4%BB%8A%E3%81%BE%E3%81%A7%E3%81%AE%E3%81%BE%E3%81%A8%E3%82%81%E3%80%9C/";

        try {
            URL urlCls = new URL(targetUrl);
            urlCls.openConnection();
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException ie) {
            ie.printStackTrace();
        }
        return "";
    }
}
