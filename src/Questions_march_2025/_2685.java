package Questions_march_2025;

import java.util.*;

public class _2685 {
    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0, 1}, {0, 2}, {1, 2}, {3, 4}};

        CountCompleteComponentsSolution solution = new CountCompleteComponentsSolution();
        System.out.println(solution.countCompleteComponents(n, edges));
    }
}

class CountCompleteComponentsSolution {
    public int countCompleteComponents(int n, int[][] edges) {
        int[][] adj = new int[n][n];

        for (int[] edge : edges) {
            adj[edge[0]][edge[1]] = 1;
            adj[edge[1]][edge[0]] = 1;
        }

        boolean[] v = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!v[i]) {
                List<Integer> com = new ArrayList<>();
                dfs(i, adj, v, com);

                if (complete(adj, com)) {
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int node, int[][] adj, boolean[] v, List<Integer> com) {
        v[node] = true;
        com.add(node);
        for (int i = 0; i < adj.length; i++) {
            if (adj[node][i] == 1 && !v[i]) {
                dfs(i, adj, v, com);
            }
        }
    }

    private boolean complete(int[][] adj, List<Integer> com) {
        int size = com.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (adj[com.get(i)][com.get(j)] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}

