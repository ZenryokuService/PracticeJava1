package jp.basic.description;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MtButtonTest {
    private static MtButton target;
    @BeforeAll
    public static void init() {
    target = new MtButton("Test", new MtLabel("Hello Goo"));
    }
    @AfterAll
    public static void terminated() {
        target = null;
    }

    @Test
    public void testIsOpe() {
        assertTrue(target.isOpe(MtButton.PLUS));
        assertTrue(target.isOpe(MtButton.MINUS));
        assertTrue(target.isOpe(MtButton.MULTIPLE));
        assertTrue(target.isOpe(MtButton.DIVIDE));
        assertFalse(target.isOpe(null));
        assertFalse(target.isOpe("1"));
        assertFalse(target.isOpe("A"));
    }
    @Test
    public void testIsNumber() {
        assertTrue(target.isNumber("1"));
        assertFalse(target.isNumber(""));
        assertFalse(target.isNumber("12"));
        assertFalse(target.isNumber(null));
    }
}
