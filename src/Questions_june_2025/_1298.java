package Questions_june_2025;

import java.util.*;

public class _1298 {
    public static void main(String[] args) {
        int[] status = {1, 0, 1, 0};
        int[] candies = {7, 5, 4, 100};
        int[][] keys = {{1}, {}, {3}, {}};
        int[][] containedBoxes = {{1, 2}, {}, {}, {}};
        int[] initialBoxes = {0};

        MaxCandiesSolution solution = new MaxCandiesSolution();
        System.out.println(solution.maxCandies(status, candies, keys, containedBoxes, initialBoxes));
    }
}

class MaxCandiesSolution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int total = 0;
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> have = new HashSet<>();
        Set<Integer> opened = new HashSet<>();
        Set<Integer> keySet = new HashSet<>();
        for (int b : initialBoxes) {
            have.add(b);
            if (status[b] == 1) {
                q.add(b);
                opened.add(b);
            }
        }
        while (!q.isEmpty()) {
            int box = q.poll();
            total += candies[box];
            for (int k : keys[box]) {
                if (!keySet.contains(k)) {
                    keySet.add(k);
                    if (have.contains(k) && !opened.contains(k) && status[k] == 0) {
                        q.add(k);
                        opened.add(k);
                    }
                }
            }
            for (int nb : containedBoxes[box]) {
                if (!have.contains(nb)) {
                    have.add(nb);
                    if ((status[nb] == 1 || keySet.contains(nb)) && !opened.contains(nb)) {
                        q.add(nb);
                        opened.add(nb);
                    }
                }
            }
        }
        return total;
    }
}

