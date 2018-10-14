package jp.zenryoku.javadoc.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class JavaDocURL {
    public static void main(String[] args) {
        URL urlCls = createURL();

    }

    private static URL createURL() {
        String targetUrl = "http://zenryokuservice.com/wp/";
        URL url = null;
        URLConnection connect = null;
        try {
            url = new URL(targetUrl);
            connect = url.openConnection();
            connect.setRequestProperty("User-Agent", "Mozilla/5.0");
            // 読み込みの設定
            connect.setDoInput(true);
            BufferedReader read = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            sysoutReader(read);
        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException ie) {
            ie.printStackTrace();
        }
        return url;
    }
    private static void sysoutReader(BufferedReader buf) throws IOException{
        String line = "";
        List<String> list = new ArrayList<String>();
        while((line = buf.readLine()) != null) {
            boolean isLink = line.indexOf("href") >= 0;
            if (isLink && list.contains(line) == false) {
                String html = line.replaceAll(" ", "");
                String st = getUrlFromHtml(html);
                list.add(st);
            }
        }
        list.forEach(System.out::println);
    }

    private static String getUrlFromHtml(String htmlLine) {
        String target = "";
        int hrefPoint = 0;
        int endTagPoint = 0;
        try {
            hrefPoint = htmlLine.indexOf("http://") >= 0 ? htmlLine.indexOf("http://") : htmlLine.indexOf("https://");
            endTagPoint = htmlLine.indexOf("'", hrefPoint) >= 0 ? htmlLine.indexOf("'", hrefPoint) : htmlLine.indexOf("\"", hrefPoint);
            System.out.println("Start: " + hrefPoint + " End: " + endTagPoint);
            target = htmlLine.substring(hrefPoint, endTagPoint);
        } catch(Exception e) {
            System.out.println("Error in: " + htmlLine);
            System.exit(-1);
        }
//        System.out.println(target);
        return target;
    }
}
