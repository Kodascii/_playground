package main;

import java.time.*;
import java.util.Arrays;


public class DatesAndTimes {

    public static void main(String[] args) throws InterruptedException {
        Instant before = Instant.now();
        Thread.sleep(1250);
        Instant after = Instant.now();

        Duration duration = Duration.between(before, after);

        System.out.println(duration.toMillis());
    }

}
