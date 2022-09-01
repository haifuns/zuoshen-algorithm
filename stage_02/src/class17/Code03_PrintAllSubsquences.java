package class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 经典递归过程，字符串子序列问题
 * 
 * @author haifuns
 * @date 2022-09-01 22:38
 */
public class Code03_PrintAllSubsquences {

    // 打印一个字符串的全部子序列
    public static List<String> subs(String s) {
        char[] str = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        process1(str, 0, ans, path);
        return ans;
    }

    // str[0..index-1]已经决定过了，决定结果是path，str[index..]需要决定
    // 把所有生成的子序列，放入到ans里
    public static void process1(char[] str, int index, List<String> ans, String path) {
        if (index == str.length) { // 所有字符都决定过了
            ans.add(path); // 结束返回
            return;
        }
        // 没有选择index位置的字符
        process1(str, index + 1, ans, path);
        // 选择index位置的字符
        process1(str, index + 1, ans, path + String.valueOf(str[index]));
    }

    // 打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(str, 0, set, path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }

    public static void process2(char[] str, int index, HashSet<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        String no = path;
        process2(str, index + 1, set, no);
        String yes = path + String.valueOf(str[index]);
        process2(str, index + 1, set, yes);
    }

    public static void main(String[] args) {
        String test = "acccc";
        List<String> ans1 = subs(test);
        List<String> ans2 = subsNoRepeat(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");

    }
}
