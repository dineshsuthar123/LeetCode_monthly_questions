package Questions_april_2025;

import java.util.*;

public class _2845 {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        int modulo = 3;
        int k = 1;
        CountInterestingSubarraysSolution solution = new CountInterestingSubarraysSolution();
        System.out.println(solution.countInterestingSubarrays(nums, modulo, k));
    }
}

class CountInterestingSubarraysSolution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        long count = 0, equals = 0;
        Map<Integer, Long> mpp = new HashMap<>();
        mpp.put(0, 1L);
        for (int i : nums) {
            if (i % modulo == k) equals++;
            int rem = (int) (equals % modulo);
            int needed = (rem - k + modulo) % modulo;
            count += mpp.getOrDefault(needed, 0L);
            mpp.put(rem, mpp.getOrDefault(rem, 0L) + 1);
        }
        return count;
    }
}
