package class16;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * 图，最小生成树之Kruskal算法
 * 
 * @author haifuns
 * @date 2022-08-31 20:01
 */
public class Code04_Kruskal {

    // 并查集
    public static class UnionFind {
        // 某一个节点 -> 节点往上的节点
        private HashMap<Node, Node> fatherMap;
        // 某一个集合的代表节点 -> 代表节点所在集合的节点个数
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }

        public void makeSets(Collection<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node); // 初始化集合，每个点都是集合
                sizeMap.put(node, 1); // 初始化集合代表节点，是自己
            }
        }

        private Node findFather(Node n) {
            Stack<Node> path = new Stack<>();
            while (n != fatherMap.get(n)) {
                path.add(n);
                n = fatherMap.get(n);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), n); // 路径压缩优化
            }
            return n;
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aDai = findFather(a); // 找到代表节点
            Node bDai = findFather(b);
            if (aDai != bDai) { // 不是同一个集合
                int aSetSize = sizeMap.get(aDai); // 集合大小
                int bSetSize = sizeMap.get(bDai);
                if (aSetSize <= bSetSize) { // 小集合挂到大集合上
                    fatherMap.put(aDai, bDai);
                    sizeMap.put(bDai, aSetSize + bSetSize);
                    sizeMap.remove(aDai);
                } else {
                    fatherMap.put(bDai, aDai);
                    sizeMap.put(aDai, aSetSize + bSetSize);
                    sizeMap.remove(bDai);
                }
            }
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }

    }

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        // 从小的边到大的边，依次弹出，小根堆！
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) { // M 条边
            priorityQueue.add(edge); // O(logM)
        }
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) { // M 条边
            Edge edge = priorityQueue.poll(); // O(logM)
            if (!unionFind.isSameSet(edge.from, edge.to)) { // 边的两边节点不在一个集合 O(1)
                result.add(edge); // 保留边
                unionFind.union(edge.from, edge.to); // 合并集合
            }
        }
        return result;
    }
}
