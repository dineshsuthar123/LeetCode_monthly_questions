package Questions_august_2025;

import java.util.*;

public class _2438 {
    public static void main(String[] args) {
        int n = 45;
        int[][] queries = { {0, 1}, {1, 2}, {2, 3}, {0, 3} };

        int[] result = new Solution2438().productQueries(n, queries);
        System.out.println(Arrays.toString(result));
    }
    static class Solution2438 {
        public int[] productQueries(int n, int[][] queries) {
            final int MOD = 1_000_000_007;
            List<Integer> powerOf2 = new ArrayList<>();
            for (int i = 0; i < 32; i++) {
                if (((n >> i) & 1) == 1) {
                    powerOf2.add(1 << i);
                }
            }
            int m = queries.length;
            int[] res = new int[m];
            for (int i = 0; i < m; i++) {
                long prod = 1;
                for (int j = queries[i][0]; j <= queries[i][1]; j++) {
                    prod = (prod * powerOf2.get(j)) % MOD;
                }
                res[i] = (int) prod;
            }

            return res;
        }
    }
}

