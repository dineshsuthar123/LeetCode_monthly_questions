package Questions_july_2025;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class _1865 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Read nums1
        int n1 = sc.nextInt();
        int[] nums1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            nums1[i] = sc.nextInt();
        }

        // Read nums2
        int n2 = sc.nextInt();
        int[] nums2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            nums2[i] = sc.nextInt();
        }

        // Initialize our unique FindSumPairs class
        FindSumPairsUnique fsp = new FindSumPairsUnique(nums1, nums2);

        // Process queries
        int q = sc.nextInt();
        while (q-- > 0) {
            String type = sc.next();
            if ("add".equals(type)) {
                int index = sc.nextInt();
                int val   = sc.nextInt();
                fsp.add(index, val);
            } else if ("count".equals(type)) {
                int total = sc.nextInt();
                System.out.println(fsp.count(total));
            }
        }

        sc.close();
    }

    // A uniquely named inner class holding the logic
    static class FindSumPairsUnique {
        private final int[] nums1;
        private final int[] nums2;
        private final Map<Integer, Integer> freq;

        public FindSumPairsUnique(int[] nums1, int[] nums2) {
            this.nums1 = nums1;
            this.nums2 = nums2;
            this.freq  = new HashMap<>();
            for (int x : nums2) {
                freq.put(x, freq.getOrDefault(x, 0) + 1);
            }
        }

        public void add(int index, int val) {
            int old = nums2[index];
            int now = old + val;
            nums2[index] = now;

            // decrement old count
            freq.put(old, freq.get(old) - 1);
            if (freq.get(old) == 0) {
                freq.remove(old);
            }
            // increment new count
            freq.put(now, freq.getOrDefault(now, 0) + 1);
        }

        public int count(int tot) {
            int cnt = 0;
            for (int a : nums1) {
                cnt += freq.getOrDefault(tot - a, 0);
            }
            return cnt;
        }
    }
}