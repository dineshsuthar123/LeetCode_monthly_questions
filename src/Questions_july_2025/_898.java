package Questions_july_2025;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Main class to solve and demonstrate LeetCode 898: Bitwise ORs of Subarrays.
 */
public class _898 {

    /**
     * The main method to run and test the solution.
     */
    public static void main(String[] args) {
        // Create an instance of our solution class
        DistinctSubarrayORs solver = new DistinctSubarrayORs();

        // --- Test Cases ---

        // Test Case 1: From Example 2
        int[] arr1 = {1, 1, 2};
        int result1 = solver.subarrayBitwiseORs(arr1);
        System.out.println("Input:  " + Arrays.toString(arr1));
        System.out.println("Output: " + result1); // Expected: 3
        System.out.println("--------------------");

        // Test Case 2: From Example 3
        int[] arr2 = {1, 2, 4};
        int result2 = solver.subarrayBitwiseORs(arr2);
        System.out.println("Input:  " + Arrays.toString(arr2));
        System.out.println("Output: " + result2); // Expected: 6
        System.out.println("--------------------");

        // Test Case 3: A simple case
        int[] arr3 = {0};
        int result3 = solver.subarrayBitwiseORs(arr3);
        System.out.println("Input:  " + Arrays.toString(arr3));
        System.out.println("Output: " + result3); // Expected: 1
        System.out.println("--------------------");

        // Test Case 4: The one that failed your initial solution
        int[] arr4 = {3, 11};
        int result4 = solver.subarrayBitwiseORs(arr4);
        System.out.println("Input:  " + Arrays.toString(arr4));
        System.out.println("Output: " + result4); // Expected: 2
        System.out.println("--------------------");
    }

    /**
     * Inner class containing the problem's solution logic.
     * Made static so it can be instantiated without an instance of the outer class.
     */
    static class DistinctSubarrayORs {
        public int subarrayBitwiseORs(int[] arr) {
            // This master set will store every unique OR value we find.
            Set<Integer> resultSet = new HashSet<>();

            // This set tracks the distinct OR values of all subarrays ending at the previous position.
            Set<Integer> currentSet = new HashSet<>();

            // Iterate through each number in the input array.
            for (int num : arr) {
                // Create a new set for the OR values of subarrays ending at the current position.
                Set<Integer> nextCurrentSet = new HashSet<>();

                // The subarray containing only the current number.
                nextCurrentSet.add(num);

                // Extend all previous subarrays with the current number.
                for (int prevResult : currentSet) {
                    nextCurrentSet.add(prevResult | num);
                }

                // Add all newly computed values for this step to our master result set.
                resultSet.addAll(nextCurrentSet);

                // For the next iteration, the "current" set becomes what we just calculated.
                currentSet = nextCurrentSet;
            }

            return resultSet.size();
        }
    }
}