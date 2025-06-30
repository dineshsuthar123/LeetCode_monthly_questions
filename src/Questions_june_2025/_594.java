package Questions_june_2025;

import java.util.*;

public class _594 {
    public static int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (int k : map.keySet()) {
            if (map.containsKey(k + 1)) {
                res = Math.max(res, map.get(k) + map.get(k + 1));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        int result = findLHS(nums);
        System.out.println(result);
    }
}
