package leetcode.swordtooffer;

public class 面试题5_替换空格 {

    public String replaceSpace(String s) {
        //使用库函数是一种简便的，但不好
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                sb.append("%20");
                continue;
            }
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}