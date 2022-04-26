/**
 * 二分法 - 数据arr[0..n-1]整体无序且满足相邻的数不相等, 返回一个局部最小
 * 
 * 局部最小:
 * 1. arr[0] < arr[1], 则0是局部最小
 * 2. arr[n-2] > arr[n-1], 则n-1是局部最小
 * 3. arr[i-1] < arr[i] < arr[i+1], 则i为局部最小
 * 
 * @author haifuns
 * @date 2022-04-26 09:29
 */
public class Code07_BSAwesome {

    public static int oneMinIndex(int[] arr) {

        if (arr == null || arr.length == 0) {
            return -1;
        }

        if (arr.length == 1) {
            return 0;
        }

        int n = arr.length;

        // 最左局部最小
        if (arr[0] < arr[1]) {
            return 0;
        }

        // 最右局部最小
        if (arr[n - 2] > arr[n - 1]) {
            return n - 1;
        }

        // 普通情况局部最小
        int l = 0;
        int r = n - 1;

        while (l < r - 1) {
            int mid = (l + r) / 2;
            // 直接满足局部最小
            if (arr[mid - 1] > arr[mid] && arr[mid] < arr[mid + 1]) {
                return mid;
            } else {
                // 此时arr最左下降↘, 最右上升↗
                // 如果mid左侧上升, 则0 - mid之间必存在局部最小, ↘ ... ↗, 移动右边界
                if (arr[mid] > arr[mid - 1]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }

        return arr[l] < arr[r] ? l : r;
    }

    /**
     * 生成随机数组且相邻不相等
     */
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int) (Math.random() * maxValue);
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (Math.random() * maxValue);
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    /**
     * 检查局部最小
     */
    public static boolean check(int[] arr, int minIndex) {

        if (arr == null || arr.length == 0) {
            return minIndex == -1;
        }

        int left = minIndex - 1;
        int right = minIndex + 1;
        boolean leftBigger = left >= 0 ? arr[left] > arr[minIndex] : true;
        boolean rightBigger = right < arr.length ? arr[right] > arr[minIndex] : true;

        return leftBigger && rightBigger;
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 20;
        int testTime = 1000000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int ans = oneMinIndex(arr);
            if (!check(arr, ans)) {
                printArray(arr);
                System.out.println(ans);
                succeed = false;
                break;
            }
        }

        System.out.println(succeed ? "没问题" : "出错了");
    }
}
