package Questions_june_2025;

import java.util.*;

public class _1061 {
    static class Solution {
        public String smallestEquivalentString(String s1, String s2, String baseStr) {
            Map<Character, List<Character>> adj = new HashMap<>();
            int n = s1.length();

            for (int i = 0; i < n; i++) {
                char u = s1.charAt(i);
                char v = s2.charAt(i);

                adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
                adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
            }

            StringBuilder result = new StringBuilder();

            for (char ch : baseStr.toCharArray()) {
                boolean[] visited = new boolean[26];
                char minChar = dfs(adj, ch, visited);
                result.append(minChar);
            }

            return result.toString();
        }

        private char dfs(Map<Character, List<Character>> adj, char ch, boolean[] visited) {
            visited[ch - 'a'] = true;
            char minChar = ch;

            for (char neighbor : adj.getOrDefault(ch, new ArrayList<>())) {
                if (!visited[neighbor - 'a']) {
                    char candidate = dfs(adj, neighbor, visited);
                    if (candidate < minChar) {
                        minChar = candidate;
                    }
                }
            }

            return minChar;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Example 1
        String s1 = "parker";
        String s2 = "morris";
        String baseStr = "parser";
        String result1 = solution.smallestEquivalentString(s1, s2, baseStr);
        System.out.println("Output for Example 1: " + result1); // Expected "makkek"

        // Example 2
        String s3 = "hello";
        String s4 = "world";
        String baseStr2 = "hold";
        String result2 = solution.smallestEquivalentString(s3, s4, baseStr2);
        System.out.println("Output for Example 2: " + result2); // Expected "hdld"
    }
}
