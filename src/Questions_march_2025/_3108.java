package Questions_march_2025;

import java.util.*;

public class _3108 {
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0, 1, 3}, {1, 2, 2}, {2, 3, 4}, {3, 4, 6}};
        int[][] queries = {{0, 2}, {1, 4}, {0, 4}, {2, 3}};

        MinimumCostSolution solution = new MinimumCostSolution();
        System.out.println(Arrays.toString(solution.minimumCost(n, edges, queries)));
    }
}

class MinimumCostSolution {
    int[] parent;
    int[] minPathCost;

    public int findRoot(int node) {
        if (parent[node] != node) {
            parent[node] = findRoot(parent[node]);
        }
        return parent[node];
    }

    public int[] minimumCost(int n, int[][] edges, int[][] queries) {
        parent = new int[n];
        minPathCost = new int[n];
        Arrays.fill(minPathCost, -1);

        for (int i = 0; i < n; i++) parent[i] = i;

        for (int[] edge : edges) {
            int source = edge[0], target = edge[1], weight = edge[2];
            int sourceRoot = findRoot(source);
            int targetRoot = findRoot(target);

            if (minPathCost[sourceRoot] == -1) minPathCost[sourceRoot] = weight;
            else minPathCost[sourceRoot] &= weight;

            if (minPathCost[targetRoot] == -1) minPathCost[targetRoot] = weight;
            else minPathCost[targetRoot] &= weight;

            if (sourceRoot != targetRoot) {
                parent[sourceRoot] = targetRoot;
                minPathCost[targetRoot] &= minPathCost[sourceRoot];
            }
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0], end = queries[i][1];

            if (start == end) result[i] = 0;
            else if (findRoot(start) != findRoot(end)) result[i] = -1;
            else result[i] = minPathCost[findRoot(start)];
        }

        return result;
    }
}
