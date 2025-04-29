package Questions_april_2025;

public class _2962 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 3, 1};
        int k = 2;
        CountMaxSubarraysSolution solution = new CountMaxSubarraysSolution();
        System.out.println(solution.countSubarrays(nums, k));
    }
}

class CountMaxSubarraysSolution {
    public long countSubarrays(int[] nums, int k) {
        int count = 0, max = 0, left = 0;
        long ans = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        for (int num : nums) {
            if (num == max) count++;
            while (count >= k) {
                if (nums[left] == max) count--;
                left++;
            }
            ans += left;
        }
        return ans;
    }
}
