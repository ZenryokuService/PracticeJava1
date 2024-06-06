package jp.basic.description;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class MtLabelTest {
    private static MtLabel target;

    @BeforeAll
    public static void init() { target = new MtLabel("Test");}
    @AfterAll
    public static void terminated() {
        target = null;
    }
    @BeforeEach
    public void initTest() {
        target = new MtLabel("TTT");
    }
    @AfterEach
    public void terminatedTest() {
        target = null;
    }

    @Test
    public void testToNumber() {
        assertEquals(1, target.toNumber("1"));
        assertEquals(2, target.toNumber("2"));
        assertEquals(3, target.toNumber("3"));
        assertEquals(-1, target.toNumber("-1"));
    }

    @Test
    public void testExeCalc() {
        // なにもセットしていない状態
        assertFalse(target.exeCalc());
        // ラベルの数字(文字)のみをセットしている
        target.setText("1");
        assertFalse(target.exeCalc());
        // ラベルに数字、演算子がある状態
        target.setOpe("*");
        assertTrue(target.exeCalc());
    }
    @Test
    public void testCalc() {
        target.setText("1");
        target.setOpe(MtButton.PLUS);
        assertEquals(2, target.calc("1"));
        target.setOpe(MtButton.MINUS);
        assertEquals(0, target.calc("1"));
        target.setOpe(MtButton.MULTIPLE);
        assertEquals(1, target.calc("1"));
        target.setOpe(MtButton.DIVIDE);
        assertEquals(1, target.calc("1"));
    }
}
