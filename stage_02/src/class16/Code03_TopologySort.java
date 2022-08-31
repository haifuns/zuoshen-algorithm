package class16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 图，拓扑排序
 * 
 * @author haifuns
 * @date 2022-08-31 12:47
 */
public class Code03_TopologySort {

    // 有向图且无环
    public static List<Node> sortedTopology(Graph graph) {
        // 节点 -> 剩余的入度
        HashMap<Node, Integer> inMap = new HashMap<>();
        // 剩余入度为0的点
        Queue<Node> zeroInQueue = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroInQueue.add(node);
            }
        }
        List<Node> result = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next) - 1); // 所有的邻接点入度-1
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next); // 如果邻接点入度为0，记录
                }
            }
        }
        return result;
    }
}
