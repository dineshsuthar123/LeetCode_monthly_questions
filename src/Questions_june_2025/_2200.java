package Questions_june_2025;

import java.util.*;

public class _2200 {
    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = Math.max(0, i - k); j <= Math.min(nums.length - 1, i + k); j++) {
                if (nums[j] == key) {
                    result.add(i);
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            if (sc.hasNextInt()) nums[i] = sc.nextInt();
            else return;
        }
        if (!sc.hasNextInt()) return;
        int key = sc.nextInt();
        if (!sc.hasNextInt()) return;
        int k = sc.nextInt();
        List<Integer> res = findKDistantIndices(nums, key, k);
        System.out.println(res);
        sc.close();
    }
}
