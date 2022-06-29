package class05;

/**
 * 归并，区间和的个数
 * 
 * 给定一个整数数组nums 以及两个整数lower 和 upper 。求数组中，值位于范围 [lower, upper]（包含lower和upper）之内的
 * 区间和的个数 。
 * 
 * 区间和S(i, j)表示在nums中，位置从i到j的元素之和，包含i和j(i ≤ j)。
 * 
 * https://leetcode.cn/problems/count-of-range-sum/
 * 
 * @author haifuns
 * @date 2022-06-29 22:21
 */
public class Code01_CountOfRangeSum {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // S(i, j) = S(0, j) - S(0, i-1), 用累加和相减代替遍历求和
        // 提前准备好前缀和数组
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process(long[] sum, int L, int R, int lower, int upper) {
        if (L == R) { // 退出条件
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0; // 不能再merge直接判断
        }
        int M = L + ((R - L) >> 1);
        return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper)
                + merge(sum, L, M, R, lower, upper);
    }

    public static int merge(long[] arr, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        int windowL = L;
        int windowR = L;
        // [windowL, windowR)
        for (int i = M + 1; i <= R; i++) { // [M+1,R]
            long min = arr[i] - upper; // 最小前缀和
            long max = arr[i] - lower; // 最大前缀和
            while (windowR <= M && arr[windowR] <= max) {
                windowR++; // 满足条件的最大位置
            }
            while (windowL <= M && arr[windowL] < min) {
                windowL++; // 满足条件的最小位置
            }
            ans += windowR - windowL; // 满足条件的前缀和个数
        }
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }
}
