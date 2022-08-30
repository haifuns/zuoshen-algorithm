package class16;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 图，图结构
 * 
 * @author haifuns
 * @date 2022-08-30 22:42
 */
public class Graph {
    // 包含的点，值 -> 点
    public HashMap<Integer, Node> nodes;
    // 包含的边
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
