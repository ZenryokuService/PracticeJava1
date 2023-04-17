package jp.zenryoku.sample.daily;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringBuilderSample {

    private String[] hako = {"ABC", "DEF", "GHI", "JKL", "MNO"};

    @Test
    public void testAppend() {
        StringBuilder build = new StringBuilder();
        for (String st : hako) {
            build.append(st);
        }
        String result = build.toString();
        System.out.println(result);
        assertEquals("ABCDEFGHIJKLMNO", result);
    }

    @Test
    public void testDelete() {
        StringBuilder build = new StringBuilder("0123456789");
        StringBuilder st = build.delete(3, 7);
        System.out.println(st.toString());
    }

    @Test
    public void testDeleteCharAt() {
        StringBuilder build = new StringBuilder("01234");
        StringBuilder b = build.deleteCharAt(2);
        System.out.println(b.toString());
    }

    @Test
    public void testGetChars() {
        StringBuilder build = new StringBuilder("01234567");
        int start = 3;
        int end = 7;
        // 配列長８の配列を作成
        char[] dst = new char[end + 1];
        dst[0] = 'A';
        dst[1] = 'B';
        dst[2] = 'C';
        build.getChars(start, end, dst, 3);
        dst[7] = 'D';

        for (char d : dst) {
            System.out.print(d);
        }
    }
}
