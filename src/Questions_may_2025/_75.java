package Questions_may_2025;

import java.util.Arrays;

public class _75 {
    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        _75 solution = new _75();
        solution.sortColors(nums);
        System.out.println(Arrays.toString(nums)); // Expected output: [0, 0, 1, 1, 2, 2]
    }

    public void sortColors(int[] nums) {
        int l = 0, r = nums.length - 1, i = 0;
        while (i <= r) {
            if (nums[i] == 0) {
                swap(nums, i++, l++);
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, r--);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
