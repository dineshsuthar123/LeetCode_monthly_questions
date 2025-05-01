package Questions_april_2025;

import java.util.*;

public class _2071 {
    public static void main(String[] args) {
        int[] tasks = {5, 9, 8, 2};
        int[] workers = {1, 6, 4, 3};
        int pills = 1;
        int strength = 5;
        MaxTaskAssignSolution solution = new MaxTaskAssignSolution();
        System.out.println(solution.maxTaskAssign(tasks, workers, pills, strength));
    }
}

class MaxTaskAssignSolution {
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int n = tasks.length, m = workers.length;
        int lo = 0, hi = Math.min(n, m), ans = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (canAssign(tasks, workers, pills, strength, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    private boolean canAssign(int[] tasks, int[] workers, int pills, int strength, int k) {
        TreeMap<Integer, Integer> multiset = new TreeMap<>();
        for (int i = workers.length - k; i < workers.length; i++) {
            multiset.merge(workers[i], 1, Integer::sum);
        }
        for (int i = k - 1; i >= 0; i--) {
            int need = tasks[i];
            int best = multiset.lastKey();
            if (best >= need) {
                removeOne(multiset, best);
            } else {
                if (pills == 0) return false;
                Integer withPill = multiset.ceilingKey(need - strength);
                if (withPill == null) return false;
                removeOne(multiset, withPill);
                pills--;
            }
        }
        return true;
    }

    private void removeOne(TreeMap<Integer, Integer> map, int key) {
        int cnt = map.get(key);
        if (cnt == 1) map.remove(key);
        else map.put(key, cnt - 1);
    }
}
