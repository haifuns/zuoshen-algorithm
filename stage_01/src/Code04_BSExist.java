import java.util.Arrays;

/**
 * 二分法 - 在有序数组中找到num
 * 
 * @author haifuns
 * @date 2022-04-25 19:13
 */
public class Code04_BSExist {

    public static boolean find(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static boolean test(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = false;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != find(arr, value)) {
                succeed = true;
                break;
            }

        }

        System.out.println(succeed ? "没问题" : "出错了");
    }
}
