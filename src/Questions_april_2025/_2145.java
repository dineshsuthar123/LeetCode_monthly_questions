package Questions_april_2025;

public class _2145 {
    public static void main(String[] args) {
        int[] differences = {1, -2, 3};
        int lower = 1;
        int upper = 6;

        NumberOfArraysSolution solution = new NumberOfArraysSolution();
        System.out.println(solution.numberOfArrays(differences, lower, upper));
    }
}

class NumberOfArraysSolution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long sum = 0, maxi = 0, mini = 0;
        for (int x : differences) {
            sum += x;
            maxi = Math.max(maxi, sum);
            mini = Math.min(mini, sum);
        }
        return (int) Math.max(0, upper - lower - maxi + mini + 1);
    }
}