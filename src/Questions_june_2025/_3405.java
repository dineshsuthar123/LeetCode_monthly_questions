package Questions_june_2025;

import java.util.Scanner;

public class _3405 {
    private static final int MOD = 1000000007;
    private static final int MAXN = 100000;
    private static final long[] fact = new long[MAXN + 1];
    private static final long[] invFact = new long[MAXN + 1];

    static {
        fact[0] = 1L;
        for (int i = 1; i <= MAXN; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        invFact[MAXN] = modExp(fact[MAXN], MOD - 2);
        for (int i = MAXN - 1; i >= 0; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }
    }

    private static long modExp(long base, int exp) {
        long result = 1L;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % _3405.MOD;
            }
            base = (base * base) % _3405.MOD;
            exp >>= 1;
        }
        return result;
    }

    private static long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        return fact[n] * invFact[r] % MOD * invFact[n - r] % MOD;
    }

    public static int countGoodArrays(int n, int m, int k) {
        if (n == 1) {
            return (k == 0) ? m : 0;
        }
        long choose = nCr(n - 1, k);
        long ways = choose % MOD;
        ways = (ways * m) % MOD;
        ways = (ways * modExp(m - 1, n - k - 1)) % MOD;
        return (int) ways;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            System.out.println(countGoodArrays(n, m, k));
        }
        sc.close();
    }
}
