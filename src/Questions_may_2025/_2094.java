package Questions_may_2025;

import java.util.*;

public class _2094 {
    public static void main(String[] args) {
        int[] digits = {2, 1, 3, 0};
        EvenNumbersSolution solution = new EvenNumbersSolution();
        int[] result = solution.findEvenNumbers(digits);
        System.out.println(Arrays.toString(result));
    }
}

class EvenNumbersSolution {
    public int[] findEvenNumbers(int[] d) {
        int[] c = new int[10];
        for (int x : d) c[x]++;
        List<Integer> r = new ArrayList<>();
        for (int x = 100; x < 1000; x += 2) {
            int a = x / 100, b = (x / 10) % 10, e = x % 10;
            if (c[a] > 0
                    && c[b] - (a == b ? 1 : 0) > 0
                    && c[e] - (a == e ? 1 : 0) - (b == e ? 1 : 0) > 0) {
                r.add(x);
            }
        }
        int[] o = new int[r.size()];
        for (int i = 0; i < o.length; i++) o[i] = r.get(i);
        return o;
    }
}
