package class11;

/**
 * 二叉树，打印纸条折痕问题
 * 
 * 请把一张纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
 * 如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次，请从上到下打印所有折痕的方向。
 * 
 * @author haifuns
 * @date 2022-07-30 15:43
 */
public class Code07_PaperFolding {

    // 模拟对折几次可以发现，每次对折都是在上一次的每条折痕前新增一条凹折痕，后新增一条凸折痕，形成一个二叉树
    // 折痕打印问题也对应着二叉树的中序遍历

    public static void printAllFolds(int N) {
        process(1, N, true); // 根节点是凹痕
        System.out.println();
    }

    // 中序打印整棵树，i是当前层，N是总层数
    public static void process(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        process(i + 1, N, true); // 左节点凹痕
        System.out.print(down ? "凹 " : "凸 ");
        process(i + 1, N, false); // 右节点凸痕
    }

    public static void main(String[] args) {
        int N = 3;
        printAllFolds(N);
    }
}
