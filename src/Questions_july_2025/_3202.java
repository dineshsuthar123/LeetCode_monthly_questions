package Questions_july_2025;

import java.util.*;

public class _3202 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // read n and k
        int n = sc.nextInt();
        int k = sc.nextInt();
        // read the array
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        // compute and print result
        Solution3202 sol = new Solution3202();
        System.out.println(sol.maximumLength(nums, k));
        sc.close();
    }

    static class Solution3202 {
        public int maximumLength(int[] nums, int k) {
            int n = nums.length;
            int[][] dp = new int[n][k];
            int res = 1;

            for (int j = 0; j < n; j++) {
                for (int i = 0; i < j; i++) {
                    int r = (nums[i] + nums[j]) % k;
                    dp[j][r] = Math.max(
                            dp[j][r],
                            dp[i][r] > 0 ? dp[i][r] + 1 : 2
                    );
                    res = Math.max(res, dp[j][r]);
                }
            }

            return res;
        }
    }
}

