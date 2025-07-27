package Questions_july_2025;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * LeetCode 2210: Count Hills and Valleys in an Array
 */
public class _2210 {

    static class Solution {

        /**
         * ## Solution 1: Intuitive Approach (Using an ArrayList)

         * This method first simplifies the problem by removing consecutive duplicates,
         * which effectively compresses plateaus into single numbers. It then iterates
         * through the cleaned list to find hills and valleys.

         * - Time Complexity: O(n), for iterating through the array once to build the list
         * and a second time to count.
         * - Space Complexity: O(n) in the worst case, to store the list of unique numbers.
         */
        public int countHillValley_Intuitive(int[] nums) {
            // Step 1: Create a new list without consecutive duplicates.
            // This simplifies an array like [2, 4, 1, 1, 6] into [2, 4, 1, 6].
            List<Integer> uniqueNums = new ArrayList<>();
            uniqueNums.add(nums[0]);
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[i-1]) {
                    uniqueNums.add(nums[i]);
                }
            }

            // A hill or valley requires at least 3 distinct points.
            if (uniqueNums.size() < 3) {
                return 0;
            }

            int count = 0;
            // Step 2: Iterate through the simplified list to find hills/valleys.
            // We only need to check the elements from the second to the second-to-last.
            for (int i = 1; i < uniqueNums.size() - 1; i++) {
                int left = uniqueNums.get(i - 1);
                int current = uniqueNums.get(i);
                int right = uniqueNums.get(i + 1);

                // Check for a hill ⛰️
                if (current > left && current > right) {
                    count++;
                }
                // Check for a valley 🏞️
                else if (current < left && current < right) {
                    count++;
                }
            }
            return count;
        }

        /**
         * ## Solution 2: Space-Optimized Brute-Force Approach
         *
         * This method avoids using extra space by iterating through the array in a single
         * pass. It keeps track of the last valid "left" neighbor and finds the next
         * valid "right" neighbor for each potential hill/valley point.
         *
         * - Time Complexity: O(n), for a single pass through the array.
         * - Space Complexity: O(1), as no extra data structures are used.
         */
        public int countHillValley_BruteForce(int[] nums) {
            int count = 0;
            // 'left' holds the value of the closest non-equal neighbor to the left.
            // Initialize it with the first element.
            int left = nums[0];

            for (int i = 1; i < nums.length - 1; i++) {
                // If the current element is the same as its right neighbor, it's part of a
                // plateau. We can skip it because a plateau's peak or trough is determined
                // by the element *before* the plateau and the element *after* it.
                // The correct check will happen when we reach the end of the plateau.
                if (nums[i] == nums[i+1]) {
                    continue;
                }

                // At this point, nums[i] is a candidate for a hill or valley.
                // 'left' is our left neighbor.
                // nums[i+1] is our right neighbor (since we know it's not equal to nums[i]).
                int right = nums[i+1];

                // Check for a hill ⛰️
                if (nums[i] > left && nums[i] > right) {
                    count++;
                }
                // Check for a valley 🏞️
                else if (nums[i] < left && nums[i] < right) {
                    count++;
                }

                // The current number now becomes the 'left' neighbor for future elements.
                left = nums[i];
            }
            return count;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // --- Test Case 1 ---
        int[] nums1 = {2, 4, 1, 1, 6, 5};
        System.out.println("Test Case 1: " + Arrays.toString(nums1));
        System.out.println("Expected: 3");
        System.out.println("Intuitive Solution: " + sol.countHillValley_Intuitive(nums1));
        System.out.println("Brute-Force Solution: " + sol.countHillValley_BruteForce(nums1));
        System.out.println("----------------------------------------");


        // --- Test Case 2 ---
        int[] nums2 = {6, 6, 5, 5, 4, 1};
        System.out.println("Test Case 2: " + Arrays.toString(nums2));
        System.out.println("Expected: 0");
        System.out.println("Intuitive Solution: " + sol.countHillValley_Intuitive(nums2));
        System.out.println("Brute-Force Solution: " + sol.countHillValley_BruteForce(nums2));
        System.out.println("----------------------------------------");

        // --- Test Case 3 ---
        int[] nums3 = {5, 7, 7, 1, 7};
        System.out.println("Test Case 3: " + Arrays.toString(nums3));
        System.out.println("Expected: 2");
        System.out.println("Intuitive Solution: " + sol.countHillValley_Intuitive(nums3));
        System.out.println("Brute-Force Solution: " + sol.countHillValley_BruteForce(nums3));
        System.out.println("----------------------------------------");
    }
}
