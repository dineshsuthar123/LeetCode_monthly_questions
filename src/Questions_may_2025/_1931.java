package Questions_may_2025;

import java.util.*;

public class _1931 {
    public static void main(String[] args) {
        int m = 2;
        int n = 3;
        ColorTheGridSolution solution = new ColorTheGridSolution();
        System.out.println("Result = " + solution.colorTheGrid(m, n));
    }
}

class ColorTheGridSolution {
    private static final int mod = 1_000_000_007;

    public int colorTheGrid(int m, int n) {
        int max = (int) Math.pow(3, m);
        List<Integer> valid = new ArrayList<>();
        // 1) collect valid masks
        for (int i = 0; i < max; i++) {
            if (isValid(i, m)) valid.add(i);
        }
        int k = valid.size();
        System.out.println("Valid masks (k=" + k + "): " + valid);

        // 2) build adjacency
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < k; i++) {
            int a = valid.get(i);
            for (int j = 0; j < k; j++) {
                int b = valid.get(j);
                if (compatible(a, b, m)) {
                    adj.get(i).add(j);
                }
            }
        }
        // print adjacency
        for (int i = 0; i < k; i++) {
            System.out.println(
                    "Mask " + valid.get(i)
                            + " (idx " + i + ") can follow idxes " + adj.get(i)
            );
        }

        // 3) dp over columns
        long[] dp = new long[k];
        Arrays.fill(dp, 1);
        System.out.println("DP after column 1: " + Arrays.toString(dp));

        for (int col = 2; col <= n; col++) {
            long[] next = new long[k];
            System.out.println("\n-- Computing column " + col + " --");
            System.out.println(" prev dp = " + Arrays.toString(dp));

            for (int i = 0; i < k; i++) {
                for (int j : adj.get(i)) {
                    next[j] = (next[j] + dp[i]) % mod;
                }
            }

            System.out.println(" next dp = " + Arrays.toString(next));
            dp = next;
        }

        // 4) sum up
        long res = 0;
        for (long x : dp) res = (res + x) % mod;
        return (int) res;
    }

    private boolean isValid(int mask, int m) {
        int prev = -1;
        for (int i = 0; i < m; i++) {
            int c = mask % 3;
            if (c == prev) return false;
            prev = c;
            mask /= 3;
        }
        return true;
    }

    private boolean compatible(int a, int b, int m) {
        for (int i = 0; i < m; i++) {
            if (a % 3 == b % 3) return false;
            a /= 3;
            b /= 3;
        }
        return true;
    }
}
