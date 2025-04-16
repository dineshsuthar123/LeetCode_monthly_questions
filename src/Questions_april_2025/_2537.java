package Questions_april_2025;

import java.util.HashMap;

public class _2537 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int k = 3;
        CountGoodSolution solution = new CountGoodSolution();
        System.out.println(solution.countGood(nums, k));
    }
}

class CountGoodSolution {
    public long countGood(int[] nums, int k) {
        int n = nums.length;
        long count = 0, pairs = 0;
        int left = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int right = 0; right < n; right++) {
            int curr = nums[right];
            int f = freq.getOrDefault(curr, 0);
            pairs += f;
            freq.put(curr, f + 1);
            while (pairs >= k && left <= right) {
                count += n - right;
                int val = nums[left];
                freq.put(val, freq.get(val) - 1);
                pairs -= freq.get(val);
                left++;
            }
        }
        return count;
    }
}
