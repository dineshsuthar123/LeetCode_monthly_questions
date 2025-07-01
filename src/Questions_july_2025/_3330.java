package Questions_july_2025;

public class _3330 {
    static class Solution {
        public int possibleStringCount(String word) {
            int ans = 1;
            int n = word.length();
            int i = 0;

            while (i < n) {
                char c = word.charAt(i);
                int j = i;
                while (j < n && word.charAt(j) == c) {
                    j++;
                }
                int l = j - i;
                ans += l - 1;
                i = j;
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String testWord = "aabbbc";  // Example input: 4
        int result = sol.possibleStringCount(testWord);
        System.out.println("Possible string count: " + result);
    }
}
