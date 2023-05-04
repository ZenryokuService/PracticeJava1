package jp.zenryoku.sample.daily;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HashSetSample {

    private HashSet<String> set;

    @BeforeEach
    public void init() {
        set = new HashSet<>();
        set.add("aaa");
        set.add("bbb");
        set.add("ccc");
    }

    @Test
    public void testAdd() {
        set.add("aaa");
        set.add("bbb");

        set.forEach(System.out::println);
    }

    @Test
    public void testIsEpm() {
        assertFalse(set.isEmpty());
        set.clear();
        assertTrue(set.isEmpty());
    }

    @Test
    public void testContains() {
        assertTrue(set.contains("aaa"));
        assertFalse(set.contains("ZZZ"));
    }

    @Test
    public void testRemove() {
        set.remove("bbb");
        set.forEach(System.out::println);
    }
}
