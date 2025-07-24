package Questions_july_2025;

import java.util.*;

/**
 * Main class to contain and test different solutions for LeetCode 2322.
 */
public class _2322 {

    public static void main(String[] args) {
        // --- Test Case 1 ---
        System.out.println("--- Running Test Case 1 ---");
        int[] nums1 = {1, 5, 5, 4, 11};
        int[][] edges1 = {{0, 1}, {1, 2}, {1, 3}, {3, 4}};

        // Test the Brute Force O(n^3) solution
        BruteForceSolution bruteForce = new BruteForceSolution();
        int score1_brute = bruteForce.minimumScore(nums1, edges1);
        System.out.println("Brute Force O(n^3) Score: " + score1_brute); // Expected: 9

        // Test the Optimized O(n^2) solution
        OptimizedNxNSolution optimized = new OptimizedNxNSolution();
        int score1_optimized = optimized.minimumScore(nums1, edges1);
        System.out.println("Optimized O(n^2) Score: " + score1_optimized); // Expected: 9
        System.out.println();

        // --- Test Case 2 ---
        System.out.println("--- Running Test Case 2 ---");
        int[] nums2 = {5, 5, 2, 4, 4, 2};
        int[][] edges2 = {{0, 1}, {1, 2}, {5, 2}, {4, 3}, {1, 3}};

        // Test the Brute Force O(n^3) solution
        int score2_brute = bruteForce.minimumScore(nums2, edges2);
        System.out.println("Brute Force O(n^3) Score: " + score2_brute); // Expected: 0

        // Test the Optimized O(n^2) solution
        int score2_optimized = optimized.minimumScore(nums2, edges2);
        System.out.println("Optimized O(n^2) Score: " + score2_optimized); // Expected: 0
    }

    /**
     * An O(n^3) brute-force solution. It iterates through every possible pair of edges to remove
     * and, for each pair, calculates the three resulting component XOR sums to find the score.
     * This is simple to understand but too slow for larger constraints.
     */
    static class BruteForceSolution {
        public int minimumScore(int[] nums, int[][] edges) {
            int n = nums.length;
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                adj.get(edge[0]).add(edge[1]);
                adj.get(edge[1]).add(edge[0]);
            }

            int minScore = Integer.MAX_VALUE;

            // O(n^2) part: Iterate through every pair of edges to remove
            for (int i = 0; i < edges.length; i++) {
                for (int j = i + 1; j < edges.length; j++) {
                    int[] removedEdge1 = edges[i];
                    int[] removedEdge2 = edges[j];

                    boolean[] visited = new boolean[n];
                    List<Integer> componentXors = new ArrayList<>();

                    // O(n) part: Find the three components using BFS/traversal
                    for (int k = 0; k < n; k++) {
                        if (!visited[k]) {
                            int currentXor = 0;
                            Queue<Integer> q = new LinkedList<>();
                            q.add(k);
                            visited[k] = true;

                            while (!q.isEmpty()) {
                                int u = q.poll();
                                currentXor ^= nums[u];
                                for (int v : adj.get(u)) {
                                    if (!visited[v] && !isRemovedEdge(u, v, removedEdge1) && !isRemovedEdge(u, v, removedEdge2)) {
                                        visited[v] = true;
                                        q.add(v);
                                    }
                                }
                            }
                            componentXors.add(currentXor);
                        }
                    }

                    // Calculate score for this pair of removals
                    int max = Math.max(componentXors.get(0), Math.max(componentXors.get(1), componentXors.get(2)));
                    int min = Math.min(componentXors.get(0), Math.min(componentXors.get(1), componentXors.get(2)));
                    minScore = Math.min(minScore, max - min);
                }
            }
            return minScore;
        }

        private boolean isRemovedEdge(int u, int v, int[] removedEdge) {
            return (u == removedEdge[0] && v == removedEdge[1]) || (u == removedEdge[1] && v == removedEdge[0]);
        }
    }


    /**
     * An optimized O(n^2) solution. This approach is much more efficient.
     * 1. It iterates through every edge, considering it the *first* cut.
     * 2. This splits the tree into two components. It calculates the XOR sum of each.
     * 3. It then performs a DFS on *each* component to find the best possible *second* cut within it.
     */
    static class OptimizedNxNSolution {
        public int minimumScore(int[] nums, int[][] edges) {
            int n = nums.length;
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                adj.get(edge[0]).add(edge[1]);
                adj.get(edge[1]).add(edge[0]);
            }

            int[] minScore = { Integer.MAX_VALUE };

            // Step 1: Iterate through every possible first edge to remove. (O(n) iterations)
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                // Step 2: Calculate the XOR sum of the two components created by removing (u, v). (O(n) work)
                int xor_u_comp = calculateSubtreeXor(u, v, nums, adj);
                int xor_v_comp = calculateSubtreeXor(v, u, nums, adj);

                // Step 3: Find the best second cut within each of the two components. (O(n) work)
                findSecondCut(minScore, u, v, xor_u_comp, xor_v_comp, nums, adj);
                findSecondCut(minScore, v, u, xor_v_comp, xor_u_comp, nums, adj);
            }

            return minScore[0];
        }

        private int findSecondCut(int[] minScore, int u, int p, int xor_this_comp, int xor_other_comp, int[] nums, List<List<Integer>> adj) {
            int currentSubtreeXor = nums[u];
            for (int v : adj.get(u)) {
                if (v != p) {
                    int childSubtreeXor = findSecondCut(minScore, v, u, xor_this_comp, xor_other_comp, nums, adj);

                    // Consider removing edge (u, v) as the second cut.
                    // The three final components are:
                    // 1. The subtree at v: childSubtreeXor
                    // 2. The other part of this component: xor_this_comp ^ childSubtreeXor
                    // 3. The other main component: xor_other_comp
                    updateScore(minScore, childSubtreeXor, xor_this_comp ^ childSubtreeXor, xor_other_comp);
                    currentSubtreeXor ^= childSubtreeXor;
                }
            }
            return currentSubtreeXor;
        }

        private int calculateSubtreeXor(int u, int p, int[] nums, List<List<Integer>> adj) {
            int xor = nums[u];
            for (int v : adj.get(u)) {
                if (v != p) {
                    xor ^= calculateSubtreeXor(v, u, nums, adj);
                }
            }
            return xor;
        }

        private void updateScore(int[] minScore, int c1, int c2, int c3) {
            int max = Math.max(c1, Math.max(c2, c3));
            int min = Math.min(c1, Math.min(c2, c3));
            minScore[0] = Math.min(minScore[0], max - min);
        }
    }
}

