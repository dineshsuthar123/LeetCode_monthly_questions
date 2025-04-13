

public class _2843 {
    public static void main(String[] args) {
        int low = 1000;
        int high = 9999;
        SymmetricIntegersSolution solution = new SymmetricIntegersSolution();
        System.out.println(solution.countSymmetricIntegers(low, high));
    }
}

class SymmetricIntegersSolution {
    public int countSymmetricIntegers(int low, int high) {
        int count = 0;
        for (int x = low; x <= high; x++) {
            String s = String.valueOf(x);
            int len = s.length();
            if (len % 2 != 0) continue;
            int half = len / 2, sum1 = 0, sum2 = 0;
            for (int i = 0; i < half; i++) sum1 += s.charAt(i) - '0';
            for (int i = half; i < len; i++) sum2 += s.charAt(i) - '0';
            if (sum1 == sum2) count++;
        }
        return count;
    }
}
