package Questions_may_2025;

import java.util.*;

public class _2901 {
    public static void main(String[] args) {
        String[] words = {"abc", "abd", "aac", "bbc", "bbd"};
        int[] groups = {1, 2, 1, 2, 3};
        GetWordsLongestSubsequenceSolution solution = new GetWordsLongestSubsequenceSolution();
        List<String> result = solution.getWordsInLongestSubsequence(words, groups);
        System.out.println(result);
    }
}

class GetWordsLongestSubsequenceSolution {
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        Map<String, List<String>> memo = new HashMap<>();
        int n = words.length;
        List<String> best = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<String> res = new ArrayList<>();
            res.add(words[i]);
            res.addAll(helper(i + 1, groups[i], words[i], words, groups, memo));
            if (res.size() > best.size()) best = res;
        }
        return best;
    }

    private List<String> helper(int i, int lastGroup, String lastWord,
                                String[] words, int[] groups, Map<String, List<String>> memo) {
        if (i >= words.length) return Collections.emptyList();
        String key = i + "|" + lastGroup + "|" + lastWord;
        if (memo.containsKey(key)) return memo.get(key);

        List<String> take = new ArrayList<>();
        if (words[i].length() == lastWord.length()
                && hamming(words[i], lastWord)
                && groups[i] != lastGroup) {
            take.add(words[i]);
            take.addAll(helper(i + 1, groups[i], words[i], words, groups, memo));
        }

        List<String> skip = helper(i + 1, lastGroup, lastWord, words, groups, memo);
        List<String> result = take.size() > skip.size() ? take : skip;
        memo.put(key, result);
        return result;
    }

    private boolean hamming(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
        }
        return diff == 1;
    }
}
