package Questions_april_2025;

import java.util.*;

public class _2563 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int lower = 3;
        int upper = 5;
        CountFairPairsSolution solution = new CountFairPairsSolution();
        System.out.println("Number of fair pairs: " + solution.countFairPairs(nums, lower, upper));
    }
}

class CountFairPairsSolution {
    public long countAtMost(int[] nums, long comp) {
        long ans = 0;
        int j = nums.length - 1;
        for (int i = 0; i < j; i++) {
            while (i < j && nums[i] + nums[j] > comp) j--;
            ans += j - i;
        }
        return ans;
    }

    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return countAtMost(nums, upper) - countAtMost(nums, lower - 1);
    }
}
