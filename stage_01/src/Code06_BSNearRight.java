import java.util.Arrays;

/**
 * 二分法 - 在有序数组中找到小于等于num最右的位置
 * 
 * @author haifuns
 * @date 2022-04-26 09:06
 */
public class Code06_BSNearRight {

    public static int mostRightNoLessNumIndex(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int l = 0;
        int r = arr.length - 1;
        int ans = -1;

        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= num) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ans;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int test(int[] arr, int num) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= num) {
                return i;
            }
        }
        return -1;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != mostRightNoLessNumIndex(arr, value)) {
                succeed = false;
                printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(mostRightNoLessNumIndex(arr, value));
                break;
            }

        }

        System.out.println(succeed ? "没问题" : "出错了");
    }
}
