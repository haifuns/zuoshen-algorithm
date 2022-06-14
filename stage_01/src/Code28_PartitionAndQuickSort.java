import java.util.Arrays;
import java.util.Stack;
/**
 * 快速排序
 * 
 * @author haifuns
 * @date 2022-06-14 09:39
 */
public class Code28_PartitionAndQuickSort {

	// 在arr[l...r]范围上, 对arr[r]做划分值
    // 对于l...r范围, 小于arr[r]在左边, 等于在中间, 大于在右边
    public static int[] partition(int[] arr, int l, int r) {
        // 小于区域右边界
        int lessR = l - 1;
        // 大于区域左边界
        int moreL = r;
        int index = l;
        while (index < moreL) {
            if (arr[index] < arr[r]) {
                // 如果小于, 跟小于区域右侧第一个值交换, 继续处理下一个值
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[r]) {
                // 如果大于, 跟大于区域左侧第一个值交换, 交换完不需要移动index
                swap(arr, --moreL, index);
            } else {
                index++;
            }
        }
        // 交换大于区域左侧第一个值和目标arr[r]
        swap(arr, moreL, r);

        // 返回等于位置左右边界
        return new int[] { lessR + 1, moreL };
    }

	// 快排递归实现
	public static void quickSort1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}

	public static void process(int[] arr, int l, int r) {
		if (l >= r) {
			return;
		}
		int[] equalE = partition(arr, l, r);
		process(arr, l, equalE[0] - 1);
		process(arr, equalE[1] + 1, r);
	}

	// 快排非递归实现
	public static void quickSort2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}

		Stack<Job> stack = new Stack<>();
		stack.push(new Job(0, arr.length - 1));

		while (!stack.isEmpty()) {
			Job cur = stack.pop();
			int[] equals = partition(arr, cur.l, cur.r);
			if (equals[0] > cur.l) {
				// 如果有小于区域
				stack.push(new Job(cur.l, equals[0] - 1));
			}
			if (equals[1] > cur.r) {
				// 如果有大于区域
				stack.push(new Job(equals[1] + 1, cur.r));
			}
		}
	}

	public static class Job {
		private int l;
		private int r;

		public Job(int l, int r) {
			this.l = l;
			this.r = r;
		}
	}

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		System.out.println("test begin");
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			int[] arr3 = copyArray(arr1);
			Arrays.sort(arr2);
			quickSort1(arr1);
			quickSort2(arr3);

			if (!isEqual(arr1, arr2) && !isEqual(arr3, arr2)) {
				System.out.println("Oops!");
				succeed = false;
				break;
			}
		}
		System.out.println("test end");
		System.out.println(succeed ? "Nice!" : "Oops!");

	}
}
