package Questions_march_2025;

import java.util.*;

public class _2551 {
    public static void main(String[] args) {
        int[] weights = {1, 3, 5, 1};
        int k = 2;

        PutMarblesSolution solution = new PutMarblesSolution();
        System.out.println("Difference: " + solution.putMarbles(weights, k));
    }
}

class PutMarblesSolution {
    public long putMarbles(int[] weights, int k) {
        if (k == 1) return 0;
        int n = weights.length;
        long s = weights[0] + weights[n - 1];

        int[] total = new int[n - 1];
        for (int i = 1; i < n; i++) {
            total[i - 1] = weights[i] + weights[i - 1];
        }

        Arrays.sort(total);

        long min = s, max = s;
        for (int i = 0; i < k - 1; i++) {
            min += total[i];
            max += total[total.length - i - 1];
        }

        return max - min;
    }
}

