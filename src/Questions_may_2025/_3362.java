package Questions_may_2025;

import java.util.*;

public class _3362 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int[][] queries = {
                {0, 1},
                {1, 2},
                {2, 2}
        };
        Solution solution = new Solution();
        System.out.println(solution.maxRemoval(nums, queries));
    }
}

class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        int n = nums.length;
        PriorityQueue<Integer> usedQuery = new PriorityQueue<>();
        PriorityQueue<Integer> availableQuery = new PriorityQueue<>(Collections.reverseOrder());
        Arrays.sort(queries, Comparator.comparingInt(a -> a[0]));
        int queryPos = 0, appliedCount = 0;
        for (int i = 0; i < n; i++) {
            while (queryPos < queries.length && queries[queryPos][0] == i) {
                availableQuery.offer(queries[queryPos][1]);
                queryPos++;
            }
            nums[i] -= usedQuery.size();
            while (nums[i] > 0 && !availableQuery.isEmpty() && availableQuery.peek() >= i) {
                usedQuery.offer(availableQuery.poll());
                nums[i]--;
                appliedCount++;
            }
            if (nums[i] > 0) {
                System.out.println("-1");
                return -1;
            }
            while (!usedQuery.isEmpty() && usedQuery.peek() == i) {
                usedQuery.poll();
            }
        }
        return queries.length - appliedCount;
    }
}
