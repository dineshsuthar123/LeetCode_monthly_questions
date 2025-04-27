package Questions_april_2025;

public class _3392 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        CountSubarraySolution solution = new CountSubarraySolution();
        System.out.println(solution.countSubarray(nums));
    }
}

class CountSubarraySolution {
    public int countSubarray(int[] nums) {
        int count = 0;
        for (int i = 0; i + 2 < nums.length; i++) {
            if (2 * (nums[i] + nums[i + 2]) == nums[i + 1]) {
                count++;
            }
        }
        return count;
    }
}
