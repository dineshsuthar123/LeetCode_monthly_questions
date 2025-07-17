package Questions_july_2025;

import java.util.*;

public class _3201 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        System.out.println(new Solution3201().maximumLength(nums));
        sc.close();
    }

    static class Solution3201 {
        public int maximumLength(int[] nums) {
            int n = nums.length, evenCount = 0, oddCount = 0;
            for (int x : nums) {
                if ((x & 1) == 0) evenCount++;
                else oddCount++;
            }
            int altLen = 1, prevParity = nums[0] & 1;
            for (int i = 1; i < n; i++) {
                int curParity = nums[i] & 1;
                if (curParity != prevParity) {
                    altLen++;
                    prevParity = curParity;
                }
            }
            return Math.max(Math.max(evenCount, oddCount), altLen);
        }
    }
}

