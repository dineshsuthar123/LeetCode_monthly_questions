package Questions_march_2025;

import java.util.*;

class _2560 {
    private static boolean Yes(int[] nums, int mid, int k) {
        int count = 0;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] <= mid) {
                count++;
                i += 2;
            } else {
                i++;
            }
        }
        return count >= k;
    }

    public static int minCapability(int[] nums, int k) {
        int left = Arrays.stream(nums).min().getAsInt();
        int right = Arrays.stream(nums).max().getAsInt();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (Yes(nums, mid, k)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7};
        int k = 2;
        System.out.println(minCapability(nums, k));
    }
}

