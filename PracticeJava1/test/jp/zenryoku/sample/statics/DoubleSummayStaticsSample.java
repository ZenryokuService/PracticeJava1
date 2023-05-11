package jp.zenryoku.sample.statics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class DoubleSummayStaticsSample {
    /** 男子のテスト結果 */
    private List<Double> mensTestResult;
    /** 女子のテスト結果 */
    private List<Double> womensTestResult;

    private List<Double> sample;

    @BeforeEach
    public void init() {
        mensTestResult = new ArrayList<>();
        // 男子の数は１２人
        mensTestResult.add(60.0);
        mensTestResult.add(70.0);
        mensTestResult.add(50.0);
        mensTestResult.add(80.0);
        mensTestResult.add(40.0);
        mensTestResult.add(90.0);
        mensTestResult.add(30.0);
        mensTestResult.add(100.0);
        mensTestResult.add(20.0);
        mensTestResult.add(97.0);
        mensTestResult.add(23.0);
        mensTestResult.add(60.0);

        // 女子の数は８人
        womensTestResult = new ArrayList<>();
        womensTestResult.add(50.0);
        womensTestResult.add(100.0);
        womensTestResult.add(00.0);
        womensTestResult.add(90.0);
        womensTestResult.add(10.0);
        womensTestResult.add(80.0);
        womensTestResult.add(20.0);
        womensTestResult.add(50.0);

        sample = new ArrayList<>();
        sample.add(5.0);
        sample.add(6.0);
        sample.add(7.0);
        sample.add(7.0);
        sample.add(10.0);
    }

    private void print(DoubleSummaryStatistics stat, List<Double> list) {
        System.out.println("最小: " + stat.getMin());
        System.out.println("最大: " + stat.getMax());
        System.out.println("平均: " + stat.getAverage());
        System.out.println("合計: " + stat.getSum());
        System.out.println("個数: " + stat.getCount());
        // 分散を求める１：平均を計算上記で求まる
        // 平均との差の２累乗：
        double ave = stat.getAverage();
        BigDecimal step2 = new BigDecimal(0);
        for (double d : list) {
            step2 = step2.add(heikintonosa(d, ave));
        }
        System.out.println("step2: " + step2.toString());
        BigDecimal bunsan = step2.setScale(1).divide(new BigDecimal(stat.getCount()), BigDecimal.ROUND_HALF_UP);
        System.out.println("分散: " + bunsan.toString());


    }

    /** 平均との差の２累乗 */
    private BigDecimal heikintonosa(double val, double ave) {
        BigDecimal res = new BigDecimal(val - ave).pow(2);

        System.out.println("(" + val + " - " + ave + ")^2 = " + res.toString());
        return res;
    }

    @Test
    public void test01() {
        // 男子
        DoubleSummaryStatistics dansi = new DoubleSummaryStatistics();
        mensTestResult.forEach(val -> dansi.accept(val));

        System.out.println("** 男子のデータ ***");
        print(dansi, mensTestResult);

        // 女子
        DoubleSummaryStatistics josi = new DoubleSummaryStatistics();
        System.out.println("** 女子のデータ ***");
        womensTestResult.forEach(val -> josi.accept(val));
        print(josi, womensTestResult);

        dansi.combine(josi);
        System.out.println("** 合計データ ***");
        print(dansi, sample);
    }

    @Test
    public void test02() {
        DoubleSummaryStatistics sm = new DoubleSummaryStatistics();
        sample.forEach(val -> sm.accept(val));

        print(sm, sample);
    }
}
