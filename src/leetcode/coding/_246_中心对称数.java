package leetcode.coding;

public class _246_中心对称数 {

    public boolean isStrobogrammatic(String s) {
        if (s == null|| s.length() == 0) return true;
        if (s.contains("2") || s.contains("3") || s.contains("4") || s.contains("5") || s.contains("7")) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '6') {
                sb.insert(0, "9");
            } else if (c == '9') {
                sb.insert(0, "6");
            } else {
                sb.insert(0, c);
            }
        }
        return sb.toString().equals(s);
    }
}