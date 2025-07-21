package Questions_july_2025;

public class _1957 {

    static class Solution {
        public String makeFancyString(String s) {
            if (s.length() < 3) {
                return s;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(s.charAt(0));
            sb.append(s.charAt(1));
            for (int i = 2; i < s.length(); i++) {
                if (!(s.charAt(i) == s.charAt(i - 1) && s.charAt(i) == s.charAt(i - 2))) {
                    sb.append(s.charAt(i));
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1
        String s1 = "leeetcode";
        System.out.println("Input:  " + s1);
        System.out.println("Output: " + solution.makeFancyString(s1)); // Expected: "leetcod"
        System.out.println();

        // Test Case 2
        String s2 = "aaabaaaa";
        System.out.println("Input:  " + s2);
        System.out.println("Output: " + solution.makeFancyString(s2)); // Expected: "aabaa"
        System.out.println();

        // Test Case 3
        String s3 = "aab";
        System.out.println("Input:  " + s3);
        System.out.println("Output: " + solution.makeFancyString(s3)); // Expected: "aab"
        System.out.println();
    }
}
