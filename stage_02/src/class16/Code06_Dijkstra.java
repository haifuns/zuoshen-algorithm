package class16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 图，顶点到其余各顶点的最短路径（Dijkstra算法）
 * 
 * @author haifuns
 * @date 2022-08-31 22:38
 */
public class Code06_Dijkstra {

    public static HashMap<Node, Integer> dijkstra1(Node from) {
        // 距离表，记录点与原始点距离，点 -> 距离
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        // 锁定的点
        HashSet<Node> selectedNodes = new HashSet<>();
        // 没有被锁定，与原始点距离最小的点
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            // 原始点 -> minNode（跳转点），最小距离distance
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight); // 表里不存在新增
                } else {
                    distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight)); // 取原始距离和经过跳转点的最小距离
                }
            }
            selectedNodes.add(minNode); // 锁定跳转点
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes); // 更换跳转点
        }
        return distanceMap;
    }

    /**
     * 从距离表中找到没有被锁定、最小距离的点
     * 
     * @param distanceMap  距离表
     * @param touchedNodes 被锁定的点
     * @return
     */
    public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> touchedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!touchedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    public static class NodeRecord {
        public Node node; // 点
        public int distance; // 距离

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        // 实际的堆结构
        private Node[] nodes;
        // 节点 -> 堆中的位置
        private HashMap<Node, Integer> heapIndexMap;
        // 节点 -> 从源节点出发到该节点的目前最小距离
        private HashMap<Node, Integer> distanceMap;
        // 堆上有多少个点
        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 从源节点出发到达node的距离为distance
        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) { // 在堆上，update
                distanceMap.put(node, Math.min(distanceMap.get(node), distance)); // 更新最小距离
                insertHeapify(heapIndexMap.get(node)); // 值变小了，上移
            }
            if (!isEntered(node)) { // 没进过堆，add
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(size++); // 新增上移
            }
        }

        // 弹出最小记录
        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1); // 把最后一个节点换到0位置
            heapIndexMap.put(nodes[size - 1], -1); // index改成-1
            distanceMap.remove(nodes[size - 1]); // 删除距离
            nodes[size - 1] = null; // 从堆中删除
            heapify(0, --size); // 下移0位置
            return nodeRecord;
        }

        // 上移
        private void insertHeapify(int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) { // 小于父节点
                swap(index, (index - 1) / 2); // 交换，上移
                index = (index - 1) / 2;
            }
        }

        // 下移
        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1
                        : left; // index最小子节点
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index); // 子节点小于index，交换位置
                index = smallest;
                left = index * 2 + 1;
            }
        }

        // 进没进过堆
        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        // 在不在堆上
        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        // 交换位置
        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    // 加强堆改进后的dijkstra算法
    // 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }

}
