package cn.faker.repaymodel.widget.view.date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 获取月份工具类
 * @author hw
 *
 */
public class MonthUtil {

    /**
     * 获取当前时间月份的最后一天
     *
     * @param nowTime
     * @return
     */
    public static String getMonthLastDay(Date nowTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar now = Calendar.getInstance();
        now.setTime(nowTime);
        now.add(Calendar.DATE, -1);
        String date = DateUtil.getDay(now.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.parse(date, "yyyy-MM-dd"));
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.DATE, -1);
        String day_last = df.format(calendar.getTime());
        StringBuffer endStr = new StringBuffer().append(day_last);
        day_last = endStr.toString();
        StringBuffer str = new StringBuffer().append(day_last);
        return str.toString();

    }

    /**
     * 获取当前时间月份的第一天
     *
     * @param nowTime
     * @return
     */
    public static String getMonthFirstDay(Date nowTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowTime);
        Date theDate = calendar.getTime();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        StringBuffer str = new StringBuffer().append(day_first);
        return str.toString();
    }

    /**
     * 获取指定区间的月份
     *
     * @param beginMonth 2018-01
     * @param endMonth 2018-12
     * @return
     */
    private static List<String> getAppointMonths(String beginMonth, String endMonth) {
        List<String> months = new ArrayList<String>();
        try {
            Date d1 = new SimpleDateFormat("yyyy-MM").parse(beginMonth);// 定义起始日期
            Date d2 = new SimpleDateFormat("yyyy-MM").parse(endMonth);// 定义结束日期，可以去当前月也可以手动写日期
            Calendar dd = Calendar.getInstance();// 定义日期实例
            dd.setTime(d1);// 设置日期起始时间
            while (dd.getTime().before(d2)) {// 判断是否到结束日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                String str = sdf.format(dd.getTime());
                dd.add(Calendar.MONTH, 1);// 进行当前日期月份加1
                months.add(str);
            }
            months.add(endMonth);
        } catch (Exception e) {
            System.out.println("异常" + e.getMessage());
        }
        return months;
    }

    public static void main(String[] args) {
        System.out.println(getMonthFirstDay(new Date()));
        System.out.println(getMonthLastDay(new Date()));
        System.out.println(Arrays.asList(getAppointMonths("2018-01", "2018-12")));
    }
}
