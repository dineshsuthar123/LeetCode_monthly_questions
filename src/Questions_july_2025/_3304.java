package Questions_july_2025;

import java.util.Scanner;

public class _3304 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        sc.close();

        Solution sol = new Solution();
        System.out.println(sol.kthCharacter(k));
    }
}

class Solution {
    public char kthCharacter(int k) {
        return (char) ('a' + Integer.bitCount(k - 1));
    }
}