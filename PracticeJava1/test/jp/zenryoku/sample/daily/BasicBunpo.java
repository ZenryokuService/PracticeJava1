package jp.zenryoku.sample.daily;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class BasicBunpo {

    public static final int WIN = 2;
    public static final int LOOSE = 1;
    public static final int AIKO = 0;

    private static final int GU = 0;
    private static final int CHOKI = 1;
    private static final int PA = 2;
    private String name;
    public static void main(String[] args) {
        BasicBunpo main = new BasicBunpo();

        Scanner sc = new Scanner(System.in);
        /////////////////////////////
        // じゃんけんゲームの処理を開始 //
        /////////////////////////////
        // 1.じゃんけんゲームを開始することを標準出力に出力
        main.printJankenHead();
        // 2.ユーザーの手を受け取る
        String in = sc.nextLine();
        // 3.入力値が１文字かつ、0-2の範囲、それ以外はエラーのチェックを行う
        if (main.okLength(in) == false
                && main.isNumber(in) == false) {
            System.out.println("府定説な入力です: " + in);
            System.exit(-1);
        }
        // 4.CPUの手を乱数で生成
        Random rnd = new Random();
        int cupTe = rnd.nextInt(3);
System.out.println("CPU; " + cupTe);
        // 5.じゃんけんの勝敗判定アルゴリズムで判定処理
        int userTe = Integer.parseInt(in);
        int res = main.judge(userTe, cupTe);
        // 6.勝敗結果の表示
        if (res == WIN) {
            System.out.println("ユーザーの勝ち");
        } else if (res == LOOSE) {
            System.out.println("ユーザーの負け");
        } else if (res == AIKO){
            System.out.println("あいこ");
        } else {
            System.out.println("想定外の値です。");
            System.exit(-1);
        }
    }

    /** じゃんけんゲームを開始する文章 */
    public void printJankenHead() {
        System.out.println("じゃんけんゲームです。");
        System.out.println("*******************");
        System.out.println(" グー　： 0");
        System.out.println(" チョキ： 1");
        System.out.println(" パー　： 2");
        System.out.println("*******************");
    }
    public void setName(String name) {
        this.name = name;
    }
    public void printName() {
        System.out.println("Name; " + name);
    }

    public boolean okLength(String str) {
        return str.length() == 1;
    }

    public int judge(int myTe, int cpuTe) {
        return (myTe - cpuTe + 3) % 3;
    }

    public boolean isNumber(String num) {
        if (num == null) {
            return false;
        }
        return num.matches("[0-9]");
    }

    @Test
    public void test1() {
        assertTrue(okLength("1"));
        assertFalse(okLength("12"));
    }

    @Test
    public void test2() {
        Random rnd = new Random();
        int res = rnd.nextInt(3);
        for (int i = 0; i < 10; i++) {
            if (res > 3) {
                fail();
            }
        }
    }
    @Test
    public void test3() {
        assertEquals(WIN, judge(GU, CHOKI));
        assertEquals(LOOSE, judge(PA, CHOKI));
        assertEquals(AIKO, judge(GU, GU));
    }

    @Test
    public void test4() {
        assertTrue(isNumber("1"));
        assertFalse(isNumber("a"));
        assertFalse(isNumber("12"));
        assertFalse(isNumber(""));
        assertFalse(isNumber(null));

    }

}