package Questions_may_2025;

public class _3355 {
    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 2, 5};
        int[][] queries = {
                {0, 2},
                {1, 3},
                {2, 4}
        };
        ZeroArraySolution solution = new ZeroArraySolution();
        System.out.println(solution.isZeroArray(nums, queries));
    }
}

class ZeroArraySolution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] freq = new int[n];

        for (int[] q : queries) {
            freq[q[0]]++;
            if (q[1] + 1 < n) {
                freq[q[1] + 1]--;
            }
        }

        int curFreq = 0;
        for (int i = 0; i < n; i++) {
            curFreq += freq[i];
            if (curFreq < nums[i]) {
                return false;
            }
        }
        return true;
    }
}
