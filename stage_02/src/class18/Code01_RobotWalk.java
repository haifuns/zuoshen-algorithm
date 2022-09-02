package class18;

/**
 * 暴力递归到动态规划，固定步数移动到终点
 * 
 * @author haifuns
 * @date 2022-09-02 13:04
 */
public class Code01_RobotWalk {

    /**
     * 固定步数移动到终点
     * 
     * 实现方式1，暴力递归
     * 
     * @param N     路线长度
     * @param start 开始位置
     * @param aim   目标位置
     * @param K     移动次数
     * @return 最多移动方案数
     */
    public static int ways1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return process1(start, K, aim, N);
    }

    /**
     * 从cur出发，走过rest步之后，最终停在aim的方案数
     * 
     * @param cur  当前位置
     * @param rest 剩余步数
     * @param aim  目标位置
     * @param N    路线长度，位置1..n
     * @return 方案数
     */
    public static int process1(int cur, int rest, int aim, int N) {
        if (rest == 0) { // 没有剩余步数，走完了！
            return cur == aim ? 1 : 0; // 当前位置是不是目标位置
        }

        if (cur == 1) { // 走到左边界只能往右走，1 -> 2，步数-1
            return process1(2, rest - 1, aim, N);
        }

        if (cur == N) { // 走到右边界只能往左走，N-1 <- N，步数-1
            return process1(N - 1, rest - 1, aim, N);
        }

        // 在中间位置可以往左走也可以往右走，步数-1
        return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);
    }

    // 实现方式1优化，利用缓存优化暴力递归
    public static int ways2(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }

        // dp就是缓存表，大小 N+1 * K+1
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1; // 初始化值为-1
            }
        }

        // dp[cur][rest] == -1 -> process2(cur, rest)之前没算过！
        // dp[cur][rest] != -1 -> process2(cur, rest)之前算过！返回值，dp[cur][rest]

        return process2(start, K, aim, N, dp);
    }

    // 当前位置cur范围： 1 ~ N
    // 剩余步数rest范围：0 ~ K
    public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest]; // 之前算过返回缓存值
        }

        // 之前没算过
        int ans = 0;
        if (rest == 0) { // 没有剩余步数
            ans = cur == aim ? 1 : 0; // 当前位置是不是目标位置
        } else if (cur == 1) { // 走到左边界
            ans = process2(2, rest - 1, aim, N, dp); // 往右走，步数-1
        } else if (cur == N) { // 走到右边界
            ans = process2(N - 1, rest - 1, aim, N, dp); // 往左走，步数-1
        } else {
            // 可以往左右也可以往右走，方案求和，步数-1
            ans = process2(cur - 1, rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim, N, dp);
        }
        dp[cur][rest] = ans; // 记录缓存
        return ans;

    }

    // 实现方式2：动态规划，直接算出1..n每个点走K步到达目标位置的方案数
    //
    // 先初始化第一列，即剩余步数为0时，只有目标位置方案数为1
    // 依次初始化每一列，
    // - 当前位置在第一行时，只能往右走，也即方案数=数组左下
    // - 当前位置在最后一行时，只能往左走，也即方案数=数组左上
    // - 当前位置在中间，可以往左或右走，也即方案数=数组左上+左下
    //
    // e.g. 目标是4
    // // 0 1 2 3 4 5 6 剩余步数
    // --------------------
    // 0| x x x x x x x
    // 1| 0 0 0 1 0 4 0
    // 2| 0 0 1 0 4 0 13
    // 3| 0 1 0 3 0 9 0
    // 4| 1 0 2 0 5 0 14
    // 5| 0 1 0 2 0 5 0
    // 当前位置
    public static int ways3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }

        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1; // 设置目标位置为1

        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1]; // 第一行，左边界
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1]; // 第n行，右边界
        }
        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }

}
