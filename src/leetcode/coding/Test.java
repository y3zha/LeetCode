package leetcode.coding;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Stack;

public class Test {

    // 打印 2020 年 2 月 - 3 月 双月日历，要求横屏展示两个月，星期日在首列
    public static void printCalandar() {
        String calandar[][] = new String[5][14];

    }

    public static String getWeek(Date date) {
        String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int idx = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(idx<0){
            idx = 0;
        }
        return weeks[idx];
    }

    public static int convertChineseToNumber(String s) {
        HashMap<String,Integer> map = new HashMap<>();
        map.put("零",0);
        map.put("一",1);
        map.put("二",2);
        map.put("三",3);
        map.put("四",4);
        map.put("五",5);
        map.put("六",6);
        map.put("七",7);
        map.put("八",8);
        map.put("九",9);
        // 单位
        HashMap<String,Integer> uMap = new HashMap<>();
        uMap.put("十",10);
        uMap.put("百", 100);
        uMap.put("千",1000);
        uMap.put("万",10000);
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i < s.length();i++){
            String str = ""+s.charAt(i);
            if(map.containsKey(str)){
                stack.push(map.get(str));
            }else{
                int num = stack.pop();
                int unit = uMap.get(str);
                stack.push(num * unit);
            }
        }
        int res = 0;
        Stack<Integer> helper = new Stack<>();
        while(!stack.isEmpty()){
            helper.push(stack.pop());
        }
        while(!helper.isEmpty()){
            res += helper.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        convertChineseToNumber("二百三十一");
    }
}