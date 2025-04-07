package Questions_april_2025;

public class _416 {
    public static void main(String[] args) {
        int[] nums = {1, 5, 11, 5};
        PartitionEqualSubsetSumSolution solution = new PartitionEqualSubsetSumSolution();
        System.out.println("Can partition: " + solution.canPartition(nums));
    }
}

class PartitionEqualSubsetSumSolution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[target];
    }
}

