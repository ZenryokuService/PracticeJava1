package jp.zenryoku.sample.daily;

import org.junit.jupiter.api.Test;

import java.util.StringTokenizer;

public class StringTokenizerSample {

    @Test
    public void testToken() {
        StringTokenizer token = new StringTokenizer(
                "I am a hero");
        while (token.hasMoreTokens()) {
            System.out.println(token.nextToken());
        }
    }
}
