package class07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 堆，最大线段重合问题
 * 
 * 给定很多线段，线段用[start,end]表示线段开始和结束位置，左右都是闭区间。
 * 
 * 规定：
 * 1. 线段的开始和结束位置一定是整数值
 * 2. 线段重合区域的长度一定>=1
 * 
 * 要求返回最多重合区域中，包含了几段线段。
 * 
 * @author haifuns
 * @date 2022-07-05 19:21
 */
public class Code01_CoverMax {

    // 暴力方法 O((max - min) * N)
    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE; // 所有线段最小值
        int max = Integer.MIN_VALUE; // 所有线段最大值
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) { // 每0.5统计在范围内的线段数
            int cur = 0; // 包含当前0.5的线段数
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur); // 重合最多的0.5对应的线段数量
        }
        return cover;
    }

    // 堆实现 O(N*logN)
    public static int maxCover2(int[][] m) {
        // 先按照线段左位置start排序
        Arrays.sort(m, (a, b) -> (a[0] - b[0]));
        // 准备好小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int[] line : m) {
            while (!heap.isEmpty() && heap.peek() <= line[0]) {
                heap.poll(); // 弹出所有小于start的数
            }
            heap.add(line[1]); // end加入小根堆
            max = Math.max(max, heap.size()); // 小根堆里剩下的是跟当前线段重合的线段数量
        }
        return max;
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static class Line {
        public int start;
        public int end;

        public Line(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }

    }

    public static void main(String[] args) {

        Line l1 = new Line(4, 9);
        Line l2 = new Line(1, 4);
        Line l3 = new Line(7, 15);
        Line l4 = new Line(2, 4);
        Line l5 = new Line(4, 6);
        Line l6 = new Line(3, 7);

        // 底层堆结构，heap
        PriorityQueue<Line> heap = new PriorityQueue<>(new StartComparator());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        while (!heap.isEmpty()) {
            Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }
}
