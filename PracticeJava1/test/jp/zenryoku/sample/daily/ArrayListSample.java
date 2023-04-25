package jp.zenryoku.sample.daily;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayListSample {

    private ArrayList<String> list;

    @BeforeEach
    public void init() {
        list = new ArrayList<>();
    }

    @AfterEach
    public void after() { list = null; }

    private void print() {
        for (String st : list) {
            System.out.println(st);
        }
    }


    @Test
    public void testAdd() {
        list.add("その１");
        list.add("その２");
        list.add("その３");
        list.add("その４");

        for (String st : list) {
            System.out.println(st);
        }
    }

    @Test
    public void testAddAll() {
        list.add("aaa");
        List<String> newList = new ArrayList<>();
        newList.add("bbb");
        newList.add("ccc");
        newList.add("eee");

        list.addAll(newList);

        for (String st : list) {
            System.out.println(st);
        }
    }

    @Test
    public void testClear() {
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        System.out.println("first: " + list.size());

        list.clear();
        System.out.println("second: " + list.size());
    }

    @Test
    public void testContains() {
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");

        assertTrue(list.contains("aaa"));
        assertFalse(list.contains("ZZZ"));

    }

    @Test
    public void testForEach() {
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");

        // ラムダ1
        list.forEach(st -> {
            System.out.println(st);
        });

        // ラムダ２
        list.forEach(System.out::println);
    }

    @Test
    public void testGet() {
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");

        assertEquals("ccc", list.get(2));
    }

    @Test
    public void testIndexOf() {
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        assertEquals(1, list.indexOf("bbb"));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.add("aaa");
        System.out.println(list.size());
        assertFalse(list.isEmpty());
    }

    @Test
    public void testIterator() {
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        // イテレータ
        Iterator<String> it = list.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void testSubList() {
        list.add("aaaa");
        list.add("bbb");
        list.add("cc");
        list.add("dddddd");
        list.add("erreeee");

        List<String> sub = list.subList(2, 4);

        for (String s : sub) {
            System.out.println(s);
        }
    }

    @Test
    public void testRemove() {
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.add("eee");

        print();

        list.remove(2);
        System.out.println("*******");

        print();
    }

    @Test
    public void testRemoveIf() {
        list.add("I am Rock!");
        list.add("I am Blues!");
        list.add("I am Jazz!");
        list.add("You are Yellow submarine!");
        list.add("You are Red bull!");
        list.add("You are Beatutiful!");

        print();

        System.out.println("-----------");

        list.removeIf(val -> val.startsWith("I am"));

        print();
    }

    @Test
    public void testSet() {
        list.add("and");
        list.add("or");
        list.add("xor");
        list.add("これらに意味はありません。");
        print();

        System.out.println("---------------");

        list.set(0, "これが追加");

        print();
    }

    @Test
    public void testSort() {
        list.add("567");
        list.add("123");
        list.add("456");
        list.add("234");
        list.add("345");

        print();
        System.out.println("--------------");

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        print();
        System.out.println("--------------");

        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        print();
    }

    @Test
    public void testToArray() {
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");

        Object[] arr = list.toArray();
        for (Object o : arr) {
            System.out.println(o);
        }
    }
}
