package class23;

/**
 * 暴力递归到动态规划，拆分数组
 * 
 * @author haifuns
 * @date 2022-09-08 23:05
 */
public class Code01_SplitSumClosed {
    public static int right(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		return process(arr, 0, sum / 2);
	}

	// 从arr[i..]自由选择，累加和尽量接近rest，但不能超过rest的情况下，最接近的累加和
	public static int process(int[] arr, int index, int rest) {
		if (index == arr.length) {
			return 0;
		} else { // 还有数，arr[i]这个数
			// 可能性1，不使用arr[i]
			int p1 = process(arr, index + 1, rest);
			// 可能性2，要使用arr[i]
			int p2 = 0;
			if (arr[index] <= rest) {
				p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
			}
			return Math.max(p1, p2);
		}
	}

	public static int dp(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int sum = 0;
		for (int num : arr) {
			sum += num;
		}
		sum /= 2;
		int N = arr.length;
		int[][] dp = new int[N + 1][sum + 1]; // N * (sum/2)
		for (int i = N - 1; i >= 0; i--) { // 从下往上
			for (int rest = 0; rest <= sum; rest++) { // 从左往右
				// 可能性1，不使用arr[i]
				int p1 = dp[i + 1][rest];
				// 可能性2，要使用arr[i]
				int p2 = 0;
				if (arr[i] <= rest) {
					p2 = arr[i] + dp[i + 1][rest - arr[i]];
				}
				dp[i][rest] = Math.max(p1, p2);
			}
		}
		return dp[0][sum];
	}

	public static int[] randomArray(int len, int value) {
		int[] arr = new int[len];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * value);
		}
		return arr;
	}

	public static void printArray(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int maxLen = 20;
		int maxValue = 50;
		int testTime = 10000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * maxLen);
			int[] arr = randomArray(len, maxValue);
			int ans1 = right(arr);
			int ans2 = dp(arr);
			if (ans1 != ans2) {
				printArray(arr);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("测试结束");
	}
}
