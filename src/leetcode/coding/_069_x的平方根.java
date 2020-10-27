package leetcode.coding;

public class _069_x的平方根 {

    public static int mySqrt(int x) {
        if(x == 0) return 0;
        if(x == 1) return 1;
        long l = 1,r = x;
        while(l + 1 < r){
            long mid = l + (r - l) / 2;
            long temp = mid * mid;
            if(x == temp){
                return (int) mid;
            } else if(x > temp){
                l = mid;
            }else{
                r = mid;
            }
        }
        if(r * r <= x){
            return (int) r;
        }
        return (int) l;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
    }
}