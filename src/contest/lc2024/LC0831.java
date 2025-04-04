package contest.lc2024;

import java.util.ArrayList;

public class LC0831 {
    public int generateKey(int num1, int num2, int num3) {
        int ans = 0;
        int c = 1;
        while (num1 > 0 || num2 > 0 || num3 > 0) {
            int num = Math.min(num1 % 10, num2 % 10);
            num = Math.min(num, num3 % 10);
            ans += num * c;
            c *= 10;
            num1 /= 10;
            num2 /= 10;
            num3 /= 10;
        }
        return ans;
    }

    public String stringHash(String s, int k) {
        int size = s.length() / k;
        StringBuilder ans = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int hash = 0;
            for (int x = i * k; x < (i + 1) * k && x < s.length(); x++) {
                hash += s.charAt(x) - 'a';
            }
            ans.append((char) ('a' + (hash % 26)));
        }
        return ans.toString();
    }

    /**
     * 给你两个 正 整数 n 和 k 。
     *
     * 如果一个整数 x 满足以下条件，那么它被称为 k 回文 整数 。
     *
     * x 是一个 回文整数 。
     * x 能被 k 整除。
     * 如果一个整数的数位重新排列后能得到一个 k 回文整数 ，那么我们称这个整数为 好 整数。比方说，k = 2 ，那么 2020 可以重新排列得到 2002 ，2002 是一个 k 回文串，所以 2020 是一个好整数。而 1010 无法重新排列数位得到一个 k 回文整数。
     *
     * 请你返回 n 个数位的整数中，有多少个 好 整数。
     *
     * 注意 ，任何整数在重新排列数位之前或者之后 都不能 有前导 0 。比方说 1010 不能重排列得到 101 。
     */
    public long countGoodIntegers(int n, int k) {


        return 0;
    }

    public long minDamage(int power, int[] damage, int[] health) {
        int n = damage.length;
        long sum = 0;
        ArrayList<Integer> p = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            p.add(i);
            sum += damage[i];
        }
        p.sort((a, b) -> count(health[a], power) * (damage[a] + damage[b]) + count(health[b],  power) * damage[b]
                - count(health[b], power) * (damage[a] + damage[b]) + count(health[a],  power) * damage[a]);

        long ans = 0;
        for (int i = 0; i < n; i++) {
            Integer index = p.get(i);
            ans += count(health[index], power) * sum;
            sum -= damage[index];
        }
        return ans;
    }

    private int count(int health, int power) {
        return health % power == 0 ? health / power : health / power + 1;
    }

    public static void main(String[] args) {
        int power = 1;
        int[] damage = {1, 1, 1, 1};
        int[] health = {1, 2, 3, 4};
        System.out.println(new LC0831().minDamage(power, damage, health));
    }
}
