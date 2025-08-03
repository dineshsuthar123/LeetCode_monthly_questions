package Questions_august_2025;

// Main class named _2106
public class _2106 {

    // Main method to run the program
    public static void main(String[] args) {
        // Create an instance of the FruitHarvester class
        FruitHarvester harvester = new FruitHarvester();

        // Example 1
        int[][] fruits1 = {{2, 8}, {6, 3}, {8, 6}};
        int startPos1 = 5;
        int k1 = 4;
        int result1 = harvester.maxTotalFruits(fruits1, startPos1, k1);
        System.out.println("Test Case 1:");
        System.out.println("Max total fruits: " + result1); // Expected output: 9

        System.out.println("--------------------");

        // Example 2
        int[][] fruits2 = {{0, 9}, {4, 1}, {5, 7}, {6, 2}, {7, 4}, {10, 9}};
        int startPos2 = 5;
        int k2 = 4;
        int result2 = harvester.maxTotalFruits(fruits2, startPos2, k2);
        System.out.println("Test Case 2:");
        System.out.println("Max total fruits: " + result2); // Expected output: 14

        System.out.println("--------------------");

        // Example 3
        int[][] fruits3 = {{0, 3}, {6, 4}, {8, 5}};
        int startPos3 = 3;
        int k3 = 2;
        int result3 = harvester.maxTotalFruits(fruits3, startPos3, k3);
        System.out.println("Test Case 3:");
        System.out.println("Max total fruits: " + result3); // Expected output: 0
    }
}

// Renamed from Solution to a unique name: FruitHarvester
class FruitHarvester {
    /**
     * Calculates the maximum number of fruits you can harvest.
     * @param fruits A 2D array where fruits[i] = [positioni, amounti].
     * @param startPos The initial starting position.
     * @param k The maximum number of steps you can take.
     * @return The maximum total number of fruits you can harvest.
     */
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int left = 0, res = 0, sum = 0;
        // Use a sliding window approach
        for (int right = 0; right < fruits.length; right++) {
            sum += fruits[right][1];
            // Shrink the window from the left if the steps required exceed k
            while (left <= right && calculateSteps(fruits[left][0], fruits[right][0], startPos) > k) {
                sum -= fruits[left][1];
                left++;
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    /**
     * Helper function to calculate the minimum steps to cover a range [left, right] from startPos.
     * There are two main strategies:
     * 1. Go left to 'left', then turn around and go to 'right'.
     * 2. Go right to 'right', then turn around and go to 'left'.
     * @param left The leftmost fruit position in the current window.
     * @param right The rightmost fruit position in the current window.
     * @param start The starting position.
     * @return The minimum steps required.
     */
    private int calculateSteps(int left, int right, int start) {
        // Option 1: Go to the leftmost point first, then to the rightmost.
        // Total distance = (start -> left) + (left -> right)
        int fromLeft = Math.abs(start - left) + (right - left);

        // Option 2: Go to the rightmost point first, then to the leftmost.
        // Total distance = (start -> right) + (right -> left)
        int fromRight = Math.abs(start - right) + (right - left);

        // Return the minimum of the two strategies.
        return Math.min(fromLeft, fromRight);
    }
}