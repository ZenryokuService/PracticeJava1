package jp.basic.description;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SampleClassTest {
    private static SampleClass target;

    @BeforeAll
    public static void init() {
        target = new SampleClass();
    }
    @AfterAll
    public static void terminated() { target = null; }


    @Test
    public void test() {
        //assertTrue(target.isNumber("1"));
    }
}
