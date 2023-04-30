package jp.zenryoku.sample.daily;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ConcurrentHashMapSample {

    private ConcurrentHashMap<String, String> map;


    @BeforeEach
    public void init() {
        map = new ConcurrentHashMap<>();
        map.put("1", "AAA");
        map.put("2", "BBB");
        map.put("3", "CCC");
    }

    private void print() {
        map.forEach((key, val) -> {
            System.out.println("Key: " + key + " | Value: " + val);
        });
    }

    private void print(Map<String, String> pmap) {
        pmap.forEach((key, val) -> {
            System.out.println("Key: " + key + " | Value: " + val);
        });
    }

    @Test
    public void testGet() {
        System.out.println(map.get("1"));
        System.out.println(map.get("2"));
        System.out.println(map.get("3"));
    }

    @Test
    public void testClear() {
        print();
        System.out.println("-----");
        map.clear();
        assertEquals(0, map.size());
        print();
    }

    @Test
    public void testContains() {
        assertTrue(map.containsKey("1"));
        assertFalse(map.containsKey("VV"));
    }

    @Test
    public void testContainsValue() {
        assertTrue(map.containsValue("AAA"));
        assertFalse(map.containsValue("ZZZ"));
    }

    @Test
    public void testForEachKey() {
        map.forEachKey(Long.MAX_VALUE, key -> System.out.println(key));
        HashSet<String> set = new HashSet<>();
    }

}
