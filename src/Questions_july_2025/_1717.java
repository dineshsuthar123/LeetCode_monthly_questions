package Questions_july_2025;

public class _1717 {

    /**
     * The main function to demonstrate the MaximumGainSolver.
     */
    public static void main(String[] args) {
        // Create an instance of the outer class to instantiate the inner class
        _1717 outerInstance = new _1717();
        MaximumGainSolver solver = outerInstance.new MaximumGainSolver();

        // --- Example 1 ---
        String s1 = "cdbcbbaaabab";
        int x1 = 4;
        int y1 = 5;
        int result1 = solver.maximumGain(s1, x1, y1);
        System.out.println("Input: s = \"" + s1 + "\", x = " + x1 + ", y = " + y1);
        System.out.println("Maximum Gain: " + result1); // Expected output: 19

        System.out.println("--------------------");

        // --- Example 2 ---
        String s2 = "aabbaaxybbaabb";
        int x2 = 5;
        int y2 = 4;
        int result2 = solver.maximumGain(s2, x2, y2);
        System.out.println("Input: s = \"" + s2 + "\", x = " + x2 + ", y = " + y2);
        System.out.println("Maximum Gain: " + result2); // Expected output: 20
    }

    /**
     * Inner class containing the solution logic.
     */
    class MaximumGainSolver {
        /**
         * Calculates the maximum gain by trying both greedy strategies (ab then ba, and ba then ab)
         * and returning the larger score.
         * @param S The input string.
         * @param x Points for removing "ab".
         * @param y Points for removing "ba".
         * @return The maximum possible score.
         */
        public int maximumGain(String S, int x, int y) {
            char[] s = S.toCharArray();
            return Math.max(
                    calculateForOrder(s, x, y, 'a', 'b'),
                    calculateForOrder(s, y, x, 'b', 'a')
            );
        }

        /**
         * Helper function to calculate the score for a specific removal order.
         * @param s The input character array.
         * @param score1 Points for the primary pair.
         * @param score2 Points for the secondary pair.
         * @param p1 The first character of the primary pair (e.g., 'a').
         * @param p2 The second character of the primary pair (e.g., 'b').
         * @return The total score for this specific removal strategy.
         */
        int calculateForOrder(char[] s, int score1, int score2, char p1, char p2) {
            int n = s.length;
            char[] stack1 = new char[n];
            int totalScore = 0;
            int p = 0; // Stack pointer for the first pass

            // First pass: remove the primary pair (p1, p2)
            for (char c : s) {
                if (p > 0 && stack1[p - 1] == p1 && c == p2) {
                    totalScore += score1;
                    p--; // Pop from stack
                } else {
                    stack1[p++] = c; // Push to stack
                }
            }

            char[] stack2 = new char[p];
            int q = 0; // Stack pointer for the second pass

            // Second pass: on the remaining characters, remove the secondary pair (p2, p1)
            for (int i = 0; i < p; i++) {
                char c = stack1[i];
                if (q > 0 && stack2[q - 1] == p2 && c == p1) {
                    totalScore += score2;
                    q--; // Pop from stack
                } else {
                    stack2[q++] = c; // Push to stack
                }
            }
            return totalScore;
        }
    }
}
