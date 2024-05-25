package jp.zenryoku.sample.daily;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

public class LocalDateTimeSample {
    private Temporal get1980() {
        return LocalDateTime.of(1980
                , 10, 10, 12, 12);
    }

    private Temporal getNow() {
        return LocalDateTime.now();
    }

    @Test
    public void testFrom() {
        Temporal muka = get1980();
        System.out.println(LocalDateTime.from(muka));
    }
}
