package jp.zenryoku.sample.daily;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArraysSample {
    private String[] TMP = new String[] {"aaa", "bbb", "ccc", "ddd"};
    private String[] BARA = new String[] {"ceee", "sbbb", "kdd", "gaa"};
    private int[] NUMS = {1,2,3,4,5,6,7,8,9,10};

    private void print(String[] arr) {
        for (String s : arr) {
            System.out.println(s);
        }
    }

    @Test
    public void testAsList() {
        Arrays.asList(TMP)
                .forEach(System.out::println);
    }


    @Test
    public void testBinSearch() {
        int result = Arrays.binarySearch(TMP
                , "ddd");
        assertEquals(3, result);
    }

    @Test
    public void testCopyOf() {
        String[] arr = Arrays.copyOf(TMP, 2);
        for (String s: arr) {
            System.out.println(s);
        }
    }

    @Test
    public void testCopyOfRange() {
        String[] arg = Arrays
                .copyOfRange(TMP, 2, 3);
        for (String s : arg) {
            System.out.println(s);
        }
    }

    @Test
    public void testFill() {
        String[] arg = new String[3];
        Arrays.fill(arg, "jjj");
        for (String s : arg) {
            System.out.println(s);
        }
    }
    @Test
    public void testPaalla() {
        Arrays.parallelPrefix(TMP, (str1, str2) ->{
            System.out.println(str1 + " : " + str2);
            return str1 + str2;
        });
    }

    @Test
    public void testPaaSort() {
        print(BARA);
        Arrays.parallelSort(BARA);
        System.out.println("*******");
        print(BARA);
    }

    @Test
    public void testSetAll() {
        int[] arr = new int[10];
        Arrays.setAll(arr, a ->a);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    @Test
    public void testSort() {
        Arrays.sort(BARA);
        for (String s : BARA) {
            System.out.println(s);
        }
    }

    @Test
    public void testStream() {
        Arrays.stream(TMP)
                .forEach(System.out::println);
    }

    @Test
    public void testSort2() {
        print(BARA);
        System.out.println("******");
        Arrays.sort(BARA, 2, 4);
        print(BARA);

    }
}
