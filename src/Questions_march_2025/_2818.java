package Questions_march_2025;

import java.util.*;

class Solution_2818 {
    static final int MOD = 1_000_000_007;

    public int maximumScore(List<Integer> nums, int k) {
        int n = nums.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums.get(i);
        }

        int[] prime = new int[n];
        for (int i = 0; i < n; i++) {
            prime[i] = getPrimeScore(arr[i]);
        }

        int[] left = new int[n];
        Arrays.fill(left, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prime[stack.peek()] < prime[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        int[] right = new int[n];
        Arrays.fill(right, n);
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && prime[stack.peek()] <= prime[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        long[] count = new long[n];
        for (int i = 0; i < n; i++) {
            count[i] = (long) (i - left[i]) * (right[i] - i);
        }

        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = arr[i];
            pairs[i][1] = i;
        }
        Arrays.sort(pairs, (a, b) -> Integer.compare(b[0], a[0]));

        long result = 1;
        long remain = k;
        for (int[] pair : pairs) {
            if (remain <= 0) break;
            int value = pair[0];
            int index = pair[1];
            long times = Math.min(count[index], remain);
            result = (result * modPow(value, times, MOD)) % MOD;
            remain -= times;
        }
        return (int) result;
    }

    private long modPow(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = (res * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }

    private int getPrimeScore(int x) {
        int score = 0;
        for (int p = 2; p * p <= x; p++) {
            if (x % p == 0) {
                score++;
                while (x % p == 0) {
                    x /= p;
                }
            }
        }
        if (x > 1) score++;
        return score;
    }

    public static void main(String[] args) {
        Solution_2818 solution = new Solution_2818();

        List<Integer> nums = Arrays.asList(8, 3, 9, 7, 2, 1);
        int k = 3;

        int result = solution.maximumScore(nums, k);
        System.out.println("Maximum Score: " + result);
    }
}

