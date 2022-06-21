package class02;

/**
 * 异或, 不用额外变量交换两个数
 * 
 * @author haifuns
 * @date 2022-06-21 23:11
 */
public class Code01_XorSwap {

    public static void main(String[] args) {
        int a = 16;
        int b = 61;

        System.out.println(a);
        System.out.println(b);

        a = a ^ b;
        b = a ^ b; // a ^ b ^ b = a
        a = a ^ b; // a ^ b ^ a = b

        System.out.println(a);
        System.out.println(b);

        int[] arr = { 1, 6, 16, 61 };
        System.out.println(arr[0]);
        System.out.println(arr[2]);

        swap(arr, 0, 2);

        System.out.println(arr[0]);
        System.out.println(arr[2]);
    }

    // 异或实现数组位置交换
    // 前提是i, j不为同一个位置
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j]; // arr[i] ^ arr[j] ^ arr[j] = arr[i]
        arr[i] = arr[i] ^ arr[j]; // arr[i] ^ arr[j] ^ arr[i] = arr[j]
    }
}