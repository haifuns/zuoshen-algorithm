package class22;

/**
 * 暴力递归到动态规划，打死怪兽的概率
 * 
 * @author haifuns
 * @date 2022-09-07 22:22
 */
public class Code01_KillMonster {

    public static double right(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K); // 总的可能性，(M+1)^K
        long kill = process(K, M, N);
        return (double) ((double) kill / (double) all);
    }

    // 怪兽还剩hp点血，每次的伤害在[0~M]范围上，还有times次可以砍，返回砍死的情况数
    public static long process(int times, int M, int hp) {
        if (times == 0) { // 没有次数了
            return hp <= 0 ? 1 : 0; // 血量小于0成功
        }
        if (hp <= 0) { // 没血了
            return (long) Math.pow(M + 1, times); // 剩下的所有情况都能成功，(M+1)^times
        }
        long ways = 0;
        for (int i = 0; i <= M; i++) { // 尝试掉 0 ~ M 滴血，每种情况概率
            ways += process(times - 1, M, hp - i);
        }
        return ways;
    }

    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1]; // 剩余步数 * 剩余血量
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) { // 从上往下
            dp[times][0] = (long) Math.pow(M + 1, times); // 已经没血了，剩下的所有步数都能成功
            for (int hp = 1; hp <= N; hp++) { // 从左往右
                long ways = 0;
                for (int i = 0; i <= M; i++) { // 枚举掉血数
                    if (hp - i >= 0) { // 还有血量
                        ways += dp[times - 1][hp - i]; // 位置转移，上一行
                    } else { // 没血了
                        ways += (long) Math.pow(M + 1, times - 1);
                    }
                }
                dp[times][hp] = ways;
            }
        }
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    // 优化枚举
    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                 // 画图观察，当前位置值 = dp[times-1][hp-m..hp]
                 // = dp[times-1][hp-1-m..hp] - dp[times-1][hp-1-m] + dp[times-1][hp] 
                 // = dp[times][hp-1] + dp[times-1][hp] - dp[times-1][hp-1-m]
                 // = 左边 + 上边 - dp[times-1][hp-1-m]
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - 1 - M >= 0) { // 不越界
                    // 减去多的格子
                    dp[times][hp] -= dp[times - 1][hp - 1 - M]; 
                } else {
                    // 越界了，要减去的格子概率等于上一行0位置
                    dp[times][hp] -= Math.pow(M + 1, times - 1);
                }
            }
        }
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right(N, M, K);
            double ans2 = dp1(N, M, K);
            double ans3 = dp2(N, M, K);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
