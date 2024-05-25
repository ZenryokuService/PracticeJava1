package jp.zenryoku.sample.daily;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GregorianCalendarSample {
    private void print(Calendar cal) {
        System.out.println(cal.get(Calendar.YEAR) + "年");
        System.out.println(cal.get(Calendar.MONTH) + "月");
        System.out.println(cal.get(Calendar.DAY_OF_MONTH) + "日");
    }

    private LocalDateTime getLocalDatetime() {
        return LocalDateTime.now(ZoneOffset.UTC);
    }

    private LocalDate getLocalDate() {
        return LocalDate.now(ZoneOffset.UTC);
    }
    @Test
    public void testCal() {
        GregorianCalendar cal;
        cal = new GregorianCalendar(2000
                , 10, 10);
        print(cal);
    }

    @Test
    public void testGreg01() {
        GregorianCalendar gol = new GregorianCalendar();
        System.out.println(gol.getGregorianChange());
    }

    @Test
    public void testCal1() {
        GregorianCalendar gol = new GregorianCalendar();
        System.out.println(gol.getTime());
    }

    @Test
    public void testCal2() {
        GregorianCalendar gol = new GregorianCalendar();
        System.out.println(gol.getTime());

        Calendar cal = (Calendar) gol;
        System.out.println(cal.getTime());

    }

    @Test
    public void testCal3() {
        GregorianCalendar gol = new GregorianCalendar();
        System.out.println(gol.get(Calendar.YEAR));
    }

    @Test
    public void testAdd() {
        GregorianCalendar gol = new GregorianCalendar();
        for (int i = 0; i < 7; i++) {
            System.out.println(gol.getTime());
            gol.add(Calendar.DAY_OF_MONTH, 1);
        }
    }
    @Test
    public void testZone() {
        LocalDateTime now = getLocalDatetime();
        ZonedDateTime z = now.atZone(
                ZoneId.of("America/Chicago"));
        System.out.println(z);
    }

    @Test
    public void testCal3_2() {
        GregorianCalendar gol = new GregorianCalendar();
        System.out.println("年* " + gol.get(Calendar.YEAR));
        System.out.println("月* "
                + gol.get(Calendar.MONTH));
        System.out.println("日* "
                + gol.get(Calendar.DAY_OF_MONTH));
    }



}

