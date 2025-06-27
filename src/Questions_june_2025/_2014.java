package Questions_june_2025;

import java.util.*;

class Solution_2014 {

    public String longestSubsequenceRepeatedK(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        List<Character> allowed = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            if (freq.getOrDefault(c, 0) >= k) {
                allowed.add(c);
            }
        }

        allowed.sort(Collections.reverseOrder());
        Queue<String> queue = new LinkedList<>();
        queue.offer("");

        String best = "";

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            for (char c : allowed) {
                String next = curr + c;

                if (isKRepeatedSubsequence(s, next, k)) {
                    if (next.length() > best.length() ||
                            (next.length() == best.length() && next.compareTo(best) > 0)) {
                        best = next;
                    }

                    if (next.length() < 7) {
                        queue.offer(next);
                    }
                }
            }
        }

        return best;
    }

    private boolean isKRepeatedSubsequence(String s, String sub, int k) {
        int count = 0, i = 0, j = 0;
        while (i < s.length()) {
            if (s.charAt(i) == sub.charAt(j)) {
                j++;
                if (j == sub.length()) {
                    j = 0;
                    count++;
                    if (count == k) return true;
                }
            }
            i++;
        }
        return false;
    }

    public static void main(String[] args) {
        Solution_2014 sol = new Solution_2014();

        String s1 = "letsleetcode";
        int k1 = 2;
        System.out.println("Test 1 Output: " + sol.longestSubsequenceRepeatedK(s1, k1)); // let

        String s2 = "bb";
        int k2 = 2;
        System.out.println("Test 2 Output: " + sol.longestSubsequenceRepeatedK(s2, k2)); // b

        String s3 = "ab";
        int k3 = 2;
        System.out.println("Test 3 Output: " + sol.longestSubsequenceRepeatedK(s3, k3)); // ""

        String s4 = "ihavndihavnditughavndsihavndiohavndihavmndihavondihabvndkihavndihavndihavndihuavndihavndihjavndihalvndihavemndzihavndihzavnd";
        int k4 = 18;
        System.out.println("Test 4 Output: " + sol.longestSubsequenceRepeatedK(s4, k4)); // test large input
    }
}

