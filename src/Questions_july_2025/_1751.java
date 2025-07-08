package Questions_july_2025;

import java.util.*;

public class _1751 {
    public static void main(String[] args) {
        int[][] events = {{1,2,4},{3,4,3},{2,3,1}};
        int k = 2;
        SolverUnique solver = new SolverUnique();
        System.out.println(solver.maxValue(events, k));
    }

    static class SolverUnique {
        private int[][] events;
        private int[] starts;
        private int[][] memo;

        public int maxValue(int[][] events, int k) {
            Arrays.sort(events, Comparator.comparingInt(e -> e[0]));
            this.events = events;
            int n = events.length;
            starts = new int[n];
            for (int i = 0; i < n; i++) {
                starts[i] = events[i][0];
            }
            memo = new int[n][k + 1];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }
            return dfs(0, k);
        }

        private int dfs(int idx, int k) {
            if (idx >= events.length || k == 0) {
                return 0;
            }
            if (memo[idx][k] != -1) {
                return memo[idx][k];
            }
            int best = dfs(idx + 1, k);
            int nextIdx = lowerBound(starts, events[idx][1] + 1);
            int take = events[idx][2] + dfs(nextIdx, k - 1);
            memo[idx][k] = Math.max(best, take);
            return memo[idx][k];
        }

        private int lowerBound(int[] arr, int target) {
            int lo = 0, hi = arr.length;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (arr[mid] < target) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return lo;
        }
    }
}