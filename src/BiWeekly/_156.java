package BiWeekly;

import java.util.*;

public class _156 {
    // 1. Maximum frequency sum of one vowel and one consonant
    public int maxFreqSum(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        int maxVowel = 0, maxConsonant = 0;
        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 'a');
            if (vowels.contains(c)) {
                maxVowel = Math.max(maxVowel, freq[i]);
            } else {
                maxConsonant = Math.max(maxConsonant, freq[i]);
            }
        }
        return maxVowel + maxConsonant;
    }

    // 2. Minimum operations to make sequence strictly increasing with stack
    public int minOperations(int[] a) {
        int ops = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int v : a) {
            while (stack.peek() > v) {
                stack.pop();
            }
            if (stack.peek() < v && v > 0) {
                ops++;
                stack.push(v);
            }
        }
        return ops;
    }

    // 3. Maximum weighted path of exactly k edges with pruning
    private int[][] dp;
    private List<int[]>[] graph;
    private int K, T, answer;

    public int maxWeight(int n, int[][] edges, int k, int t) {
        this.K = k;
        this.T = t;
        this.answer = -1;
        graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(new int[]{ e[1], e[2] });
        }
        int NEG = Integer.MIN_VALUE / 2;
        dp = new int[K + 1][n];
        for (int i = 0; i <= K; i++) {
            Arrays.fill(dp[i], NEG);
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 0;
        }
        for (int len = 1; len <= K; len++) {
            for (int u = 0; u < n; u++) {
                for (int[] e : graph[u]) {
                    int v = e[0], w = e[1];
                    if (dp[len - 1][v] > NEG) {
                        dp[len][u] = Math.max(dp[len][u], w + dp[len - 1][v]);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            dfs(i, 0, 0);
        }
        return answer;
    }

    private void dfs(int u, int depth, int sum) {
        if (depth == K) {
            answer = Math.max(answer, sum);
            return;
        }
        int maxRem = dp[K - depth][u];
        if (sum + maxRem <= answer) {
            return;
        }
        for (int[] e : graph[u]) {
            int v = e[0], w = e[1];
            int nextSum = sum + w;
            if (nextSum < T) {
                dfs(v, depth + 1, nextSum);
            }
        }
    }

    public static void main(String[] args) {
        _156 sol = new _156();

        String s = "examplestring";
        System.out.println("maxFreqSum: " + sol.maxFreqSum(s));

        int[] arr = {1, 3, 2, 5, 4};
        System.out.println("minOperations: " + sol.minOperations(arr));

        int n = 5;
        int[][] edges = {{0,1,4}, {1,2,3}, {2,3,2}, {3,4,1}};
        int k = 3;
        int t = 10;
        System.out.println("maxWeight: " + sol.maxWeight(n, edges, k, t));
    }
}
