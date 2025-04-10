package Questions_april_2025;

public class _2999 {
    public static void main(String[] args) {
        long start = 1000;
        long finish = 100000;
        int limit = 7;
        String suffix = "123";

        NumberOfPowerfulIntSolution1 sol1 = new NumberOfPowerfulIntSolution1();
        long result1 = sol1.numberOfPowerfulInt(start, finish, limit, suffix);
        System.out.println("Solution1 result: " + result1);

        NumberOfPowerfulIntSolution2 sol2 = new NumberOfPowerfulIntSolution2();
        long result2 = sol2.numberOfPowerfulInt(start, finish, limit, suffix);
        System.out.println("Solution2 result: " + result2);
    }
}

class NumberOfPowerfulIntSolution1 {
    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        long sNum = Long.parseLong(s);
        int m = s.length();
        long pow10m = 1;
        for (int i = 0; i < m; i++) {
            pow10m *= 10;
        }
        int lenStart = String.valueOf(start).length();
        int lenFinish = String.valueOf(finish).length();
        long count = 0;
        if (sNum >= start && sNum <= finish) {
            count++;
        }
        int maxK = lenFinish - m;
        for (int k = 1; k <= maxK; k++) {
            int lenX = k + m;
            long pow10kMinus1 = 1;
            for (int i = 0; i < k - 1; i++) {
                pow10kMinus1 *= 10;
            }
            long pow10k = pow10kMinus1 * 10;
            long prefixMin = pow10kMinus1;
            long prefixMax = (pow10k - 1) / 9 * limit;
            long xMin = prefixMin * pow10m + sNum;
            long xMax = prefixMax * pow10m + sNum;
            if (lenX < lenStart) {
                if (xMax < start) {
                    continue;
                }
            } else if (lenX > lenFinish) {
                continue;
            } else {
                if (xMin > finish || xMax < start) {
                    continue;
                }
            }
            long numerator = start - sNum;
            long lowerP;
            lowerP = (numerator + pow10m - 1) / pow10m;
            long upperP = (finish - sNum) / pow10m;
            long adjustedLower = Math.max(lowerP, prefixMin);
            long adjustedUpper = Math.min(upperP, prefixMax);
            if (adjustedUpper < adjustedLower) {
                continue;
            }
            long currentCount = countValidPrefixes(adjustedLower, adjustedUpper, limit, k);
            count += currentCount;
        }
        return count;
    }

    private long countValidPrefixes(long a, long b, int limit, int k) {
        if (a > b) {
            return 0;
        }
        long countB = countUpTo(b, limit, k);
        long countAminus1 = countUpTo(a - 1, limit, k);
        return countB - countAminus1;
    }

    private long countUpTo(long num, int limit, int k) {
        String s = Long.toString(num);
        if (s.length() < k) {
            return 0;
        }
        if (s.length() > k) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < k; i++) {
                sb.append('9');
            }
            s = sb.toString();
            num = Long.parseLong(s);
        }
        return digitDP(s, limit, k);
    }

    private long digitDP(String s, int limit, int requiredLen) {
        Long[][][] memo = new Long[requiredLen][2][2];
        return dfs(0, true, true, s, limit, requiredLen, memo);
    }

    private long dfs(int pos, boolean isLimit, boolean isLeading, String s, int limit, int requiredLen, Long[][][] memo) {
        if (pos == requiredLen) {
            return isLeading ? 0 : 1;
        }
        int memoLimit = isLimit ? 1 : 0;
        int memoLeading = isLeading ? 1 : 0;
        if (memo[pos][memoLimit][memoLeading] != null) {
            return memo[pos][memoLimit][memoLeading];
        }
        long res = 0;
        int upper = isLimit ? (s.charAt(pos) - '0') : 9;
        if (isLeading) {
            for (int d = 1; d <= upper; d++) {
                if (d > limit) {
                    break;
                }
                boolean newIsLimit = isLimit && (d == upper);
                res += dfs(pos + 1, newIsLimit, false, s, limit, requiredLen, memo);
            }
        } else {
            for (int d = 0; d <= upper; d++) {
                if (d > limit) {
                    continue;
                }
                boolean newIsLimit = isLimit && (d == upper);
                res += dfs(pos + 1, newIsLimit, false, s, limit, requiredLen, memo);
            }
        }
        memo[pos][memoLimit][memoLeading] = res;
        return res;
    }
}

class NumberOfPowerfulIntSolution2 {
    private Long[][] dp;
    private String suffix;
    private int limit;

    public long numberOfPowerfulInt(long start, long finish, int limit, String suffix) {
        this.limit = limit;
        this.suffix = suffix;
        long countToFinish = countValid(finish);
        long countToStart = countValid(start - 1);
        return countToFinish - countToStart;
    }

    private long countValid(long num) {
        if (num < Long.parseLong(suffix)) return 0;
        String numStr = Long.toString(num);
        dp = new Long[numStr.length()][2];
        return dfs(0, true, numStr);
    }

    private long dfs(int idx, boolean tight, String num) {
        if (idx == num.length()) return 1L;
        if (dp[idx][tight ? 1 : 0] != null) return dp[idx][tight ? 1 : 0];
        long res = 0;
        int maxDigit = tight ? num.charAt(idx) - '0' : 9;
        int suffixStart = num.length() - suffix.length();
        if (idx >= suffixStart) {
            int suffixIdx = idx - suffixStart;
            int digit = suffix.charAt(suffixIdx) - '0';
            if (digit <= maxDigit && digit <= limit)
                res += dfs(idx + 1, tight && digit == maxDigit, num);
        } else {
            for (int d = 0; d <= Math.min(maxDigit, limit); d++) {
                res += dfs(idx + 1, tight && d == maxDigit, num);
            }
        }
        dp[idx][tight ? 1 : 0] = res;
        return res;
    }
}

