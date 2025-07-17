package Questions_july_2025;

import java.util.*;

public class _3136 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.next();
        System.out.println(new Solution3136().isValid(word));
        sc.close();
    }

    static class Solution3136 {
        public boolean isValid(String word) {
            if (word.length() < 3) return false;
            word = word.toLowerCase();
            boolean v = false, c = false;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (Character.isLetter(ch) || Character.isDigit(ch)) {
                    if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                        v = true;
                    } else if (Character.isLetter(ch)) {
                        c = true;
                    }
                } else {
                    return false;
                }
            }
            return v && c;
        }
    }
}

