package class17;

import java.util.ArrayList;
import java.util.List;

/**
 * 经典递归过程，字符串全排列问题
 * 
 * @author haifuns
 * @date 2022-09-01 22:49
 */
public class Code04_PrintAllPermutations {

    // 打印一个字符串的全部排列
    // 第一版递归实现
    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        ArrayList<Character> rest = new ArrayList<Character>();
        for (char cha : str) {
            rest.add(cha);
        }
        String path = "";
        f(rest, path, ans);
        return ans;
    }

    public static void f(ArrayList<Character> rest, String path, List<String> ans) {
        if (rest.isEmpty()) {
            ans.add(path);
        } else {
            int N = rest.size();
            for (int i = 0; i < N; i++) {
                char cur = rest.get(i); // 顺序选择rest中剩余的字符
                rest.remove(i); // 使用完移除
                f(rest, path + cur, ans); // 选择当前字符后，继续从剩余的字符中选择下一个字符
                rest.add(i, cur); // 恢复现场
            }
        }
    }

    // 第二版递归实现（优）
    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g1(str, 0, ans);
        return ans;
    }

    // str[0..index-1]为已决定位置固定，str[index..]需要决定
    public static void g1(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            for (int i = index; i < str.length; i++) {
                swap(str, index, i); // 选择i，放到index位置
                g1(str, index + 1, ans); // 继续决定index+1位置
                swap(str, index, i); // 恢复现场
            }
        }
    }

    // 打印一个字符串的全部排列，要求不要出现重复的排列
    public static List<String> permutation3(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g2(str, 0, ans);
        return ans;
    }

    public static void g2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            boolean[] visited = new boolean[256]; // 记录字符是否已经被使用
            for (int i = index; i < str.length; i++) {
                if (!visited[str[i]]) {
                    visited[str[i]] = true;
                    swap(str, index, i);
                    g2(str, index + 1, ans);
                    swap(str, index, i);
                }
            }
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static void main(String[] args) {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans2 = permutation2(s);
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=======");
        List<String> ans3 = permutation3(s);
        for (String str : ans3) {
            System.out.println(str);
        }

    }
}
