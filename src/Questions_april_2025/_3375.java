package Questions_april_2025;

import java.util.*;

public class _3375 {
    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 4, 8};
        int k = 4;

        MinOperationsSolution solution = new MinOperationsSolution();
        System.out.println("Minimum Operations: " + solution.minOperations(nums, k));
    }
}

class MinOperationsSolution {
    public int minOperations(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums)
            if (i < k)
                return -1;
            else if (i > k)
                map.put(i, map.getOrDefault(i, 0) + 1);
        return map.size();
    }
}

