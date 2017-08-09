package com.hh.calendartest;

import java.util.Calendar;

/**
 * Created by haohe on 2017/8/9 0009.
 */

public class DateUtil {


    /**
     * 获取某年某月份的天数
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month){

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, 1);
        calendar.roll(Calendar.DATE, -1);

        return  calendar.get(Calendar.DATE);
    }


}
