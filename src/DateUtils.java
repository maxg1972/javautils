import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtils {
    //Allowed date formats
    public enum DateType {
        DMY,
        MDY,
        YMD,
        SQL,
        DMY_HM,
        DMY_HMS,
        MDY_HM,
        MDY_HMS,
        YMD_HM,
        YMD_HMS,
        SQL_HM,
        SQL_HMS,
    }

    /**
     * Return string format from DataType
     * @param format    DataType format
     * @return          String representing DataType format
     */
    private static String getDateFormat(DateType format) {
        String str_format;
        switch (format) {
            case DMY:
                str_format = "dd/MM/yyyy";
                break;
            case MDY:
                str_format = "MM/dd/yyyy";
                break;
            case YMD:
                str_format = "MM/dd/yyyy";
                break;
            case SQL:
                str_format = "yyyy-MM-dd";
                break;
            case DMY_HM:
                str_format = "dd/MM/yyyy HH:mm";
                break;
            case DMY_HMS:
                str_format = "dd/MM/yyyy HH:mm:ss";
                break;
            case MDY_HM:
                str_format = "MM/dd/yyyy HH:mm";
                break;
            case MDY_HMS:
                str_format = "MM/dd/yyyy HH:mm:ss";
                break;
            case YMD_HM:
                str_format = "MM/dd/yyyy HH:mm";
                break;
            case YMD_HMS:
                str_format = "MM/dd/yyyy HH:mm:ss";
                break;
            case SQL_HM:
                str_format = "yyyy-MM-dd HH:mm";
                break;
            case SQL_HMS:
                str_format = "yyyy-MM-dd HH:mm:ss";
                break;
            default:
                str_format = "";
        }

        return str_format;
    }

    /**
     * Convert String to Date
     * @param string_date   String to be converted
     * @param date_type     DateType of string date format
     * @return              Date object
     * @throws ParseException Exception
     */
    public static Date strToDate(String string_date, DateType date_type) throws ParseException {
        String format = getDateFormat(date_type);

        DateFormat df = new SimpleDateFormat(format);

        return df.parse(string_date);
    }

    /**
     * Convert Date to String
     * @param date_value Date value to be converted
     * @param data_type DateType of finale string date format
     * @return          String representing Date
     */
    public static String dateToString(Date date_value, DateType data_type) {
        String format = getDateFormat(data_type);

        DateFormat df = new SimpleDateFormat(format);

        return df.format(date_value);
    }

    /**
     * Return if year is a leap year
     * @param year  year to test
     * @return      true if year is a leap year
     */
    public static Boolean isLeapYear(int year) {
        return ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)));
    }

    /**
     * Convert date to unix timestamp
     * @param date_value    date value to be converted
     * @return              timestamp value
     */
    public static long dateToTimestamp(Date date_value) {
        return date_value.getTime() / 1000;
    }

    /**
     * Convert unix timestamp to date
     * @param timestamp timestamp to be converted
     * @return          date value
     */
    public static Date timestampToDate(long timestamp) {
        return new Date(timestamp * 1000);
    }

    /**
     * Add days to a date and return it
     * @param date_value    date value to add days
     * @param days          days to be added
     * @return              new date value
     */
    public static Date addDays(Date date_value, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date_value);
        calendar.add(Calendar.DAY_OF_MONTH, days);

        return calendar.getTime();
    }

    /**
     * Add months to a date and return it
     * @param date_value    date value to add months
     * @param months        months to be added
     * @return              new date value
     */
    public static Date addMonths(Date date_value, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date_value);
        calendar.add(Calendar.MONTH, months);

        return calendar.getTime();
    }

    /**
     * Add years to a date and return it
     * @param date_value    date value to add years
     * @param years         years to be added
     * @return              new date value
     */
    public static Date addYears(Date date_value, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date_value);;
        calendar.add(Calendar.YEAR, years);

        return calendar.getTime();
    }

    /**
     * Get a date representing first day of month
     * @param date_value    date value to find first day of month
     * @return              date representing first day of month
     */
    public static Date firstOfMonth(Date date_value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date_value);;
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }

    /**
     * Get date representing last day of month
     * @param date_value    date value to find last day of month
     * @return              date representing last day of month
     */
    public static Date lastOfMonth(Date date_value) {
        Date new_date = firstOfMonth(addMonths(date_value,1));
        new_date = addDays(new_date, -1);

        return new_date;
    }
}
