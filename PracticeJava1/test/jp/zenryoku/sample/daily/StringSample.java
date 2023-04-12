package jp.zenryoku.sample.daily;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringSample {
    /** デフォルトコンストラクタ */
    public void init() {
    }

    @Test
    public void test01() {
        char a = "It's been a hard days night.".charAt(10);
        System.out.println("変数 a = " + a);
        assertEquals('a', a);
    }

    @Test
    public void textConcat() {
        String left = "abc";
        String right = "def";
        String ans = left.concat(right);
        System.out.println(ans);
        assertEquals("abcdef", ans);
    }
}
