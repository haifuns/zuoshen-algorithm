package class20;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 暴力递归到动态规划，洗咖啡杯问题（京东）
 * 
 * @author haifuns
 * @date 2022-09-05 13:13
 */
public class Code03_Coffee {

	// 暴力递归
	public static int minTime0(int[] arr, int n, int a, int b) {
		int[] times = new int[arr.length];
		int[] drink = new int[n];
		return forceMake(arr, times, 0, drink, n, a, b);
	}

	// 每个人暴力尝试用每一个咖啡机给自己做咖啡
	public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
		if (kth == n) { // 所有咖啡制作完成，判断清洗杯子时间
			int[] drinkSorted = Arrays.copyOf(drink, kth); // 每杯咖啡制作完成时间
			Arrays.sort(drinkSorted);
			return forceWash(drinkSorted, a, b, 0, 0, 0);
		}
		int time = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) { // 每个咖啡机
			int work = arr[i]; // 咖啡机需要的时间
			int pre = times[i]; // 上一杯咖啡结束时间
			drink[kth] = pre + work; // 当前杯咖啡制作完成时间
			times[i] = pre + work; // 咖啡机排队时间
			time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b)); // 下一杯咖啡所有可能..
			drink[kth] = 0;
			times[i] = pre;
		}
		return time;
	}

	public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
		if (index == drinks.length) {
			return time;
		}
		// 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
		int wash = Math.max(drinks[index], washLine) + a;
		int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

		// 选择二：当前index号咖啡杯，选择自然挥发
		int dry = drinks[index] + b;
		int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
		return Math.min(ans1, ans2);
	}

	// 以下为贪心+优良暴力
	public static class Machine {
		public int timePoint; // 时间点
		public int workTime; // 制作咖啡需要的时间

		public Machine(int t, int w) {
			timePoint = t;
			workTime = w;
		}
	}

	public static class MachineComparator implements Comparator<Machine> {

		@Override
		public int compare(Machine o1, Machine o2) {
			return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
		}

	}

	// 优良一点的暴力尝试的方法
	public static int minTime1(int[] arr, int n, int a, int b) {
        // 小根堆
		PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
		for (int i = 0; i < arr.length; i++) {
			heap.add(new Machine(0, arr[i]));
		}
		int[] drinks = new int[n]; // 每个人喝完咖啡时间
		for (int i = 0; i < n; i++) {
			Machine cur = heap.poll();
			cur.timePoint += cur.workTime;
			drinks[i] = cur.timePoint;
			heap.add(cur);
		}
		return bestTime(drinks, a, b, 0, 0);
	}

	// drinks 所有杯子可以开始洗的时间
	// wash 单杯洗干净的时间（串行）
	// air 挥发干净的时间(并行)
	// free 洗的机器什么时候可用
	// drinks[index..] 都变干净，最早的结束时间（返回）
	public static int bestTime(int[] drinks, int wash, int air, int index, int free) {
		if (index == drinks.length) {
			return 0;
		}
		// index号杯子 决定洗
		int selfClean1 = Math.max(drinks[index], free) + wash;
		// 剩余杯子干净时间
		int restClean1 = bestTime(drinks, wash, air, index + 1, selfClean1);
		int p1 = Math.max(selfClean1, restClean1);

		// index号杯子 决定挥发
		int selfClean2 = drinks[index] + air;
		// 剩余杯子干净时间
		int restClean2 = bestTime(drinks, wash, air, index + 1, free);
		int p2 = Math.max(selfClean2, restClean2);
		return Math.min(p1, p2);
	}

	// 贪心+优良尝试改成动态规划
	public static int minTime2(int[] arr, int n, int a, int b) {
		PriorityQueue<Machine> heap = new PriorityQueue<Machine>(new MachineComparator());
		for (int i = 0; i < arr.length; i++) {
			heap.add(new Machine(0, arr[i]));
		}
		int[] drinks = new int[n];
		for (int i = 0; i < n; i++) {
			Machine cur = heap.poll();
			cur.timePoint += cur.workTime;
			drinks[i] = cur.timePoint;
			heap.add(cur);
		}
		return bestTimeDp(drinks, a, b);
	}

	public static int bestTimeDp(int[] drinks, int wash, int air) {
		int N = drinks.length;
		int maxFree = 0;
		for (int i = 0; i < drinks.length; i++) {
			maxFree = Math.max(maxFree, drinks[i]) + wash;
		}
		int[][] dp = new int[N + 1][maxFree + 1];
		// 最下层都是0
		for (int index = N - 1; index >= 0; index--) { // 从下往上填
			for (int free = 0; free <= maxFree; free++) { // 从左往右填
				int selfClean1 = Math.max(drinks[index], free) + wash;
				if (selfClean1 > maxFree) {
					break; // 因为后面的也都不用填了
				}
				// index号杯子 决定洗
				int restClean1 = dp[index + 1][selfClean1];
				int p1 = Math.max(selfClean1, restClean1);
				// index号杯子 决定挥发
				int selfClean2 = drinks[index] + air;
				int restClean2 = dp[index + 1][free];
				int p2 = Math.max(selfClean2, restClean2);
				dp[index][free] = Math.min(p1, p2);
			}
		}
		return dp[0][0];
	}

	// for test
	public static int[] randomArray(int len, int max) {
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			arr[i] = (int) (Math.random() * max) + 1;
		}
		return arr;
	}

	// for test
	public static void printArray(int[] arr) {
		System.out.print("arr : ");
		for (int j = 0; j < arr.length; j++) {
			System.out.print(arr[j] + ", ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int len = 10;
		int max = 10;
		int testTime = 10;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(len, max);
			int n = (int) (Math.random() * 7) + 1;
			int a = (int) (Math.random() * 7) + 1;
			int b = (int) (Math.random() * 10) + 1;
			int ans1 = minTime0(arr, n, a, b);
			int ans2 = minTime1(arr, n, a, b);
			int ans3 = minTime2(arr, n, a, b);
			if (ans1 != ans2 || ans2 != ans3) {
				printArray(arr);
				System.out.println("n : " + n);
				System.out.println("a : " + a);
				System.out.println("b : " + b);
				System.out.println(ans1 + " , " + ans2 + " , " + ans3);
				System.out.println("===============");
				break;
			}
		}
		System.out.println("测试结束");

	}
}
