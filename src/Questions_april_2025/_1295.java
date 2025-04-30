package Questions_april_2025;

public class _1295 {
    public static void main(String[] args) {
        int[] nums = {12, 345, 2, 6, 7896};
        EvenDigitsSolution solution = new EvenDigitsSolution();
        System.out.println(solution.findNumbers(nums));
    }
}

class EvenDigitsSolution {
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            int digits = 0;
            int x = num;
            while (x > 0) {
                x /= 10;
                digits++;
            }
            if (digits % 2 == 0) count++;
        }
        return count;
    }
}
