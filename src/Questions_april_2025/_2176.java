package Questions_april_2025;

public class _2176 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 2, 3};
        int k = 2;
        CountPairsSolution solution = new CountPairsSolution();
        System.out.println(solution.countPairs(nums, k));
    }
}

class CountPairsSolution {
    public int countPairs(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && (i * j) % k == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
