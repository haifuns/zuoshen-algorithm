package class21;

/**
 * 暴力递归到动态规划，最小距离累计和问题，空间压缩优化
 * 
 * @author haifuns
 * @date 2022-09-06 22:13
 */
public class Code01_MinPathSum {

    public static int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0]; // 第0列，上边值 + 当其值
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j]; // 第0行，左边值 + 当其值
        }
        for (int i = 1; i < row; i++) { // 从上往下
            for (int j = 1; j < col; j++) { // 从左到右
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j]; // min(上边值, 左边值) + 当其值
            }
        }
        return dp[row - 1][col - 1]; // 右下角
    }

    // 动态规划，空间压缩优化
    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[] dp = new int[col]; // 列，也可以用行计算，推算过程同理，行列谁短用谁
        dp[0] = m[0][0];
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + m[0][j]; // 初始第0列，左边值 + 当前值
        }
        for (int i = 1; i < row; i++) { // 从上往下
            dp[0] += m[i][0]; // 当前行第一个值，上边 + 当前值
            for (int j = 1; j < col; j++) { // 从左往右
                // dp[j - 1] -> 当前位置左边值，更新过了
                // dp[j] -> 当前位置上边值，没更新还是上一行的值
                dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j]; // min(上边值, 左边值) + 当其值
            }
        }
        return dp[col - 1];
    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));

    }
}
