package class14;

import java.util.PriorityQueue;

/**
 * 贪心算法，最小代价分金（哈夫曼编码问题）
 * 
 * 一块金条切成两半，需要花费和长度相同的铜板。如果长度为20的金条不管怎么切都要花费20个铜板。
 * 例如：给定数组{10,20,30}，表示一共三个人，整块金条长度为60，金条想要分成10，20，30三个部分。
 * 如果先把长度60的金条分成10和50，花费60；再把50分为20和30花费50，总花费110。
 * 如果先把长度60的金条分为30和30，花费60；再把30分为10和20花费30，总花费90。
 * 一群人想要整分整块金条，怎么分割代价最小？
 * 
 * @author haifuns
 * @date 2022-08-28 21:20
 */
public class Code02_LessMoneySplitGold {
    // 纯暴力！
	public static int lessMoney1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		return process(arr, 0);
	}

	// 等待合并的数都在arr里，pre之前的合并行为产生了多少总代价
	// arr中只剩一个数字的时候，停止合并，返回最小的总代价
	public static int process(int[] arr, int pre) {
		if (arr.length == 1) {
			return pre;
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
			}
		}
		return ans;
	}

	public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
		int[] ans = new int[arr.length - 1];
		int ansi = 0;
		for (int arri = 0; arri < arr.length; arri++) {
			if (arri != i && arri != j) {
				ans[ansi++] = arr[arri];
			}
		}
		ans[ansi] = arr[i] + arr[j];
		return ans;
	}

	public static int lessMoney2(int[] arr) {
		PriorityQueue<Integer> pQ = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) {
			pQ.add(arr[i]);
		}
		int sum = 0;
		int cur = 0;
		while (pQ.size() > 1) {
			cur = pQ.poll() + pQ.poll();
			sum += cur;
			pQ.add(cur);
		}
		return sum;
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * (maxValue + 1));
		}
		return arr;
	}

	public static void main(String[] args) {
		int testTime = 100000;
		int maxSize = 6;
		int maxValue = 1000;
		for (int i = 0; i < testTime; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			if (lessMoney1(arr) != lessMoney2(arr)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}
}
