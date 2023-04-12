package jp.basic.description;

/**
 * 変数の説明用のクラス。
 */
public class VariableDisc {
    public static void main(String[] args) {
        // 論理積
        System.out.println("*** 論理積 ***");
        boolean b1 = true & false;
        System.out.println("真 AND 偽 = " + toJapanese(b1));
        boolean b2 = true & true;
        System.out.println("真 AND 真 = " + toJapanese(b2));
        boolean b3 = false& false;
        System.out.println("偽 AND 真 = " + toJapanese(b3));
        boolean b4 = false & false;
        System.out.println("偽 AND 偽 = " + toJapanese(b4));

        // 論理和
        System.out.println("*** 論理和 ***");
        boolean b5 = true | false;
        System.out.println("真 OR 偽 = " + toJapanese(b5));
        boolean b6 = true | true;
        System.out.println("真 OR 真 = " + toJapanese(b6));
        boolean b7 = false | false;
        System.out.println("偽 OR 真 = " + toJapanese(b7));
        boolean b8 = false | false;
        System.out.println("偽 OR 偽 = " + toJapanese(b8));

        int[] na = new int[]{1,2,3,4};
        double[] da = new double[]{0.1,0.2,0.3};
        String[] sa = new String[]{"aaa","bbb","ccc"};
    }

    public static String toJapanese(boolean b) {
        if (b) {
            return "真";
        } else {
            return "偽";
        }
    }
}
