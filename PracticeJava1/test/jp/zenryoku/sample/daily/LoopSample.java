package jp.zenryoku.sample.daily;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LoopSample {
    private static String[] strHako;
    private static int[] intHako;
    private static double[] doubleHako;

    private static final String[] YOBI = {"Mon", "Tue","Wed","Thi","Fri","Sat","Sun" };

    private void printLoop(String[] arr) {
        System.out.println("*** 文字列のループ ***");
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.println("i=" + i + " 配列: " + arr[i]);
            count = i;
        }
        System.out.println("count=" + count);
    }

    private void printLoop(String[] arr, boolean isPrint) {
        String SEP = System.lineSeparator();
        System.out.println("*** 文字列のループ ***");
        if (isPrint) {
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                System.out.println("i=" + i + " 配列: " + arr[i]);
                count = i;
            }
            System.out.println("count=" + count);
        } else {
            StringBuilder build = new StringBuilder();
            for (String s : arr) {
                build.append(s + " ");
            }
            System.out.print(build.toString() + SEP);
        }
    }

    private void printLoop(int[] arr) {
        System.out.println("*** int型のループ ***");
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.println("i=" + i + " 配列: " + arr[i]);
            count = i;
        }
        System.out.println("count=" + count);
    }

    private void printLoop(double[] arr) {
        System.out.println("*** double型のループ ***");
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.println("i=" + i + " 配列: " + arr[i]);
            count = i;
        }
        System.out.println("count=" + count);
    }

    @BeforeAll
    public static void init() {
        strHako = new String[] {"aaa", "bbb", "ccc", "ddd", "eee", "fff"};
        intHako = new int[] {0,1,2,3,4,5};
        doubleHako = new double[]{1.0, 2.0, 3.0, 4.0};
    }

    @Test
    public void testSS1() {
        printLoop(YOBI);
        YOBI[0] = "aaa";
        YOBI[1] = "bbb";

        printLoop(YOBI);
    }

    @Test
    public void testSSS1() {
        String[] arr = new String[2];
        arr[0] = YOBI[0];
        arr[1] = YOBI[3];

        printLoop(arr);
    }
    @Test
    public void testLoop() {
        printLoop(strHako);
        printLoop(intHako);
        printLoop(doubleHako);
    }
    @Test
    public void testFor() {
        for (int i = 0; i < intHako.length; i++) {
            if (i % 2 == 0) {
                System.out.println(intHako[i]);
            }
        }
    }
    @Test
    public void testFor2() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 1) {
                System.out.println(i);
            }
        }
    }

    @Test
    public void testFor4() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                continue;
            }
            System.out.println(i);
        }
    }

    @Test
    public void testWhile() {
        int i = 0;
        while(i < intHako.length) {
            if (i % 2 == 0) {
                System.out.println(intHako[i]);
            }
            i++;
        }
    }

    @Test
    public void testWhile2() {
        int i = 0;
        while(i < YOBI.length) {
            i++;
            if (i % 2 == 0) {
                continue;
            }
            System.out.println(YOBI[i]);
        }
    }

    @Test
    public void testCal() {
        printLoop(YOBI, false);
        for (int i =1; i < 32; i++) {
            System.out.print(String.format("% 4d",i));
            if (i %7==0) {
                System.out.println();
            }
        }
    }

    @Test
    public void testCalWhile() {
        printLoop(YOBI, false);
        int i =1;
        while(i < 32) {
            System.out.print(String.format("% 4d",i));
            if (i %7==0) {
                System.out.println();
            }
            i++;
        }
    }
    @Test
    public void testForEx() {
        for (String st : YOBI) {
            System.out.println(st);
        }
    }
    @Test
    public void testForRev() {
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
        }
    }

    @Test
    public void testArr() {
        String[] arr = new String[7];
        for (int i = 0; i < YOBI.length; i++) {
            if (i % 2 == 0) {
                arr[i] = YOBI[i];
            }
        }
        printLoop(arr);
    }


    @Test
    public void testS1() {
        String[] arr = new String[2];
        printLoop(arr);

        arr[0] = "aaa";
        arr[1] = "bbb";

        System.out.println("*****");
        printLoop(arr);

        String tmp = arr[0];
        arr[0] = arr[1];
        arr[1] = tmp;

        printLoop(arr);
    }
}
