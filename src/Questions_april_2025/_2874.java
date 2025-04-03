package Questions_april_2025;

public class _2874 {
    public static void main(String[] args) {
        int[] nums = {9, 1, 8, 2, 7, 3};

        MaximumTripletValueSolution solution = new MaximumTripletValueSolution();
        System.out.println("Maximum Triplet Value: " + solution.maximumTripletValue(nums));
    }
}

class MaximumTripletValueSolution {
    public long maximumTripletValue(int[] nums) {
        long max = 0, diff = 0, ele = 0;
        for (int num : nums) {
            max = Math.max(max, diff * num);
            diff = Math.max(diff, ele - num);
            ele = Math.max(ele, num);
        }
        return max;
    }
}
