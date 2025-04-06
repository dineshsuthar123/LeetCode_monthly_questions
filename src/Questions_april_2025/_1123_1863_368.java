package Questions_april_2025;

import java.util.*;

public class _1123_1863_368 {
    public static void main(String[] args) {
        int[] numsForXor = {1, 2, 3};
        SubsetXORSumSolution xorSolution = new SubsetXORSumSolution();
        System.out.println("Subset XOR Sum: " + xorSolution.subsetXORSum(numsForXor));

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        LcaDeepestLeavesSolution lcaSolution = new LcaDeepestLeavesSolution();
        TreeNode lca = lcaSolution.lcaDeepestLeaves(root);
        System.out.println("LCA of deepest leaves: " + (lca != null ? lca.val : "null"));

        int[] numsForSubset = {1, 2, 3, 8, 4, 16};
        LargestDivisibleSubsetSolution subsetSolution = new LargestDivisibleSubsetSolution();
        List<Integer> largestSubset = subsetSolution.largestDivisibleSubset(numsForSubset);
        System.out.println("Largest Divisible Subset: " + largestSubset);
    }
}

class SubsetXORSumSolution {
    public int subsetXORSum(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum |= num;
        }
        return sum * (1 << (nums.length - 1));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

class LcaDeepestLeavesSolution {
    static class Tree {
        int d;
        TreeNode tree;
        Tree(int d, TreeNode tree) {
            this.d = d;
            this.tree = tree;
        }
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return helper(root).tree;
    }

    private Tree helper(TreeNode root) {
        if (root == null) return new Tree(0, null);
        Tree left = helper(root.left);
        Tree right = helper(root.right);
        if (left.d == right.d) {
            return new Tree(left.d + 1, root);
        } else if (left.d > right.d) {
            return new Tree(left.d + 1, left.tree);
        } else {
            return new Tree(right.d + 1, right.tree);
        }
    }
}

class LargestDivisibleSubsetSolution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        List<Integer> answer = new ArrayList<>();
        if (n == 0) return answer;
        int[] dp = new int[n];
        int[] previous = new int[n];
        Arrays.sort(nums);
        Arrays.fill(dp, 1);
        Arrays.fill(previous, -1);
        int max = 0;
        for (int i = 1; i < n; i++){
            for (int j = 0; j < i; j++){
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1){
                    dp[i] = dp[j] + 1;
                    previous[i] = j;
                }
            }
            if (dp[i] > dp[max]) max = i;
        }
        for (int i = max; i >= 0; i = previous[i]){
            answer.add(nums[i]);
            if (previous[i] == -1) break;
        }
        Collections.reverse(answer);
        return answer;
    }
}
