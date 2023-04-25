package jp.zenryoku.sample.daily;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class LinkedListSample {
    private LinkedList<String> list;
    @BeforeEach
    public void init() {

        list = new LinkedList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
    }

    private void print() {
        list.forEach(System.out::println);
    }

    @Test
    public void testAddFirst() {

        list.addFirst("First");

        list.forEach(System.out::println);
    }

    @Test
    public void testAddLast() {

        list.addLast("Last");

        list.forEach(System.out::println);

    }

    @Test
    public void testGetFirst() {
        System.out.println(list.getFirst());
    }

    @Test
    public void testGetLast() {
        System.out.println(list.getLast());
    }

    @Test
    public void testPoll() {

        String s = list.poll();

        System.out.println("取得したもの：" + s);

        list.forEach(System.out::println);
    }

    @Test
    public void testPollFirst() {
        System.out.println("First; " + list.pollFirst());

        print();
    }

    @Test
    public void testPollLast() {
        print();

        String st = list.pollLast();
        System.out.println("Last: " + st);

        print();
    }
}
