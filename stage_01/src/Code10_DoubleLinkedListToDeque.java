import java.util.Deque;
import java.util.LinkedList;

/**
 * 双链表实现双端队列
 * 
 * @author haifuns
 * @date 2022-04-29 10:32
 */
public class Code10_DoubleLinkedListToDeque {

    public static class Node<V> {

        private V value;

        private Node<V> last;

        private Node<V> next;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class MyDeque<V> {

        private Node<V> head;

        private Node<V> tail;

        private int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void pushHead(V v) {
            Node<V> cur = new Node<V>(v);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
            size++;
        }

        public void pushTail(V v) {
            Node<V> cur = new Node<V>(v);
            if (tail == null) {
                head = cur;
                tail = cur;
            } else {
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
            size++;
        }

        public V pollHead() {
            V ans = null;
            if (head == null) {
                return ans;
            }
            ans = head.value;
            size--;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
            }
            return ans;
        }

        public V pollTail() {
            V ans = null;
            if (tail == null) {
                return ans;
            }
            ans = tail.value;
            size--;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
            }
            return ans;
        }

        public V peekHead() {
            V ans = null;
            if (head != null) {
                ans = head.value;
            }
            return ans;
        }

        public V peekTail() {
            V ans = null;
            if (tail != null) {
                ans = tail.value;
            }
            return ans;
        }
    }

    public static void testDeque() {
		MyDeque<Integer> myDeque = new MyDeque<>();
		Deque<Integer> test = new LinkedList<>();
		int testTime = 5000000;
		int maxValue = 200000000;
		System.out.println("测试开始！");
		for (int i = 0; i < testTime; i++) {
			if (myDeque.isEmpty() != test.isEmpty()) {
				System.out.println("Oops!");
			}
			if (myDeque.size() != test.size()) {
				System.out.println("Oops!");
			}
			double decide = Math.random();
			if (decide < 0.33) {
				int num = (int) (Math.random() * maxValue);
				if (Math.random() < 0.5) {
					myDeque.pushHead(num);
					test.addFirst(num);
				} else {
					myDeque.pushTail(num);
					test.addLast(num);
				}
			} else if (decide < 0.66) {
				if (!myDeque.isEmpty()) {
					int num1 = 0;
					int num2 = 0;
					if (Math.random() < 0.5) {
						num1 = myDeque.pollHead();
						num2 = test.pollFirst();
					} else {
						num1 = myDeque.pollTail();
						num2 = test.pollLast();
					}
					if (num1 != num2) {
						System.out.println("Oops!");
					}
				}
			} else {
				if (!myDeque.isEmpty()) {
					int num1 = 0;
					int num2 = 0;
					if (Math.random() < 0.5) {
						num1 = myDeque.peekHead();
						num2 = test.peekFirst();
					} else {
						num1 = myDeque.peekTail();
						num2 = test.peekLast();
					}
					if (num1 != num2) {
						System.out.println("Oops!");
					}
				}
			}
		}
		if (myDeque.size() != test.size()) {
			System.out.println("Oops!");
		}
		while (!myDeque.isEmpty()) {
			int num1 = myDeque.pollHead();
			int num2 = test.pollFirst();
			if (num1 != num2) {
				System.out.println("Oops!");
			}
		}
		System.out.println("测试结束！");
	}

	public static void main(String[] args) {
		testDeque();
	}
}
