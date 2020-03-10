package leetcode.coding;

public class _1013_将数组分成和相等的三个部分 {

    public static boolean canThreePartsEqualSum(int[] A) {
        //首先计算下sum，如果和取余3不为0，那肯定不能划分，然后拿到每一部分得要划分多大
        int n = A.length;
        int sum = 0;
        for(int i = 0; i<n; i++){
            sum += A[i];
        }
        if(sum % 3 != 0) return false;
        int cnt = 0;
        int temp = 0;
        for(int i = 0; i < n; i++){
            temp += A[i];
            if(temp  == (sum / 3)){
                temp = 0;
                cnt++;
                continue;
            }
        }
        System.out.println(cnt);
        return cnt == 3;
    }

    public static void main(String[] args) {
        int[] a = {0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1};
        int[] b = {10, -10, 10, -10, 10, -10, 10, -10};
        canThreePartsEqualSum(b);
    }
}