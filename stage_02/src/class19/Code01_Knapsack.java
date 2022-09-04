package class19;

/**
 * 暴力递归到动态规划，背包的最大价值
 * 
 * @author haifuns
 * @date 2022-09-04 17:13
 */
public class Code01_Knapsack {

    /**
     * 返回不超重情况下，背包货物的最大价值
     * 
     * 暴力递归实现
     * 
     * @param w   所有货物质量
     * @param v   所有货物价值
     * @param bag 背包容量
     * @return
     */
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }

        return process(w, v, 0, bag);
    }

    /**
     * 从index开始选择包里还能装的最大价值
     * 
     * @param w     所有货物质量
     * @param v     所有货物价值
     * @param index 当前货物位置
     * @param rest  剩余容量
     * @return
     */
    public static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, rest); // 不要当前货
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]); // 要当前货
        if (next != -1) {
            p2 = v[index] + next; // 不要index了，减掉index价值
        }
        return Math.max(p1, p2);
    }

    // 动态规划实现
    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        // index 0 ~ N
        // rest 0 ~ bag
        int[][] dp = new int[N + 1][bag + 1];

        // dp[N][..] = 0，从倒数第二行往上填
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest]; // 不要货物，下一行、容量不变位置
                int p2 = 0;
                // 要货物，如果容量够，下一行、容量减当前货质量位置
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }
}
