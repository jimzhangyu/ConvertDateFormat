

/**
 * Created by Lenovo on 2018/1/11.
 */


import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.util.*;
import static org.apache.commons.lang3.time.DateUtils.parseDate;

    public final class DateUtils {

        private static final String[] TIME_CHINESE;

        private static final String[] TIME_ENGLISH;

        private static final String[] DATE_CHINESE;

        private static final String[] DATETIME_CHINESE;

        private static final String[] DATE_ENGLISH;

        private static final String[] DATETIME_ENGLISH;

        static {
            Set<String> partterns = new LinkedHashSet<>();

            partterns.add("HH:mm:ss");
            partterns.add("HH:mm");
            TIME_ENGLISH = partterns.toArray(new String[partterns.size()]);
            partterns.clear();

            partterns.add("HH:mm:ss");
            partterns.add("HH:mm");
            partterns.add("HH时mm分ss秒");
            partterns.add("mm分ss秒");
            partterns.add("HH时mm分");
            partterns.add("HH时mm分ss");
            TIME_CHINESE = partterns.toArray(new String[partterns.size()]);
            partterns.clear();

            partterns.add("yyyy/MM/dd");
            partterns.add("MM/dd/yyyy");
            partterns.add("M/dd");
            partterns.add("yyyyMMdd");
            partterns.add("yyyy年MM月dd日");
            partterns.add("yyyy年M月dd日");
            partterns.add("yyyy年M月d日");
            partterns.add("yyyy年MM月d日");
            partterns.add("MM月dd日");
            partterns.add("M月dd日");
            partterns.add("MM月d日");
            partterns.add("M月d日");
            partterns.add("yyyy年MM月");
            partterns.add("yyyy年M月");
            partterns.add("yyyy.MM.dd");
            partterns.add("yyyy-MM-dd");
            partterns.add("yyyyMM");
            partterns.add("yyyy.MM");
            DATE_CHINESE = partterns.toArray(new String[partterns.size()]);
            partterns.clear();

            partterns.add("dd-MMM-yy");
            partterns.add("dd-MMM");
            partterns.add("dd-MMM-yyyy");
            partterns.add("d-MMM");
            partterns.add("d-MMM");
            partterns.add("d-MMM-yyyy");
            partterns.add("d-MMM-yy");
            partterns.add("MMM d");
            partterns.add("MMM dd");
            partterns.add("MMM-dd");
            partterns.add("MMM-d");
            partterns.add("MMM d,yyyy");
            DATE_ENGLISH = partterns.toArray(new String[partterns.size()]);
            partterns.clear();

            for (String partternDate : DATE_CHINESE) {
                for (String partternTime : TIME_CHINESE) {
                    partterns.add(partternDate + " " + partternTime);
                }
            }
            DATETIME_CHINESE = partterns.toArray(new String[partterns.size()]);
            partterns.clear();

            for (String partternDate : DATE_ENGLISH) {
                for (String partternTime : TIME_ENGLISH) {
                    partterns.add(partternDate + " " + partternTime);
                }
            }
            DATETIME_ENGLISH = partterns.toArray(new String[partterns.size()]);
            partterns.clear();
        }

        /**
         * 最后输出结果
         */
        public static String convert(String dateFormat) {
            if (dateFormat == null || dateFormat.trim().isEmpty()) {
                throw new IllegalArgumentException("不能为空");
            }
            boolean isEnglish = dateFormat.matches(".*\\p{Alpha}.*");
            return isEnglish ? print4English(dateFormat) : print4Chinese(dateFormat);
        }

        private static String print4Chinese(final String dateFormat) {
            Locale china = Locale.CHINA;
            String time1 = asTime(dateFormat, china, TIME_CHINESE);
            if (time1 != null) {
                return time1;
            }
            String date1 = asDate(dateFormat, china, DATE_CHINESE);
            if (date1 != null) {
                return date1;
            }
            String dateTime1 = asDateTime(dateFormat, china, DATETIME_CHINESE);
            if (dateTime1 != null) {
                return dateTime1;
            }
            return null;
        }

        private static String print4English(final String dateFormat) {
            Locale english = Locale.ENGLISH;
            String time1 = asTime(dateFormat, english, TIME_ENGLISH);
            if (time1 != null) {
                return time1;
            }
            String date1 = asDate(dateFormat, english, DATE_ENGLISH);
            if (date1 != null) {
                return date1;
            }
            String dateTime1 = asDateTime(dateFormat, english, DATETIME_ENGLISH);
            if (dateTime1 != null) {
                return dateTime1;
            }
            return null;
        }

        private static String asDateTime(String dateFormat, Locale locale, String[] patternDateTimes) {
            Date dateTime = null;
            try {
                dateTime = parseDate(dateFormat, locale, patternDateTimes);
            } catch (ParseException ignored) {
            }
            if (dateTime != null) {
                return DateFormatUtils.format(dateTime, "yyyy-MM-dd HH:mm:ss");
            }
            return null;
        }

        private static String asDate(String dateFormat, Locale locale, String[] patternDates) {
            Date date = null;
            try {
                date = parseDate(dateFormat, locale, patternDates);
            } catch (ParseException ignored) {

            }
            if (date != null) {
                return DateFormatUtils.format(date, "yyyy-MM-dd");
            }
            return null;
        }

        private static String asTime(String dateFormat, Locale locale, String[] patternTimes) {
            Date time = null;
            try {
                time = parseDate(dateFormat, locale, patternTimes);
            } catch (ParseException ignored) {

            }
            if (time != null) {
                return DateFormatUtils.format(time, "HH:mm:ss");
            }
            return null;
        }
    }

