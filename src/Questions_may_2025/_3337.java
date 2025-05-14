package Questions_may_2025;

import java.util.*;

public class _3337 {
    public static void main(String[] args) {
        String s = "abc";
        int t = 2;
        List<Integer> nums = Arrays.asList(
                2, 1, 3, 0, 2, 1, 1, 4, 2, 0, 1, 2, 3,
                1, 2, 1, 3, 2, 1, 0, 2, 1, 3, 0, 2, 1
        );
        LengthAfterTransformationsMatrixSolution solution = new LengthAfterTransformationsMatrixSolution();
        System.out.println(solution.lengthAfterTransformations(s, t, nums));
    }
}

class LengthAfterTransformationsMatrixSolution {
    static final int MOD = 1_000_000_007;

    private long[][] multiply(long[][] a, long[][] b) {
        long[][] res = new long[26][26];
        for (int i = 0; i < 26; ++i) {
            for (int k = 0; k < 26; ++k) {
                if (a[i][k] == 0) continue;
                for (int j = 0; j < 26; ++j) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return res;
    }

    private long[][] matrixPow(long[][] mat, int power) {
        long[][] result = new long[26][26];
        for (int i = 0; i < 26; ++i) {
            result[i][i] = 1;
        }
        while (power > 0) {
            if ((power & 1) == 1) {
                result = multiply(result, mat);
            }
            mat = multiply(mat, mat);
            power >>= 1;
        }
        return result;
    }

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        long[][] transition = new long[26][26];
        for (int c = 0; c < 26; ++c) {
            int num = nums.get(c);
            for (int j = 1; j <= num; ++j) {
                int nextChar = (c + j) % 26;
                transition[c][nextChar]++;
            }
        }

        long[][] matPow = matrixPow(transition, t);

        long[] cnt = new long[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        long[] newCnt = new long[26];
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < 26; ++j) {
                newCnt[j] = (newCnt[j] + cnt[i] * matPow[i][j]) % MOD;
            }
        }

        long total = 0;
        for (long x : newCnt) {
            total = (total + x) % MOD;
        }
        return (int) total;
    }
}
