package jp.zenryoku.sample.daily;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class StringSample {
    private final String[] YOBI = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    /** デフォルトコンストラクタ */
    public void init() {
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("入力してください: ");
        String st = sc.nextLine();
        System.out.println(st);
    }
    @Test
    public void testHashCode() {
        System.out.println("aaa".hashCode());
        System.out.println(new StringSample().hashCode());
    }
    private void printType(Object o) {
        System.out.println(o.getClass().getSimpleName());
    }


    @Test
    public void isInstance() {
        System.out.println("aaa" instanceof String);
        System.out.println("aaa" instanceof Object);
    }

    @Test
    public void testSSS1() {
        int i = 10;
        printType(i);
        printType("12");
        printType(this);
    }
    @Test
    public void testFormat1() {
        System.out.println(String.format("[%10d]", 1234));
        System.out.println(String.format("[%-10d]", 1234));
    }
    @Test
    public void testEnss() {
        String a = "Hello";
        System.out.println(a.endsWith("o"));
        System.out.println(a.endsWith("H"));
    }
    @Test
    public void testStart() {
        String a = "Hello";
        String b = "Baster";
        System.out.println(a.startsWith("H"));
        System.out.println(b.startsWith("H"));
    }

    @Test
    public void testSplit() {
        String st = "a,b,c,d,e";
        String[] a = st.split(",");

        for (String s : a) {
            System.out.println(s);
        }
    }

    @Test
    public void testParse() {
        String a = String.valueOf(12);
        System.out.println(a);
    }
    @Test
    public void testJoin() {
        String res = String.join("-", YOBI);
        System.out.println(res);
    }
    @Test
    public void testTrim1() {
        String s = "    Hello   ";
        System.out.println(s.trim());
    }

    @Test
    public void testFormat2() {
        System.out.println(
                String.format("%tY年%d月%d日"
                , new Date(), 10, 20)
        );
    }
    @Test
    public void testMatch() {
        String st = "Hello World";
        int count = StringUtils.countMatches(
                st, "l");
        System.out.println(count);
    }

    @Test
    public void testIndex() {
        String s = "Hello World";
        System.out.println(s.indexOf("r"));
        System.out.println(
                s.indexOf("l", 5));
        System.out.println(StringUtils.countMatches(s, "l"));
    }
    @Test
    public void testStr2() {
        StringBuilder b = new StringBuilder(
                "Hello");
        System.out.println(b.reverse());
    }
    @Test
    public void testStrSub() {
        String str = "Hello World";
        System.out.println(str);
    }

    @Test
    public void testStr1() {
        String s = "Hello World";
        String conv = StringUtils.swapCase(s);
        System.out.println(s);
        System.out.println(conv);
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

    @Test
    public void testIsString() {
        String str = "abc";
        assertTrue(str.contains("c"));
        assertFalse(str.contains("d"));
    }

    @Test
    public void testEndsWith() {
        String str = "abcde";
        assertTrue(str.endsWith("e"));
        assertFalse(str.endsWith("a"));
    }

    @Test
    public void testEquals() {
        String str = "abc";
        assertTrue("abc".equals(str));
        assertFalse("bbc".equals(str));
    }

    @Test
    public void testFormat() {
        String str = "これは%d円です";
        System.out.println(String.format(
                str, 12
        ));
        String str1 = "いま%tk時です";
        Date date = new Date();
        System.out.println(
                String.format(str1, date));
    }

    @Test
    public void testIndexOf() {
        String str = "あいうえお";
        int res = str.indexOf("え");
        System.out.println(res);
        assertEquals(3, res);
    }

    @Test
    public void testMatchies() {
        String str = "12345";
        // ５桁の数字であればTRUE
        assertTrue(str.matches("[0-9]{5}"));
        // 違うパターン
        String str1 = "123456";
        assertFalse(str1.matches("[0-9]{5}"));
    }

    @Test
    public void testReplace() {
        String str = "abcdefaaa";
        String res = str.replace("a",
                "X");
        System.out.println(res);
    }

    @Test
    public void testSplit2() {
        String str = "aaaa:bbb";
        // ：コロンで分割する
        String[] hako = str.split(":");
        assertEquals("aaaa", hako[0]);
        assertEquals("bbb", hako[1]);
    }

    @Test
    public void testStartsWith() {
        String str = "本日は晴天なり";
        assertTrue(str.startsWith("本"));
        assertFalse(str.startsWith("あああ"));
    }

    @Test
    public void testSubString() {
        String str = "01234567";
        assertEquals("34567", str.substring(3));
        assertEquals("345", str.substring(3, 6));
    }

    @Test
    public void testToCharArray() {
        String str = "01234567";
        char[] chArr = str.toCharArray();
        // 配列長
        assertEquals(8, chArr.length);
        assertEquals('0', chArr[0]);
        assertEquals('1', chArr[1]);
        assertEquals('2', chArr[2]);
        assertEquals('3', chArr[3]);
        assertEquals('4', chArr[4]);
        assertEquals('5', chArr[5]);
        assertEquals('6', chArr[6]);
    }

    @Test
    public void testtoUppercase() {
        String str = "good moorning";
        System.out.println(str.toUpperCase());
    }

    @Test
    public void testtoLowerCase() {
        String str = "Good By See YOU! GAAA";
        System.out.println(str.toLowerCase());
    }

    @Test
    public void testTrim() {
        String str = "    agrerg      ";
        String result = str.trim();
        System.out.println(result);
        assertEquals("agrerg", result);
    }

    @Test
    public void testValueOf() {
        int num = 12;
        double d = 2.3;
        long l = 3L;
        float f = 4.4f;
        assertEquals("12", String.valueOf(num));
        assertEquals("2.3", String.valueOf(d));
        assertEquals("3", String.valueOf(l));
        assertEquals("4.4", String.valueOf(f));
    }
}
