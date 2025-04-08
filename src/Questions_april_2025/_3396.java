package Questions_april_2025;

import java.util.*;

public class _3396 {
    public static void main(String[] args) {
        int[] nums = {4, 1, 3, 2, 4, 5, 6};
        MinimumOperationsSolution solution = new MinimumOperationsSolution();
        System.out.println(solution.minimumOperations(nums));
    }
}

class MinimumOperationsSolution {
    public int minimumOperations(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = n - 1; i >= 0; i--) {
            if (set.contains(nums[i])) {
                return i / 3 + 1;
            }
            set.add(nums[i]);
        }
        return 0;
    }
}

