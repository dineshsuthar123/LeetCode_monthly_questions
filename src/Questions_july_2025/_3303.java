package Questions_july_2025;

import java.util.Scanner;

public class _3303 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Read k
        long k = sc.nextLong();
        // Read number of operations
        int n = sc.nextInt();
        int[] op = new int[n];
        for (int i = 0; i < n; i++) {
            op[i] = sc.nextInt();
        }
        sc.close();

        Solution_3303 sol = new Solution_3303();
        char result = sol.kthCharacter(k, op);
        System.out.println(result);
    }
}

class Solution_3303{
    public char kthCharacter(long k, int[] op) {
        int n = op.length;
        long[] len = new long[n + 1];
        len[0] = 1;
        for (int i = 1; i <= n; i++) {
            long doubled = len[i - 1] << 1;
            len[i] = doubled > k ? k + 1 : doubled;
        }

        long increments = 0;
        for (int i = n; i > 0; i--) {
            long half = len[i - 1];
            if (k > half) {
                k -= half;
                if (op[i - 1] == 1) {
                    increments++;
                }
            }
        }

        int shift = (int)(increments % 26);
        return (char) ('a' + shift);
    }
}