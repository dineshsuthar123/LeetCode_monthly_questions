package Questions_april_2025;

public class _38 {
    public static void main(String[] args) {
        int n = 5;
        CountAndSaySolution solution = new CountAndSaySolution();
        System.out.println("Count and Say sequence at n = " + n + ": " + solution.countAndSay(n));
    }
}

class CountAndSaySolution {
    public String countAndSay(int n) {
        String res = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder temp = new StringBuilder();
            int count = 1;
            for (int j = 1; j < res.length(); j++) {
                if (res.charAt(j) == res.charAt(j - 1)) {
                    count++;
                } else {
                    temp.append(count).append(res.charAt(j - 1));
                    count = 1;
                }
            }
            temp.append(count).append(res.charAt(res.length() - 1));
            res = temp.toString();
        }
        return res;
    }
}
