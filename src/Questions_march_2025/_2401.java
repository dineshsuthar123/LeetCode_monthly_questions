package Questions_march_2025;

public class _2401 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 8, 48, 10};
        LongestNiceSubarraySolution solution = new LongestNiceSubarraySolution();
        System.out.println(solution.longestNiceSubarray(nums));
    }
}

class LongestNiceSubarraySolution {
    public int longestNiceSubarray(int[] nums) {
        int i = 0, j = 0, max = 0, b = 0;
        while (j < nums.length) {
            if ((b & nums[j]) == 0) {
                b ^= nums[j];
                j++;
                max = Math.max(max, j - i);
            } else {
                b ^= nums[i];
                i++;
            }
        }
        return max;
    }
}
