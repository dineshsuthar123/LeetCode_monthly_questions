package Questions_may_2025;

public class _1128 {
    public static void main(String[] args) {
        int[][] dominoes = {{1, 2}, {2, 1}, {3, 4}, {5, 6}, {2, 1}};
        EquivDominoPairsSolution solution = new EquivDominoPairsSolution();
        System.out.println("Number of equivalent domino pairs: " + solution.numEquivDominoPairs(dominoes));
    }
}

class EquivDominoPairsSolution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int[] freq = new int[100];
        int ans = 0;
        for (int[] d : dominoes) {
            int key = d[0] <= d[1] ? d[0] * 10 + d[1] : d[1] * 10 + d[0];
            ans += freq[key];
            freq[key]++;
        }
        return ans;
    }
}
