package class16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * 图，拓扑排序(lintcode 127)，dfs，统计点次排序
 * 
 * https://www.lintcode.com/problem/127/
 * 
 * @author haifuns
 * @date 2022-08-31 13:14
 */
public class Code03_TopologicalOrderDFS2 {

	public static class DirectedGraphNode {
		public int label;
		public ArrayList<DirectedGraphNode> neighbors;

		public DirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<DirectedGraphNode>();
		}
	}

	public static class Record {
		public DirectedGraphNode node; // 点
		public long nodes; // 点次

		public Record(DirectedGraphNode n, long o) {
			node = n;
			nodes = o;
		}
	}

	public static class MyComparator implements Comparator<Record> {

		@Override
		public int compare(Record o1, Record o2) {
			return o1.nodes == o2.nodes ? 0 : (o1.nodes > o2.nodes ? -1 : 1);
		}
	}

	// dfs思路：统计每个点的点次，点次排序对应拓扑排序
	public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		HashMap<DirectedGraphNode, Record> order = new HashMap<>();
		for (DirectedGraphNode cur : graph) {
			f(cur, order); // 计算每个点的点次
		}
		ArrayList<Record> recordArr = new ArrayList<>();
		for (Record r : order.values()) {
			recordArr.add(r);
		}
		recordArr.sort(new MyComparator()); // 排序，点次高在前
		ArrayList<DirectedGraphNode> ans = new ArrayList<DirectedGraphNode>();
		for (Record r : recordArr) {
			ans.add(r.node); // 点次排序对应拓扑排序
		}
		return ans;
	}

	/**
	 * 当前来到cur点，返回cur点所到之处，所有的点次
	 * 
	 * @param cur   当前点
	 * @param order 缓存，点 -> 点次
	 * @return （cur，点次）
	 */
	public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
		if (order.containsKey(cur)) {
			return order.get(cur);
		}
		// cur的点次之前没算过！
		long nodes = 0;
		for (DirectedGraphNode next : cur.neighbors) {
			nodes += f(next, order).nodes;
		}
		Record ans = new Record(cur, nodes + 1);
		order.put(cur, ans);
		return ans;
	}
}
