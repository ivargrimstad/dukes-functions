package eu.agilejava;

import java.time.LocalDate;

import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.time.temporal.ChronoUnit.DAYS;

public class BirthdayFunction {

    public static class Input {
        public String name;
        public String birthDay;
    }

    public static class Result {
        public int daysToBirthday;
        public int daysSinceBirthday;
        public String message;
    }

    public Result handleRequest(Input input) {

        Result result = new Result();
        if(input == null || input.birthDay == null || input.birthDay.isEmpty()) {
            result.message = "No input";
            return result;
        }

        result.daysToBirthday = calculateDaysToBirthday(input.birthDay);
        result.daysSinceBirthday = calculateDaysSinceBirthday(input.birthDay);

        final String greeting = System.getenv("greeting");
        result.message = (greeting != null && !greeting.isEmpty() ? greeting : "Hello") + ", " + input.name + "!";

        return result;
    }

    private int calculateDaysToBirthday(final String birthDate) {

        if(birthDate == null || birthDate.isEmpty() ) {
            return 0;
        }

        final LocalDate now = LocalDate.now();
        final LocalDate bdThisYear = parse(birthDate, ISO_DATE).withYear(now.getYear());

        if (bdThisYear.isAfter(now)) {
            return (int) now.until(bdThisYear, DAYS);
        } else {
            return (int) now.until(bdThisYear.plusYears(1), DAYS);
        }
    }

    private int calculateDaysSinceBirthday(final String birthDate) {

        if(birthDate == null || birthDate.isEmpty() ) {
            return 0;
        }

        final LocalDate now = LocalDate.now();
        final LocalDate bdThisYear = parse(birthDate, ISO_DATE).withYear(now.getYear());

        if (bdThisYear.isAfter(now)) {
            return (int) bdThisYear.plusYears(1).until(now, DAYS);
        } else {
            return (int) bdThisYear.until(now, DAYS);
        }
    }

}