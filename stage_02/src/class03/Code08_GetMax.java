package class03;

/**
 * 递归, 从数组中找到最大值
 * 
 * @author haifuns
 * @date 2022-06-26 21:29
 */
public class Code08_GetMax {

    // 求arr中的最大值
	public static int getMax(int[] arr) {
		return process(arr, 0, arr.length - 1);
	}

    // arr[l..r]位置找到最大值
    public static int process(int[] arr, int l, int r) {
        // arr[l..r]范围只有一个数, 直接返回, bad case
        if (l == r) {
            return arr[l];
        }

        // 中点
        int mid = l + ((r - l) >> 1);
        int leftMax = process(arr, l, mid);
        int rightMax = process(arr, mid + 1, r);
        return Math.max(leftMax, rightMax);
    }

}
