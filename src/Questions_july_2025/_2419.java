package Questions_july_2025;

import java.util.Arrays;

public class _2419 {

    /**
     * Inner class containing the solution logic for finding the longest subarray
     * with the maximum possible bitwise AND.
     */
    static class MaxAndSubarrayFinder {

        /**
         * Calculates the length of the longest subarray with the maximum bitwise AND.
         * The maximum possible bitwise AND of any subarray is simply the largest
         * number present in the entire array. This method finds that number and then
         * finds the longest contiguous run of that number.
         *
         * @param nums The input array of integers.
         * @return The length of the longest subarray.
         */
        public int longestSubarray(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            int maxVal = 0;
            // Step 1: Find the maximum element in the array.
            for (int num : nums) {
                if (num > maxVal) {
                    maxVal = num;
                }
            }

            int maxLength = 0;
            int currentLength = 0;

            // Step 2: Find the longest contiguous run of that maximum element.
            for (int num : nums) {
                if (num == maxVal) {
                    // If we find the max element, increment the current streak length.
                    currentLength++;
                } else {
                    // If the streak is broken, reset the current length.
                    currentLength = 0;
                }
                // Update the overall max length found so far.
                maxLength = Math.max(maxLength, currentLength);
            }

            return maxLength;
        }
    }

    /**
     * The main method to test the solution.
     */
    public static void main(String[] args) {
        // To create an instance of a non-static inner class, you first need an
        // instance of the outer class.
        _2419 outerInstance = new _2419();
        MaxAndSubarrayFinder solver = new MaxAndSubarrayFinder();

        // --- Test Case 1 ---
        int[] nums1 = {1, 2, 3, 3, 2, 2};
        int result1 = solver.longestSubarray(nums1);
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Expected Output: 2");
        System.out.println("Actual Output:   " + result1);
        System.out.println("------------------------------------");

        // --- Test Case 2 ---
        int[] nums2 = {1, 2, 3, 4};
        int result2 = solver.longestSubarray(nums2);
        System.out.println("Input: " + Arrays.toString(nums2));
        System.out.println("Expected Output: 1");
        System.out.println("Actual Output:   " + result2);
        System.out.println("------------------------------------");

        // --- Test Case 3 (from your failing example) ---
        int[] nums3 = {5, 5, 3, 5, 5, 5, 2};
        int result3 = solver.longestSubarray(nums3);
        System.out.println("Input: " + Arrays.toString(nums3));
        System.out.println("Expected Output: 3");
        System.out.println("Actual Output:   " + result3);
        System.out.println("------------------------------------");
    }
}
