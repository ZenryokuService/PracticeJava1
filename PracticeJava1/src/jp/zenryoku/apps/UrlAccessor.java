package jp.zenryoku.apps;

import javafx.scene.control.TextArea;
import jp.zenryoku.game.gui.CommandIF;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * このクラスは、指定したURLへのアクセスをします。<BR/>
 *
 */
public class UrlAccessor implements CommandIF {
    @Override
    public String execute() {
        String targetUrl = "https://ja.wikipedia.org/wiki/%E6%AD%A6%E5%99%A8";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
            URL urlCls = new URL(targetUrl);
            urlCls.openConnection();
            InputStream inp = (InputStream) urlCls.getContent();
            Document html = builder.parse(inp);
            NodeList nodes = html.getElementsByTagName("");
            for (int i = 0; i < nodes.getLength(); i++) {
                loopPrint(nodes.item(i).getChildNodes());
            }

        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch(IOException ie) {
            ie.printStackTrace();
        } catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "";
    }
    
    private boolean hasChild(Node node) {
    	return node.hasChildNodes();
    }
    private void loopPrint(NodeList list) {
		String emp = System.getProperty("separator") + "\t\t";
    	for (int i = 0; i < list.getLength(); i++) {
    		Node node = list.item(i);
    		if (hasChild(node)) {
    			loopPrint(node.getChildNodes());
    		} else {
    			if ("#text".equals(node.getNodeName())) {
    				System.out.println("NodeName: " + node.getNodeName() + " -> Value: "+ node.getNodeValue());
    			}
    		}
    	}
    }
    public static void main(String[] args) {
    	UrlAccessor main = new UrlAccessor();
    	main.execute();
    }
}
