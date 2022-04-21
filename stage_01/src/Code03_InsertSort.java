/**
 * 插入排序
 * 
 * @author haifuns
 * @date 2022-04-21 14:41
 */
public class Code03_InsertSort {

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
        // bubbleSort(arr);
        print(arr);
    }
}
