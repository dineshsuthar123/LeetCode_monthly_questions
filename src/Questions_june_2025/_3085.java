package Questions_june_2025;

import java.util.*;

public class _3085 {
    public static int minimumDeletions(String word, int k) {
        int[] freq = new int[26];
        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }
        List<Integer> f = new ArrayList<>();
        for (int fq : freq) {
            if (fq > 0) {
                f.add(fq);
            }
        }
        if (f.isEmpty()) return 0;
        int maxf = Collections.max(f);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= maxf; i++) {
            int delete = 0;
            for (int curr : f) {
                if (curr > i + k) {
                    delete += curr - (i + k);
                } else if (curr < i) {
                    delete += curr;
                }
            }
            min = Math.min(min, delete);
        }
        return min;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNext()) return;
        String word = sc.next();
        if (!sc.hasNextInt()) return;
        int k = sc.nextInt();
        System.out.println(minimumDeletions(word, k));
        sc.close();
    }
}
