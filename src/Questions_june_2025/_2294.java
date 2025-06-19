package Questions_june_2025;

import java.util.*;

public class _2294 {
    public static int partitionArray(int[] A, int k) {
        Arrays.sort(A);
        int res = 1, mn = A[0], mx = A[0];
        for (int a: A) {
            mn = Math.min(mn, a);
            mx = Math.max(mx, a);
            if (mx - mn > k) {
                res++;
                mn = mx = a;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            if (sc.hasNextInt()) A[i] = sc.nextInt();
            else return;
        }
        if (!sc.hasNextInt()) return;
        int k = sc.nextInt();
        System.out.println(partitionArray(A, k));
        sc.close();
    }
}
