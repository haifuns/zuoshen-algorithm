package class15;

/**
 * 并查集，省份数量
 * 
 * https://leetcode.cn/problems/number-of-provinces/
 * 
 * @author haifuns
 * @date 2022-08-30 12:35
 */
public class Code01_FriendCircles {
    public static int findCircleNum(int[][] M) {
        int N = M.length;
        // 0..N-1，初始集合数=城市数
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) { // 只遍历矩阵右上部分
                if (M[i][j] == 1) { // i和j互相认识
                    unionFind.union(i, j); // 合并集合
                }
            }
        }
        return unionFind.sets();
    }

    public static class UnionFind {
        // parent[i]=k -> i的父亲是k
        private int[] parent;
        // size[i]=k -> i所在的集合大小是k，如果i是代表节点，size[i]才有意义，否则无意义
        private int[] size;
        // 辅助结构
        private int[] help;
        // 一共有多少个集合
        private int sets;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i; // 初始代表节点都是自己
                size[i] = 1; // 每个集合大小是1
            }
        }

        // 从i开始一直往上，往上到不能再往上，代表节点，返回
        // 过程中做路径压缩优化
        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) { // parent是自己为代表节点
                help[hi++] = i; // help中存放的是链上经过的节点
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i; // 路径压缩，链上经过的所有节点都指向代表节点
            }
            return i;
        }

        public void union(int i, int j) {
            int f1 = find(i); // i所在集合代表节点
            int f2 = find(j); // j所在集合代表节点
            if (f1 != f2) { // 不在同一个集合
                if (size[f1] >= size[f2]) { // 小集合挂到大集合上
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--; // 集合总数量-1
            }
        }

        public int sets() {
            return sets;
        }
    }
}
