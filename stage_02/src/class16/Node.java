package class16;

import java.util.ArrayList;

/**
 * 图，点结构
 * 
 * @author haifuns
 * @date 2022-08-30 22:36
 */
public class Node {
    // 点值
    public int value;
    // 入度，顶点被指向的箭头个数
    public int in;
    // 出度，顶点指出去的箭头个数
    public int out;
    // 后继点
    public ArrayList<Node> nexts;
    // 指出去的边
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
