package kw.learn.androidbezier.utils;

import java.util.Calendar;
import java.util.Date;

import kw.learn.androidbezier.bean.DateBean;

/**
 * @Auther jian xian si qi
 * @Date 2023/4/6 19:34
 */
public class NoteUtils {
    public static DateBean longToDateBean(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        DateBean bean = new DateBean();
        bean.setMonth(calendar.get(Calendar.MONTH));
        bean.setYear(calendar.get(Calendar.YEAR));
        bean.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        bean.setWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
        return bean;
    }

}
