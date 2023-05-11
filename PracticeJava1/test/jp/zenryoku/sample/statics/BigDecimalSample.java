package jp.zenryoku.sample.statics;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BigDecimalSample {

    private void tashizan(BigDecimal left, BigDecimal right) {
        left.setScale(1);
        right.setScale(1);
        BigDecimal res = left.add(right);
        res.setScale(1);
        System.out.println(res.toString());
    }

    private void hikizan(BigDecimal left, BigDecimal right) {
        BigDecimal res = left.subtract(right);
        System.out.println(res.toString());
    }

    private void kakezan(BigDecimal left, BigDecimal right) {
        BigDecimal res = left.multiply(right);
        System.out.println(res.toString());
    }

    private void warizan(BigDecimal left, BigDecimal right) {
        BigDecimal setScale = left.setScale(2);
        BigDecimal res = setScale.divide(right, BigDecimal.ROUND_HALF_UP);
        System.out.println(res.toString());
    }

    private void print(BigDecimal big) {
        System.out.println(big.toString());
    }

    private void warizanJoyo(BigDecimal left, BigDecimal right) {
        BigDecimal setScale = left.setScale(2);
        BigDecimal[] res = setScale.divideAndRemainder(right);
        System.out.println("商: " + res[0].toString() + " 余り: " + res[1].toString());
    }

    private void compare(String mes, BigDecimal left, BigDecimal right) {
        System.out.println(mes + " : " + left.compareTo(right));
    }

    @Test
    public void testAbs() {
        BigDecimal one = new BigDecimal(-1);
        System.out.print(one.abs());
    }

    @Test
    public void testAdd() {
        BigDecimal one = new BigDecimal(1);
        System.out.println(one.add(one));
        byte b = new BigDecimal(0.3).byteValueExact();
    }

    @Test
    public void testCom() {
        compare("1 : 2"
                ,new BigDecimal(1)
                , new BigDecimal(2));
        compare("2 : 1"
                ,new BigDecimal(2)
                , new BigDecimal(1));
        compare("1 : 1"
                ,new BigDecimal(1)
                , new BigDecimal(1));
    }

    @Test
    public void testDiv() {
        warizanJoyo(new BigDecimal(1)
            ,new BigDecimal(3));
    }

    @Test
    public void testDivJoyo() {
        warizanJoyo(new BigDecimal(2)
                , new BigDecimal(5));
    }

    @Test
    public void testIntDouble() {
        System.out.println(new BigDecimal(1).intValue());
        System.out.println(new BigDecimal(1.0).doubleValue());
    }

    @Test
    public void testMax() {
        System.out.println(new BigDecimal(1)
                .max(new BigDecimal(2)));
    }

    @Test
    public void testMin() {
        System.out.println(new BigDecimal(1)
                .min(new BigDecimal(2)));
    }

    @Test
    public void testMove() {
        print(new BigDecimal(1).movePointLeft(1));
    }

    @Test
    public void testMoveRight() {
        print(new BigDecimal(1).movePointRight(1));
    }

    @Test
    public void testKakezan() {
        kakezan(new BigDecimal(2)
                , new BigDecimal(3));
    }

    @Test
    public void testPow() {
        System.out.println(
                new BigDecimal(2).pow(2)
        );
    }

    @Test
    public void testSetScale() {
        BigDecimal n = new BigDecimal(2.001);
        BigDecimal nn = n.setScale(3
                , BigDecimal.ROUND_HALF_UP);

        print(n);
        print(nn);
    }

    @Test
    public void testSigNum() {
        System.out.println(new BigDecimal(2).signum());
        System.out.println(new BigDecimal(-2).signum());
        System.out.println(new BigDecimal(0).signum());
    }
}
