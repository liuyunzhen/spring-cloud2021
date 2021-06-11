package utils;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

public class DateTestUtils {
    @Test
    public void getDate(){
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}
