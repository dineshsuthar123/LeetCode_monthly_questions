package Questions_july_2025;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class to solve and demonstrate LeetCode 118: Pascal's Triangle.
 */
public class _118 {

    /**
     * The main method to run and test the solution.
     */
    public static void main(String[] args) {
        // Create an instance of our solution class
        PascalsTriangleGenerator solver = new PascalsTriangleGenerator();

        // --- Test Case ---
        int numRows = 5;
        System.out.println("Generating Pascal's Triangle for numRows = " + numRows + ":");

        List<List<Integer>> triangle = solver.generate(numRows);

        // Print the triangle in a readable format
        for (List<Integer> row : triangle) {
            System.out.println(row);
        }
    }

    /**
     * Inner class containing the problem's solution logic.
     * Made static so it can be instantiated without an instance of the outer class.
     */
    static class PascalsTriangleGenerator {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> result = new ArrayList<>();
            if (numRows == 0) {
                return result;
            }

            // Manually add the first row
            List<Integer> firstRow = new ArrayList<>();
            firstRow.add(1);
            result.add(firstRow);

            // Loop to generate all subsequent rows
            for (int i = 1; i < numRows; i++) {
                List<Integer> prevRow = result.get(i - 1);
                List<Integer> currentRow = new ArrayList<>();

                // First element of any row is always 1
                currentRow.add(1);

                // Calculate the middle elements based on the previous row
                for (int j = 1; j < i; j++) {
                    currentRow.add(prevRow.get(j - 1) + prevRow.get(j));
                }

                // Last element of any row is always 1
                currentRow.add(1);

                result.add(currentRow);
            }

            return result;
        }
    }
}
