import java.text.ParseException;
import java.util.Date;

public class DateUtilsTest {
    public static void main(String[] args) {
        strToDate_Test();
        System.out.println();

        leapYear_Test();
        System.out.println();

        timestamp_Test();
        System.out.println();

        add_Test();
        System.out.println();
    }

    private static void strToDate_Test() {
        System.out.println("[String From Date and vice versa Test]");

        try {
            String string1 = "31/12/2015";
            Date date1 = DateUtils.strToDate(string1, DateUtils.DateType.DMY);
            System.out.printf("%s -> %s\n", string1,DateUtils.dateToString(date1, DateUtils.DateType.SQL));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void leapYear_Test() {
        System.out.println("[Leap Year Test]");

        int year = 2015;
        System.out.printf("Test %d : %b\n", year,DateUtils.isLeapYear(year));

        year = 2016;
        System.out.printf("Test %d : %b\n", year,DateUtils.isLeapYear(year));
    }

    private static void timestamp_Test() {
        System.out.println("[Timestampa Test]");

        Date today = new Date();
        long timestamp = DateUtils.dateToTimestamp(today);
        System.out.printf("%s : %d\n", DateUtils.dateToString(today,DateUtils.DateType.DMY_HMS),timestamp);

        timestamp += 100000;
        Date new_day = DateUtils.timestampToDate(timestamp);
        System.out.printf("Test %d : %s\n", timestamp, DateUtils.dateToString(new_day,DateUtils.DateType.DMY_HMS));
    }

    private static void add_Test() {
        System.out.println("[Adding to Date Test]");

        Date today = new Date();
        Date today_plus_5d = DateUtils.addDays(today,5);
        System.out.printf("%s + %d days = %s\n", DateUtils.dateToString(today,DateUtils.DateType.DMY),5, DateUtils.dateToString(today_plus_5d,DateUtils.DateType.DMY));

        Date today_plus_2m = DateUtils.addMonths(today,2);
        System.out.printf("%s + %d months = %s\n", DateUtils.dateToString(today,DateUtils.DateType.DMY),2, DateUtils.dateToString(today_plus_2m,DateUtils.DateType.DMY));

        Date today_plus_1y = DateUtils.addYears(today,1);
        System.out.printf("%s + %d years = %s\n", DateUtils.dateToString(today,DateUtils.DateType.DMY),1, DateUtils.dateToString(today_plus_1y,DateUtils.DateType.DMY));

        Date fom = DateUtils.firstOfMonth(today);
        System.out.printf("f.o.m %s = %s\n", DateUtils.dateToString(today,DateUtils.DateType.DMY), DateUtils.dateToString(fom,DateUtils.DateType.DMY));

        try {
            Date new_date = DateUtils.strToDate("15/02/2016", DateUtils.DateType.DMY);
            Date lom = DateUtils.lastOfMonth(new_date);
            System.out.printf("l.o.m %s = %s\n", DateUtils.dateToString(new_date, DateUtils.DateType.DMY), DateUtils.dateToString(lom, DateUtils.DateType.DMY));
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
