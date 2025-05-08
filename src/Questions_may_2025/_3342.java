package Questions_may_2025;

import java.util.*;

public class _3342 {
    public static void main(String[] args) {
        int[][] moveTime = {
                {0, 2, 3},
                {1, 5, 2},
                {4, 2, 0}
        };
        int result = new MinTimeToReachWithParitySolution().minTimeToReach(moveTime);
        System.out.println(result);
    }
}

class MinTimeToReachWithParitySolution {
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;
        int[][][] dp = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j][0] = dp[i][j][1] = Integer.MAX_VALUE;
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        dp[0][0][0] = 0;
        pq.add(new int[]{0, 0, 0, 0});
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int t = cur[0], r = cur[1], c = cur[2], parity = cur[3];
            if (t > dp[r][c][parity]) continue;
            if (r == n-1 && c == m-1) return t;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                int stepCost = (parity == 0 ? 1 : 2);
                int arrive = Math.max(t, moveTime[nr][nc]) + stepCost;
                int nextParity = 1 - parity;
                if (arrive < dp[nr][nc][nextParity]) {
                    dp[nr][nc][nextParity] = arrive;
                    pq.add(new int[]{arrive, nr, nc, nextParity});
                }
            }
        }
        return -1;
    }
}
