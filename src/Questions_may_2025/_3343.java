package Questions_may_2025;

public class _3343 {
    public static void main(String[] args) {
        String num = "1122";
        BalancedPermutationsSolution solution = new BalancedPermutationsSolution();
        System.out.println(solution.countBalancedPermutations(num));
    }
}

class BalancedPermutationsSolution {
    private static final int M = 1_000_000_007;

    public int countBalancedPermutations(String num) {
        int n = num.length(), s = 0;
        int[] f = new int[10];
        for (char c : num.toCharArray()) {
            int d = c - '0';
            f[d]++;
            s += d;
        }
        if ((s & 1) == 1) return 0;
        int half = s / 2, e = (n + 1) / 2, o = n / 2;
        long[] fact = new long[n + 1], inv = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i % M;
        inv[n] = pow(fact[n], M - 2);
        for (int i = n; i > 0; i--) inv[i - 1] = inv[i] * i % M;
        long[][] dp = new long[e + 1][half + 1];
        dp[0][0] = 1;
        for (int d = 0; d <= 9; d++) if (f[d] > 0) {
            int cnt = f[d];
            long[] w = new long[cnt + 1];
            for (int k = 0; k <= cnt; k++) {
                w[k] = inv[k] * inv[cnt - k] % M;
            }
            for (int i = e; i >= 0; i--) {
                for (int j = half; j >= 0; j--) {
                    long cur = dp[i][j];
                    if (cur == 0) continue;
                    dp[i][j] = 0;
                    for (int k = 0; k <= cnt && i + k <= e && j + k * d <= half; k++) {
                        dp[i + k][j + k * d] = (dp[i + k][j + k * d] + cur * w[k]) % M;
                    }
                }
            }
        }
        return (int)(dp[e][half] * fact[e] % M * fact[o] % M);
    }

    private long pow(long x, int p) {
        long r = 1;
        while (p > 0) {
            if ((p & 1) == 1) r = r * x % M;
            x = x * x % M;
            p >>= 1;
        }
        return r;
    }
}
