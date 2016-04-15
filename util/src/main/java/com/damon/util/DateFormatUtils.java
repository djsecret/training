package com.damon.util;


import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式化工具类
 * Created by dongjun.wei on 16/2/22.
 */
public class DateFormatUtils {
    private static final int MAX_SDF_SIZE = 20;
    private static final ThreadLocal<LRUMap> dateFormatThreadLocal = new ThreadLocal<LRUMap>() {
        @Override
        protected LRUMap initialValue() {
            return new LRUMap(MAX_SDF_SIZE);
        }
    };
    private final static String[] WEEKDAYS = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public static SimpleDateFormat getDateFormat(String pattern) {
        LRUMap simpleDateFormatMap = dateFormatThreadLocal.get();
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) simpleDateFormatMap.get(pattern);
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(pattern);
            simpleDateFormat.setLenient(false);//需要精确格式化
            simpleDateFormatMap.put(pattern, simpleDateFormat);
        }
        return simpleDateFormat;
    }

    public static void destroy() {
        dateFormatThreadLocal.remove();
    }

    public static Date parse2d2M4y(String source) {
        try {
            return getDateFormat("dd/MM/yyyy").parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseyyyyMMdd(String source) {
        try {
            return getDateFormat("yyyyMMdd").parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseyyMMddHHmmss(String source) {
        try {
            return getDateFormat("yyMMddHHmmss").parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parse4y2M2d(String source) {
        try {
            return getDateFormat("yyyy-MM-dd").parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parse4y2M2d2h2m2s(String source) {
        try {
            return getDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parse4y2M2d2h2m(String source) {
        try {
            return getDateFormat("yyyy-MM-dd HH:mm").parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parse4y2M2dT2h2m2s(String source) {
        try {
            return getDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parse2M2d4y(String source) {
        try {
            return getDateFormat("MM/dd/yyyy").parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    public static String format4y2M2d2h2m2s(long date) {
        if (date <= 0)
            return null;
        try {
            return getDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parse4Y2M1d2H2m2s(String source) {
        try {
            return getDateFormat("yyyy/MM/d HH:mm:ss").parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseHHmm(String source) {
        try {
            if (StringUtils.equals("24:00", source)){
                source = "23:59";
            }
            return getDateFormat("HH:mm").parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseHHmmss(String source) {
        try {
            if (StringUtils.equals("24:00:00", source)){
                source = "23:59:59";
            }
            return getDateFormat("HH:mm:ss").parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatHHmm(Date date) {
        try {
            return getDateFormat("HH:mm").format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatHHmmss(Date date) {
        try {
            return getDateFormat("HH:mm:ss").format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseHHmmWithoutDelimiter(String source) {
        try {
            return getDateFormat("HHmm").parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parse(String source, String pattern) {
        try {
            return getDateFormat(pattern).parse(source);
        } catch (Exception e) {
            return null;
        }
    }

    public static String format4y2M2d(Date date) {
        return getDateFormat("yyyy-MM-dd").format(date);
    }

    public static String format2d2M4y(Date date) {
        return getDateFormat("dd/MM/yyyy").format(date);
    }

    public static String format2M2d4y(Date date) {
        return getDateFormat("MM/dd/yyyy").format(date);
    }

    public static String format4y2M2d2h2m2s(Date date) {
        return getDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String format4y2M2dT2h2m2s(Date date) {
        return getDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(date);
    }

    public static String format4y2M2d2h2m(Date date) {
        return getDateFormat("yyyy-MM-dd HH:mm").format(date);
    }

    public static Calendar dateToCalender(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static int diff(Date startDate, Date endDate) {
        return (int) Math.ceil((endDate.getTime() - startDate.getTime()) / 86400000.0D);
    }

    public static int diffHour(Date startDate, Date endDate) {
        return (int) Math.ceil((endDate.getTime() - startDate.getTime()) / 3600000.0D);
    }

    public static int diffSecond(Date startDate, Date endDate) {
        return (int) Math.ceil((endDate.getTime() - startDate.getTime()) / 1000.0D);
    }

    public static Date dateAddDays(Date date, int days) {
        Calendar cal = dateToCalender(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    public static String formatyyyyMMdd(Date date) {
        return getDateFormat("yyyyMMdd").format(date);
    }

    public static int getHour(Date time) {
        try {
            String hourStr = getDateFormat("H").format(time);
            return Integer.parseInt(hourStr);
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getYear(Date time) {
        try {
            return getDateFormat("yyyy").format(time);
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    public static String getMonth(Date time) {
        try {
            return getDateFormat("MM").format(time);
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    public static String getDay(Date time) {
        try {
            return getDateFormat("dd").format(time);
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    public static int getWeekday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static String formatWeekday(Date date) {
        int w = getWeekday(date);
        return WEEKDAYS[w];
    }

    public static boolean isSameYearMonth(Date d1, Date d2) {
        try {
            String yearMonthStr1 = getDateFormat("yyyyMM").format(d1);
            String yearMonthStr2 = getDateFormat("yyyyMM").format(d2);
            return StringUtils.equals(yearMonthStr1, yearMonthStr2);
        } catch (Exception e) {
            return false;
        }
    }

}
