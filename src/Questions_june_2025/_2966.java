package Questions_june_2025;

import java.util.*;

public class _2966 {
    public static int[][] divideArray(int[] nums, int k) {
        int size = nums.length;
        if (size % 3 != 0) return new int[0][0];
        Arrays.sort(nums);
        List<int[]> groups = new ArrayList<>();
        for (int i = 0; i < size; i += 3) {
            if (nums[i + 2] - nums[i] <= k) {
                groups.add(new int[]{nums[i], nums[i + 1], nums[i + 2]});
            } else {
                return new int[0][0];
            }
        }
        int[][] res = new int[groups.size()][3];
        for (int i = 0; i < groups.size(); i++) {
            res[i] = groups.get(i);
        }
        return res;
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
        int k = sc.nextInt();
        int[][] result = divideArray(nums, k);
        if (result.length == 0) {
            System.out.println("[]");
        } else {
            for (int[] group : result) {
                System.out.println(Arrays.toString(group));
            }
        }
        sc.close();
    }
}
