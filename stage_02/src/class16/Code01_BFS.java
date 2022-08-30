package class16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图，宽度优先遍历
 * 
 * @author haifuns
 * @date 2022-08-30 23:18
 */
public class Code01_BFS {

    // 从node出发，进行宽度优先遍历
    public static void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        // 记录已经进入过队列的节点，防止出现回路重复遍历
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
}
