package Questions_may_2025;

public class _2918 {
    public static void main(String[] args) {
        int[] nums1 = {0, 2, 3, 0};
        int[] nums2 = {1, 0, 4};
        MinSumSolution solution = new MinSumSolution();
        System.out.println(solution.minSum(nums1, nums2));
    }
}

class MinSumSolution {
    public long minSum(int[] nums1, int[] nums2) {
        long zerosCount1 = 0, zerosCount2 = 0;
        long totalSum1 = 0, totalSum2 = 0;

        for (int val : nums1) {
            if (val == 0) zerosCount1++;
            totalSum1 += val;
        }

        for (int val : nums2) {
            if (val == 0) zerosCount2++;
            totalSum2 += val;
        }

        long candidateMin1 = totalSum1 + zerosCount1;
        long candidateMin2 = totalSum2 + zerosCount2;

        if (zerosCount1 == 0 && zerosCount2 == 0) {
            return (totalSum1 == totalSum2) ? totalSum1 : -1;
        } else if (zerosCount1 == 0) {
            return (candidateMin2 <= totalSum1) ? totalSum1 : -1;
        } else if (zerosCount2 == 0) {
            return (candidateMin1 <= totalSum2) ? totalSum2 : -1;
        }
        return Math.max(candidateMin1, candidateMin2);
    }
}
