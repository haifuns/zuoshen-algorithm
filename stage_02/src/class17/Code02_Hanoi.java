package class17;

import java.util.Stack;

/**
 * 经典递归过程，汉诺（Hanoi）塔问题
 * 
 * @author haifuns
 * @date 2022-09-01 13:23
 */
public class Code02_Hanoi {

	// 递归实现
	public static void hanoi1(int n) {
		leftToRight(n); // 1..n从左到右
	}

	// 1~N层圆盘 从左 -> 右
	public static void leftToRight(int n) {
		if (n == 1) { // base case，只有一个盘，直接移
			System.out.println("Move 1 from left to right");
			return;
		}
		leftToMid(n - 1); // 1..n-1从左到中
		System.out.println("Move " + n + " from left to right"); // n从左到右
		midToRight(n - 1); // 1..n-1从中到右
	}

	// 1~N层圆盘 从左 -> 中
	public static void leftToMid(int n) {
		if (n == 1) { // base case，只有一个盘，直接移
			System.out.println("Move 1 from left to mid");
			return;
		}
		leftToRight(n - 1); // 1..n-1从左到右
		System.out.println("Move " + n + " from left to mid"); // n从左到中
		rightToMid(n - 1); // 1..n-1从右到中
	}

	// 1~N层圆盘 从右 -> 中
	public static void rightToMid(int n) {
		if (n == 1) { // base case，只有一个盘，直接移
			System.out.println("Move 1 from right to mid");
			return;
		}
		rightToLeft(n - 1); // 1..n-1从右到左
		System.out.println("Move " + n + " from right to mid"); // n从右到中
		leftToMid(n - 1); // 1..n-1从中到右
	}

	// 1~N层圆盘 从中 -> 右
	public static void midToRight(int n) {
		if (n == 1) { // base case，只有一个盘，直接移
			System.out.println("Move 1 from mid to right");
			return;
		}
		midToLeft(n - 1); // 1..n-1从中到左
		System.out.println("Move " + n + " from mid to right"); // n从中到右
		leftToRight(n - 1); // 1..n-1从左到右
	}

	// 1~N层圆盘 从中 -> 左
	public static void midToLeft(int n) {
		if (n == 1) { // base case，只有一个盘，直接移
			System.out.println("Move 1 from mid to left");
			return;
		}
		midToRight(n - 1); // 1..n-1从中到右
		System.out.println("Move " + n + " from mid to left"); // n从中到左
		rightToLeft(n - 1); // 1..n-1从右到左
	}

	// 1~N层圆盘 从右 -> 左
	public static void rightToLeft(int n) {
		if (n == 1) { // base case，只有一个盘，直接移
			System.out.println("Move 1 from right to left");
			return;
		}
		rightToMid(n - 1); // 1..n-1从右到中
		System.out.println("Move " + n + " from right to left"); // n从右到左
		midToLeft(n - 1); // 1..n-1从中到左
	}

	// 递归简化实现，6种移动动作合一
	public static void hanoi2(int n) {
		if (n > 0) {
			func(n, "left", "right", "mid"); // 1..n 从左到右，剩余中间的柱子
		}
	}

	// 1..n从from移动到to，other为剩余的柱子
	public static void func(int N, String from, String to, String other) {
		if (N == 1) { // base case，只有一个盘，直接移
			System.out.println("Move 1 from " + from + " to " + to);
		} else {
			func(N - 1, from, other, to); // 1..n-1从from移动到other，剩余to
			System.out.println("Move " + N + " from " + from + " to " + to); // n从from移动到to
			func(N - 1, other, to, from); // 1..n-1从other移动到to，剩余from
		}
	}

	public static class Record {
		public boolean finish1;
		public int base;
		public String from;
		public String to;
		public String other;

		public Record(boolean f1, int b, String f, String t, String o) {
			finish1 = false;
			base = b;
			from = f;
			to = t;
			other = o;
		}
	}

	// 非递归实现
	public static void hanoi3(int N) {
		if (N < 1) {
			return;
		}
		Stack<Record> stack = new Stack<>();
		stack.add(new Record(false, N, "left", "right", "mid"));
		while (!stack.isEmpty()) {
			Record cur = stack.pop();
			if (cur.base == 1) {
				System.out.println("Move 1 from " + cur.from + " to " + cur.to);
				if (!stack.isEmpty()) {
					stack.peek().finish1 = true;
				}
			} else {
				if (!cur.finish1) {
					stack.push(cur);
					stack.push(new Record(false, cur.base - 1, cur.from, cur.other, cur.to));
				} else {
					System.out.println("Move " + cur.base + " from " + cur.from + " to " + cur.to);
					stack.push(new Record(false, cur.base - 1, cur.other, cur.to, cur.from));
				}
			}
		}
	}

	public static void main(String[] args) {
		int n = 3;
		hanoi1(n);
		System.out.println("============");
		hanoi2(n);
		// System.out.println("============");
		// hanoi3(n);
	}
}
