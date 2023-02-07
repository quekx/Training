package contest.lc2022;

import java.util.*;

public class LC1224 {
    /**
     * 给你一个长度为 n ，下标从 0 开始的整数数组 forts ，表示一些城堡。forts[i] 可以是 -1 ，0 或者 1 ，其中：
     *
     * -1 表示第 i 个位置 没有 城堡。
     * 0 表示第 i 个位置有一个 敌人 的城堡。
     * 1 表示第 i 个位置有一个你控制的城堡。
     * 现在，你需要决定，将你的军队从某个你控制的城堡位置 i 移动到一个空的位置 j ，满足：
     *
     * 0 <= i, j <= n - 1
     * 军队经过的位置 只有 敌人的城堡。正式的，对于所有 min(i,j) < k < max(i,j) 的 k ，都满足 forts[k] == 0 。
     * 当军队移动时，所有途中经过的敌人城堡都会被 摧毁 。
     *
     * 请你返回 最多 可以摧毁的敌人城堡数目。如果 无法 移动你的军队，或者没有你控制的城堡，请返回 0 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximum-enemy-forts-that-can-be-captured
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     * <p>
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 39.2 MB
     * , 在所有 Java 提交中击败了
     * 75.04%
     * 的用户
     * 通过测试用例：
     * 45 / 45
     */
    public int captureForts(int[] forts) {
        int i = 0, n = forts.length;
        while (i < n && forts[i] == 0) {
            i++;
        }
        int ans = 0;
        int num = 0;
        for (int j = i + 1; j < n; j++) {
            if (forts[j] == 0) {
                num++;
            } else if (forts[j] == forts[i]) {
                num = 0;
                i = j;
            } else {
                ans = Math.max(ans, num);
                num = 0;
                i = j;
            }
        }
        return ans;
    }

