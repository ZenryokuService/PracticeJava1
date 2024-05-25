package jp.basic.description;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MtLabelTest {
    private static MtLabel target;

    @BeforeAll
    public static void init() {
        target = new MtLabel("Test");
    }
    @AfterAll
    public static void terminated() {
        target = null;
    }

    @Test
    public void testAddStack() {
        //target.addStack(1);
    }
}
