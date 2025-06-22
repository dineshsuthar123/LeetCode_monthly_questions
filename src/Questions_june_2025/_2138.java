package Questions_june_2025;

import java.util.*;

public class _2138 {
    public static String[] divideString(String s, int k, char fill) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < s.length(); i += k) {
            int end = Math.min(i + k, s.length());
            StringBuilder tmp = new StringBuilder(s.substring(i, end));
            while (tmp.length() < k) {
                tmp.append(fill);
            }
            ans.add(tmp.toString());
        }
        return ans.toArray(new String[0]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNext()) return;
        String s = sc.next();
        if (!sc.hasNextInt()) return;
        int k = sc.nextInt();
        if (!sc.hasNext()) return;
        char fill = sc.next().charAt(0);
        String[] parts = divideString(s, k, fill);
        for (String part : parts) {
            System.out.println(part);
        }
        sc.close();
    }
}