    /**
     * 给你两个字符串数组 positive_feedback 和 negative_feedback ，分别包含表示正面的和负面的词汇。不会 有单词同时是正面的和负面的。
     * <p>
     * 一开始，每位学生分数为 0 。每个正面的单词会给学生的分数 加 3 分，每个负面的词会给学生的分数 减  1 分。
     * <p>
     * 给你 n 个学生的评语，用一个下标从 0 开始的字符串数组 report 和一个下标从 0 开始的整数数组 student_id 表示，其中 student_id[i] 表示这名学生的 ID ，这名学生的评语是 report[i] 。每名学生的 ID 互不相同。
     * <p>
     * 给你一个整数 k ，请你返回按照得分 从高到低 最顶尖的 k 名学生。如果有多名学生分数相同，ID 越小排名越前。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/reward-top-k-students
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 77 ms
     * , 在所有 Java 提交中击败了
     * 28.18%
     * 的用户
     * 内存消耗：
     * 49.1 MB
     * , 在所有 Java 提交中击败了
     * 94.98%
     * 的用户
     * 通过测试用例：
     * 27 / 27
     */
    public List<Integer> topStudents(String[] positive_feedback, String[] negative_feedback, String[] report, int[] student_id, int k) {
        Set<String> p = new HashSet<>(positive_feedback.length);
        Set<String> n = new HashSet<>(negative_feedback.length);
        for (String positive : positive_feedback) {
            p.add(positive);
        }
        for (String negative : negative_feedback) {
            n.add(negative);
        }

        StuInfo[] stuInfos = new StuInfo[report.length];
        for (int i = 0; i < report.length; i++) {
            int score = calScore(p, n, report[i]);
            stuInfos[i] = new StuInfo(score, student_id[i]);
        }

        Arrays.sort(stuInfos, (a, b) -> {
            if (a.score != b.score) {
                return b.score - a.score;
            } else {
                return a.id - b.id;
            }
        });
        List<Integer> ans = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            ans.add(stuInfos[i].id);
        }
        return ans;
    }

    private int calScore(Set<String> p, Set<String> n, String report) {
        int score = 0;
        for (String word : report.split(" ")) {
            if (p.contains(word)) {
                score += 3;
            } else if (n.contains(word)) {
                score -= 1;
            }
        }
        return score;
    }

    class StuInfo {
        int score;
        int id;

        public StuInfo(int score, int id) {
            this.score = score;
            this.id = id;
        }
    }

    /**
     * 给你两个数组 arr1 和 arr2 ，它们一开始都是空的。你需要往它们中添加正整数，使它们满足以下条件：
     *
     * arr1 包含 uniqueCnt1 个 互不相同 的正整数，每个整数都 不能 被 divisor1 整除 。
     * arr2 包含 uniqueCnt2 个 互不相同 的正整数，每个整数都 不能 被 divisor2 整除 。
     * arr1 和 arr2 中的元素 互不相同 。
     * 给你 divisor1 ，divisor2 ，uniqueCnt1 和 uniqueCnt2 ，请你返回两个数组中 最大元素 的 最小值 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimize-the-maximum-of-two-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 0 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 38.2 MB
     * , 在所有 Java 提交中击败了
     * 88.07%
     * 的用户
     * 通过测试用例：
     * 63 / 63
     */
    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        long gcd = gcd(divisor1, divisor2);
        long lcm = divisor1 / gcd * divisor2;
        long l = 0, r = (uniqueCnt1 + uniqueCnt2) * 2L;
        while (l < r) {
            long mid = l + ((r - l) >> 1);
            if (isValid(mid, divisor1, divisor2, lcm, uniqueCnt1, uniqueCnt2)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;
    }

    private static boolean isValid(long num, int divisor1, int divisor2, long lcm, int uniqueCnt1, int uniqueCnt2) {
        int a = (int) (num / divisor2 - num / lcm);
        int b = (int) (num / divisor1 - num / lcm);
        int common = (int) (num - a - b - num / lcm);
        return common >= Math.max(uniqueCnt1 - a, 0) + Math.max(uniqueCnt2 - b, 0);
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    /**
     * 给你一个字符串 s ，它包含一个或者多个单词。单词之间用单个空格 ' ' 隔开。
     *
     * 如果字符串 t 中第 i 个单词是 s 中第 i 个单词的一个 排列 ，那么我们称字符串 t 是字符串 s 的同位异构字符串。
     *
     * 比方说，"acb dfe" 是 "abc def" 的同位异构字符串，但是 "def cab" 和 "adc bef" 不是。
     * 请你返回 s 的同位异构字符串的数目，由于答案可能很大，请你将它对 109 + 7 取余 后返回。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-anagrams
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /**
     * 执行结果：
     * 通过
     * 显示详情
     * 添加备注
     *
     * 执行用时：
     * 32 ms
     * , 在所有 Java 提交中击败了
     * 66.20%
     * 的用户
     * 内存消耗：
     * 45.1 MB
     * , 在所有 Java 提交中击败了
     * 65.73%
     * 的用户
     * 通过测试用例：
     * 41 / 41
     */
    public int countAnagrams(String s) {
        int mod = 1000_000_007;
        String[] words = s.split(" ");
        long ans = 1L;
        for (String word : words) {
            ans *= calAnagramsNum(word, mod);
            ans %= mod;
        }
        return (int) ans;
    }

    private int calAnagramsNum(String word, int mod) {
        int[] counter = new int[26];
        long top = 1L, bottom = 1L;
        int n = word.length();
        // 排列组合
        for (int i = 0; i < n; i++) {
            top = (top * (n - i)) % mod;
            bottom = (bottom * ++counter[word.charAt(i) - 'a']) % mod;
        }
        // 实际数目 = top / bottom
        // 但是由于 top 和 bottom 是取余后的结果，不能直接相除
        // 除法取余转换成乘法，乘上分母的逆元
        // bottom 的逆元为 bottom ^ (p - 2)
        // 因为有费马小定理: bottom, p 互质且 p 为质数， bottom ^ (p - 1) mod p = 1
        // 则 bottom * bottom ^ (p - 2) mod p = 1
        // (top / bottom) mod p = (top * bottom ^ (p - 2)) mod p
        return (int) ((top * pow(bottom, mod - 2, mod)) % mod);
    }

    private long pow(long a, int n, int mod) {
        if (n == 1) {
            return a;
        }

        long x = pow(a, n / 2, mod);
        long ans = (x * x) % mod;
        ans = n % 2 == 0 ? ans : (ans * a) % mod;
        return ans;
    }

    private static long calCombination(int a, int n, int mod) {
        long ans = 1;
        for (int i = 0; i < a; i++) {
            ans = ans * (n - a + 1 + i);
            if (ans % (i + 1) == 0) {
                System.out.println("123");
            } else {
                System.out.println("456");
            }
            ans = ans / (i + 1);
            ans %= mod;
        }
//        for (int i = 0; i < a; i++) {
//            ans = ans * (n - i);
//            ans %= mod;
//        }
//        for (int i = 0; i < a; i++) {
//            ans = ans / (i + 1);
//            ans %= mod;
//        }
//        System.out.println("a >> " + a + ", n >> " + n + " >> " + ans);
        return  ans;
    }

    public static void main(String[] args) {
        int a = 12;
        int b = 8;
        System.out.println(gcd(a, b));
//        94560
//        71250
//        30680567
//        87765279
        int divisor1 = 94560;
        int divisor2 = 71250;
        int uniqueCnt1 = 30680567;
        int uniqueCnt2 = 87765279;
        System.out.println(new LC1224().minimizeSet(divisor1, divisor2, uniqueCnt1, uniqueCnt2));

        System.out.println(new LC1224().countAnagrams("nhpfmisnbjjqynnkxrnozldiisyesiaimzdazz jttbtnmamtxkipvpkyjaaurjsosmbqemhknxzpdlvpbfvkkrmvfzaplkbkotkpcktpiwgztzvqwzniyet kfdmnmronhznibizykggsvpwkbbbuebhvyspuyrqhljveiewcesqpvajyadimfvdeolscvyrvuyclhdaejubwveoasazfyqyblyrszheshsfhzpnygdgduayybbdvsi xlakns ujzoherwnmp niyenmni pkq"));
        System.out.println(calCombination(9, 59, 1000_000_007));
    }
}
