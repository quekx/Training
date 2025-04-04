package contest.lc2024;

public class LC0825 {
    public int[] getFinalState1(int[] nums, int k, int multiplier) {
        for (int i = 0; i < k; i++) {
            int p = 0;
            for (int j = 1; j < nums.length; j++) {
                if (nums[j] < nums[p]) {
                    p = j;
                }
            }
            nums[p] *= multiplier;
        }
        return nums;
    }

    public int countPairs(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (check(nums[i], nums[j])) {
                    System.out.println(nums[i] + ", " + nums[j]);
                    ans++;
                }
            }
        }
        return ans;
    }

    private boolean check(int a, int b) {
        if (a == b) {
            return true;
        }
        int[] counter1 = new int[10];
        int[] counter2 = new int[10];
        int diff = 0;
        while (a > 0 || b > 0) {
            if (a % 10 != b % 10) {
                diff++;
            }
            counter1[a % 10]++;
            counter2[b % 10]++;
            a /= 10;
            b /= 10;
        }
        for (int i = 0; i < 10; i++) {
            if (counter1[i] != counter2[i]) {
                return false;
            }
        }
        return diff <= 4;
    }

    public static void main(String[] args) {
        int[] nums = {1023,2310,2130,213};
        System.out.println(new LC0825().countPairs(nums));
    }

    public int[] getFinalState(int[] nums, int k, int multiplier) {


        return nums;
    }
}
