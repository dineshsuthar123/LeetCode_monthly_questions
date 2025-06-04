package Questions_june_2025;

public class _3403 {
    public static void main(String[] args) {
        String word = "leetcode";
        int numFriends = 3;
        AnswerStringSolution solution = new AnswerStringSolution();
        System.out.println(solution.answerString(word, numFriends));
    }
}

class AnswerStringSolution {
    public String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        int n = word.length();
        int m = n - numFriends + 1;
        String res = " ",curr ;
        for (int i = 0; i < n; ++i) {
            curr = word.substring(i, Math.min(i + m, n));
            if (res.compareTo(curr) < 0) {
                res = curr;
            }
        }
        return res;
    }
}
