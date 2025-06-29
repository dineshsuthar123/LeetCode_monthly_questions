package Questions_june_2025;

import java.util.*;

public class _1498 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        int[] nums1 = {3, 5, 6, 7};
        int target1 = 9;
        System.out.println("Output 1: " + sol.numSubseq(nums1, target1)); // Expected: 4

        int[] nums2 = {3, 3, 6, 8};
        int target2 = 10;
        System.out.println("Output 2: " + sol.numSubseq(nums2, target2)); // Expected: 6

        int[] nums3 = {2, 3, 3, 4, 6, 7};
        int target3 = 12;
        System.out.println("Output 3: " + sol.numSubseq(nums3, target3)); // Expected: 61
    }
}

class Solution {
    static final int MOD = 1_000_000_007;

    public int numSubseq(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] pow2 = new int[n];
        pow2[0] = 1;
        for (int k = 1; k < n; k++) {
            pow2[k] = (pow2[k - 1] * 2) % MOD;
        }

        int i = 0, j = n - 1, ans = 0;
        while (i <= j) {
            if (nums[i] + nums[j] <= target) {
                ans = (ans + pow2[j - i]) % MOD;
                i++;
            } else {
                j--;
            }
        }
        return ans;
    }
}
