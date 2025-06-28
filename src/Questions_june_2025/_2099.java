package Questions_june_2025;

import java.util.*;

public class _2099 {
    public static int[] maxSubsequence(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        Map<Integer, Integer> countMap = new HashMap<>();
        while (!pq.isEmpty()) {
            int val = pq.poll();
            countMap.put(val, countMap.getOrDefault(val, 0) + 1);
        }
        int[] result = new int[k];
        int idx = 0;
        for (int num : nums) {
            if (countMap.getOrDefault(num, 0) > 0) {
                result[idx++] = num;
                countMap.put(num, countMap.get(num) - 1);
                if (idx == k) break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        sc.close();

        int[] subsequence = maxSubsequence(nums, k);
        System.out.println(Arrays.toString(subsequence));
    }
}
