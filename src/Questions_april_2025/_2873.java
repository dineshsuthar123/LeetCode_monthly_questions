package Questions_april_2025;

public class _2873 {
    public static void main(String[] args) {
        int[] nums = {3, 7, 2, 5, 8};
        Solution solution = new Solution();
        System.out.println("Maximum Triplet Value: " + solution.maximumTripletValue(nums));
    }
}

class Solution {
    public long maximumTripletValue(int[] nums) {
        long max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int k = nums.length - 1; k > i; k--) {
                int j = i + 1;
                while (j < k) {
                    max = Math.max(max, (long) (nums[i] - nums[j]) * nums[k]);
                    j++;
                }
            }
        }
        return max;
    }
}

