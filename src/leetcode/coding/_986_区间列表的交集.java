package leetcode.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _986_区间列表的交集 {

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        //先给A和B的数组排个序
        Arrays.sort(A, (o1, o2) -> o1[0] == o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        Arrays.sort(B, (o1, o2) -> o1[0] == o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
        List<int[]> res = new ArrayList<>();

        int i = 0,j = 0;
        int n = A.length,m = B.length;
        while(i < n && j < m){
            int start = Math.max(A[i][0],B[j][0]);
            int end = Math.min(A[i][1],B[j][1]);
            if(start <= end){
                res.add(new int[]{start,end});
            }
            //[1,2] [5,10] 和 [1,5]，我们已经得到了区间[1,2]，A的末端点已经被用了，所以[1,2]这一个已经废掉了
            //也就是说，哪个数组的末端点被用了，这个数组就是可以不看了
            //但是对于开始断电，B的末端点为5，可能与A的下一个数组[5,10]这个有交集
            if(A[i][1] < B[j][1]) i++;
            else j++;
        }
        return res.toArray(new int[0][]);
    }


}