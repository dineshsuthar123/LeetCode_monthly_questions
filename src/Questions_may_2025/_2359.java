package Questions_may_2025;

import java.util.*;

public class _2359 {
    public static void main(String[] args) {
        int[] edges = {2, 2, 3, -1};
        int node1 = 0;
        int node2 = 1;
        ClosestMeetingNodeSolution solution = new ClosestMeetingNodeSolution();
        System.out.println(solution.closestMeetingNode(edges, node1, node2));
    }
}

class ClosestMeetingNodeSolution {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        int[] dist1 = dfs(edges, node1, n);
        int[] dist2 = dfs(edges, node2, n);
        int max = Integer.MAX_VALUE;
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (dist1[i] >= 0 && dist2[i] >= 0) {
                int m = Math.max(dist1[i], dist2[i]);
                if (m < max) {
                    max = m;
                    ans = i;
                }
            }
        }
        return ans;
    }

    private int[] dfs(int[] edges, int u, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        int d = 0;
        while (u != -1 && dist[u] == -1) {
            dist[u] = d++;
            u = edges[u];
        }
        return dist;
    }
}
