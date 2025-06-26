package Questions_june_2025;

import java.util.*;

public class _2311 {
    public static int longestSubsequence(String s, int k) {
        int n = s.length();
        long value = 0, power = 1;
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                count++;
            } else if (value + power <= k) {
                value += power;
                count++;
            }
            power = Math.min(power * 2, (long) k + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNext()) return;
        String s = sc.next();
        if (!sc.hasNextInt()) return;
        int k = sc.nextInt();
        System.out.println(longestSubsequence(s, k));
        sc.close();
    }
}
