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

    /**
     * 除法(正常情况下, 不涉及最小, 最小值无法取绝对值)
     */
    public static int div(int a, int b) {
        int res = 0;

        // 取绝对值
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;

        // x/y
        for (int i = 30; i >= 0; i--) {
            // 除法, x/y, x右移规避符号位问题
            // e.g.:
            //   0110000 -> x: 48
            // / 0000110 -> y: 6
            //      0110 x右移3位, 1<<3, 1000 -> 8
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }

        // 如果符号位不一样取负数
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    /**
     * 除法(所有情况)
     */
    public static int divide(int a, int b) {
		if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
			return 1;
		} else if (b == Integer.MIN_VALUE) {
			return 0;
		} else if (a == Integer.MIN_VALUE) {
			if (b == negNum(1)) {
				return Integer.MAX_VALUE;
			} else {
				int c = div(add(a, 1), b);
				return add(c, div(minus(a, multi(c, b)), b));
			}
		} else {
			return div(a, b);
		}
	}

    /**
     * 是负数
     */
    public static boolean isNeg(int n) {
        return n < 0;
    }

    /**
     * 取负数
     */
    public static int negNum(int n) {
        return add(~n, 1);
    }
}
