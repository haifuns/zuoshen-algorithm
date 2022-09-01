package class17;

import java.util.Stack;

/**
 * 经典递归过程，实现栈逆序
 * 
 * @author haifuns
 * @date 2022-09-01 23:15
 */
public class Code05_ReverseStackUsingRecursive {

    // 逆序栈
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) { // base case，栈空返回
            return;
        }
        int i = f(stack); // 移除并返回栈底元素
        reverse(stack); // 逆序栈中剩下的元素
        stack.push(i); // 重新压入到栈顶
    }

    // 移除并返回栈底元素
    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) { // base case，栈中只剩最后一个元素返回
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }
    }
}
