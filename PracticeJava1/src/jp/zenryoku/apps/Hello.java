package jp.zenryoku.apps;

import jp.zenryoku.game.gui.CommandIF;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Hello implements CommandIF {
    public String execute() {
        StringBuilder response = new StringBuilder();
        System.out.println("*** Testing ***");
        try {
            BufferedReader read =  Files.newBufferedReader(Paths.get("resources/title.txt"), Charset.forName(System.getProperty("file.encoding")));
            String line = "";
            while((line = read.readLine()) != null) {
                response.append(line);
                response.append(System.getProperty("line.separator"));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }
}
