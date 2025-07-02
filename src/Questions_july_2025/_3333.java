package Questions_july_2025;

import java.util.*;
import java.io.*;

public class _3333 {
    private static final int mod = 1000000007;

    public int possibleStringCount(String word, int k) {
        int n = word.length();
        int currentCount = 1;

        List<Integer> groupSizes = new ArrayList<>();
        for (int i = 1; i < n; ++i) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                currentCount++;
            } else {
                groupSizes.add(currentCount);
                currentCount = 1;
            }
        }
        groupSizes.add(currentCount);

        long totalWays = 1;
        for (int size : groupSizes) {
            totalWays = (totalWays * size) % mod;
        }

        if (groupSizes.size() >= k) {
            return (int) totalWays;
        }

        int[] dp = new int[k];
        int[] prefixSum = new int[k];
        dp[0] = 1;
        Arrays.fill(prefixSum, 1);

        for (int size : groupSizes) {
            int[] newDp = new int[k];
            for (int j = 1; j < k; ++j) {
                newDp[j] = prefixSum[j - 1];
                if (j - size - 1 >= 0) {
                    newDp[j] = (newDp[j] - prefixSum[j - size - 1] + mod) % mod;
                }
            }

            int[] newPrefix = new int[k];
            newPrefix[0] = newDp[0];
            for (int j = 1; j < k; ++j) {
                newPrefix[j] = (newPrefix[j - 1] + newDp[j]) % mod;
            }

            dp = newDp;
            prefixSum = newPrefix;
        }

        return (int) ((totalWays - prefixSum[k - 1] + mod) % mod);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String word = reader.readLine().trim();
        int k = Integer.parseInt(reader.readLine().trim());

        _3333 solution = new _3333();
        int result = solution.possibleStringCount(word, k);
        System.out.println(result);
    }
}