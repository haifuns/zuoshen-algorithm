import java.util.Arrays;

/**
 * 归并排序
 * 
 * O(nlog(n))
 * 
 * @author haifuns
 * @date 2022-06-13 09:47
 */
public class Code27_MergeSort {

    // 递归实现
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }

        // 中点位置 (l+r)/2
        int mid = l + ((r - l) >> 1);

        // 二分后分别排序
        process(arr, l, mid);
        process(arr, mid + 1, r);

        // 合并已经排好序的两块数组
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int mid, int r) {
        // 辅助数组
        int[] help = new int[r - l + 1];

        int i = 0;

        // 用两个指针分别指向[l,mid], (mid, r]
        int p1 = l;
        int p2 = mid + 1;

        // 正常范围, 依次比较, 小的放到辅助数组, 指针后移
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        // 如果p1没越界, p2越界, 把p1剩下的数拷贝到辅助数组
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }

        // 如果p2没越界, p1越界, 把p2剩下的数拷贝到辅助数组
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
    }

    // 非递归实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 非递归核心在于操作步长
        // 1. 步长为2^0, 每1个一组排序
        // 2. 步长为2^1, 每2个一组排序, 最后凑不够单独一组
        // 3. 步长为2^2, 每4个一组排序, 最后凑不够单独一组
        // ...

        int step = 1;
        int n = arr.length;

        while (step < n) {
            int l = 0;
			while (l < n) {
				int mid = 0;
				if (n - l >= step) {
					mid = l + step - 1;
				} else {
                    // 最后凑不够
					mid = n - 1;
				}
				if (mid == n - 1) {
					break;
				}
				int r = 0;
				if (n - 1 - mid >= step) {
					r = mid + step;
				} else {
					r = n - 1;
				}
				merge(arr, l, mid, r);
				if (r == n - 1) {
					break;
				} else {
					l = r + 1;
				}
			}
			if (step > n / 2) {
				break;
			}
			step *= 2;
        }
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
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			mergeSort1(arr1);
            // Arrays.sort(arr2);
            mergeSort2(arr2);
			if (!isEqual(arr1, arr2)) {
				System.out.println("出错了！");
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
