/**
 * 冒泡排序
 * 
 * @author haifuns
 * @date 2022-04-21 14:40
 */
public class Code02_BubbleSort {

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int n = arr.length;

        // 0 - n
        // 0 - n-1
        // 0 - n-2
        for (int end = n - 1; end >= 0; end--) {

            // 提前退出冒泡循环的标志位
            boolean flag = false;

            // 0, 1
            // 1, 2
            // 2, 3
            for (int second = 1; second <= end; second++) {
                if (arr[second - 1] > arr[second]) {
                    // 表示有数据交换
                    flag = true;
                    swap(arr, second - 1, second);
                }
            }

            // 没有数据交换，提前退出
            if (!flag) {
                break;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] arg) {
        int[] arr = new int[] { 1, 2, 5, 1, 7, 3, 2, 8 };
        print(arr);
        bubbleSort(arr);
        print(arr);
    }
}
