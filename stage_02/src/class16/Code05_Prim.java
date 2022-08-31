package class16;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 图，最小生成树之Prim算法
 * 
 * @author haifuns
 * @date 2022-08-31 22:14
 */
public class Code05_Prim {

    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    public static Set<Edge> primMST(Graph graph) {
        // 解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

        // 哪些点被解锁出来了
        HashSet<Node> nodeSet = new HashSet<>();

        Set<Edge> result = new HashSet<>(); // 依次挑选的的边在result里

        for (Node node : graph.nodes.values()) { // 随便挑一个点，循环的目的是防止森林
            // node 是开始点
            if (!nodeSet.contains(node)) {
                nodeSet.add(node); // 接入到解锁点集合
                for (Edge edge : node.edges) { // 由一个点，解锁所有相连的边
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll(); // 弹出解锁的边中，最小的边
                    Node toNode = edge.to; // 可能的一个新的点
                    if (!nodeSet.contains(toNode)) { // 不含有的时候，就是新的点
                        nodeSet.add(toNode); // 解锁点
                        result.add(edge); // 记录有效边
                        for (Edge nextEdge : toNode.edges) { // 解锁<解锁点>的所有边
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
            // break; // 确定图只有一条线时可以直接跳过
        }
        return result;
    }

    // 请保证graph是连通图
    // graph[i][j]表示点i到点j的距离，如果是系统最大值代表无路
    // 返回值是最小连通图的路径之和
    public static int prim(int[][] graph) {
        int size = graph.length; // 线数量
        int[] distances = new int[size]; // 某个点到其他点的距离
        boolean[] visit = new boolean[size]; //是否访问过点

        // 设置第一个点为出发点
        visit[0] = true; // 访问过自己
        for (int i = 0; i < size; i++) {
            distances[i] = graph[0][i]; // 记录出发点到其他每个点的距离
        }

        int sum = 0;
        for (int i = 1; i < size; i++) { // 遍历其他每一个点
            int minPath = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] < minPath) { // 没有访问过，并且和目标点有距离
                    minPath = distances[j];
                    minIndex = j; // 当前点位置
                }
            }
            if (minIndex == -1) {
                return sum;
            }
            visit[minIndex] = true; // 标记访问
            sum += minPath;
            for (int j = 0; j < size; j++) {
                if (!visit[j] && distances[j] > graph[minIndex][j]) {
                    distances[j] = graph[minIndex][j]; // 更新目标点，重新记录与其他没有访问过点的距离
                }
            }
        }
        return sum;
    }
}
