package Questions_april_2025;

public class _2140 {
    public static void main(String[] args) {
        int[][] questions = {
                {3, 2},
                {4, 3},
                {4, 4},
                {2, 5}
        };

        MostPointsSolution solution = new MostPointsSolution();
        System.out.println("Maximum points: " + solution.mostPoints(questions));
    }
}

class MostPointsSolution {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            int points = questions[i][0];
            int power = questions[i][1];
            int next = i + power + 1;

            long s = points + (next < n ? dp[next] : 0);
            long t = dp[i + 1];
            dp[i] = Math.max(s, t);
        }

        return dp[0];
    }
}

