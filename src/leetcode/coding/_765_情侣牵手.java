package leetcode.coding;

/**
 * 设想一下加入有两对情侣互相坐错了位置，我们至多只需要换一次。
 * 如果三对情侣相互坐错了位置，A1+B2,B1+C2,C1+A2。他们三个之间形成了一个环，我们只需要交换两次。
 * 如果四队情侣相互坐错了位置，即这四对情侣不与其他情侣坐在一起，A1+B2,B1+C2,C1+D2,D1+A2.他们四个之间形成了一个环,只需要交换三次
 * 假设k对情侣形成一个环状的错误链，我们只需要交换k - 1次就可以将这k对情侣的位置排好。
 * 问题转化成N对情侣中，有几个这样的错误环
 *
 * 使用并查集来处理，每次遍历相邻的两个位置，如果他们本来就是情侣，他们处于大小为1的错误环中，只需要交换0次
 * 如果不是情侣，说明他们呢两对处在同一个错误环中，我们将他们合并（union），将所有的错坐情侣合并和后，答案就是 情侣对 - 环个数
 * 最差的情况就是所有N对情侣都在一个环中，这时候我们需要 N - 1调换
 */
public class _765_情侣牵手 {

/*  ====算法分析====
    并查集
    1.首先初始状态把这N对情侣分别构成一个连通块
    2.考虑k对情侣相互错开的情况，他们形成一个环，可以知道需k-1次交换使排列正确
    3.这样相互错开的情况，分别构成连通块，最后用N - 连通块个数即为答案
    例如：0，1|2，3|... |2N-2,2N-1
    |0 3| ... |7 2|...|6 1| 看相对顺序，可以发现这三对构成一个环，只需2次交换
    同理还有其他类型的环构成连通块
*/
    public int minSwapsCouples(int[] row) {
        int len = row.length;
        int n = len / 2;    //情侣对数
        UF uf = new UF(len);

        //合并
        for (int i = 0; i < len; i += 2) {
            //先找到row[i]的fatrher，再找row[i+1] 的 father，union起来
            //这样子 0 2 1 3 这种情况 ，就是0和2 union了，然后 1和3 union了，
            // 后 0和1，初始化的时候，都是一个father 那么久形成一个环了，也就是只有一个连通块了
            //结果就是情侣个数 - 连通块个数
            uf.union(row[i],row[i + 1]);
        }

        //统计结果
        int res = n;
        for (int i = 0; i < len; i++) {
            //如果我这个位置还是原来的father，那就--，代表本来就是情侣
            if (uf.find(i) == i) {
                res--;
            }
        }
        return res;
    }

    class UF{
        private int[] father;
        int count;

        public UF(int n) {  //n对情侣
            father = new int[n];
            for (int i = 0; i < n; i += 2) {
                father[i] = i;  //把相邻的father设置为一样的  这样0 和 1 就是一个father 了。。其他同理
                father[i + 1] = i;
            }
            count = n;
        }

        public void union(int a, int b) {
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b) {
                father[root_a] = root_b;
            }
        }

        public int find(int x) {
            if (father[x] == x) {
                return x;
            }
            return father[x] = find(father[x]);
        }
    }
}