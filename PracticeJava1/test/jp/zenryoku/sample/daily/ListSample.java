package jp.zenryoku.sample.daily;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListSample {
    public List<String>[] listArray() {
        List<String>[] arr = new List[2];
        arr[0] = arrayList();
        arr[1] = linkedList();
        return arr;
    }

    public List<String> arrayList() {
        List<String> list1 = new ArrayList<>();
        list1.add("aaa");
        list1.add("bbb");
        list1.add("ccc");

        return list1;
    }

    public List<String> linkedList() {
        List<String> list2 = new LinkedList<>();
        list2.add("AAA");
        list2.add("BBB");
        list2.add("CCC");

        return list2;
    }

    private void printList(List<String> list) {
        list.forEach(System.out::println);
    }

    @Test
    public void testList() {
        List<String> list1 = new ArrayList<>();
        list1.add("aaa");
        list1.add("bbb");
        list1.add("ccc");
        list1.forEach(System.out::println);
        System.out.println("*******");
        List<String> list2 = new LinkedList<>();
        list2.add("AAA");
        list2.add("BBB");
        list2.add("CCC");
        list2.forEach(System.out::println);
    }

    @Test
    public void testForEach() {
        List<String> list = arrayList();
        list.forEach(System.out::println);

        list.forEach(val -> System.out.println(val));
    }

    @Test
    public void testAddAll() {
        List<String> listA = arrayList();
        List<String> listB = linkedList();

        listA.addAll(listB);

        printList(listA);
    }

    @Test
    public void testCleaer() {
        List<String> list = arrayList();
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testIsEmpty() {
        List<String> listA = arrayList();
        List<String> listB = linkedList();
        listA.clear();

        assertTrue(listA.isEmpty());
        assertFalse(listB.isEmpty());
    }

    @Test
    public void testRemove() {
        List<String> list = arrayList();
        list.remove("bbb");
        list.forEach(System.out::println);
    }

    @Test
    public void testSet() {
        List<String> list = arrayList();
        list.set(1, "ABC");

        list.forEach(System.out::println);
    }

    @Test
    public void testSize() {
        List<String> list = arrayList();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.size());
            list.remove(list.size() - 1);
        }
    }
}
