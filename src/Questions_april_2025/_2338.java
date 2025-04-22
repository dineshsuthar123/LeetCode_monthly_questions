package Questions_april_2025;

public class _2338 {
    public static void main(String[] args) {
        int n = 4;
        int maxValue = 3;
        IdealArraysSolution solution = new IdealArraysSolution();
        System.out.println(solution.idealArrays(n, maxValue));
    }
}

class IdealArraysSolution {
    private static final int MOD = 1_000_000_007;
    private final int[][] cnt = new int[10001][14];
    private final int[][] comb = new int[10001][14];

    public IdealArraysSolution() {
        for (int s = 0; s <= 10000; s++) {
            for (int r = 0; r <= Math.min(s, 13); r++) {
                if (r == 0 || r == s) {
                    comb[s][r] = 1;
                } else {
                    comb[s][r] = (comb[s - 1][r - 1] + comb[s - 1][r]) % MOD;
                }
            }
        }
        for (int div = 1; div <= 10000; div++) {
            cnt[div][0] = 1;
            for (int multiple = div * 2; multiple <= 10000; multiple += div) {
                for (int bars = 0; bars < 13; bars++) {
                    if (cnt[div][bars] > 0) {
                        cnt[multiple][bars + 1] = (cnt[multiple][bars + 1] + cnt[div][bars]) % MOD;
                    }
                }
            }
        }
    }

    public int idealArrays(int n, int maxValue) {
        long result = 0;
        for (int i = 1; i <= maxValue; i++) {
            for (int bars = 0; bars < Math.min(n, 14); bars++) {
                result = (result + (long) cnt[i][bars] * comb[n - 1][bars]) % MOD;
            }
        }
        return (int) result;
    }
}
