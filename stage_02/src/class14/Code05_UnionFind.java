package class14;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集
 * 
 * @author haifuns
 * @date 2022-08-29 12:51
 */
public class Code05_UnionFind {
    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind<V> {
        // 样本 -> 封装对象
        public HashMap<V, Node<V>> nodes;
        // 封装对象 -> 封装对象，表实现父指针
        public HashMap<Node<V>, Node<V>> parents;
        // 集合代表节点 -> 集合大小
        public HashMap<Node<V>, Integer> sizeMap;

        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                parents.put(node, node); // 初始化时每一个节点指向自己
                sizeMap.put(node, 1); // 初始化时每一个节点都是自己所在集合的代表节点
            }
        }

        // 找到给定节点的代表节点
        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }

            // 此时cur为代表节点

            while (!path.isEmpty()) {
                // 优化点，减少链长度
                // 经过的所有节点都直接指向代表节点
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        // a b样本是否在一个集合
        public boolean isSameSet(V a, V b) {
            // 找a b的代表节点，比较是否是一个
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            // a代表节点
            Node<V> aHead = findFather(nodes.get(a));
            // b代表节点
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                // a所在集合大小
                int aSetSize = sizeMap.get(aHead);
                // b所在集合大小
                int bSetSize = sizeMap.get(bHead);
                // 大集合代表节点
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                // 小集合代表节点
                Node<V> small = big == aHead ? bHead : aHead;
                // 优化点，小挂到大上，减少链长度
                // 小集合代表节点指向大集合代表节点
                parents.put(small, big);
                // 更新大集合代表节点对应的集合大小
                sizeMap.put(big, aSetSize + bSetSize);
                // 删除小集合
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }

    }
}
