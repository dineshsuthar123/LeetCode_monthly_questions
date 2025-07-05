package Questions_july_2025;

import java.util.*;

public class _1394 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();

        Solution_1394 sol = new Solution_1394();
        int result = sol.findLucky(arr);
        System.out.println(result);
    }
}

class Solution_1394 {
    public int findLucky(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int freq = entry.getValue();
            if (key == freq) {
                ans.add(key);
            }
        }
        if (ans.isEmpty()) {
            return -1;
        }
        return Collections.max(ans);
    }
}
