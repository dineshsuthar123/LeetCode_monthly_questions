package Questions_april_2025;

public class _2179 {
    public static void main(String[] args) {
        int[] nums1 = {1, 0, 2};
        int[] nums2 = {2, 1, 0};
        Solution_2179 solution = new Solution_2179();
        long result = solution.goodTriplets(nums1, nums2);
        System.out.println("Good triplets count: " + result);
    }
}

class Solution_2179 {
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] posInNums2 = new int[n];
        for (int i = 0; i < n; i++) {
            posInNums2[nums2[i]] = i;
        }
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = posInNums2[nums1[i]];
        }
        long[] leftCount = new long[n];
        BIT bitLeft = new BIT(n);
        for (int i = 0; i < n; i++) {
            leftCount[i] = bitLeft.query(A[i] + 1);
            bitLeft.update(A[i] + 1, 1);
        }
        long result = 0;
        BIT bitRight = new BIT(n);
        for (int i = n - 1; i >= 0; i--) {
            long rightCount = bitRight.query(n) - bitRight.query(A[i] + 1);
            result += leftCount[i] * rightCount;
            bitRight.update(A[i] + 1, 1);
        }
        return result;
    }

    static class BIT {
        int n;
        long[] tree;

        BIT(int n) {
            this.n = n;
            tree = new long[n + 1];
        }

        public void update(int index, int delta) {
            while (index <= n) {
                tree[index] += delta;
                index += index & (-index);
            }
        }

        public long query(int index) {
            long sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & (-index);
            }
            return sum;
        }
    }
}
