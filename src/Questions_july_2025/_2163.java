package Questions_july_2025;

import java.util.PriorityQueue;
import java.util.Collections;

public class _2163 {

    /**
     * This inner class contains the core logic for solving the problem.
     * Encapsulating the solution makes the code modular and easy to test.
     */
    static class OptimalDifferenceFinder {
        /**
         * Calculates the minimum possible difference between the sum of the first n elements
         * and the sum of the second n elements after removing n elements from the array.
         *
         * @param nums An integer array of size 3*n.
         * @return The minimum possible difference (sum_first - sum_second).
         */
        public long minimumDifference(int[] nums) {
            int totalSize = nums.length;
            int n = totalSize / 3;

            // `prefixMinSums[i]` will store the minimum sum of n elements from nums[0...i].
            long[] prefixMinSums = new long[totalSize];

            // `suffixMaxSums[i]` will store the maximum sum of n elements from nums[i...3n-1].
            long[] suffixMaxSums = new long[totalSize];

            // --- Pass 1: Calculate Minimum Prefix Sums (Left to Right) ---
            // We use a max-heap to efficiently find the n smallest elements.
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            long currentSum = 0;

            for (int i = 0; i < totalSize; i++) {
                currentSum += nums[i];
                maxHeap.add(nums[i]);

                // If the heap size exceeds n, we remove the largest element to maintain
                // the sum of the n smallest elements.
                if (maxHeap.size() > n) {
                    currentSum -= maxHeap.poll();
                }

                // Once we have at least n elements, we can start recording the minimum sums.
                if (maxHeap.size() == n) {
                    prefixMinSums[i] = currentSum;
                }
            }

            // --- Pass 2: Calculate Maximum Suffix Sums (Right to Left) ---
            // We use a min-heap to efficiently find the n largest elements.
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            currentSum = 0;

            for (int i = totalSize - 1; i >= 0; i--) {
                currentSum += nums[i];
                minHeap.add(nums[i]);

                // If the heap size exceeds n, we remove the smallest element to maintain
                // the sum of the n largest elements.
                if (minHeap.size() > n) {
                    currentSum -= minHeap.poll();
                }

                // Once we have at least n elements, we can start recording the maximum sums.
                if (minHeap.size() == n) {
                    suffixMaxSums[i] = currentSum;
                }
            }

            // --- Pass 3: Combine Results to Find the Minimum Difference ---
            // We iterate through all possible split points. A split occurs between index `i`
            // and `i+1`.
            long minDifference = Long.MAX_VALUE;

            for (int i = n - 1; i < 2 * n; i++) {
                long sumFirst = prefixMinSums[i];
                long sumSecond = suffixMaxSums[i + 1];
                minDifference = Math.min(minDifference, sumFirst - sumSecond);
            }

            return minDifference;
        }
    }

    /**
     * The main method to test the solution with example cases.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        OptimalDifferenceFinder solver = new OptimalDifferenceFinder();

        // Example 1
        int[] nums1 = {3, 1, 2};
        long result1 = solver.minimumDifference(nums1);
        System.out.println("Example 1 Input: [3, 1, 2]");
        System.out.println("Minimum Difference: " + result1); // Expected output: -1
        System.out.println("---------------------------------");


        // Example 2
        int[] nums2 = {7, 9, 5, 8, 1, 3};
        long result2 = solver.minimumDifference(nums2);
        System.out.println("Example 2 Input: [7, 9, 5, 8, 1, 3]");
        System.out.println("Minimum Difference: " + result2); // Expected output: 1
        System.out.println("---------------------------------");

        // Example 3
        int[] nums3 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        long result3 = solver.minimumDifference(nums3);
        System.out.println("Example 3 Input: [1, 2, 3, 4, 5, 6, 7, 8, 9]");
        System.out.println("Minimum Difference: " + result3); // Expected output: -9
        System.out.println("---------------------------------");
    }
}

