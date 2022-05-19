/**
 * 位运算实现加减乘除
 * 
 * 29. 两数相除 https://leetcode.cn/problems/divide-two-integers/
 * 
 * @author haifuns
 * @date 2022-05-19 18:56
 */
public class Code15_BitAddMinusMuitiDiv {

    /**
     * 加法
     */
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            // a ^ b 得到无进位相加信息
            sum = a ^ b;
            // (a & b) << 1 得到进位信息
            b = (a & b) << 1;
            a = sum;
            // 无进位循环加进位, 直到不再进位
        }
        return sum;
    }

    /**
     * 减法
     */
    public static int minus(int a, int b) {
        // a - b = a + (-b) = a + (~b + 1)
        return add(a, negNum(b));
    }

    /**
     * 乘法, 此方法可以正确处理正负号
     */
    public static int multi(int a, int b) {

        // e.g.:
        //    0000101 -> 5
        //  * 0000110 -> 6
        // ----------
        //    0000000
        //   0000101
        //  0000101
        // =000011110 -> 30

        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }

            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    public static int div(int a, int b) {
        int res = 0;
        return res;
    }

    /**
     * 取负数
     */
    public static int negNum(int n) {
        return add(~n, 1);
    }
}
