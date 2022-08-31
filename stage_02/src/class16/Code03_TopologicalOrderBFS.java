package class16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图，拓扑排序(lintcode 127)，bfs，统计入度数
 * 
 * https://www.lintcode.com/problem/127/
 * 
 * @author haifuns
 * @date 2022-08-31 12:58
 */
public class Code03_TopologicalOrderBFS {

    public static class DirectedGraphNode {
        public int label;
        // 邻接点
        public ArrayList<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    // 此题特殊之处在于只给定了图中所有的点
    // bfs实现，统计入度数
    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Integer> indegreeMap = new HashMap<>();
        // 初始化每个点入度数为0
        for (DirectedGraphNode cur : graph) {
            indegreeMap.put(cur, 0);
        }

        // 遍历统计每个点入度数
        for (DirectedGraphNode cur : graph) {
            for (DirectedGraphNode next : cur.neighbors) {
                indegreeMap.put(next, indegreeMap.get(next) + 1);
            }
        }

        // 拓扑队列
        Queue<DirectedGraphNode> zeroQueue = new LinkedList<>();
        // 取出第一层依次压入队列
        for (DirectedGraphNode cur : indegreeMap.keySet()) {
            if (indegreeMap.get(cur) == 0) {
                zeroQueue.add(cur);
            }
        }

        // 拓扑序结果
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while (!zeroQueue.isEmpty()) {
            DirectedGraphNode cur = zeroQueue.poll(); // 弹出一个入度为0的点
            ans.add(cur); // 记到结果里
            for (DirectedGraphNode next : cur.neighbors) { // 当前点所有临接点入度-1
                indegreeMap.put(next, indegreeMap.get(next) - 1);
                if (indegreeMap.get(next) == 0) {
                    zeroQueue.offer(next); // 如果邻接点入度为0压入队列
                }
            }
        }
        return ans;
    }
}
