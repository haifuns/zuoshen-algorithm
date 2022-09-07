package class22;

/**
 * 暴力递归到动态规划，拆分整数
 * 
 * @author haifuns
 * @date 2022-09-07 23:21
 */
public class Code03_SplitNumber {

    // n为正数
    public static int ways(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(1, n);
    }

    // 上一个拆出来的数是pre，还剩rest需要去拆，返回拆解的方法数
    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) { // 上一个拆出来的数不能比剩下的大
            return 0;
        }
        int ways = 0;
        for (int first = pre; first <= rest; first++) { // 不能比pre小，pre..rest
            ways += process(first, rest - first);
        }
        return ways;
    }

    public static int dp1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) { // base case
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) { // 从下往上
            for (int rest = pre + 1; rest <= n; rest++) { // 从左往右
                int ways = 0;
                for (int first = pre; first <= rest; first++) { // pre..rest
                    ways += dp[first][rest - first];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    // 动态规划，斜率优化
    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                // 画图观察，当前位置 = dp[pre + 1][rest] 下边 +  dp[pre][rest - pre]
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int test = 39;
        System.out.println(ways(test));
        System.out.println(dp1(test));
        System.out.println(dp2(test));
    }
}
