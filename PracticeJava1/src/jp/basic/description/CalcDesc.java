package jp.basic.description;

/** 計算処理の説明クラス */
public class CalcDesc {
    public static void main(String[] args) {
        int left = 1;
        int right = 2;
        // 足し算
        System.out.println("left + right = " + (left + right));
        System.out.println("かっこ外して、left + right = " + left + right);

        // 引き算
        System.out.println("left - right = " + (left - right));

        // 掛け算
        System.out.println("left * right = " + (left * right));

        System.out.println("left / right = " + (left / right));

        int[] numArray = new int[] {3, 2, 1, 0};
        String[] strArray = new String[] {"あああ", "aaa", "bbb", "おおお"};

        for (int i = 0; i < 4; i++) {
            int val = numArray[i];
            System.out.println(val + "番目の値: " + strArray[val]);
        }

    }
}
