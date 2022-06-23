package class03;

/**
 * 数组实现队列和栈
 * 
 * @author haifuns
 * @date 2022-06-23 23:11
 */
public class Code04_ArrayToQueueAndStack {

    /**
     * 循环数组实现队列
     */
    public static class MyQueue {
		private int[] arr;
		private int pushi;// end
		private int polli;// begin
		private int size;
		private final int limit;

		public MyQueue(int limit) {
			arr = new int[limit];
			pushi = 0;
			polli = 0;
			size = 0;
			this.limit = limit;
		}

		public void push(int value) {
			if (size == limit) {
				throw new RuntimeException("队列满了，不能再加了");
			}
			size++;
			arr[pushi] = value;
			pushi = nextIndex(pushi);
		}

		public int poll() {
			if (size == 0) {
				throw new RuntimeException("队列空了，不能再拿了");
			}
			size--;
			int ans = arr[polli];
			polli = nextIndex(polli);
			return ans;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		// 如果现在的下标是i，返回下一个位置
		private int nextIndex(int i) {
			return i < limit - 1 ? i + 1 : 0;
		}

	}

    public static class MyStack {
        private int top = -1; //栈顶
        private int[] arr;
        public MyStack(int length){
            arr = new int[length];
        }

        public void push(int num){
            if (top > arr.length - 1){
                throw new RuntimeException("栈满了，不能再加了");
            }
            arr[++top] = num;
        }
        public int pop(){
            if (top < 0){
                throw new RuntimeException("栈空了，不能再拿了");
            }
            return arr[top--];
        }
    }

}
