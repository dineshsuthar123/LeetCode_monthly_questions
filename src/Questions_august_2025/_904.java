package Questions_august_2025;

import java.util.HashMap;
import java.util.Map;

/**
 * Main class for LeetCode problem 904.
 * This class contains the main method to test the solution.
 */
public class _904 {

    public static void main(String[] args) {
        // Create an instance of the solution class
        FruitBasket fruitBasket = new FruitBasket();

        // --- Test Case 1 ---
        int[] fruits1 = {1, 2, 1};
        int result1 = fruitBasket.totalFruit(fruits1);
        System.out.println("Test Case 1: [1, 2, 1]");
        System.out.println("Maximum number of fruits: " + result1); // Expected: 3
        System.out.println("---------------------------------");

        // --- Test Case 2 ---
        int[] fruits2 = {0, 1, 2, 2};
        int result2 = fruitBasket.totalFruit(fruits2);
        System.out.println("Test Case 2: [0, 1, 2, 2]");
        System.out.println("Maximum number of fruits: " + result2); // Expected: 3
        System.out.println("---------------------------------");

        // --- Test Case 3 ---
        int[] fruits3 = {1, 2, 3, 2, 2};
        int result3 = fruitBasket.totalFruit(fruits3);
        System.out.println("Test Case 3: [1, 2, 3, 2, 2]");
        System.out.println("Maximum number of fruits: " + result3); // Expected: 4
        System.out.println("---------------------------------");

        // --- Test Case 4 ---
        int[] fruits4 = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        int result4 = fruitBasket.totalFruit(fruits4);
        System.out.println("Test Case 4: [3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4]");
        System.out.println("Maximum number of fruits: " + result4); // Expected: 5
        System.out.println("---------------------------------");
    }
}

/**
 * Implements the solution for the "Fruit Into Baskets" problem.
 * This is a classic sliding window problem.
 */
class FruitBasket {
    /**
     * Finds the total number of fruits in at most two types of baskets.
     * This method uses a sliding window approach with a HashMap to keep track
     * of the fruit types in the current window.
     *
     * @param fruits An array representing the types of fruit on a row of trees.
     * @return The maximum number of fruits you can pick.
     */
    public int totalFruit(int[] fruits) {
        if (fruits == null || fruits.length == 0) {
            return 0;
        }

        int start = 0; // The left pointer of our sliding window
        int end = 0;   // The right pointer of our sliding window
        int n = fruits.length;
        int maxLen = 0;

        // This map stores the count of each fruit type in the current window
        Map<Integer, Integer> map = new HashMap<>();

        while (end < n) {
            // Add the current fruit to our window (and map)
            map.put(fruits[end], map.getOrDefault(fruits[end], 0) + 1);

            // If the window contains more than 2 types of fruit,
            // we need to shrink it from the left.
            while (map.size() > 2) { // Changed from >= 3 to > 2 for clarity
                // Decrease the count of the fruit at the 'start' pointer
                map.put(fruits[start], map.get(fruits[start]) - 1);

                // If the count of that fruit becomes zero, remove it from the map
                if (map.get(fruits[start]) == 0) {
                    map.remove(fruits[start]);
                }
                // Shrink the window by moving the left pointer
                start++;
            }

            // Calculate the length of the current valid window
            int currLen = end - start + 1;
            // Update the maximum length found so far
            maxLen = Math.max(maxLen, currLen);

            // Expand the window by moving the right pointer
            end++;
        }
        return maxLen;
    }
}
