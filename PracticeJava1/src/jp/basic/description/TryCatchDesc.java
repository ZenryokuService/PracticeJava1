package jp.basic.description;

/** 例外処理の説明クラス */
public class TryCatchDesc {
    public static void main(String[] args) {
        int ans = 2 * 3; // 掛算は「アスタリスク」を使用します。
        System.out.println(ans);
        int warizan1 = 3 / 2; // 処理の結果がdouble型のためエラーになります。
        System.out.println(warizan1);
        double warizan2 = 3 / 2; // エラーになりません。
        System.out.println(warizan2);
    }
}
