package Questions_may_2025;

public class _3335 {
    public static void main(String[] args) {
        String s = "abc";
        int t = 2;
        LengthAfterTransformationsSolution solution = new LengthAfterTransformationsSolution();
        System.out.println(solution.lengthAfterTransformations(s, t));
    }
}

class LengthAfterTransformationsSolution {
    static final int mod = 1_000_000_007;

    public int lengthAfterTransformations(String s, int t) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        while (t-- > 0) {
            int[] curr = new int[26];
            for (int i = 0; i < 26; i++) {
                if (i == 25 && freq[i] > 0) {
                    curr[0] = mod_add(curr[0], freq[i]);
                    curr[1] = mod_add(curr[1], freq[i]);
                } else if (i < 25) {
                    curr[i + 1] = mod_add(curr[i + 1], freq[i]);
                }
            }
            freq = curr;
        }
        int ans = 0;
        for (int v : freq) {
            ans = mod_add(ans, v);
        }
        return ans;
    }

    private int mod_add(int a, int b) {
        a %= mod; b %= mod;
        return (a + b) % mod;
    }
}
