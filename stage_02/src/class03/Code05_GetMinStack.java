package class03;

import java.util.Stack;

/**
 * 栈，实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 * 
 * 要求：
 * 1. pop、push、getMin操作的时间复杂度都是O(1)
 * 2. 设计的栈类型可以使用现成的栈结构
 * 
 * @author haifuns
 * @date 2022-06-24 09:51
 */
public class Code05_GetMinStack {

    public static class MyStack {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (this.stackMin.isEmpty()) {
                this.stackMin.push(newNum);
            } else if (newNum < this.getmin()) {
                this.stackMin.push(newNum);
            } else {
                int newMin = this.stackMin.peek();
                this.stackMin.push(newMin);
            }
            this.stackData.push(newNum);
        }

        public int pop() {
            if (this.stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            this.stackMin.pop();
            return this.stackData.pop();
        }

        public int getmin() {
            if (this.stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return this.stackMin.peek();
        }
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(3);
        System.out.println(stack.getmin());
        stack.push(4);
        System.out.println(stack.getmin());
        stack.push(1);
        System.out.println(stack.getmin());
        System.out.println(stack.pop());
        System.out.println(stack.getmin());
    }
}
