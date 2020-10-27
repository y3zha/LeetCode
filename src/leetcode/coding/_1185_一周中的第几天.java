package leetcode.coding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class _1185_一周中的第几天 {

    // 给你一个日期，请你设计一个算法来判断它是对应一周中的星期几
    // Zeller 公式可以做
    public String dayOfTheWeek(int day, int month, int year) {
        // 日 我只想调用库函数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = year + "-" + month + "-" + day;
        Date date = null;
        try {
            date = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 需要注意的是，国际上是以星期日为一周第一天的开始，Calendar中提供的DAY_OF_WEEK获取的一周也是以星期日作为一周的开始。
        // 若一周第一天为星期天，则-1

        int idx = cal.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println(idx);
        // sun 0
        // sat 6
        // fri 5
        // thur 4
        // wen 3
        // thus 2
        // mon 1
        String[] week = {"Sunday","Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return week[idx];
    }
}