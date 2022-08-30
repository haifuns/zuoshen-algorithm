package class16;

/**
 * 图，图结构数据转换
 * 
 * @author haifuns
 * @date 2022-08-30 22:49
 */
public class GraphGenerator {

    /**
     * 将以 N*3 矩阵表示所有的边的图转换为需要的图结构
     * 
     * e.g.: [[5, 0, 7], [3, 0, 1]]
     * 
     *          0
     *        5/  \3
     *       /      \
     *      7        1
     * 
     * 每个元素 -> [weight, from节点上面的值，to节点上面的值]
     * 
     * @param matrix N*3 边矩阵
     * @return 需要的图结构
     */
    public static Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            // 拿到每一条边， matrix[i]
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            // 记录点
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from); // 出发点
            Node toNode = graph.nodes.get(to); // 到达点
            Edge newEdge = new Edge(weight, fromNode, toNode); // 边
            fromNode.nexts.add(toNode); // 后面的点挂到前面点上
            fromNode.out++; // 前面点出度+1
            toNode.in++; // 后面点入度+1
            fromNode.edges.add(newEdge); // 边挂到前面点上
            graph.edges.add(newEdge); // 边记录到图上
        }
        return graph;
    }
}
