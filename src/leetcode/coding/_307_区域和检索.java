package leetcode.coding;

/**
 * 线段树
 */
public class _307_区域和检索 {

    class NumArray {
        private SegmentTree segmentTree;

        public NumArray(int[] A) {
            if (A == null || A.length == 0) {
                return;
            }
            segmentTree = new SegmentTree(A);

        }

        public void update(int index, int val) {
            segmentTree.modify(index, val);
        }

        public int sumRange(int i, int j) {
            return segmentTree.querySum(i, j);
        }
    }

    class SegmentTreeNode{
        public int start, end;
        public int sum;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            sum = 0;
            left = right = null;
        }
    }

    class SegmentTree{
        public SegmentTreeNode root;
        public int size;

        public SegmentTree(int[] A) {
            size = A.length;
            root = buildTree(A, 0, size - 1);
        }

        private SegmentTreeNode buildTree(int[] A, int start, int end) {
            SegmentTreeNode node = new SegmentTreeNode(start, end);
            //递归出口
            if (start == end) {
                node.sum = A[start];
                return node;
            }
            //不是出口则递归建所有子树
            int mid = (start + end) / 2;
            node.left = buildTree(A, start, mid);
            node.right = buildTree(A, mid + 1, end);
            node.sum = node.left.sum + node.right.sum;
            return node;
        }

        //公开接口
        public int querySum(int start, int end) {
            return querySum(root, start, end);
        }

        //公开接口
        public void modify(int index, int val) {
            modify(root, index, val);
        }

        private int querySum(SegmentTreeNode node, int start, int end) {
            if (node.start == start && node.end == end) {
                return node.sum;
            }
            int mid = (node.start + node.end) / 2;  //这边不是start和end 是node的区间
            int leftSum = 0, rightSum = 0;
            if (start <= mid) {
                leftSum = querySum(node.left, start, Math.min(end, mid));
            }
            if (end >= mid + 1) {
                rightSum = querySum(node.right, Math.max(start, mid + 1), end);
            }
            return leftSum + rightSum;
        }

        private void modify(SegmentTreeNode node, int index, int val) {
            //递归出口：到达这个叶子节点，并修改它的值
            if (node.start == node.end && node.end == index) {
                node.sum = val;
                return;
            }
            //递归：分为在左子树和右子树两种情况,不用求mid
            if (node.left.end >= index) {
                modify(node.left, index, val);
            } else {
                modify(node.right, index, val);
            }
            //最后改下根
            node.sum = node.left.sum + node.right.sum;
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
}