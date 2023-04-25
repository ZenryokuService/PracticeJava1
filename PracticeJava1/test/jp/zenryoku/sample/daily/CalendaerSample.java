package jp.zenryoku.sample.daily;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class CalendaerSample {
    @Test
    public void testCalender() {
        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        Date mpw1 = new Date();
        assertFalse(now.equals(mpw1));
    }

    @Test
    public void testGetTimeMillis() {
        Calendar cal = Calendar.getInstance();
        long time = new Date().getTime();
        long tt = cal.getTimeInMillis();
        System.out.println(tt);
        System.out.println(time);
        assertNotEquals(tt, time);
    }

    @Test
    public void testAdd() {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTime());
        // 年を１減算する
        cal.add(Calendar.YEAR, -1);
        System.out.println(cal.getTime());
    }

    @Test
    public void testGet() {
        Calendar cal = Calendar.getInstance();
        assertEquals(2023, cal.get(Calendar.YEAR));
        // 月は０から始まるようです。
        assertEquals(3, cal.get(Calendar.MONTH));
        assertEquals(19, cal.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testDisplayName() {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getDisplayName(Calendar.DAY_OF_WEEK
                , Calendar.SHORT_FORMAT, Locale.JAPAN));
    }

    @Test
    public void testGetWeeksInWeekYear() {
        Calendar cal = Calendar.getInstance();
        System.out.println(
                cal.getWeeksInWeekYear()
        );
    }

    @Test
    public void testSet() {
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTime());
        cal.set(2050, 1,1);
        System.out.println(cal.getTime());
    }
}
