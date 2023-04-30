package jp.zenryoku.sample.daily;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapSample {
    private Map<String, String> map;


    @BeforeEach
    public void init() {
        map = new HashMap<>();
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
        System.out.println(map.get("4"));
    }

    @Test
    public void testClear() {
        print();
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    public void testContainsKey() {
        assertTrue(map.containsKey("1"));
        assertFalse(map.containsKey("aaa"));
    }

    @Test
    public void testContainsValue() {
        assertTrue(map.containsValue("AAA"));
        assertFalse(map.containsValue("1"));
    }

    @Test
    public void testGetOrDefault() {
        System.out.println("Key: " + map.get("1"));
        System.out.println("D: "
                + map.getOrDefault("VVV", "Test"));
    }

    @Test
    public void testIsEmpty() {
        System.out.println("Map中身あり：" + map.isEmpty());
        map.clear();
        System.out.println("Map中身なし：" + map.isEmpty());
    }

    @Test
    public void testkeySet() {
        map.keySet().forEach(System.out::println);
    }

    @Test
    public void testPutAll() {
        HashMap<String, String> mm = new HashMap<>();
        mm.putAll(map);
        print(mm);
    }

    @Test
    public void testValues() {
        map.values().forEach(System.out::println);
    }
}
