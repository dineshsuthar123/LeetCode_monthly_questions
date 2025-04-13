package Questions_april_2025;

import java.util.*;

public class _3272_1922 {
    public static void main(String[] args) {
        PalindromeGoodIntegersSolution palindromeSolution = new PalindromeGoodIntegersSolution();
        int n = 4;
        int k = 7;
        long result1 = palindromeSolution.countGoodIntegers(n, k);
        System.out.println("Palindrome Good Integers: " + result1);

        CountGoodNumbersSolution countSolution = new CountGoodNumbersSolution();
        long n2 = 50;
        int result2 = countSolution.countGoodNumbers(n2);
        System.out.println("Count Good Numbers: " + result2);
    }
}

class PalindromeGoodIntegersSolution {
    public long factorial(int num) {
        long result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    public void generatePalindromes(char[] currentNumber, int index, List<String> validPalindromes, int k) {
        if (index >= (currentNumber.length + 1) / 2) {
            String palindrome = new String(currentNumber);
            if (Long.parseLong(palindrome) % k == 0) {
                validPalindromes.add(palindrome);
            }
            return;
        }
        if (index != 0) {
            currentNumber[index] = '0';
            currentNumber[currentNumber.length - 1 - index] = '0';
            generatePalindromes(currentNumber, index + 1, validPalindromes, k);
        }
        for (char c = '1'; c <= '9'; c++) {
            currentNumber[index] = c;
            currentNumber[currentNumber.length - 1 - index] = c;
            generatePalindromes(currentNumber, index + 1, validPalindromes, k);
        }
    }

    public long countGoodIntegers(int n, int k) {
        List<String> validPalindromes = new ArrayList<>();
        char[] basePalindrome = new char[n];
        Arrays.fill(basePalindrome, '0');
        generatePalindromes(basePalindrome, 0, validPalindromes, k);
        Set<String> uniquePatterns = new HashSet<>();
        for (String palindrome : validPalindromes) {
            int[] freq = new int[10];
            for (char ch : palindrome.toCharArray()) {
                freq[ch - '0']++;
            }
            StringBuilder pattern = new StringBuilder();
            for (int f : freq) {
                pattern.append((char) ('a' + f));
            }
            uniquePatterns.add(pattern.toString());
        }
        long totalPermutations = factorial(n);
        long totalValidPermutations = 0;
        for (String pattern : uniquePatterns) {
            long permutations = totalPermutations;
            for (char ch : pattern.toCharArray()) {
                permutations /= factorial(ch - 'a');
            }
            if (pattern.charAt(0) != 'a') {
                int zeroCount = pattern.charAt(0) - 'a' - 1;
                long invalidPerms = factorial(n - 1);
                for (int j = 1; j < pattern.length(); j++) {
                    invalidPerms /= factorial(pattern.charAt(j) - 'a');
                }
                invalidPerms /= factorial(zeroCount);
                permutations -= invalidPerms;
            }
            totalValidPermutations += permutations;
        }
        return totalValidPermutations;
    }
}

class CountGoodNumbersSolution {
    static final int MOD = 1000000007;
    public int countGoodNumbers(long n) {
        long even = (n + 1) / 2;
        long odd = (n / 2);
        long evenside = cal(5, even);
        long oddside = cal(4, odd);
        return (int) ((evenside * oddside) % MOD);
    }

    private long cal(long base, long power) {
        long res = 1;
        base %= MOD;
        while (power > 0) {
            if (power % 2 == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            power /= 2;
        }
        return res;
    }
}
