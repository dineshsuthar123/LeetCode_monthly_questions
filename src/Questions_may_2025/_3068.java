package Questions_may_2025;

public class _3068 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int k = 2;
        int[][] edges = {{0,1}, {1,2}, {2,3}};

        MaximumValueSumSolution solution = new MaximumValueSumSolution();
        System.out.println(solution.maximumValueSum(nums, k, edges));
    }
}

class MaximumValueSumSolution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long total = 0;
        for (int num : nums) {
            total += num;
        }
        long totalDiff = 0;
        long minAbsDiff = Long.MAX_VALUE;
        int positiveCount = 0;
        for (int num : nums) {
            long diff = (num ^ k) - num;
            if (diff > 0) {
                totalDiff += diff;
                positiveCount++;
            }
            minAbsDiff = Math.min(minAbsDiff, Math.abs(diff));
        }
        if ((positiveCount & 1) == 1) {
            totalDiff -= minAbsDiff;
        }
        return total + totalDiff;
    }
}
