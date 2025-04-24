package Questions_april_2025;

import java.util.*;

public class _2799 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2};
        CountCompleteSubarraysSolution solution = new CountCompleteSubarraysSolution();
        System.out.println(solution.countCompleteSubarrays(nums));
    }
}

class CountCompleteSubarraysSolution {
    public int countCompleteSubarrays(int[] nums) {
        int left = 0, res = 0;
        int k = (int) Arrays.stream(nums).distinct().count();
        Map<Integer, Integer> mpp = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            mpp.put(nums[i], mpp.getOrDefault(nums[i], 0) + 1);
            while (mpp.size() == k) {
                res += nums.length - i;
                mpp.put(nums[left], mpp.get(nums[left]) - 1);
                if (mpp.get(nums[left]) == 0) mpp.remove(nums[left]);
                left++;
            }
        }
        return res;
    }
}
