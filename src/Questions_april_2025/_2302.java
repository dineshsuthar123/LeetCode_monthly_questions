package Questions_april_2025;

public class _2302 {
    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 5, 1};
        long k = 7;
        CountSubarraysKSolution solution = new CountSubarraysKSolution();
        System.out.println(solution.countSubarrays(nums, k));
    }
}

class CountSubarraysKSolution {
    public long countSubarrays(int[] nums, long k) {
        int left = 0;
        long sum = 0;
        long count = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum * (right - left + 1) >= k) {
                sum -= nums[left++];
            }
            count += (right - left + 1);
        }
        return count;
    }
}
