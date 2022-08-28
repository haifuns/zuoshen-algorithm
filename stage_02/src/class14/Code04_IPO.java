package class14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 贪心算法，项目安排最大利润
 * 
 * 输入：正数数组costs、正数数组profits、正数K、正数M
 * - costs[i]表示i号项目的花费
 * - profits[i]表示i号项目在扣除花费之后的利润
 * - K表示最多能串行的做K个项目
 * - M表示初始资金
 * 
 * 说明：每做完一个项目，马上就能获得收益，可以支持去做下一个项目。不能并行做项目。
 * 
 * 要求：输出可以获得的最大钱数。
 * 
 * @author haifuns
 * @date 2022-08-28 21:38
 */
public class Code04_IPO {

    // 最多K个项目
    // W是初始资金
    // Profits[] Capital[] 一定等长
    // 返回最终最大的资金
    public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        // 小根堆，按花费排序
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        // 大根堆，按利润排序
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < Profits.length; i++) {
            minCostQ.add(new Program(Profits[i], Capital[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll()); // 当前资金所有可以做的项目，加入利润大根堆
            }
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().p; // 选择利润最大的项目，剩余资金=初始+利润
        }
        return W;
    }

    public static class Program {
        public int p; // 利润
        public int c; // 花费

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }

    }

    public static class MaxProfitComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }

    }
}
