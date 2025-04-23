package Questions_april_2025;

public class _1399 {
    public static void main(String[] args) {
        int n = 13;
        CountLargestGroupSolution solution = new CountLargestGroupSolution();
        System.out.println(solution.countLargestGroup(n));
    }
}

class CountLargestGroupSolution {
    public int countLargestGroup(int n) {
        int[] freq = new int[37];
        int max = 1;
        int size = 1;
        freq[1] = 1;
        for (int i = 2; i <= n; i++) {
            int digit_sum = 0;
            for (int j = i; j > 0; j /= 10) {
                digit_sum += j % 10;
            }
            int f = ++freq[digit_sum];
            if (f == max) {
                size++;
            } else if (f > max) {
                max = f;
                size = 1;
            }
        }
        return size;
    }
}
