package class18;

/**
 * 暴力递归到动态规划，预测赢家
 * 
 * @author haifuns
 * @date 2022-09-02 19:33
 */
public class Code02_CardsInLine {

    // 根据规则，返回获胜者的分数
    // 暴力递归
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr, 0, arr.length - 1); // 先手最大分数
        int second = g1(arr, 0, arr.length - 1); // 后手最大分数
        return Math.max(first, second);
    }

    // arr[L..R]，先手获得的最好分数返回
    public static int f1(int[] arr, int L, int R) {
        if (L == R) { // 只剩一张牌时先手获得
            return arr[L];
        }
        int p1 = arr[L] + g1(arr, L + 1, R); // 拿L，下一手后手
        int p2 = arr[R] + g1(arr, L, R - 1); // 拿R，下一手后手
        return Math.max(p1, p2); // 先手拿大
    }

    // arr[L..R]，后手获得的最好分数返回
    public static int g1(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int p1 = f1(arr, L + 1, R); // 对手拿走了L位置的数，后手变 L+1..R 先手
        int p2 = f1(arr, L, R - 1); // 对手拿走了R位置的数，后手变 L..R-1 先手
        return Math.min(p1, p2); // 对手先拿，后手一定拿的小的
    }

    // 暴力递归缓存优化
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N]; // 先手缓存
        int[][] gmap = new int[N][N]; // 后手缓存
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }
        int first = f2(arr, 0, arr.length - 1, fmap, gmap);
        int second = g2(arr, 0, arr.length - 1, fmap, gmap);
        return Math.max(first, second);
    }

    // arr[L..R]，先手获得的最好分数返回
    public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (fmap[L][R] != -1) {
            return fmap[L][R];
        }
        int ans = 0;
        if (L == R) {
            ans = arr[L];
        } else {
            int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
            int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
            ans = Math.max(p1, p2);
        }
        fmap[L][R] = ans;
        return ans;
    }

    // arr[L..R]，后手获得的最好分数返回
    public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
        if (gmap[L][R] != -1) {
            return gmap[L][R];
        }
        int ans = 0;
        if (L != R) {
            int p1 = f2(arr, L + 1, R, fmap, gmap); // 对手拿走了L位置的数
            int p2 = f2(arr, L, R - 1, fmap, gmap); // 对手拿走了R位置的数
            ans = Math.min(p1, p2);
        }
        gmap[L][R] = ans;
        return ans;
    }

    // 动态规划实现
    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            fmap[i][i] = arr[i]; // 初始化L=R 位置，先手arr[i]，后手0
        }
        for (int startCol = 1; startCol < N; startCol++) { // 列，1..n
            int L = 0;
            int R = startCol;
            while (R < N) { // 沿着对角线推算，每次处理前一个位置右下位置，表格左下部分L>R无用
                fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]); // gmap 对应 fmap 位置，左一格、下一格
                gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]); // fmap 对应 gmap 位置，左一格、下一格
                L++;
                R++;
            }
        }
        return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));

    }
}
