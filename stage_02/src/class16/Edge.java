package class16;

/**
 * 图，边结构
 * 
 * @author haifuns
 * @date 2022-08-30 22:41
 */
public class Edge {
    // 权重
    public int weight;
    // 出发点
    public Node from;
    // 到达点
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
