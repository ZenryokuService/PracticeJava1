package jp.zenryoku.sample.daily;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateSample {

    private Date getSetedDate(int year, int month
            , int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        return cal.getTime();
    }
    @Test
    public void testDate() {
        // 単純にインスタンス化
        Date d = new Date();
        System.out.println(d);
    }
    @Test
    public void testAfter() {
        Date mukashi = getSetedDate(2000, 12, 10);
        Date now = new Date();
        assertFalse(mukashi.after(now));
        assertTrue(now.after(mukashi));
    }

    @Test
    public void testBefore() {
        Date now = new Date();
        Date mirai = getSetedDate(2050, 12,10);
        assertFalse(mirai.before(now));
        assertTrue(now.before(mirai));
    }

    @Test
    public void testCompareTo() {
        Date now = new Date();
        Date mirai = getSetedDate(2050, 12,10);
        Date mukasi = getSetedDate(1999, 12,10);
        assertTrue(now.compareTo(mirai) < 0);
        assertTrue(now.compareTo(now) == 0);
        assertTrue(now.compareTo(mukasi) > 0);
    }
}
