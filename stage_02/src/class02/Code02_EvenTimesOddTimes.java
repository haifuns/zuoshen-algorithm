package class02;

/**
 * 异或, 找到出现奇数次的数
 * 
 * @author haifuns
 * @date 2022-06-22 19:01
 */
public class Code02_EvenTimesOddTimes {

    // arr中，只有一种数，出现奇数次
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    // arr中，有两种数，出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        int rightOne = eor & (-eor); // 提取出最右的1

        int onlyOne = 0; // eor'
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) { // &运算不等于0, 满足条件条件的是部分出现偶数次的数和一个奇数次的数
                onlyOne ^= arr[i]; // 最终eor'为在eor最右1位置不为1的奇数
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne)); // eor ^ eor'得到另一个奇数
    }

    public static void main(String[] args) {

        int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
        printOddTimesNum1(arr1);

        int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
        printOddTimesNum2(arr2);

    }
}
