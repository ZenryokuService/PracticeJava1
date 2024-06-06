package jp.zenryoku.sample.daily;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CollectionsSample {
    private Map<String, String> map;
    private Map<String, Boolean> boolMap;
    private List<String> list;
    private Set<String> set;

    public CollectionsSample() {
        map = new HashMap<>();
        map.put("1", "aaa");
        map.put("2", "bbb");
        map.put("3", "ccc");
        list = new ArrayList<>();
        list.add("ddd");
        list.add("eee");
        list.add("fff");
        set = new HashSet<>();
        set.add("ggg");
        set.add("hhh");
        set.add("iii");
        boolMap = new HashMap<>();
        boolMap.put("jjj", true);
        boolMap.put("kkk", false);
        boolMap.put("lll", true);
    }

    private void printMap() {
        map.forEach((key, val) -> {
            System.out.println("key: " + key + " : Value: " + val);
        });
    }

    private void printList() {
        for (String s : list) {
            System.out.println(s);
        }
    }

    private void print(Collection<String> c) {
        for (String s : c) {
            System.out.println(s);
        }
    }
    @BeforeEach
    public void init() {
        new CollectionsSample();
    }


    @Test
    public void testAddAll() {
        List<String> list1 = new ArrayList<>();
        list1.add("1235");
        list1.addAll(list);

        print(list1);
    }
    @Test
    public void testMin() {
        String res = Collections.min(set);
        System.out.println(res);
    }

    @Test
    public void testMax() {
        String res = Collections.max(set);
        System.out.println(res);
    }

    @Test
    public void testReplaceAll() {
        Collections.replaceAll(list
                , "ddd", "SSS");
    }
    @Test
    public void testRplaceAll() {
        Collections.replaceAll(list
                , "eee", "AAA");
        list.forEach(System.out::println);
    }


    @Test
    public void testReverse() {
        print(list);
        Collections.reverse(list);
        print(list);
    }

    @Test
    public void testRotate() {
        printList();
        for (int i = 0; i < 3; i++) {
            Collections.rotate(list, 1);
            printList();
        }
    }

    @Test
    public void testShuffle() {
        printList();
        Collections.shuffle(list);
        printList();
    }

    @Test
    public void testSwap() {
        printList();
        Collections.swap(list, 0,2);
        printList();
    }

}
