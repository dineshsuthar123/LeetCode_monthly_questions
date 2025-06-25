package Questions_june_2025;
import java.util.*;

public class _2040 {
    public static long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long left = -10000000000L, right = 10000000000L;
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (countProducts(nums1, nums2, mid) < k) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    private static long countProducts(int[] nums1, int[] nums2, long target) {
        long count = 0;
        for (int num1 : nums1) {
            if (num1 == 0) {
                if (target >= 0) count += nums2.length;
            } else {
                int low = 0, high = nums2.length;
                while (low < high) {
                    int mid = low + (high - low) / 2;
                    long prod = (long) num1 * nums2[mid];
                    if (prod <= target) {
                        if (num1 > 0) low = mid + 1;
                        else high = mid;
                    } else {
                        if (num1 > 0) high = mid;
                        else low = mid + 1;
                    }
                }
                count += (num1 > 0) ? low : nums2.length - low;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums1 = new int[n];
        for (int i = 0; i < n; i++) nums1[i] = sc.nextInt();
        int m = sc.nextInt();
        int[] nums2 = new int[m];
        for (int i = 0; i < m; i++) nums2[i] = sc.nextInt();
        long k = sc.nextLong();
        System.out.println(kthSmallestProduct(nums1, nums2, k));
        sc.close();
    }
}

